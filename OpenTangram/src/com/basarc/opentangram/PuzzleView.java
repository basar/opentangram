package com.basarc.opentangram;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.basarc.opentangram.game.Position;
import com.basarc.opentangram.game.PuzzleBoard;
import com.basarc.opentangram.game.Shape;

public class PuzzleView extends SurfaceView implements SurfaceHolder.Callback {

	private PuzzleThread thread;

	public PuzzleView(Context context) {
		super(context);

		SurfaceHolder holder = getHolder();
		holder.addCallback(this);

		thread = new PuzzleThread(holder, context);

	}

	public PuzzleThread getThread() {
		return thread;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread.setRunning(true);
		try {
			thread.start();
		} catch (IllegalThreadStateException ex) {
			Log.w(Globals.TAG, ex.getMessage());
		}
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		thread.setRunning(false);
		while (retry) {
			try {
				thread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		final int action = event.getAction();

		switch (action) {
		case MotionEvent.ACTION_DOWN:
			thread.onActionDown(event);
			break;
		case MotionEvent.ACTION_MOVE:
			thread.onActionMove(event);
			break;
		case MotionEvent.ACTION_UP:
			thread.onActionUp(event);
		default:
			break;
		}

		return true;
	}

	class PuzzleThread extends Thread {

		/**
		 * Background image for puzzle view
		 */
		private Bitmap backgroundImage;

		private PuzzleBoard puzzleBoard;

		private int canvasHeight;

		private int canvasWidth;

		private SurfaceHolder surfaceHolder;

		private boolean run = false;

		float lastDownX = 0;

		float lastDownY = 0;

		float lastMoveX = 0;

		float lastMoveY = 0;

		boolean rotatable = false;

		boolean movable = false;

		boolean isMoving = false;

		int minRadius = 0;

		int maxRadius = 0;

		int rotationAngle = 0;

		boolean firstAngleControl = false;

		// 1 specifies left rotation -1 specifies right rotation
		int direction = 0;

		public PuzzleThread(SurfaceHolder surfaceHolder, Context cntx) {

			this.surfaceHolder = surfaceHolder;

			Resources resources = cntx.getResources();

			backgroundImage = BitmapFactory.decodeResource(resources,
					R.drawable.background);

			canvasWidth = Globals.getScreenWidth(cntx);
			canvasHeight = Globals.getScreenHeight(cntx);

			puzzleBoard = PuzzleBoard.getInstance();
			puzzleBoard.initialize(canvasWidth, canvasHeight);

			minRadius = puzzleBoard.getScale() * 2;
			maxRadius = minRadius + 40;

		}

		private void doDraw(Canvas canvas) {

			canvas.drawBitmap(backgroundImage, 0, 0, null);
			drawShapes(canvas);
			drawRotationCircle(canvas);

		}

		private void drawShapes(Canvas c) {

			Paint p = new Paint();
			p.setAntiAlias(true);

			int size = puzzleBoard.getShapes().size();

			for (int i = 0; i < size; i++) {

				Shape shape = puzzleBoard.getShape(i);
				Path path = createPath(shape);

				if (shape.equals(puzzleBoard.getSelectedShape())) {
					p.setColor(Color.BLUE);
				} else {
					p.setColor(Color.GREEN);
				}

				p.setStyle(Paint.Style.FILL);
				c.drawPath(path, p);
				p.setStyle(Paint.Style.STROKE);
				p.setColor(Color.BLACK);
				p.setStrokeWidth(2);
				c.drawPath(path, p);

			}
		}

		private void drawRotationCircle(Canvas c) {

			// To prevent to draw circle during the moving
			if (isMoving)
				return;

			Paint p = new Paint();
			p.setAntiAlias(true);
			p.setColor(Color.BLACK);
			p.setStyle(Paint.Style.STROKE);

			Shape selected = puzzleBoard.getSelectedShape();

			if (selected != null) {
				c.drawCircle(selected.getCenter().x, selected.getCenter().y,
						minRadius, p);
				c.drawCircle(selected.getCenter().x, selected.getCenter().y,
						maxRadius, p);
			}
		}

		private Path createPath(Shape shape) {

			Path p = new Path();
			int size = shape.getVertices().size();
			for (int i = 0; i < size + 1; i++) {
				if (i == 0) {
					p.moveTo(shape.getVertex(i).x, shape.getVertex(i).y);
					continue;
				}
				p.lineTo(shape.getVertex(i % size).x,
						shape.getVertex(i % size).y);
			}

			p.close();
			return p;
		}

		public void onActionDown(MotionEvent event) {

			lastDownX = event.getX();
			lastDownY = event.getY();

			lastMoveX = lastDownX;
			lastMoveY = lastDownY;

			Shape selected = puzzleBoard.getSelectedShape();

			if (selected != null) {
				if (isRotationArea(selected)) {
					movable = false;
					rotatable = true;
					firstAngleControl = true;
					return;
				}
			}

			Shape shape = puzzleBoard.getShape(lastDownX, lastDownY);

			if (shape != null) {
				puzzleBoard.setSelectedShape(shape);
				movable = true;
				rotatable = false;
				changeDrawingPriority();
				return;
			}

			// reset states
			rotatable = false;
			movable = false;
			firstAngleControl = false;
			puzzleBoard.setSelectedShape(null);
		}

		public void onActionMove(MotionEvent event) {

			float x = event.getX();
			float y = event.getY();

			if (movable) {
				dragShape(x, y);
			}

			if (rotatable) {
				rotateShape(x, y);
			}

			lastMoveX = x;
			lastMoveY = y;

		}

		public void onActionUp(MotionEvent event) {

			if (rotatable) {
				puzzleBoard.roundedRotateSelectedShape(direction);
			}

			isMoving = false;
			direction = 0;
			rotationAngle = 0;
		}

		private void dragShape(float x, float y) {

			puzzleBoard.dragSelectedShapeTo(x - lastMoveX, y - lastMoveY);
			isMoving = true;

		}

		private void rotateShape(float x, float y) {

			Shape selected = puzzleBoard.getSelectedShape();
			if (selected == null)
				return;

			Position center = selected.getCenter();

			Position p1 = new Position(lastDownX - center.x, lastDownY
					- center.y);
			p1.normalize();

			Position p2 = new Position(x - center.x, y - center.y);
			p2.normalize();

			int lastRotatedAngle = rotationAngle;
			rotationAngle = (int) p1.calculateAngle(p2);

			puzzleBoard.rotateSelectedShape(rotationAngle - lastRotatedAngle);

			if (firstAngleControl) {
				firstAngleControl = false;
				if (rotationAngle > 180) {
					direction = -1;
				} else {
					direction = 1;
				}
			}

		}

		private void changeDrawingPriority() {

			synchronized (surfaceHolder) {

				// selected shape index
				int index = 0;
				// size
				int size = puzzleBoard.getShapes().size();
				// selected shape
				Shape selected = puzzleBoard.getSelectedShape();
				if (selected == null || selected.getTag() == size - 1)
					return;

				index = selected.getTag();

				for (int i = index + 1; i < size; i++) {
					Shape sh = puzzleBoard.getShape(i);
					sh.setTag(sh.getTag() - 1);
				}

				selected.setTag(size - 1);

			}

		}

		private boolean isRotationArea(Shape s) {

			boolean result = false;

			Position p = new Position(s.getCenter().x - lastDownX,
					s.getCenter().y - lastDownY);
			float magnitude = p.magnitude();
			if (magnitude >= minRadius && magnitude <= maxRadius) {
				result = true;
			}

			return result;
		}

		public void setRunning(boolean run) {
			this.run = run;
		}

		@Override
		public void run() {
			while (run) {
				Canvas c = null;
				try {
					c = surfaceHolder.lockCanvas(null);
					synchronized (surfaceHolder) {
						if (c != null)
							doDraw(c);
					}
				} finally {
					// do this in a finally so that if an exception is thrown
					// during the above, we don't leave the Surface in an
					// inconsistent state
					if (c != null) {
						surfaceHolder.unlockCanvasAndPost(c);
					}
				}
			}
		}

	}

}
