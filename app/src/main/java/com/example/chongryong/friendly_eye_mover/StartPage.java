package com.example.chongryong.friendly_eye_mover;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Display;
import android.view.MotionEvent;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;


public class StartPage extends Activity {

    public static int start_flag;
    public int stepX = 10;
    public int width, height;
    public Timer timer;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        height = size.y;

        if (start_flag == 1) {
            startActivity(new Intent(StartPage.this, APPIntroTwo.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        final ImageView image = (ImageView) findViewById(R.id.ball_img);

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void run() {
                        if (image.getX() > width - image.getWidth() * 2) {
                            stepX = -10;
                        }
                        if (image.getX() < image.getWidth()) {
                            stepX = +10;
                        }
                        image.setX(image.getX() + stepX);
                    }
                });
            }
        }, 20, 20);
    }

    @Override
    public boolean onTouchEvent (MotionEvent m) {
        if (m.getAction() == MotionEvent.ACTION_DOWN) {
            timer.cancel();
            startActivity(new Intent(StartPage.this, PlayPage.class));
            finish();
        }
        return true;
    }

    public  int getAppPrefInt( String prefName){
        int result = -1;
        if(getApplicationContext() != null){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            if(sharedPreferences!=null){
                result = sharedPreferences.getInt(
                        prefName, -1);
            }
        }
        return result;
    }

    public  void putAppPrefInt(  String prefName, int value) {
        if(getApplicationContext()!=null){
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putInt(prefName, value);
            if(Build.VERSION.SDK_INT>=9){
                edit.apply();
            }else{
                edit.commit();
            }
        }
    }

    public  int getAppVersionCode( ) {
        int version = -1;
        try {
            version = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();

        }

        return version;
    }
}
