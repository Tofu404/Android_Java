package com.example.listview_wechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private int image[] = new int[]{
            R.drawable.picture1, R.drawable.picture2, R.drawable.picture3,
            R.drawable.picture4, R.drawable.picture5, R.drawable.picture6,
            R.drawable.picture7, R.drawable.picture8, R.drawable.picture9
    };

    String name[] = new String[]{
            "1号选手", "2号选手", "3号选手",
            "4号选手", "5号选手", "6号选手",
            "7号选手", "8号选手", "9号选手"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Map<String,Object>> listItem = new ArrayList<Map<String,Object>>();
        for (int i = 0;i < image.length;i++){
            Map<String,Object> map = new HashMap<String,Object>();
            map.put("image",image[i]);
            map.put("text",name[i]);
            listItem.add(map);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,listItem,R.layout.layout,new String[]{"image","text"},new int[]{R.id.imageView,R.id.layouttext});
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,Object> map = (Map<String, Object>) parent.getItemAtPosition(position);
                Toast.makeText(MainActivity.this,map.get("text").toString(),Toast.LENGTH_SHORT).show();
            }
        });
    }
}
