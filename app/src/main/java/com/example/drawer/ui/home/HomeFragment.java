package com.example.drawer.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.drawer.MySQLiteOpenHelper;
import com.example.drawer.R;
import com.example.drawer.detailActivity;
import com.example.drawer.detail_setting;
import com.example.drawer.termin;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private MySQLiteOpenHelper sqliteHelper;
    private ListAdapter listAdapter;
    private Context thiscontext;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Context thiscontext;
        thiscontext=container.getContext();

        if (sqliteHelper == null) {
            sqliteHelper = new MySQLiteOpenHelper(thiscontext);
        }

        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));

        List<termin> terminList = getterminList();
        listAdapter = new ListAdapter(thiscontext, terminList);
        recyclerView.setAdapter(listAdapter);
        FloatingActionButton plusBtn =root.findViewById(R.id.btn_termin_plus);
        plusBtn.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), detailActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        List<termin> terminList = getterminList();
//        if (terminList.size() <= 0) {
//            Toast.makeText(thiscontext, R.string.text_NoDataFound, Toast.LENGTH_SHORT).show();
//        }
//
//        if (listAdapter == null) {
//            listAdapter = new ListAdapter(thiscontext, terminList);
//            recyclerView.setAdapter(listAdapter);
//        } else {
//            listAdapter.setTerminList(terminList);
//            listAdapter.notifyDataSetChanged();
//        }
//
//    }
    private class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
        private Context context;
        private List<termin> terminList;
        void setTerminList(List<termin> terminList) {
            this.terminList = terminList;
        }

        ListAdapter(Context context, List<termin>terminList){
            this.context=context;
            this.terminList=terminList;
        }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,tvType,tvTime,tvNote;
        Button tvBtn;
        MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvType = itemView.findViewById(R.id.tvType);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvNote = itemView.findViewById(R.id.textNote);
            tvBtn=itemView.findViewById(R.id.detailbtn2);
        }

    }
        @Override
        public int getItemCount(){
            return terminList.size();
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup,int viewType){
            LayoutInflater layoutInflater=LayoutInflater.from(context);
            View item=layoutInflater.inflate(R.layout.item,viewGroup,false);
            return new MyViewHolder(item);
        }
        @Override
        public void onBindViewHolder(MyViewHolder viewHolder, int position){
            final termin termin=terminList.get(position);
            viewHolder.tvName.setText(termin.getName());
            viewHolder.tvTime.setText(termin.getTime());
            viewHolder.tvType.setText(termin.getType());
            if (termin.getNote().equals("")){
                viewHolder.tvNote.setVisibility(View.GONE);
            }else {
                viewHolder.tvNote.setText(termin.getNote());
            }
            viewHolder.tvBtn.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), detailActivity.class);
                    Bundle bag = new Bundle();
                    bag.putInt("id",termin.getId());
                    bag.putString("name",termin.getName());
                    bag.putString("type",termin.getType());
                    bag.putString("time",termin.getTime());
                    bag.putString("note",termin.getNote());
                    intent.putExtra("termin",bag);
                    startActivity(intent);
                }
            });

        }
}
//    public List<termin> getterminList(){
//        List<termin> terminList= new ArrayList<>();
//        terminList.add(new termin(1,"play","play","play","play"));
//        terminList.add(new termin(2,"eat","eat","eat","eat"));
//        terminList.add(new termin(3,"dance","dance","dance","dance"));
//        return terminList;
//    }
    public List<termin> getterminList() {
        return sqliteHelper.getAllSpots();
    }

}
