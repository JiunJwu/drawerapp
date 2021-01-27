package com.example.drawer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import  android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity implements Animation.AnimationListener {
    private ImageView imageView;
    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    setContentView(R.layout.welcome);
    imageView=findViewById(R.id.imageView);

    //imageView 設定動畫元件(透明度調整)
    Animation animation = AnimationUtils.loadAnimation(this,R.anim.welcome);
    animation.setFillEnabled(true);
    animation.setAnimationListener(this);
    imageView.setAnimation(animation);
    }
    @Override
    public void onAnimationStart(Animation animation){}

    @Override
    public void onAnimationEnd(Animation animation) {
        startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
        WelcomeActivity.this.finish();
    }

    @Override
    public void onAnimationRepeat(Animation animation) { }
}
