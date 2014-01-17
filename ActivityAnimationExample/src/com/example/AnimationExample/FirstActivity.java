package com.example.AnimationExample;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.io.ByteArrayOutputStream;

public class FirstActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first);

        LinearLayout click = (LinearLayout) findViewById(R.id.click_layout);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bitmap bmp = getBitmap();

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("picture", byteArray);
                startActivity(intent);
                overridePendingTransition(0,0);
            }
        });
    }

    private Bitmap getBitmap(){
        View root = getWindow().getDecorView().findViewById(android.R.id.content);
        root.setDrawingCacheEnabled(true);
        return root.getDrawingCache();
    }
}
