package com.example.replacehead;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class HeadActivity extends AppCompatActivity {
    int [] pictureResource = new int[]{
            R.drawable.touxiang1,
            R.drawable.touxiang2,
            R.drawable.touxiang3,
            R.drawable.touxiang4,
            R.drawable.touxiang5
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head);
        BaseAdapter baseAdapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return pictureResource.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ImageView imageView;
                if(convertView == null){
                    imageView = new ImageView(HeadActivity.this);
                    imageView.setMaxHeight(300);
                    imageView.setMaxWidth(200);
                    imageView.setAdjustViewBounds(true);
                    imageView.setPadding(5,5,5,5);
                }else{
                    imageView = (ImageView) convertView;
                }
                imageView.setImageResource(pictureResource[position]);
                return imageView;
            }
        };
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(baseAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = getIntent();//要是软件中的存在intend对象的话那就获取该intend对象
                Bundle bundle = new Bundle();
                bundle.putInt("image",pictureResource[position]);
                intent.putExtras(bundle);
                setResult(0x11,intent);
                finish();
            }
        });
    }
}
