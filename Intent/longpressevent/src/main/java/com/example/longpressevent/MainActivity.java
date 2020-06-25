package com.example.longpressevent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //将长按事件注册到菜单中，并打开菜单
        ImageView imageView = findViewById(R.id.picture);
        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //将容器的菜单注册到imageView上
                registerForContextMenu(v);
                //启动菜单
                openContextMenu(v);
                Toast.makeText(MainActivity.this,"你长按了按键",Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    //第一步在MainActivity中重写onCreateContentMenu，为菜单项添加选项值

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add("收藏");
        menu.add("举报");
    }
}
