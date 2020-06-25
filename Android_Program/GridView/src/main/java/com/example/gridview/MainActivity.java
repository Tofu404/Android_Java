package com.example.gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    //初始化资源数组
    private int picture [] = new int []{
            R.drawable.picture1,
            R.drawable.picture2,
            R.drawable.picture3,
            R.drawable.picture4,
            R.drawable.picture5,
            R.drawable.picture6,
            R.drawable.picture7,
            R.drawable.picture8,
            R.drawable.picture9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.gridView);
        //创建一个List对象用于存放带有键、值的Map对象
        List<Map<String,Object>> sourceList = new ArrayList<Map<String,Object>>();
        //为List中的Map对象添加内容
        for(int i = 0;i < picture.length;i++){
            //创建Map对象
            Map<String,Object> map = new HashMap<String,Object>();
            //往Map对象中添加
            map.put("image",picture[i]);
            //将Map对象存放到List当中去
            sourceList.add(map);
        }
        //创建SimpleAdapter对象
        SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,sourceList,R.layout.cell,new String[]{"image"},new int[]{R.id.image});
        //设置GridView对象的适配器
        gridView.setAdapter(adapter);
    }
}
