package com.applifarm.activitytransitionanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class BlankActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_blank);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		finish();
		Intent intent = getIntent();
		int rowIndex = intent.getIntExtra("row", 0);
		switch (rowIndex) {
		case 0:
			overridePendingTransition(R.animator.slide_in_left,
					R.animator.slide_out_right);
			break;
		case 1:
			overridePendingTransition(R.animator.slide_in_up,
					R.animator.slide_out_down);
			break;
		default:
			break;
		}

	}

}
