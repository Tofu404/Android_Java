package create.by.mylauncher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "tianze";

    private List<ResolveInfo> mApps = new ArrayList<>();

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
        getApps();
        init();
    }

    private void init() {
        GridView gridView = findViewById(R.id.grid_view);
        LauncherAdapter adapter = new LauncherAdapter(this);
        mApps.size();
        adapter.setAppData(mApps);
        gridView.setAdapter(adapter);
    }

    private void getApps() {
        Intent launcherIntent = new Intent(Intent.ACTION_MAIN,null);
        launcherIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        mApps = getPackageManager().queryIntentActivities(launcherIntent, 0);

        //打印包名的信息
        for (ResolveInfo app : mApps) {
            Log.e(TAG, "getApps: app的包名 == > "+ app.activityInfo.packageName );
            Log.e(TAG, "getApps: app的icon == > "+ app.loadIcon(getPackageManager()) );
            Log.e(TAG, "getApps: app的应用名称 == > "+ app.loadLabel(getPackageManager()).toString() );
        }
    }

    /**
     * launcher要屏屏蔽返回键
     */
    @Override
    public void onBackPressed() {
    }
}