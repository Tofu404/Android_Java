package create.by.taobaounion.ui.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import create.by.taobaounion.R;
import create.by.taobaounion.ui.fragments.HomeFragment;
import create.by.taobaounion.ui.fragments.MeFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView bottomNavigationView;
    private Unbinder mBind;
    private Map<Integer,Fragment> mFragmentMap;
    private FragmentManager mSupportFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        init();
    }

    private void init() {

        /**
         * 初始化fragment列表
         */
        mFragmentMap = new HashMap<>();
        mFragmentMap.put(R.id.home_item,new HomeFragment());
        mFragmentMap.put(R.id.me_item,new MeFragment());

        /**
         * 将fragment添加到容器当中
         */
        mSupportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = mSupportFragmentManager.beginTransaction();
        for (Integer integer : mFragmentMap.keySet()) {
            fragmentTransaction.add(R.id.fragment_container, Objects.requireNonNull(mFragmentMap.get(integer)));
        }
        //默认显示首页
        showFragmentById(R.id.home_item);
        fragmentTransaction.commit();

        /**
         * 为item项添加监听器
         */
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_item:
                        showFragmentById(R.id.home_item);
                        break;
                    case R.id.me_item:
                        showFragmentById(R.id.me_item);
                        break;
                }
                return true;
            }
        });
    }

    private void showFragmentById(int id){
        FragmentTransaction ft = mSupportFragmentManager.beginTransaction();
        for (Integer itemId : mFragmentMap.keySet()) {
            Fragment fragment = mFragmentMap.get(itemId);
            assert fragment != null;
            if (id == itemId){
                ft.show(fragment);
            }else {
                ft.hide(fragment);
            }
        }
        ft.commit();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBind.unbind();
    }
}