package com.huashao.Transition3d;



import com.huashao.Transition3d.ITabHostMenuHandler.TabHostSubClazzSimpleName;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageView;

public class ModuleView2 extends Activity implements View.OnClickListener {
	 private ITabHostMenuHandler tabHostMenuHandler;
	private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view2layout);
        img=(ImageView) findViewById(R.id.picture);
        img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("", "ModuleView1");
				tabHostMenuHandler.applyRotation(1, 0, 90);
			}
		});
        tabHostMenuHandler = (ITabHostMenuHandler) getIntent().getExtras().getSerializable(TabHostSubClazzSimpleName.TAG_MODULE2);
        ImageView i = (ImageView) findViewById(R.id.img);
        i.setOnClickListener(this);
    }
    

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.img:
			Intent in = new Intent(ModuleView2.this,MainActivity.class);
			startActivity(in);
			break;

		default:
			break;
		}
	}


}
