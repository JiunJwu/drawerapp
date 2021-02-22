package com.example.drawer;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class PopWinShare extends PopupWindow {
    private static final String TAG = "PopWinShare";

    public PopWinShare(Context context) {
        super(context);
        //设置view
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.popup_share, null);
        this.setContentView(view);
        initView(view,context);
        //其他设置
        this.setFocusable(true);//是否获取焦点
        this.setBackgroundDrawable(null);
        this.setAnimationStyle(R.style.AnimationFade);
        this.update();
        //setOutsideTouchable(true);//是否可以通过点击屏幕外来关闭
        Button btnback = view.findViewById(R.id.btn_shareback);
        //取消按钮
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

//    @Override
//    public void showAtLocation (View parent,int gravity, int x, int y){
//        super.showAtLocation(parent, gravity, x, y);
//        //加入动画
//
//        ObjectAnimator.ofFloat(getContentView(), "translationY", parent.getHeight(), 0).setDuration(1000).start();
//    }

    private void initView (View view,Context context){
        RecyclerView recyview_share = view.findViewById(R.id.recyview_share);
        recyview_share.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        List<Share> shareList = getShareList();
        recyview_share.setAdapter(new ShareAdapter(context,shareList));
    }
    private class ShareAdapter extends RecyclerView.Adapter<PopWinShare.ShareAdapter.popViewHolder> {
        private Context context;
        private List<Share> shareList;

        ShareAdapter(Context context,List<Share> getschareList) {
            this.context = context;
            this.shareList = getschareList;
        }

        class popViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView shareway;

            popViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.ivImage);
                shareway = itemView.findViewById(R.id.share_action);
            }
        }

        @Override
        public int getItemCount() {
            return shareList.size();
        }

        @Override
        public popViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View item = layoutInflater.inflate(R.layout.share_option, viewGroup, false);
            return new PopWinShare.ShareAdapter.popViewHolder(item);
        }

        @Override
        public void onBindViewHolder(popViewHolder viewHolder, int position) {
            final Share share_setting = shareList.get(position);
            viewHolder.shareway.setText(share_setting.getName());
            switch (share_setting.getImage()) {
                case 0:
                    viewHolder.imageView.setImageResource(R.drawable.ic_baseline_email_40);
                    break;
                case 1:
                    viewHolder.imageView.setImageResource(R.drawable.ic_baseline_textsms_40);
                    break;
                case 2:
                    viewHolder.imageView.setImageResource(R.drawable.ic_baseline_insert_link_40);
                    break;
                case 3:
                    viewHolder.imageView.setImageResource(R.drawable.ic_baseline_more_40);
                    break;
            }

            viewHolder.itemView.setOnClickListener(v -> {
                switch (position) {
                    case 0:
                        break;
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }

            });

        }
    }

    public List<Share> getShareList() {
        List<Share> shareList = new ArrayList<>();
        shareList.add(new Share(1, 0, "Send Email"));
        shareList.add(new Share(2, 1, "Send Text"));
        shareList.add(new Share(3, 2, "Copy Link"));
        shareList.add(new Share(4, 3, "More Options"));
        return shareList;
    }
}