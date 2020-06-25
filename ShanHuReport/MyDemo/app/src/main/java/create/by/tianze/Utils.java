package create.by.tianze;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

public class Utils {

    /**
     * 申请权限
     * 例如: Manifest.permission.WRITE_EXTERNAL_STORAGE
     *  **/
    public static void requsetPermission(Activity activity, String[] permissions) {
        try {
            if (permissions == null) {
                return;
            }
            int len = permissions.length;
            if (len > 0) {
                for (int i = 0; i < len; i++) {
                    // 使用兼容库，无需判断系统版本
                    int hasPermission = ContextCompat.checkSelfPermission(activity.getApplicationContext(),
                            permissions[i]);
                    if (hasPermission == PackageManager.PERMISSION_GRANTED) {
                        // 拥有权限，执行操作
                    } else {
                        // 没有权限，向用户请求权限
                        ActivityCompat.requestPermissions(activity, permissions, 200);
                    }
                }
            }
        } catch (Throwable e) {
        }
    }
}
