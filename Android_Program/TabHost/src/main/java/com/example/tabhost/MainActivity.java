package com.example.tabhost;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.TabHost;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得TabHost对象
        TabHost tabHost = findViewById(android.R.id.tabhost);
        //初始化TabHost对象
        tabHost.setup();
        //要往TabHost对象里面添加选项卡前先要创建LayoutInflater对象，在tabcontent中添加布局文件
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        layoutInflater.inflate(R.layout.tab1,tabHost.getTabContentView());
        layoutInflater.inflate(R.layout.tab2,tabHost.getTabContentView());
        //往tabhost中添加选项卡
        /**
         * 参数函数：
         * 1、tag：就是选项卡的名称
         * 2、indicator就是为选项卡添加外在名称
         * 3、指定一个容器
         */
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("首页").setContent(R.id.left));
        tabHost.addTab(tabHost.newTabSpec("tab11").setIndicator("新闻").setContent(R.id.right));
        tabHost.addTab(tabHost.newTabSpec("tab21").setIndicator("新闻").setContent(R.id.right));
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("游戏").setContent(R.id.left));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("数码").setContent(R.id.right));
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator("摄影").setContent(R.id.left));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator("娱乐").setContent(R.id.right));
        tabHost.addTab(tabHost.newTabSpec("tab22").setContent(R.id.left).setIndicator("孔天泽"));
    }
}
