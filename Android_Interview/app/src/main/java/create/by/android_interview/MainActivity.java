package create.by.android_interview;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import create.by.android_interview.adapters.ViewPager2Adapter;
import create.by.android_interview.base.BaseFragment;
import create.by.android_interview.fragments.AndroidFragment;
import create.by.android_interview.fragments.JavaFragment;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.viewpager2)
    ViewPager2 viewpager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置白色状态栏和黑色字体
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility( View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new JavaFragment());
        fragments.add(new AndroidFragment());

        //tab的名称
        final String[] tabTitle = new String[]{
                "Android", "Java"
        };

        ViewPager2Adapter adapter = new ViewPager2Adapter(getSupportFragmentManager());
        adapter.setFragmentData(fragments);
        viewpager2.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewpager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(tabTitle[position]);
            }
        }).attach();

    }

}