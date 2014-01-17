package duanqing.test;

import duanqing.test.ITabHostMenuHandler.SUB_NAME;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class ActivityTwo extends Activity {
	private ITabHostMenuHandler tabHostMenuHandler;
	private ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.view2);
		img = (ImageView) findViewById(R.id.picture);
		img.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				tabHostMenuHandler.applyRotation(1, 0, 90);
			}
		});
		tabHostMenuHandler = (ITabHostMenuHandler) getIntent().getExtras()
				.getSerializable(SUB_NAME.TAG_TWO);
	}

}
