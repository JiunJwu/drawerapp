package com.example.drawer.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.drawer.MySQLiteOpenHelper;
import com.example.drawer.R;
import com.example.drawer.detailActivity;
import com.example.drawer.termin;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private MySQLiteOpenHelper sqliteHelper;
    private ListAdapter listAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Context thiscontext;
        thiscontext=container.getContext();
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        List<termin> terminlist = getterminList();
        recyclerView.setAdapter(new ListAdapter(thiscontext, terminlist));

        return root;
    }

    private class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {
        private Context context;
        private List<termin> terminList;
        ListAdapter(Context context, List<termin>terminList){
            this.context=context;
            this.terminList=terminList;
        }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        Button tvBtn;
        MyViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
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
            viewHolder.tvBtn.setOnClickListener(new View.OnClickListener()  {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), detailActivity.class);
                    //detail_setting detail =new detail_setting(termin.getId(),true,true,true,termin.getName(),termin.getRaw);
                    //intent.putExtra("detail",detail);
                    startActivity(intent);
                }
            });
        }
}
    public List<termin> getterminList(){
        List<termin> terminList= new ArrayList<>();
        terminList.add(new termin(1,"play"));
        terminList.add(new termin(2,"eat"));
        terminList.add(new termin(3,"dance"));
        return terminList;
    }

}
