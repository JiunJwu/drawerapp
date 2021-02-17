package com.example.drawer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class detailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(detailActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });

        final RecyclerView recyclerView = findViewById(R.id.recyclerView_detail);
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        List<detail_setting> detaillist = getdetailList();
        recyclerView.setAdapter(new detailActivity.ListAdapter(this, detaillist));

    }

    private class ListAdapter extends RecyclerView.Adapter<detailActivity.ListAdapter.MyViewHolder> {
        private Context context;
        private List<detail_setting> detailList;

        ListAdapter(Context context, List<detail_setting> getdetailList) {
            this.context = context;
            this.detailList = getdetailList;
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView tvsubId, tvsubId2,tvsubId3;
            LinearLayout sec1,sec2,sec3,sec4;
            List<LinearLayout> seclist = new LinkedList<>();
            List<View> divideline = new LinkedList<>();
            Button bnt1,bnt2,bnt4;
            MyViewHolder(View itemView) {
                super(itemView);
                tvsubId = itemView.findViewById(R.id.tvsubId);
                tvsubId2 = itemView.findViewById(R.id.tvsubId2);
                bnt1 =itemView.findViewById(R.id.detailbtn);
                bnt2 =itemView.findViewById(R.id.detailbtn2);
                bnt4 =itemView.findViewById(R.id.detailbtn4);
                sec1=itemView.findViewById(R.id.sec1);
                sec2=itemView.findViewById(R.id.sec2);
                sec3=itemView.findViewById(R.id.sec3);
                sec4=itemView.findViewById(R.id.sec4);
                seclist.add(sec1);
                seclist.add(sec2);
                seclist.add(sec3);
                seclist.add(sec4);
                divideline.add(itemView.findViewById(R.id.divider1));
                divideline.add(itemView.findViewById(R.id.divider2));
                divideline.add(itemView.findViewById(R.id.divider3));
            }
        }

        @Override
        public int getItemCount() {
            return detailList.size();
        }

        @Override
        public detailActivity.ListAdapter.MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View item = layoutInflater.inflate(R.layout.item_detail, viewGroup, false);
            return new detailActivity.ListAdapter.MyViewHolder(item);
        }

        @Override
        public void onBindViewHolder(detailActivity.ListAdapter.MyViewHolder viewHolder, int position) {
            final detail_setting detail_setting = detailList.get(position);
            viewHolder.tvsubId.setText(String.valueOf(detail_setting.getName()));

            try {
                for (int i =0 ;i<viewHolder.seclist.size(); i++){
                    if (i < detail_setting.getRaw()){
                        LinearLayout thissec = (LinearLayout) viewHolder.seclist.get(i);
                        thissec.setVisibility(View.VISIBLE);
                    }else {
                        LinearLayout thissec = (LinearLayout) viewHolder.seclist.get(i);
                        thissec.setVisibility(View.GONE);
                        if (i>=2){
                            View dline= (View) viewHolder.divideline.get(i-1);
                            dline.setVisibility(View.GONE);
                        }
                    }
                }
                if(!detail_setting.getSec1goto()){viewHolder.bnt1.setVisibility(View.GONE);}
                if(!detail_setting.getSec2goto()){viewHolder.bnt2.setVisibility(View.GONE);}
                if(!detail_setting.getSec3goto()){viewHolder.bnt4.setVisibility(View.GONE);}
            }catch (Exception e){
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
            }
            Intent intent = getIntent();
            termin termin=new termin();
            Bundle bag = intent.getExtras();
            termin.setName(intent.getBundleExtra("termin").getString("name"));
            termin.setType(intent.getBundleExtra("termin").getString("type"));
            termin.setTime(intent.getBundleExtra("termin").getString("time"));
            termin.setNote(intent.getBundleExtra("termin").getString("note"));
            switch(position){
                case 0:
                    viewHolder.tvsubId2.setText(termin.getName());
                    break;
                case 1:
                    viewHolder.tvsubId2.setText(termin.getType());
                    break;
                case 2:
                    viewHolder.tvsubId2.setText(termin.getTime());
                    break;
                case 3:
                    viewHolder.tvsubId2.setText(termin.getNote());
                    break;
            }
        }
    }

    public List<detail_setting> getdetailList() {
        List<detail_setting> detailList = new ArrayList<>();
        detailList.add(new detail_setting(1, false, true, true, "Name and duration",3));
        detailList.add(new detail_setting(2, false, true, true, "Status",2));
        detailList.add(new detail_setting(3, true, true, false, "Internal note",2));
        detailList.add(new detail_setting(4, false, false, false, "Days and times",4));
        return detailList;
    }

}