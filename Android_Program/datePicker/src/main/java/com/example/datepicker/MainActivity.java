package com.example.datepicker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * 程序的编写的步骤
 * 1、定义year、month、day和DatePicker成员变量
 * 2、获取datepicker对象和获取Calendar日历对象
 * 3、通过Calendar获取当前的日期信息
 * 4、初始化datepicker，并添加日期改变的监听器
 * 5、通过show方法输出选择的日期的信息
 *
 */
public class MainActivity extends AppCompatActivity{
    //定义全局变量
    int y;
    int m;
    int d;
    DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获取datepicker对象
        datePicker = findViewById(R.id.datepicker);
        //获取Calendar日历对象
        Calendar calendar = Calendar.getInstance();
        //通过日历对象过去当的计算机上的日期信息
        y = calendar.get(Calendar.YEAR);
        m = calendar.get(Calendar.MONTH);
        d = calendar.get(Calendar.DAY_OF_MONTH);
        datePicker.init(y, m, d, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                MainActivity.this.y = year;
                MainActivity.this.m = monthOfYear;
                MainActivity.this.d = dayOfMonth;
                show(year,monthOfYear,dayOfMonth);
            }
        });
    }
    private void show(int a,int b,int c){
        Toast.makeText(MainActivity.this,y+"年"+(m+1)+"月"+d+"日",Toast.LENGTH_SHORT).show();
    }
}

