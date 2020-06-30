package create.by.shakimganimation;

import android.animation.Animator;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtn1;
    private Button mBtn2;
    private Button mBtn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtn1 = findViewById(R.id.shakeing_1);
        mBtn2 = findViewById(R.id.shakeing_2);
        mBtn3 = findViewById(R.id.button3);


        mBtn1.setOnClickListener(this);
        mBtn2.setOnClickListener(this);
        mBtn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shakeing_1:
                ObjectAnimator animator1 = shaking1Animation(mBtn1, 2);
                animator1.setDuration(1300);
                animator1.setRepeatCount(-1);
                animator1.start();
                break;
            case R.id.shakeing_2:
                ObjectAnimator animator2 = shaking2Animation(mBtn2);
                animator2.setDuration(1500);
                animator2.setRepeatCount(-1);
                animator2.start();
                break;
            case R.id.button3:
                ObjectAnimator animator3 = sinkAnimation(mBtn3);
                animator3.setDuration(500);
                animator3.start();
                break;
        }
    }

    //旋转抖动动画
    private ObjectAnimator shaking1Animation(View view, int shakingRange) {

        PropertyValuesHolder rotationHolder = PropertyValuesHolder.ofKeyframe(View.ROTATION,
                Keyframe.ofFloat(0.0f, -2 * shakingRange),
                Keyframe.ofFloat(0.1f, 2 * shakingRange),
                Keyframe.ofFloat(0.2f, -2 * shakingRange),
                Keyframe.ofFloat(0.3f, 2 * shakingRange),
                Keyframe.ofFloat(0.4f, -2 * shakingRange),
                Keyframe.ofFloat(0.5f, 2 * shakingRange),
                Keyframe.ofFloat(0.6f, -2 * shakingRange),
                Keyframe.ofFloat(0.7f, 2 * shakingRange),
                Keyframe.ofFloat(0.8f, -2 * shakingRange),
                Keyframe.ofFloat(0.9f, 2 * shakingRange),
                Keyframe.ofFloat(1.0f, -2 * shakingRange)
        );

        PropertyValuesHolder scale_xHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0.0f, 1.0f),
                Keyframe.ofFloat(0.1f, 1.01f),
                Keyframe.ofFloat(0.2f, 1.01f),
                Keyframe.ofFloat(0.3f, 1.02f),
                Keyframe.ofFloat(0.4f, 1.03f),
                Keyframe.ofFloat(0.5f, 1.03f),
                Keyframe.ofFloat(0.6f, 1.02f),
                Keyframe.ofFloat(0.7f, 1.02f),
                Keyframe.ofFloat(0.8f, 1.01f),
                Keyframe.ofFloat(0.9f, 1.01f),
                Keyframe.ofFloat(1.0f, 1.0f)
        );

        PropertyValuesHolder scale_yHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0.0f, 1.0f),
                Keyframe.ofFloat(0.1f, 1.01f),
                Keyframe.ofFloat(0.2f, 1.01f),
                Keyframe.ofFloat(0.3f, 1.02f),
                Keyframe.ofFloat(0.4f, 1.03f),
                Keyframe.ofFloat(0.5f, 1.03f),
                Keyframe.ofFloat(0.6f, 1.02f),
                Keyframe.ofFloat(0.7f, 1.02f),
                Keyframe.ofFloat(0.8f, 1.01f),
                Keyframe.ofFloat(0.9f, 1.01f),
                Keyframe.ofFloat(1.0f, 1.0f)
        );
        return ObjectAnimator.ofPropertyValuesHolder(view, rotationHolder,scale_xHolder,scale_yHolder);
    }

    //左右抖动动画
    private ObjectAnimator shaking2Animation(View view) {

        PropertyValuesHolder translation_xHolder = PropertyValuesHolder.ofKeyframe(View.TRANSLATION_X,
                Keyframe.ofFloat(0.0f,10),
                Keyframe.ofFloat(0.1f,-10),
                Keyframe.ofFloat(0.2f,15),
                Keyframe.ofFloat(0.3f,-15),
                Keyframe.ofFloat(0.4f,20),
                Keyframe.ofFloat(0.5f,-20),
                Keyframe.ofFloat(0.6f,20),
                Keyframe.ofFloat(0.7f,-20),
                Keyframe.ofFloat(0.8f,15),
                Keyframe.ofFloat(0.9f,-15),
                Keyframe.ofFloat(1.0f,10)

        );
        return ObjectAnimator.ofPropertyValuesHolder(view,translation_xHolder);
    }

    //下沉动画
    private ObjectAnimator sinkAnimation(View view){

        PropertyValuesHolder scale_xHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_X,
                Keyframe.ofFloat(0.0f, 1.0f),
                Keyframe.ofFloat(0.1f, 0.99f),
                Keyframe.ofFloat(0.2f, 0.98f),
                Keyframe.ofFloat(0.3f, 0.97f),
                Keyframe.ofFloat(0.4f, 0.96f),
                Keyframe.ofFloat(0.5f, 0.95f),
                Keyframe.ofFloat(0.6f, 0.96f),
                Keyframe.ofFloat(0.7f, 0.97f),
                Keyframe.ofFloat(0.8f, 0.98f),
                Keyframe.ofFloat(0.9f, 0.99f),
                Keyframe.ofFloat(1.0f, 1.0f)
        );

        PropertyValuesHolder scale_yHolder = PropertyValuesHolder.ofKeyframe(View.SCALE_Y,
                Keyframe.ofFloat(0.0f, 1.0f),
                Keyframe.ofFloat(0.1f, 0.99f),
                Keyframe.ofFloat(0.2f, 0.98f),
                Keyframe.ofFloat(0.3f, 0.97f),
                Keyframe.ofFloat(0.4f, 0.96f),
                Keyframe.ofFloat(0.5f, 0.95f),
                Keyframe.ofFloat(0.6f, 0.96f),
                Keyframe.ofFloat(0.7f, 0.97f),
                Keyframe.ofFloat(0.8f, 0.98f),
                Keyframe.ofFloat(0.9f, 0.99f),
                Keyframe.ofFloat(1.0f, 1.0f)
        );

        return ObjectAnimator.ofPropertyValuesHolder(view,scale_yHolder,scale_xHolder);
    }

}