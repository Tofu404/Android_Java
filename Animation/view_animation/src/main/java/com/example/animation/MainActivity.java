package com.example.animation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 这个demo是用来是演示平移、旋转、缩放、渐变这四种的视图动画的。
     * @param savedInstanceState
     */

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button translation = findViewById(R.id.translation);
        Button scale = findViewById(R.id.scale);
        Button rotate = findViewById(R.id.rotate);
        Button alpha = findViewById(R.id.alpha);
        Button all = findViewById(R.id.all);
        translation.setOnClickListener(this);
        scale.setOnClickListener(this);
        rotate.setOnClickListener(this);
        all.setOnClickListener(this);
        alpha.setOnClickListener(this);
        imageView = findViewById(R.id.image);
    }

    private void translateAnimation(){
        /**
         * 视图动画中的平移动画（TranslateAnimation）中有三个构造函数他们分别是
         *
         * TranslateAnimation(Context context, AttributeSet attrs)
         * Constructor used when a TranslateAnimation is loaded from a resource.
         *
         * 参数含义：
         *          context：这是是指动画要运行的上下文对象
         *
         *          attrs：这是参数现在还不知道是用来干嘛的
         *
         * TranslateAnimation(float fromXDelta, float toXDelta, float fromYDelta, float toYDelta)
         * Constructor to use when building a TranslateAnimation from code
         *
         * 参数含义：
         *          fromXDelta：这个参数是指动画开始平移的X轴的位置
         *
         *          toXDelta：这个参数是指动画结束平移的X轴上的位置
         *
         *          fromYDelta：这个参数是指动画开始平移的Y轴上的位置
         *
         *          toYDelta：这个参数是指动画结束平移的Y轴上的位置
         *
         *
         * TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue)
         * Constructor to use when building a TranslateAnimation from code
         *
         * 参数含义：
         *          fromXType：这个参数的含义是指开始的X轴的参照位置，有三个固定值（Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF,or Animation.RELATIVE_TO_PARENT）
         *
         *          fromXValue：这个参数是指动画开始的时候X轴的位置的值
         *
         *          toXType：这个参数的含义是值结束的X轴的参照位置，有三个固定值（Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF,or Animation.RELATIVE_TO_PARENT）
         *
         *          toXValue：这个参数是指动画的结束的时候X轴的位置的值
         *
         *          fromYType：这个参数含义是指Y开始的时候的参照位置，有三个固定值（Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF,or Animation.RELATIVE_TO_PARENT）
         *
         *          fromYValue：这个参数的含义是指Y轴开始的时候的参照位置的值
         *
         *          toYType：这个参数含义是指Y轴结束的时候的参照位置，有三个固定值（Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF,or Animation.RELATIVE_TO_PARENT）
         *
         *          toYValue：这个参数的含义是指Y轴结束的时候的参照位置的值
         *
         * 这个三个构造函数中，第二个函数是最常用的构造函数，所以我们的例子用第二个构造函数开始使用
         */

        TranslateAnimation translateAnimation  = new TranslateAnimation(0f, 200f, 0f, 0f);
        translateAnimation.setDuration(1000);
        translateAnimation.setFillAfter(true);  //这个属性是设定，动画发生平移后要不要恢复原来的位置，要是true的话，view就在平移后的位置
        imageView.startAnimation(translateAnimation);
    }

    private void scaleAnimation(){
        /**
         *视图动画中缩放动画（scaleAnimation）中有四个构造函数他们分别是：
         *
         * ScaleAnimation(Context context, AttributeSet attrs)
         * Constructor used when a ScaleAnimation is loaded from a resource.
         *
         * 参数含义：
         *          context：动画运行的上下文对象
         *
         *          attrs：这函数不知到时用来干嘛的
         *
         * ScaleAnimation(float fromX, float toX, float fromY, float toY)
         * Constructor to use when building a ScaleAnimation from code
         *
         * 参数含义：
         *          fromX：在X轴上的缩放尺寸的大小（原值为 1）
         *
         *          toX：：在X轴上的缩放尺寸的大小
         *
         *          fromY：在Y轴上的缩放尺寸的大小（原值为 1）
         *
         *          toY：在Y轴上的缩放尺寸的大小
         *
         *
         * ScaleAnimation(float fromX, float toX, float fromY, float toY, float pivotX, float pivotY)
         * Constructor to use when building a ScaleAnimation from code
         *
         * 参数含义：
         *          fromX：在X轴上的缩放尺寸的大小（原值为 1）
         *
         *          toX：在X轴上的缩放尺寸的大小
         *
         *          fromY：在Y轴上的缩放尺寸的大小（原值为 1）
         *
         *          toY：在Y轴上的缩放尺寸的大小
         *
         *          pivotX：缩放中心点的X轴坐标
         *
         *          pivotY：缩放中心点的Y轴坐标
         *
         *          选定了中心点之后，中心点不会被缩放，以中心点开始四周开始缩放
         *
         * ScaleAnimation(float fromX, float toX, float fromY, float toY, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
         * Constructor to use when building a ScaleAnimation from code
         *
         * 参数含义：
         *          fromX：在X轴上的缩放尺寸的大小（原值为 1）
         *
         *          toX：在X轴上的缩放尺寸的大小
         *
         *          fromY：在Y轴上的缩放尺寸的大小（原值为 1）
         *
         *          toY：在Y轴上的缩放尺寸的大小
         *
         *          pivotXType：指定中心点X轴的中心位置，这个参数有三个值分别是（Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT）
         *
         *          pivotXValue：被缩放对象所在点的X坐标，指定为一个绝对值，其中0为左边缘。(当对象改变大小时，此点保持不变。)如果pivotXType是绝对的，则此值可以是绝对值，否则可以是百分比(其中1.0是100%)。
         *
         *          pivotYType：指定中心点Y轴的中心位置，这个参数有三个值分别是（Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT）
         *
         *          pivotYValue：被缩放对象所在点的坐标，指定为一个绝对值，其中0为左边缘。(当对象改变大小时，此点保持不变。)如果pivotXYType是绝对的，则此值可以是绝对值，否则可以是百分比(其中1.0是100%)。
         */
        ScaleAnimation scaleAnimation = new ScaleAnimation(1, 2, 1, 2,50,50);
        scaleAnimation.setDuration(1000);
        //scaleAnimation.setFillAfter(true);
        imageView.startAnimation(scaleAnimation);
    }

    private void alphaAnimation(){
        /**
         * 视图动画中旋转动画（AlphaAnimation）,这个有两个构造动画分别是：
         *
         * AlphaAnimation(Context context, AttributeSet attrs)
         * Constructor used when an AlphaAnimation is loaded from a resource.
         *
         * 参数含义：
         *          context：运行动画的上下文对象
         *
         *          attrs：这个是XML动画文件
         *
         * AlphaAnimation(float fromAlpha, float toAlpha)
         * Constructor to use when building an AlphaAnimation from code
         *
         * 参数含义：
         *          fromAlpha：这个参数是渐变开始的透明度
         *
         *          toAlpha：这个参数变化后的透明度的参数
         *
         *          注意：“0” 是完全透明，“1”是完全不透明
         *
         *
         */
        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setDuration(1000);
        alphaAnimation.setFillAfter(true);
        imageView.startAnimation(alphaAnimation);
    }

    private void rotateAnimation(){
        /**
         * 视图动画中旋转动画（RotateAnimation）,这个有两个构造动画分别是：
         *
         * RotateAnimation(Context context, AttributeSet attrs)
         * Constructor used when a RotateAnimation is loaded from a resource.
         *
         * 参数含义：
         *          context：这个是上下文对象，运行的环境
         *
         *          attrs：这是XML的动画文件
         *
         * RotateAnimation(float fromDegrees, float toDegrees)
         * Constructor to use when building a RotateAnimation from code.
         *
         * 参数含义：
         *          fromDegrees：这个参数是指开始旋转的角度
         *
         *          toDegrees：这个参数是指旋转之后的角度
         *
         * RotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY)
         * Constructor to use when building a RotateAnimation from code
         *
         * 参数含义：
         *          fromDegrees：这个参数是指开始旋转的角度
         *
         *          toDegrees：这个参数是指旋转之后的角度
         *
         *          pivotX：这个是参数旋转中心点的X轴的值
         *
         *          pivotY：这个是参数旋转中心点的Y轴的值
         *
         * RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue)
         * Constructor to use when building a RotateAnimation from code
         *
         * 参数含义：
         *          fromDegrees：这个参数是指开始旋转的角度
         *
         *          toDegrees：这个参数是指旋转之后的角度
         *
         *          pivotXType：这个值是指中心点X轴的相对位置，这个参数有三个值（Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT）
         *
         *          pivotXValue：这个是参数旋转中心点的X轴的值
         *
         *          pivotYType：这个值是指中心点Y轴的相对位置，这个参数有三个值（Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT）
         *
         *          pivotYValue：这个是参数旋转中心点的Y轴的值
         *
         *          如果pivotXType=Animation.ABSOLUTE，则此参数为旋转中心在屏幕上X轴的值；
         *
         *          如果pivotXType=Animation.RELATIVE_TO_PARENT，则参数pivotXValue为旋转中心在父控件水平位置百分比，如0.5表示在父控件水平方向中间位置；
         *
         *          如果pivotXType=Animation.RELATIVE_TO_SELF，则参数pivotXValue为旋转中心在控件自身水平位置百分比，如果X和Y的Value都设置为0.5，则该控件以自身中心旋转。
         */
            RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,0.5f);
            //RotateAnimation rotateAnimation = new RotateAnimation(0, 90, 0, 0);
            rotateAnimation.setDuration(5000);
            //rotateAnimation.setFillAfter(true);
            imageView.startAnimation(rotateAnimation);

    }

    private void allAnimation(){
        /**
         * ////////////////////////////////////////////////////////////////////////////////////////////////////
         *
         *      Animation.ABSOLUTE, Animation.RELATIVE_TO_SELF, or Animation.RELATIVE_TO_PARENT
         *
         *      Animation.ABSOLUTE：这个参数就是一个绝对值，要是平移的话value值是多少view就会平移多少
         *
         *      Animation.RELATIVE_TO_SELF：这个参数是一个相对值，当value值为”1“的时候时候表示平移的距离是view自身的X、Y方向的一倍，为”0.5“的时候时候表示平移的距离是view自身的X、Y方向的一半
         *
         *      Animation.RELATIVE_TO_PARENT：这个参数是一个相对值，当value值为”1“的时候时候表示平移的距离是屏幕大小自身的X、Y方向的一倍，为”0.5“的时候时候表示平移的距离是屏幕大小自身的X、Y方向的一半
         *
         *
         * ////////////////////////////////////////////////////////////////////////////////////////////////////
         */

        //TranslateAnimation translateAnimation = new TranslateAnimation(0, 500, 0, 800);
        TranslateAnimation translateAnimation = new TranslateAnimation(Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 100f, Animation.ABSOLUTE, 0, Animation.ABSOLUTE, 0);
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0, 1);
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(translateAnimation);
        set.addAnimation(rotateAnimation);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.setDuration(2000);
        set.setFillAfter(true);
        imageView.startAnimation(set);

        /**
         * 使用在一个view当中使用多个动画的方法。
         * 通过AnimationSet动画集合将想要使用的动画，都通过addAnimation（）方法添加在AnimationSet当中去
         * 在AnimationSet当中可以统一设置动画的属性，也可各个动画在添加到set中的时候逐一添加
         */

    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.all:
                allAnimation();
                break;
            case R.id.translation:
                translateAnimation();
                break;
            case R.id.scale:
                scaleAnimation();
                break;
            case R.id.alpha:
                alphaAnimation();
                break;
            case R.id.rotate:
                rotateAnimation();
                break;
            default:
                Toast.makeText(MainActivity.this, "没有该按钮的信息！", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
