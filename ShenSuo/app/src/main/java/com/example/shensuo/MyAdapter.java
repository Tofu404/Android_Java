package com.example.shensuo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

public class MyAdapter extends ArrayAdapter<CountMessage> {
    private List<CountMessage> list;
    public MyAdapter(Context context, int resource, List<CountMessage> objects) {
        super(context, resource, objects);
        this.list = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        TextView count_tv;
        TextView pw_tv;
        ImageView imageView;
        ViewHolder holder;
        CountMessage countMessage = list.get(position);
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_layout, parent, false);
            count_tv = view.findViewById(R.id.list_item_user_name);
            pw_tv = view.findViewById(R.id.list_item_user_passwor);
            imageView = view.findViewById(R.id.list_item_image);
            holder = new ViewHolder(imageView, count_tv, pw_tv);
            view.setTag(holder);
        }else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
            count_tv = holder.count_tv;
            pw_tv = holder.pw_tv;
            imageView = holder.imageView;
        }
        count_tv.setText(countMessage.getUserName());
        pw_tv.setText(countMessage.getUserCount());
        imageView.setImageResource(countMessage.getImageID());
        return view;
    }

}
