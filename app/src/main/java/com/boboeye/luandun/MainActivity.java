package com.boboeye.luandun;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.boboeye.luandun.activitys.HomeActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity implements Animation.AnimationListener {
    @InjectView(R.id.start_image)
    public ImageView start_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(R.layout.activity_main);
        startHomeActivity();
    }

    private void initWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    private void startHomeActivity() {
        ButterKnife.inject(this);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.start_alpha);
        anim.setAnimationListener(this);
        start_image.startAnimation(anim);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
