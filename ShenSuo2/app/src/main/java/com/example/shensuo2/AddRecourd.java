package com.example.shensuo2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Random;

public class AddRecourd extends AppCompatActivity implements View.OnClickListener {

    //布局中的控件
    private TextView cancel;
    private TextView save;
    private EditText platformName;
    private TextView platformTextImage;
    private EditText countName;
    private EditText password;
    private ToggleButton showOrHidePassword;
    private TextView helpMeBuildPassword;
    private Button optionBtn;
    private Switch switchNumber;
    private Switch switchLetter;
    private Switch switchSymbol;
    //private Switch switchEmoji;//这个功能暂时没有实现
    TextView tips;
    private TextView pwLengthText;
    private SeekBar pwLengthSeekBar;
    private EditText remark;
    private EditText addLabelEditText;
    private TextView addLabelTextView;
    private MyDBHelper myDBHelper;
    //判断是否点击“选项按钮”
    private boolean isClick = true;
    //颜色资源数组
    public int colorArray[] = new int[]{
            R.color.blue,R.color.colorAccent,R.color.colorPrimary,
            R.color.colorPrimaryDark,R.color.gray,R.color.green,
            R.color.red,R.color.white,R.color.yellow
    };
    //这个变量是用来存放颜色id值和图片的id值
    private int resourceID = 0;
    //这个变量是判断是否是纯颜色背景还是图片背景的
    private int imageBgOrColorBg = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recourd);
        //初始化dbhelper对象
        myDBHelper = new MyDBHelper(AddRecourd.this,"record.db",null,1);
        //自定义的ActionBar
        myActionBar();
        //初始化布局中的控件
        initControl();
        //为控件添加监听器
        addListener();
        //seekBar和textView一起移动
        seekBarMove();
        EidtTextLetterLengthchange();
    }

    //启动AddRecourd页面函数
    public static void startAddRecourd(Context context){
        Intent intent = new Intent(context,AddRecourd.class);
        context.startActivity(intent);
    }

    //自定义ActionBar
    private void myActionBar(){
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(false);
        bar.setHomeButtonEnabled(false);
        bar.setDisplayShowCustomEnabled(true);
        bar.setCustomView(R.layout.add_recourd_action_bar_layout);
    }

    //初始化布局中的控件
    private void initControl(){
        cancel = findViewById(R.id.cancel);
        save = findViewById(R.id.save);
        platformName = findViewById(R.id.platform_name);
        platformTextImage = findViewById(R.id.platform_text_image);
        countName = findViewById(R.id.user_name);
        password = findViewById(R.id.password);
        tips = findViewById(R.id.i_am_tips);
        showOrHidePassword = findViewById(R.id.show_or_hide_password);
        helpMeBuildPassword = findViewById(R.id.buildPW);
        optionBtn = findViewById(R.id.options);
        switchNumber =findViewById(R.id.switch_number);
        switchLetter = findViewById(R.id.switch_letter);
        switchSymbol = findViewById(R.id.switch_symbol);
        //switchEmoji = findViewById(R.id.switch_emoji);
        pwLengthText = findViewById(R.id.pw_length_text);
        pwLengthSeekBar = findViewById(R.id.pw_length_seek_bar);
        remark = findViewById(R.id.remark);
        addLabelEditText = findViewById(R.id.my_label);
        addLabelTextView = findViewById(R.id.add_label);
    }

    //给控件添加监听器
    private void addListener(){
        cancel.setOnClickListener(this);
        save.setOnClickListener(this);
        showOrHidePassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    password.setSelection(getTV(password).length());
                }else {
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    password.setSelection(getTV(password).length());
                }
            }
        });
        helpMeBuildPassword.setOnClickListener(this);
        optionBtn.setOnClickListener(this);

    }

    //点击控件触发的事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancel:
                finish();
                break;
            case R.id.save:
                if (TextUtils.isEmpty(platformName.getText().toString())){
                    Toast.makeText(AddRecourd.this,"请输入平台名称",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(countName.getText().toString())){
                    Toast.makeText(AddRecourd.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(AddRecourd.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else {
                    saveData();
                    finish();
                }
                break;
            case R.id.options:
                LinearLayout linearLayout = findViewById(R.id.linear_layout);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                LinearLayout test = findViewById(R.id.add_label_layout);
                RelativeLayout relativeLayout = findViewById(R.id.PW_option);
                if (isClick){
                    params.setMargins(0,0,0,relativeLayout.getHeight());
                    test.setLayoutParams(params);
                    ObjectAnimator animator = ObjectAnimator.ofFloat(linearLayout,"translationY",0f,(float)relativeLayout.getHeight());
                    animator.setDuration(1000);
                    animator.setInterpolator(new BounceInterpolator());
                    animator.start();
                    isClick = false;
                }else {
                    params.setMargins(0,0,0,0);
                    test.setLayoutParams(params);
                    ObjectAnimator.ofFloat(linearLayout,"translationY",(float)relativeLayout.getHeight(),0f).setDuration(400).start();
                    isClick = true;
                }
                break;
            case R.id.buildPW:
                int shuzi = 0;
                int zimu = 0;
                int fuhao = 0;
                if (switchNumber.isChecked()){
                    shuzi = 1;
                }
                if (switchLetter.isChecked()){
                    zimu = 1;
                }
                if (switchSymbol.isChecked()){
                    fuhao = 1;
                }
                int pwLength = 6 + pwLengthSeekBar.getProgress();
                BuildPassword buildPassword = new BuildPassword(shuzi,zimu,fuhao,pwLength);
                password.setText(buildPassword.returnPassword());
                break;
        }
    }

    //隐藏或显示密码后将光标移动到最后的一个字符后面
    private CharSequence getTV(EditText password) {
        return password == null ? "" : password.getText().toString();
    }

    //保存用户输入的密码信息
    private void saveData() {
        SQLiteDatabase db = myDBHelper.getWritableDatabase();
        //获取本地时间作为保存记录的id
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        StringBuilder builder = new StringBuilder();
        builder.append(year).append(month).append(day).append(hour).append(minute).append(second);
        String id = builder.toString();
        //create table tb_record (id text primary key,platformName text,countName text,password text,remark text ,resourceID integer
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("platformName",platformName.getText().toString());
        values.put("countName",countName.getText().toString());
        values.put("password",password.getText().toString());
        values.put("remark",remark.getText().toString());
        values.put("resourceID",resourceID);
        values.put("imageBgOrColorBg",imageBgOrColorBg);
        //第一参数是表名，第二参数是给没有赋值的列自动赋值为null，第三个参数是要插入的数据项
        db.insert("tb_record",null,values);
    }

    //SeekBar上的TextView和SeekBar一起滑动
    private void seekBarMove(){
        pwLengthSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int text = 6+i;
                //设置文本显示
                pwLengthText.setText(String.valueOf(text));
                //获取文本宽度
                float textWidth = pwLengthText.getWidth();
                //获取seekbar最左端的x位置
                float left = seekBar.getLeft();
                //进度条的刻度值
                float max = Math.abs(seekBar.getMax());
                //这不叫thumb的宽度,叫seekbar距左边宽度,实验了一下，seekbar 不是顶格的，两头都存在一定空间，所以xml 需要用paddingStart 和 paddingEnd 来确定具体空了多少值,我这里设置15dp;
                float thumb = dip2px(AddRecourd.this,15);
                //每移动1个单位，text应该变化的距离 = (seekBar的宽度 - 两头空的空间) / 总的progress长度
                float average = (((float) seekBar.getWidth())-2*thumb)/max;
                //textview 应该所处的位置 = seekbar最左端 + seekbar左端空的空间 + 当前progress应该加的长度 - textview宽度的一半(保持居中作用)
                float pox = thumb + average * (i) - textWidth/2;
                AnimatorSet set = new AnimatorSet();
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(pwLengthText,"x",pox);
                ObjectAnimator animator2 = ObjectAnimator.ofFloat(pwLengthText, "alpha", 1f,0f,1f);
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
        });
    }

    //这个函数是用来进行像素的转换的将（dp）转化成（px）
    private int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    //执行动态检测EditText文本长度的变化情况
    private void EidtTextLetterLengthchange(){

        //检测密码长度的变化
        password.addTextChangedListener(new TextWatcher() {
            //文字变化前的执行
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            //文字变的执行
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //在这个方法中检测密码强度的变化
            }

            //文字变化后的执行，现在主要用这个方法
            @Override
            public void afterTextChanged(Editable editable) {
                //检测密码长度的变化
                if (editable.length() == 0){
                    tips.setVisibility(View.GONE);
                }else {
                    tips.setVisibility(View.VISIBLE);
                    int textLength = editable.length();
                    if (textLength >= 0 && textLength <= 6){
                        tips.setText("你的密码太弱了~");
                        tips.setTextColor(getResources().getColor(R.color.red));
                    }
                    if (textLength >= 7 && textLength <= 10){
                        tips.setText("密码强度一般~");
                        tips.setTextColor(getResources().getColor(R.color.blue));
                    }
                    if (textLength >= 11){
                        tips.setText("密码强度很强！");
                        tips.setTextColor(getResources().getColor(R.color.green));
                    }
                }
            }
        });

        //检测平台名称文本长度的变化
        platformName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0){
                    platformTextImage.setVisibility(View.GONE);
                }else {
                    if (editable.toString().equals("baidu")||editable.toString().equals("百度")){
                        resourceID = R.drawable.baidu;
                        platformTextImage.setBackground(getApplicationContext().getResources().getDrawable(resourceID,null));
                        platformTextImage.setText("");
                        imageBgOrColorBg = 1;
                    }else{
                        Random random = new Random();
                        int randomColor = random.nextInt(10000000)%colorArray.length;
                        resourceID = colorArray[randomColor];
                        imageBgOrColorBg = 0;
                        String str = editable.toString();
                        String firstLetterOfEditable = str.substring(0,1);
                        platformTextImage.setText(firstLetterOfEditable);
                        platformTextImage.setBackgroundColor(getResources().getColor(resourceID));
                    }
                    platformTextImage.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}
