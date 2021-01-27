package com.example.drawer.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.drawer.R;
import com.example.drawer.termin;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

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
        TextView tvId, tvName;

        MyViewHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvName = itemView.findViewById(R.id.tvName);
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
            viewHolder.tvId.setText(String.valueOf(termin.getId()));
            viewHolder.tvName.setText(termin.getName());

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
