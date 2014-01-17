package com.huashao.Transition3d;

import com.huashao.Transition3d.ITabHostMenuHandler.TabHostSubClazzSimpleName;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class ModuleView1 extends Activity implements View.OnClickListener {
	 private ITabHostMenuHandler tabHostMenuHandler;
	 private ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.view1layout);
        img=(ImageView) findViewById(R.id.picture);
        img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.d("", "ModuleView1");
				tabHostMenuHandler. applyRotation(-1, 180, 90);
			}
		});
        tabHostMenuHandler = (ITabHostMenuHandler) getIntent().getExtras().getSerializable(TabHostSubClazzSimpleName.TAG_MODULE1);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
