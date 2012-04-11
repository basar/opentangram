package com.basarc.opentangram;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.basarc.opentangram.game.PuzzleBoard;
import com.basarc.opentangram.game.Shape;
import com.basarc.opentangram.game.SmallTriangle;

public class PlayFieldActivity extends Activity {

	SmallTriangle square;

	PuzzleBoard puzzleBoard;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		puzzleBoard = PuzzleBoard.getInstance();
		puzzleBoard.initialize(480, 800);

		setContentView(new DrawView(this));
	}

	class DrawView extends SurfaceView implements SurfaceHolder.Callback {

		private DrawingThread thread;

		public DrawView(Context context) {
			super(context);
			getHolder().addCallback(this);
			this.thread = new DrawingThread(getHolder(), this);
		}

		@Override
		protected void onDraw(Canvas c) {

			Paint p = new Paint();
			p.setColor(Color.BLUE);

			drawShapes(c, p);

		}

		private void drawShapes(Canvas c, Paint p) {

			for (Shape shape : puzzleBoard.getShapes()) {
				int size = shape.getVertices().size();
				for (int i = 0; i < size; i++) {
					c.drawLine(shape.getVertex(i).x, shape.getVertex(i).y,
							shape.getVertex((i + 1) % size).x,
							shape.getVertex((i + 1) % size).y, p);
				}
			}
		}

		@Override
		public void surfaceChanged(SurfaceHolder holder, int format, int width,
				int height) {

		}

		@Override
		public void surfaceCreated(SurfaceHolder holder) {
			thread.setRunning(true);
			thread.start();
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

	}

	class DrawingThread extends Thread {

		private SurfaceHolder surfaceHolder;
		private DrawView drawView;
		private boolean run = false;

		public DrawingThread(SurfaceHolder surfaceHolder, DrawView drawView) {
			this.surfaceHolder = surfaceHolder;
			this.drawView = drawView;
		}

		public void setRunning(boolean run) {
			this.run = run;
		}

		@Override
		public void run() {
			Canvas c;
			while (run) {
				c = null;
				try {
					c = surfaceHolder.lockCanvas(null);
					synchronized (surfaceHolder) {
						if (c != null)
							drawView.onDraw(c);
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
