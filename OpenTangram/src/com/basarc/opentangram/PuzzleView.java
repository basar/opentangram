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

import com.basarc.opentangram.game.PuzzleBoard;
import com.basarc.opentangram.game.Shape;

public class PuzzleView extends SurfaceView implements SurfaceHolder.Callback {

	private Context context;

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

		PuzzleBoard puzzleBoard;

		private int canvasHeight;

		private int canvasWidth;

		private SurfaceHolder surfaceHolder;

		private boolean run = false;

		float lastDownX = 0;

		float lastDownY = 0;

		public PuzzleThread(SurfaceHolder surfaceHolder, Context cntx) {

			this.surfaceHolder = surfaceHolder;

			context = cntx;
			Resources resources = cntx.getResources();

			backgroundImage = BitmapFactory.decodeResource(resources,
					R.drawable.background);

			canvasWidth = Globals.getScreenWidth(cntx);
			canvasHeight = Globals.getScreenHeight(cntx);

			puzzleBoard = PuzzleBoard.getInstance();
			puzzleBoard.initialize(canvasWidth, canvasHeight);

		}

		private void doDraw(Canvas canvas) {

			canvas.drawBitmap(backgroundImage, 0, 0, null);
			drawShapes(canvas);

		}

		private void drawShapes(Canvas c) {

			Paint p = new Paint();
			p.setAntiAlias(true);

			for (int i = 0; i < 4; i++) {

				Shape shape = puzzleBoard.getShape(i);

				Path path = createPath(shape);
				p.setColor(Color.GREEN);
				p.setStyle(Paint.Style.FILL);
				c.drawPath(path, p);
				p.setStyle(Paint.Style.STROKE);
				p.setColor(Color.BLACK);
				p.setStrokeWidth(2);
				c.drawPath(path, p);
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

			float x = event.getX();
			float y = event.getY();

			lastDownX = x;
			lastDownY = y;

			puzzleBoard.setSelectedShape(x, y);
			changeDrawingPriority();
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

		public void onActionMove(MotionEvent event) {

			float x = event.getX();
			float y = event.getY();
			puzzleBoard.dragSelectedShapeTo(x - lastDownX, y - lastDownY);
			lastDownX = x;
			lastDownY = y;
		}

		public void onActionUp(MotionEvent event) {
			// puzzleBoard.setSelectedShape(null);
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
