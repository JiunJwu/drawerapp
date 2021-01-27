package com.example.drawer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import  android.widget.ImageView;
import android.app.Activity;

import androidx.annotation.Nullable;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends Activity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000; //開啟畫面時間(2秒)

    private ImageView imageView;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent intent = new Intent(WelcomeActivity.this, Login.class); //MainActivity為主要檔案名稱
                WelcomeActivity.this.startActivity(intent);

                // close this activity
                WelcomeActivity.this.finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
