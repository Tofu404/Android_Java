package create.by.mylauncher;

import android.app.ActivityOptions;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LauncherAdapter.OnItemClickListener {

    private static final String TAG = "tianze";

    private static List<ResolveInfo> sAppsInfo = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //获取手机上安装的app列表
        getAppsInfo();
        init();
    }

    private void init() {
        GridView gridView = findViewById(R.id.grid_view);
        LauncherAdapter adapter = new LauncherAdapter(this);
        adapter.setOnItemClickListener(this);
        sAppsInfo.size();
        adapter.setAppData(sAppsInfo);
        gridView.setAdapter(adapter);
    }

    /**
     * 获取手机上的全部应用信息
     */
    private void getAppsInfo() {
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN, null);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        sAppsInfo = getPackageManager().queryIntentActivities(launcherIntent, 0);
    }

    /**
     * launcher要屏屏蔽返回键
     */
    @Override
    public void onBackPressed() {
    }


    /**
     * 点击app icon 进行跳转
     *
     * @param appInfo
     */
    @Override
    public void onItemClick(ResolveInfo appInfo) {
        if (appInfo != null) {
            //获取应用包名
            String packageName = appInfo.activityInfo.packageName;
            //获取应用的启动类名称
            String launcherClassName = appInfo.activityInfo.name;
            //获取生成app的特定标识
            ComponentName componentName = new ComponentName(packageName, launcherClassName);
            Intent intent = new Intent();
            intent.setComponent(componentName);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent, ActivityOptions.makeCustomAnimation(this, R.anim.task_open_enter, R.anim.no_anim).toBundle());
        }
    }
}