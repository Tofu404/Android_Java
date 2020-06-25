package com.example.shensuo2.countFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.shensuo2.R;

import java.util.List;

public class MyAdapter extends ArrayAdapter<CountMessage> {

    private List<CountMessage> list;
    private int resource;
    public MyAdapter(Context context, int resource, List<CountMessage> objects) {
        super(context, resource, objects);
        this.resource = resource;
        list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CountMessage message = list.get(position);
        View view;
        ViewHolder holder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resource,parent,false);
            holder = new ViewHolder();
            holder. textImageView= view.findViewById(R.id.item_text_image);
            holder.tv_count_name = view.findViewById(R.id.item_count_name);
            holder.tv_password = view.findViewById(R.id.item_password);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        if (message.getImageBgOrColorBg() == 1){
            holder.textImageView.setBackground(getContext().getResources().getDrawable(message.getResourceID(),null));
            holder.textImageView.setText("");
        }
        if (message.getImageBgOrColorBg() == 0){
            Log.e("nihao", "getView: "+message.getResourceID());
            holder.textImageView.setBackgroundColor(getContext().getResources().getColor(message.getResourceID()));
            holder.textImageView.setText(message.getPlatformNaem().substring(0,1));
        }
        holder.tv_count_name.setText("平台名称："+message.getPlatformNaem());
        holder.tv_password.setText("账号："+message.getCountName());
        return view;
    }
    class ViewHolder{
        TextView tv_count_name;
        TextView tv_password;
        TextView textImageView;
    }
}
