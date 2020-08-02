package create.by.gank.activity;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import create.by.gank.R;
import create.by.gank.base.BaseFragment;
import create.by.gank.fragments.GanHuoFragment;
import create.by.gank.fragments.GirlFragment;
import create.by.gank.fragments.MeFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private Map<Integer, BaseFragment> mFragmentMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //用map将导航的id和fragment对应起来
        mFragmentMap = new HashMap<>();
        mFragmentMap.put(R.id.girl, new GirlFragment());
        mFragmentMap.put(R.id.ganhuo, new GanHuoFragment());
        mFragmentMap.put(R.id.me, new MeFragment());

        //将fragment添加到布局当中去
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction ft = sfm.beginTransaction();
        for (Integer integer : mFragmentMap.keySet()) {
            BaseFragment baseFragment = mFragmentMap.get(integer);
            assert baseFragment != null;
            ft.add(R.id.fragment_container, baseFragment);
            ft.hide(baseFragment);
        }
        ft.commit();
        //默认显的是girl页面
        showByItem(R.id.girl);

        //为item项添加点击事件
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showByItem(item.getItemId());
                return true;
            }
        });
    }

    private void showByItem(int id) {
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction ft = sfm.beginTransaction();
        for (Integer integer : mFragmentMap.keySet()) {
            BaseFragment baseFragment = mFragmentMap.get(integer);
            assert baseFragment != null : "fragment 为空";
            if (id == integer) {
                ft.show(baseFragment);
            } else {
                ft.hide(baseFragment);
            }
        }
        ft.commit();
    }
}