package com.tianze.listgrount;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    MyAdapter myAdapter;
    public List<String> listTag = new ArrayList<String>();

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.list);
        myAdapter = new MyAdapter(this,
                android.R.layout.simple_expandable_list_item_1, getData());
        listView.setAdapter(myAdapter);
    }

    private List<String> getData() {

        List<String> data = new ArrayList<String>();
        int i = 0;
        data.add("A");
        listTag.add("A");
        data.add("aa试数据" + (i++));
        data.add("a试数据" + (i++));
        data.add("aa试数据" + (i++));
        listTag.add("B");
        data.add("B");
        data.add("bb试数据" + (i++));
        data.add("b试数据" + (i++));
        data.add("b试数据" + (i++));
        data.add("b试数据" + (i++));
        listTag.add("C");
        data.add("C");
        data.add("c测试数据" + (i++));
        data.add("c测试数据" + (i++));
        listTag.add("D");
        data.add("D");
        data.add("d测试数据" + (i++));
        data.add("d测试数据" + (i++));
        data.add("d测试数据" + (i++));
        listTag.add("E");
        data.add("E");
        data.add("e测试数据" + (i++));
        data.add("e测试数据" + (i++));
        data.add("e测试数据" + (i++));
        listTag.add("F");
        data.add("F");
        data.add("f测试数据" + (i++));
        return data;
    }


    class MyAdapter extends ArrayAdapter<String> {

        public MyAdapter(Context context, int textViewResourceId, List<String> objects) {
            super(context, textViewResourceId, objects);
        }

        @Override
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override
        public boolean isEnabled(int position) {
            // 如果-开头，则该项不可选
            return !listTag.contains(getItem(position));
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            //根据标签类型加载不通的布局模板
            if (listTag.contains(getItem(position))) {
                //如果是标签项
                view = LayoutInflater.from(getContext()).inflate(R.layout.group_list_item_tag, null);
            } else {
                //否则就是数据项
                view = LayoutInflater.from(getContext()).inflate(R.layout.group_list_item, null);
            }
            //显示名称
            TextView textView = (TextView) view.findViewById(R.id.group_list_item_text);
            textView.setText(getItem(position));
            //返回重写的view
            return view;
        }


    }
}
