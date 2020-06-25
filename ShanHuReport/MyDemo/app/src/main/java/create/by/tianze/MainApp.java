package create.by.tianze;

import android.app.Application;

import com.pw.WinLib;

public class MainApp extends Application {

    //private static String APPKEY = "ERNDQ6YND2J13CFW2ED4AJEP";
    private static String APPKEY = BuildConfig.APPKEY;

    @Override
    public void onCreate() {
        super.onCreate();
        WinLib.setShanHuTestMode(BuildConfig.TEST_MODE);
        WinLib.init(this, APPKEY, "test");
    }
}
