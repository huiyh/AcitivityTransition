package com.example.AnimationExample;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class SecondActivity extends Activity {
    ImageView left,right;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);

        Bundle extras = getIntent().getExtras();
        byte[] byteArray = extras.getByteArray("picture");

        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        left = (ImageView) findViewById(R.id.left_image);
        right = (ImageView) findViewById(R.id.right_image);
        int centerWidth = bmp.getWidth()/2;
        Bitmap bmpLeft,bmpRight;
        bmpLeft = Bitmap.createBitmap(bmp,0,0,centerWidth,bmp.getHeight());
        bmpRight = Bitmap.createBitmap(bmp,centerWidth,0,bmp.getWidth() - centerWidth,bmp.getHeight());

        left.setImageBitmap(bmpLeft);
        right.setImageBitmap(bmpRight);

        ViewTreeObserver observer = left.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                left.getViewTreeObserver().removeOnPreDrawListener(this);
                startEnterAnimation();
                return true;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });

        FrameLayout click = (FrameLayout) findViewById(R.id.click_layout);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SecondActivity.this,ThirdActivity.class);
                startActivity(i);
            }
        });
    }

    private void startEnterAnimation() {
        left.setPivotY(left.getHeight()/2);
        left.setPivotX(0);
        right.setPivotY(left.getHeight()/2);
        right.setPivotX(right.getWidth());
        Animator leftAnim = ObjectAnimator.ofFloat(left, "rotationY", 0, 90);
        Animator rightAnim = ObjectAnimator.ofFloat(right, "rotationY", 0, -90);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.playTogether(leftAnim, rightAnim);
        set.start();
    }

    private void startEndAnimation(final Runnable listener) {
        left.setPivotY(left.getHeight()/2);
        left.setPivotX(0);
        right.setPivotY(left.getHeight()/2);
        right.setPivotX(right.getWidth());
        Animator leftAnim = ObjectAnimator.ofFloat(left, "rotationY", 90, 0);
        Animator rightAnim = ObjectAnimator.ofFloat(right, "rotationY", -90, 0);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.playTogether(leftAnim, rightAnim);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                listener.run();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }
        });
        set.start();
    }

    @Override
    public void onBackPressed() {
        startEndAnimation(new Runnable() {
            @Override
            public void run() {
                finish();
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        // override transitions to skip the standard window animations
        overridePendingTransition(0, 0);
    }
}
