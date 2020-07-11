package create.by.myview;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import create.by.myslipview.MySlipView;

public class MainActivity extends AppCompatActivity implements MySlipView.MyOnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MySlipView byId = findViewById(R.id.my_slip_view);
        byId.setMyOnClickListener(this);
    }

    @Override
    public void onReadClick() {
        Toast.makeText(this, "点击了已读", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTopClick() {
        Toast.makeText(this, "点击了顶置", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick() {
        Toast.makeText(this, "点击了删除", Toast.LENGTH_SHORT).show();
    }
}