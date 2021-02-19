package com.example.drawer;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

public class PopWinShare extends PopupWindow{
    private static final String TAG = "PopWinShare";

    public PopWinShare(Context context) {
        //设置view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popup_share, null);
        setContentView(view);
        initView();
        //其他设置
        setFocusable(true);//是否获取焦点
        this.setAnimationStyle(R.style.AnimBottom);
        //setOutsideTouchable(true);//是否可以通过点击屏幕外来关闭
        Button btnback =  view.findViewById(R.id.btn_shareback);
        //取消按钮
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) { dismiss(); }
        });

    }



    @Override
    public void showAtLocation(View parent, int gravity, int x, int y) {
        super.showAtLocation(parent, gravity, x, y);
        //加入动画
        ObjectAnimator.ofFloat(getContentView(), "translationY", getHeight(), 0).setDuration(500).start();
    }
    private void initView() {

    }
}
