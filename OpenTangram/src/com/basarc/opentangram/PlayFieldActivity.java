package com.basarc.opentangram;

import android.app.Activity;
import android.os.Bundle;

import com.basarc.opentangram.PuzzleView.PuzzleThread;

public class PlayFieldActivity extends Activity {

	private PuzzleView puzzleView;

	private PuzzleThread puzzleThread;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

		puzzleView = new PuzzleView(this);
		puzzleThread = puzzleView.getThread();

		setContentView(puzzleView);

	}

}
