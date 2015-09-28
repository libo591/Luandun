package com.boboeye.luandun;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.boboeye.luandun.activitys.HomeActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends Activity implements Animation.AnimationListener {
    private static final String TAG = MainActivity.class.getSimpleName();
    @InjectView(R.id.frameLayout)
    public FrameLayout frameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initWindow();
        setContentView(R.layout.activity_main);
        startHomeActivity();
        ///data--/system--/storage/emulated/0---/cache
//        Log.d(TAG, Environment.getDataDirectory().getAbsolutePath() + "--" + Environment.getRootDirectory().getAbsolutePath()
//                + "--" + Environment.getExternalStorageDirectory().getAbsolutePath() + "---" + Environment.getDownloadCacheDirectory().getAbsolutePath());
    }

    private void initWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    private void startHomeActivity() {
        ButterKnife.inject(this);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.start_alpha);
        anim.setAnimationListener(this);
        frameLayout.startAnimation(anim);
    }


    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
