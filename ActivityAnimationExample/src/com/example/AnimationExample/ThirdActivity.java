package com.example.AnimationExample;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LayoutAnimationController;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created with IntelliJ IDEA.
 * User: ra1ph
 * Date: 02.10.13
 * Time: 16:14
 * To change this template use File | Settings | File Templates.
 */
public class ThirdActivity extends Activity implements Animator.AnimatorListener {
    Animator bottomRightAnim, topRightAnim, topLeftAnim, bottomLeftAnim
            ,bottomStartRightAnim, topStartRightAnim, topStartLeftAnim, bottomStartLeftAnim;
    ImageView topLeft,topRight,bottomLeft,bottomRight;
    LinearLayout main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    //To change body of overridden methods use File | Settings | File Templates.
        setContentView(R.layout.third);

        main = (LinearLayout) findViewById(R.id.main_layout);
        topLeft = (ImageView) findViewById(R.id.left_top_image);
        topRight = (ImageView) findViewById(R.id.right_top_image);
        bottomLeft = (ImageView) findViewById(R.id.left_bottom_image);
        bottomRight = (ImageView) findViewById(R.id.right_bottom_image);

        ViewTreeObserver observer = main.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                main.getViewTreeObserver().removeOnPreDrawListener(this);
                Bitmap bmp = getBitmap();

                int centerVertical = bmp.getHeight()/2;
                int centerHorizontal = bmp.getWidth()/2;

                Bitmap bmpLeftTop = Bitmap.createBitmap(bmp,0,0,centerHorizontal,centerVertical);
                Bitmap bmpLeftBottom = Bitmap.createBitmap(bmp,0,centerVertical,centerHorizontal,bmp. getHeight() - centerVertical);
                Bitmap bmpRightTop = Bitmap.createBitmap(bmp,centerHorizontal,0,bmp.getWidth() - centerHorizontal,centerVertical);
                Bitmap bmpRightBottom = Bitmap.createBitmap(bmp,centerHorizontal,centerVertical,bmp.getWidth() - centerHorizontal,bmp. getHeight() - centerVertical);

                topLeft.setImageBitmap(bmpLeftTop);
                topRight.setImageBitmap(bmpRightTop);
                bottomLeft.setImageBitmap(bmpLeftBottom);
                bottomRight.setImageBitmap(bmpRightBottom);

                main.setVisibility(View.GONE);
                startEnterAnimation();
                return true;
            }
        });
    }

    private Bitmap getBitmap(){
        View root = getWindow().getDecorView().findViewById(android.R.id.content);
        root.setDrawingCacheEnabled(true);
        return root.getDrawingCache();
    }

    private void startEnterAnimation(){
        bottomStartRightAnim = ObjectAnimator.ofFloat(bottomRight, "rotationX", -180,0);
        bottomStartRightAnim.addListener(this);
        bottomRight.setPivotX(bottomRight.getHeight()/2);
        bottomRight.setPivotY(0);
        topStartRightAnim = ObjectAnimator.ofFloat(topRight,"rotationY",180,0);
        topStartRightAnim.addListener(this);
        topRight.setPivotX(0);
        topRight.setPivotY(topRight.getWidth()/2);
        topStartLeftAnim = ObjectAnimator.ofFloat(topLeft,"rotationX",180,0);
        topStartLeftAnim.addListener(this);
        topLeft.setPivotX(topLeft.getWidth()/2);
        topLeft.setPivotY(topLeft.getHeight());
        bottomStartLeftAnim = ObjectAnimator.ofFloat(bottomLeft,"rotationX",180,0);
        bottomStartLeftAnim.addListener(this);
        bottomLeft.setPivotX(bottomLeft.getWidth()/2);
        bottomLeft.setPivotY(bottomLeft.getHeight());

        bottomLeft.setVisibility(View.VISIBLE);

        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.playSequentially(bottomStartLeftAnim, topStartLeftAnim, topStartRightAnim, bottomStartRightAnim);
        set.start();
    }

    private void startEndAnimation(final Runnable run){
        bottomRightAnim = ObjectAnimator.ofFloat(bottomRight, "rotationX", 0, -180);
        bottomRightAnim.addListener(this);
        bottomRight.setPivotX(bottomRight.getHeight()/2);
        bottomRight.setPivotY(0);
        topRightAnim = ObjectAnimator.ofFloat(topRight,"rotationY",0,180);
        topRightAnim.addListener(this);
        topRight.setPivotX(0);
        topRight.setPivotY(topRight.getWidth()/2);
        topLeftAnim = ObjectAnimator.ofFloat(topLeft,"rotationX",0,180);
        topLeftAnim.addListener(this);
        topLeft.setPivotX(topLeft.getWidth()/2);
        topLeft.setPivotY(topLeft.getHeight());
        bottomLeftAnim = ObjectAnimator.ofFloat(bottomLeft,"rotationX",0,180);
        bottomLeftAnim.addListener(this);
        bottomLeft.setPivotX(bottomLeft.getWidth()/2);
        bottomLeft.setPivotY(bottomLeft.getHeight());

        AnimatorSet set = new AnimatorSet();
        set.setDuration(500);
        set.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                run.run();
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
        set.playSequentially(bottomRightAnim, topRightAnim, topLeftAnim, bottomLeftAnim);
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

    @Override
    public void onAnimationStart(Animator animation) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        if (animation == bottomRightAnim) bottomRight.setVisibility(View.INVISIBLE);
        else if (animation == topRightAnim) topRight.setVisibility(View.INVISIBLE);
        else if (animation == topLeftAnim) topLeft.setVisibility(View.INVISIBLE);
        else if (animation == bottomLeftAnim) bottomLeft.setVisibility(View.INVISIBLE);

        else if (animation == bottomStartRightAnim) topRight.setVisibility(View.VISIBLE);
        else if (animation == topStartRightAnim) bottomRight.setVisibility(View.VISIBLE);
        else if (animation == topStartLeftAnim) topRight.setVisibility(View.VISIBLE);
        else if (animation == bottomStartLeftAnim) topLeft.setVisibility(View.VISIBLE);
    }

    @Override
    public void onAnimationCancel(Animator animation) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}

