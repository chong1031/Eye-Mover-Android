package com.example.chongryong.friendly_eye_mover;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PlayPage extends Activity {

    int stepX = 10;
    int width, height;
    int moving_index = 1;
    public int streamId1, streamId2;
    public Timer timer;
    private TimerTask task;
    private MediaPlayer gong_mp;
    int gongId;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play_page);

        final MediaPlayer mpSplash = MediaPlayer.create(this, R.raw.background_sound);
        mpSplash.start();
        mpSplash.isLooping();

        int orietation = getResources().getConfiguration().orientation;
        Log.d("device orientation : ", String.valueOf(orietation));

        final ImageView ballImg = (ImageView) findViewById(R.id.play_ball);
        final TextView stop_txt = (TextView) findViewById(R.id.stop_txt);

        timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
                    @Override
                    public void run() {
                        Display getOrient = getWindowManager().getDefaultDisplay();
                        int orientation = getResources().getConfiguration().orientation;
                        Display display = getWindowManager().getDefaultDisplay();
                        Point size = new Point();
                        display.getSize(size);
                        width = size.x;
                        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                            stepX = stepX / 10 * 18;
                            Log.d("OK", "Landscape");
                        }
                        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                            Log.d("OK", "Portrait");
                        }
                        height = size.y;

                        if (ballImg.getX() > width - ballImg.getWidth()) {
                            if (stepX > 0) {
                                moving_index++;
                                stopPlaying();
                                gong_mp = MediaPlayer.create(PlayPage.this, R.raw.chinese_gong);
                                gong_mp.start();
                                gong_mp.setVolume(0, 1);
                            }
                            stepX = -10;
                        }
                        if (ballImg.getX() < 0) {
                            if (stepX < 0) {
                                moving_index++;
                                stopPlaying();
                                gong_mp = MediaPlayer.create(PlayPage.this, R.raw.chinese_gong);
                                gong_mp.start();
                                gong_mp.setVolume(1, 0);
                            }
                            stepX = +10;
                        }
                        if (stepX > 0)

                            if (moving_index % 7 == 0) {
                                if (moving_index == 3 * 7 * 3) {
                                    moving_index = 0;
                                    timer.cancel();
                                    mpSplash.stop();
                                    stopPlaying();
                                    Log.d("moving_index : ", String.valueOf(moving_index));
                                    Intent intent = new Intent(PlayPage.this, EndPage.class);
                                    startActivity(intent);
                                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                                    finish();
                                }
                                moving_index++;
                                if (ballImg.getY() < ballImg.getHeight()) {
                                    ballImg.setY(height / 2);
                                    return;
                                }
                                if (ballImg.getY() > height / 3 && ballImg.getY() < height / 3 * 2) {
                                    ballImg.setY(height - ballImg.getHeight() * 2);
                                    return;

                                }
                                if (ballImg.getY() > height / 3 * 2) {
                                    ballImg.setY(0);
                                    return;
                                }
                            }
                        ballImg.setX(ballImg.getX() + stepX);
                    }
                });
            }
        };
        timer.schedule(task, 8, 8);

        stop_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moving_index = 0;
                timer.cancel();
                timer = null;
                task.cancel();
                mpSplash.stop();
                stopPlaying();
                stepX = 0;
                ballImg.setX(width * 10);
                Log.d("moving_index : ", String.valueOf(moving_index));
                Intent intent = new Intent(PlayPage.this, EndPage.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
            }
        });
    }

    private void stopPlaying() {
        if (gong_mp != null) {
            gong_mp.stop();
            gong_mp.release();
            gong_mp = null;
        }
    }
}
