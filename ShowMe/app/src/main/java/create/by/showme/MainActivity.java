package create.by.showme;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import create.by.showme.fragments.HomeFragment;
import create.by.showme.fragments.MeFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.fragment_container)
    FrameLayout mFragmentContainer;
    @BindView(R.id.bottomNavigationView)
    BottomNavigationView mBottomNavigationView;
    private Unbinder mBind;

    private HomeFragment mHomeFragment = null;
    private MeFragment mMeFragment = null;
    private Map<Integer, Fragment> mBindFragmentWithId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_main);
        mBind = ButterKnife.bind(this);
        init();
    }

    private void init() {

        mHomeFragment = new HomeFragment();
        mMeFragment = new MeFragment();

        //将navigation的item的id和fragment关联起来
        mBindFragmentWithId = new HashMap<>();
        mBindFragmentWithId.put(R.id.home, mHomeFragment);
        mBindFragmentWithId.put(R.id.me, mMeFragment);

        final FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        for (Integer id : mBindFragmentWithId.keySet()) {
            Fragment fragment = mBindFragmentWithId.get(id);
            assert fragment != null;
            ft.add(R.id.fragment_container, fragment);
            ft.hide(fragment);
        }
        ft.commit();
        //默认显示首页
        showFragmentById(R.id.home);

        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                showFragmentById(item.getItemId());
                return true;
            }
        });
    }

    private void showFragmentById(int id) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        for (Integer item : mBindFragmentWithId.keySet()) {
            Fragment fragment = mBindFragmentWithId.get(item);
            assert fragment!=null;
            if (id== item){
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