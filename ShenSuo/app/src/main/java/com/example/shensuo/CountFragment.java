package com.example.shensuo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class CountFragment extends Fragment {
    private int imageID = R.drawable.pause;
    List<CountMessage> list = new ArrayList<>();
    MyDBHelper dbHelper;
    private int countMessageID;
    MyAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.count_fragment_layout , container, false);
        ListView listView = view.findViewById(R.id.list_view);
        dbHelper = new MyDBHelper(getContext(), "record.db", null, 1);
        getData();
        adapter = new MyAdapter(getContext(), R.layout.item_layout, list);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        listView.setOnItemLongClickListener(longClickListener);
        listView.setOnItemClickListener(listener);
        return view;
    }



    AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            CountMessage countMessage = list.get(i);
            countMessageID = countMessage.getId();
            CountDetails.startCountDetailsActivity(getContext(),countMessageID);
        }
    };


    AdapterView.OnItemLongClickListener longClickListener = new AdapterView.OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
            CountMessage countMessage = list.get(i);
            countMessageID = countMessage.getId();
            return false;//要是返回值为true的话，就不会执行onCreateContextMenu（）方法
        }
    };


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("记录管理");
        menu.add(0,1,0,"编辑");
        menu.add(0,2,0,"删除");
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                EditCountMessage.startEditCountMessageActivity(getContext(),countMessageID);
                break;
            case 2:
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("确定删除？？?");
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteItem();
                        list.clear();
                        getData();
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.create().show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    //从数据表获取数据
    private void getData(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb",null);
        CountMessage message;
        if (cursor.moveToFirst()){
            do {
                message = new CountMessage(cursor.getInt(0),cursor.getInt(5), cursor.getString(2), cursor.getString(3));
                list.add(message);
            }while (cursor.moveToNext());
            cursor.close();
        }
    }

    //删除列表的记录
    private void deleteItem(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("delete from tb where id = "+countMessageID);
    }


    //在onResume中的从新查询数据，然后再更新listView item数据项
    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        getData();
        adapter.notifyDataSetChanged();
    }

}
