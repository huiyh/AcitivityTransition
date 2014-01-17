package com.applifarm.activitytransitionanimation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ListView listView = (ListView) findViewById(R.id.listView);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> av, View view, int rowIndex,
					long id) {
				Intent intent = new Intent(MainActivity.this,
						BlankActivity.class);
				intent.putExtra("row", rowIndex);
				switch (rowIndex) {
				case 0:
					startActivity(intent);
					overridePendingTransition(R.animator.slide_in_right,
							R.animator.slide_out_left);
					break;
				case 1:
					startActivity(intent);
					overridePendingTransition(R.animator.slide_in_down,
							R.animator.slide_out_up);
					break;
				default:
					break;
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
