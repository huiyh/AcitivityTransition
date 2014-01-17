package com.huashao.Transition3d;


import java.io.Serializable;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class NewsTabActivity extends ActivityGroup implements ITabHostMenuHandler {

    private FrameLayout container = null;
	private View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置视图
        setContentView(R.layout.layout);

        container = (FrameLayout) findViewById(R.id.container);
        container.removeAllViews();
        Intent Module1Intent = new Intent(this, ModuleView1.class);
        Module1Intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
		Bundle Bundle = new Bundle();
		Bundle.putSerializable(ITabHostMenuHandler.TabHostSubClazzSimpleName.TAG_MODULE1, this);
		Module1Intent.putExtras(Bundle);
        view=getLocalActivityManager().startActivity("Module1", Module1Intent).getDecorView();
        container.addView(view);
        
        container.setPersistentDrawingCache(ViewGroup.PERSISTENT_ANIMATION_CACHE);
    }
    public void applyRotation(int position, float start, float end) {
        // Find the center of the container
        final float centerX = container.getWidth() / 2.0f;
        final float centerY = container.getHeight() / 2.0f;

        // Create a new 3D rotation with the supplied parameter
        // The animation listener is used to trigger the next animation
        final Rotate3dAnimation rotation =
                new Rotate3dAnimation(start, end, centerX, centerY, 310.0f, true);
        rotation.setDuration(500);
        rotation.setFillAfter(true);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new DisplayNextView(position));

        container.startAnimation(rotation);
    }




    /**
     * This class listens for the end of the first half of the animation.
     * It then posts a new action that effectively swaps the views when the container
     * is rotated 90 degrees and thus invisible.
     */
    private final class DisplayNextView implements Animation.AnimationListener {
        private final int mPosition;

        private DisplayNextView(int position) {
            mPosition = position;
        }

        public void onAnimationStart(Animation animation) {
        }

        public void onAnimationEnd(Animation animation) {
            container.post(new SwapViews(mPosition));
        }

        public void onAnimationRepeat(Animation animation) {
        }
    }

    /**
     * This class is responsible for swapping the views and start the second
     * half of the animation.
     */
    private final class SwapViews implements Runnable {
        private final int mPosition;

        public SwapViews(int position) {
            mPosition = position;
        }

        public void run() {
            final float centerX = container.getWidth() / 2.0f;
            final float centerY = container.getHeight() / 2.0f;
            Rotate3dAnimation rotation;
            
            if (mPosition > -1) {
            	container.removeAllViews();
                Intent Module1Intent = new Intent(NewsTabActivity.this, ModuleView1.class);
                Module1Intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        		Bundle Bundle = new Bundle();
        		Bundle.putSerializable(ITabHostMenuHandler.TabHostSubClazzSimpleName.TAG_MODULE1, NewsTabActivity.this);
        		Module1Intent.putExtras(Bundle);
                view=getLocalActivityManager().startActivity("Module1", Module1Intent).getDecorView();
                container.addView(view);
            	  
                rotation = new Rotate3dAnimation(90, 180, centerX, centerY, 310.0f, false);
            } else {
            	container.removeAllViews();
                Intent Module1Intent = new Intent(NewsTabActivity.this, ModuleView2.class);
                Module1Intent.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
        		Bundle Bundle = new Bundle();
        		Bundle.putSerializable(ITabHostMenuHandler.TabHostSubClazzSimpleName.TAG_MODULE2, NewsTabActivity.this);
        		Module1Intent.putExtras(Bundle);
                view=getLocalActivityManager().startActivity("Module2", Module1Intent).getDecorView();
                container.addView(view);

                rotation = new Rotate3dAnimation(90, 0, centerX, centerY, 310.0f, false);
            }

            rotation.setDuration(500);
            rotation.setFillAfter(true);
            rotation.setInterpolator(new DecelerateInterpolator());

            container.startAnimation(rotation);
        }
    }
}
