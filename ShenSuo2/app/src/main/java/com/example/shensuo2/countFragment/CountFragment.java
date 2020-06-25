package com.example.shensuo2.countFragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import androidx.fragment.app.Fragment;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.shensuo2.MyDBHelper;
import com.example.shensuo2.R;

import java.util.ArrayList;
import java.util.List;


public class CountFragment extends Fragment {
    private List<CountMessage> list = new ArrayList<>();
    private MyAdapter adapter;
    ListView listView;
    private String dataID;//这个值是数据在数据库中的id值，在点击和长按的时候进行赋值
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_count,container,false);
        getData();
        listView = view.findViewById(R.id.list_view);
        //为listview的每一个item项添加点击和长按事件
        addListeners();
        adapter = new MyAdapter(getContext(),R.layout.list_view_item_layout,list);
        listView.setAdapter(adapter);
        return view;
    }

    //获取数据库中的数据并将数据显示到listview上去
    private void getData(){
        MyDBHelper dbHelper = new MyDBHelper(getContext(),"record.db",null,1);
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from tb_record",null);
        if (cursor.moveToFirst()){
            do {
                CountMessage message = new CountMessage(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getInt(5),cursor.getInt(6));
                list.add(message);
            }while (cursor.moveToNext());
        }
    }

    private void addListeners(){
        //点击item跳转到账号信息详情页面
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CountMessage message = list.get(i);
                dataID = message.getDataID();
                ShowDetails.startShowDetails(getContext(),dataID);
            }
        });

        //长按item弹出“编辑”和“删除”item的选项
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                CountMessage message = list.get(i);
                dataID = message.getDataID();
                registerForContextMenu(adapterView);
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("更改账号信息：");
        menu.add(0,1,0,"编辑账号信息");
        menu.add(0,2,0,"删除账号信息");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case 1:
                EditRecord.startEditRecord(getContext(),dataID);
                break;
            case 2:
                AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
                dialog.setTitle("确认删除？");
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {}
                });

                dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //当用户点击了确认按钮之后就执行删除数据操作
                        MyDBHelper dbHelper = new MyDBHelper(getContext(),"record.db",null,1);
                        SQLiteDatabase database = dbHelper.getWritableDatabase();
                        database.execSQL("delete from tb_record where id = "+ dataID);
                        list.clear();
                        getData();
                        //这个方法是在数据结果集发生改变的时候，从新绘制listview可见的部分
                        adapter.notifyDataSetChanged();
                    }
                });
                dialog.create().show();
                break;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        list.clear();
        getData();
        adapter.notifyDataSetChanged();
    }
}
