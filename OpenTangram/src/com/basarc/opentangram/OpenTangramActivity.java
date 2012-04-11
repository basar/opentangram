package com.basarc.opentangram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.basarc.opentangram.game.Parallelogram;

public class OpenTangramActivity extends Activity {
	/** Called when the activity is first created. */

	Parallelogram square;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		Intent intent = new Intent(this, PlayFieldActivity.class);
		startActivity(intent);
	}

}
