package create.by.taobaounion;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

public class MyApplication extends Application {

    private static Context sContext;
    public static final String TAG = "tianze";

    @SuppressLint("HandlerLeak")
    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }

    public static Context getAppContext() {
        if (sContext != null) {
            return sContext;
        }
        return null;
    }

}
