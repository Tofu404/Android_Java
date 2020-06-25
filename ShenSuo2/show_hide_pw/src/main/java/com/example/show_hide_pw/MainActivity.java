package com.example.show_hide_pw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;


public class MainActivity extends Activity {


    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.nihao);
        ToggleButton toggleButton = findViewById(R.id.test);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editText.setSelection(getTV(editText).length());
                }else {
                    editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editText.setSelection(getTV(editText).length());
                }
            }
        });
    }

    public String getTV(TextView tv) {
        return tv == null ? "" : tv.getText().toString();
    }

}
