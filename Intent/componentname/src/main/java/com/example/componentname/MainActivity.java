/**
 * 这个属性是用来设置intent对象的组件名称的
 */

/**
 * 这个程序中遇到的问题：
 * 编写玩代码的时候，点击按钮进行Activity之间的跳转，发先无法进行跳转，
 * 并出现Process: com.example.componentname, PID: 32105
 * android.content.ActivityNotFoundException: Unable to find explicit activity class {com.example.componentname/com.example.componentname.ComponentActivity};
 * have you declared this activity in your AndroidManifest.xml?
 * 这样的错误。
 * 原因：就是没有在AndroidManifest文件中声明要跳转的Avtivity的名称，解决的方法就是在Android中进行声明Activity
 */
package com.example.componentname;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                ComponentName componentName = new ComponentName("com.example.componentname","com.example.componentname.ComponentActivity");
                intent.setComponent(componentName);
                startActivity(intent);
            }
        });
    }
}
