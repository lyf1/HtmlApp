package com.example.lenovo.htmlapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.lenovo.htmlapp.R;
import com.example.lenovo.htmlapp.bean.Bean;

import com.zhy.autolayout.AutoLinearLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.List;

/**
 * Created by lenovo on 2018/1/15.
 */

public class ClassGroupAdapater extends BaseAdapter {

    List<Bean> list;
    Context context;
    LayoutInflater inflater;

    public ClassGroupAdapater(List<Bean> list, Context context) {
        this.list = list;
        this.context = context;

        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MyViewHolder holder;
        if(convertView==null){
            holder = new MyViewHolder();
            convertView= View.inflate(context, R.layout.classgroup_item, null);
             holder.title=convertView.findViewById(R.id.cg_title);
             holder.all=convertView.findViewById(R.id.cg_all);
             holder.view=convertView.findViewById(R.id.cg_view);
            convertView.setTag(holder);
        }else{
            holder= (MyViewHolder) convertView.getTag();
        }
        Bean bean = list.get(position);
        holder.title.setText(bean.title);
        if (bean.isChecked()) {

            holder.view.setVisibility(View.VISIBLE);
            holder.title.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.title.setTextColor(Color.parseColor("#2a79ce"));
        } else {
            holder.view.setVisibility(View.GONE);
            holder.title.setBackgroundColor(Color.parseColor("#eff1f4"));
            holder.title.setTextColor(Color.parseColor("#1a1b1b"));
        }

        return convertView;
    }
    class MyViewHolder {
        View view;
        AutoLinearLayout all;
        TextView title;
    }
    public void press(int position) {
        //先遍历循环list集合
        for (int i = 0; i < list.size(); i++) {
            Bean bean = list.get(i);
            bean.setChecked(false);

        }
        Bean dataBean = list.get(position);
        dataBean.setChecked(true);
        notifyDataSetChanged();
    }

}
