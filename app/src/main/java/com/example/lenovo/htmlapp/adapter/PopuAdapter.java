package com.example.lenovo.htmlapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo.htmlapp.R;
import com.example.lenovo.htmlapp.bean.Bean;
import com.example.lenovo.htmlapp.bean.User;
import com.example.lenovo.htmlapp.bookshelf.view.ShowBookActivity;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * Created by lenovo on 2018/1/14.
 */

public class PopuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<User> list;
    private Context context;
    private LayoutInflater inflater;

    public PopuAdapter(List<User> list, Context context) {
        this.list = list;
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.popu_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final User user = list.get(position);
     MyViewHolder  myViewHolder = (MyViewHolder) holder;
        myViewHolder.all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ShowBookActivity.class);
                intent.putExtra("url", user.getUrl());
                context.startActivity(intent);
            }
        });
        myViewHolder.title.setText(user.getTitle());
        myViewHolder.time.setText(user.getTime());

    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        AutoRelativeLayout all;
        TextView title;
        TextView time;
        public MyViewHolder(View itemView) {
            super(itemView);
            all=itemView.findViewById(R.id.popu_all);
            time=itemView.findViewById(R.id.popu_time);
            title=itemView.findViewById(R.id.popu_title);
        }
    }
}
