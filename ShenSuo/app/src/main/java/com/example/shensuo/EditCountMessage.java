package com.example.shensuo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.text.style.QuoteSpan;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class EditCountMessage extends AppCompatActivity {

    private boolean flag = true;
    private TextView showNumber;
    private SeekBar seekBar;
    private EditText editText;
    private MyDBHelper dbHelper;
    private EditText platformName;
    private EditText count;
    private EditText password;
    private int imageID = R.drawable.pause;
    private String countMessageID;
    private ImageView imageView;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        editText = findViewById(R.id.remark);
        ObjectAnimator.ofFloat(editText,"translationY",0f,-400f).start();//这个动画，在创建的时候将文本区域上升
        showNumber = findViewById(R.id.show_number);
        seekBar = findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(listener);
        findViewById(R.id.options).setOnClickListener(upAndDownListener);
        myActionBar();
        findID();
        dbHelper = new MyDBHelper(this, "record.db", null, 1);
        Intent intent = getIntent();
        countMessageID = intent.getStringExtra("countMessageID");
        fillData();
        buildPW();
    }

    private void buildPW(){
        TextView buildPW = findViewById(R.id.help_me_choice);
        buildPW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PasswordBuilder builder = new PasswordBuilder();
                SeekBar seekBar = findViewById(R.id.seek_bar);
                int PWLength = seekBar.getProgress() + 6;
                int s = 0;
                int n = 0;
                int letter = 0 ;
                Switch s1 = findViewById(R.id.switch1);
                Switch s2 = findViewById(R.id.switch2);
                Switch s3 = findViewById(R.id.switch3);
                if (s1.isChecked()){
                    n = 1;
                }else {
                    n = 0;
                }
                if (s2.isChecked()){
                    letter = 1;
                }else {
                    letter = 0;
                }
                if (s3.isChecked()){
                    s = 1;
                }else {
                    s = 0;
                }
                password.setText(builder.getPW(s,n,letter,PWLength));
            }
        });
    }

    //seekBar滑动数值发生变化的时候的监听器
    SeekBar.OnSeekBarChangeListener listener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            int text = 6+i;
            //设置文本显示
            showNumber.setText(String.valueOf(text));

            //获取文本宽度
            float textWidth = showNumber.getWidth();
            //获取seekbar最左端的x位置
            float left = seekBar.getLeft();
            //进度条的刻度值
            float max = Math.abs(seekBar.getMax());
            //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
            float thumb = dip2px(EditCountMessage.this,10);
            //每移动1个单位，text应该变化的距离 = (seekBar的宽度 - 两头空的空间) / 总的progress长度
            float average = (((float) seekBar.getWidth())-2*thumb)/max;
            //textview 应该所处的位置 = seekbar最左端 + seekbar左端空的空间 + 当前progress应该加的长度 - textview宽度的一半(保持居中作用)
            float pox = thumb + average * (i) - textWidth/2;
            AnimatorSet set = new AnimatorSet();
            ObjectAnimator animator1 = ObjectAnimator.ofFloat(showNumber,"x",pox);
            ObjectAnimator animator2 = ObjectAnimator.ofFloat(showNumber, "alpha", 1f,0f,1f);
            set.setDuration(200);
            set.playTogether(animator1,animator2);
            set.start();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    //这个函数是用来进行像素的转换的将（dp）转化成（px）
    public  int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //这个监听器是用来控制文本区域升降的
    View.OnClickListener upAndDownListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (flag) {
                ObjectAnimator down = ObjectAnimator.ofFloat(editText, "translationY", -400f, 0f);
                down.setDuration(1500);
                down.setInterpolator(new BounceInterpolator());
                down.start();
                flag = false;
            } else {
                ObjectAnimator up = ObjectAnimator.ofFloat(editText, "translationY", 0f, -400f);
                up.setDuration(800);
                up.setInterpolator(new DecelerateInterpolator());
                up.start();
                flag = true;
            }
        }
    };


    //这个函数是用来启动EditCountMessage
    public static void startEditCountMessageActivity(Context context,int ID) {
        Intent intent = new Intent(context, EditCountMessage.class);
        intent.putExtra("countMessageID",String.valueOf(ID));
        context.startActivity(intent);
    }

    private void myActionBar(){
        ActionBar bar = getSupportActionBar();
        bar.setDisplayShowCustomEnabled(true);
        bar.setCustomView(R.layout.actionbar_addrecord_layout);

        //取消按钮
        findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //保存按钮
        findViewById(R.id.save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(platformName.getText().toString())){
                    Toast.makeText(EditCountMessage.this, "请输入平台名称", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(count.getText().toString())){
                    Toast.makeText(EditCountMessage.this, "请输入用户名", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(EditCountMessage.this, "请输入密码", Toast.LENGTH_SHORT).show();
                }else {
                    updateData();
                    Toast.makeText(EditCountMessage.this, "编辑成功~", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }

    //保存用户输入的账号和密码信息更新数据库记录
    private void updateData(){
        //create table tb (id integer primary key autoincrement,platformName text,count text,password text,remark text, imageID integer
        db.execSQL("update tb set platformName = ?,count = ?,password = ?,remark = ? where id = ?",
                new String[]{platformName.getText().toString(),count.getText().toString(),
                password.getText().toString(),editText.getText().toString(),countMessageID});
    }

    private void findID(){
        platformName = findViewById(R.id.add_platform_name);
        count = findViewById(R.id.add_user_name);
        password = findViewById(R.id.add_user_pw);
        imageView = findViewById(R.id.add_imageID);
    }

    //将数据库中原有的数据填充到EditText中
    private void fillData(){
        db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb where id = "+countMessageID,null);
        if (cursor.moveToFirst()){
            platformName.setText(cursor.getString(1));
            count.setText(cursor.getString(2));
            password.setText(cursor.getString(3));
            editText.setText(cursor.getString(4));
            imageView.setImageResource(cursor.getInt(5));
        }
        cursor.close();
    }

}
