package create.by.gank.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

import java.net.HttpURLConnection;
import java.net.URL;

public class UrlUtil {

    public static String realUrl;

    @SuppressLint("HandlerLeak")
    private static Handler sHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.obj != null) {
                realUrl = (String) msg.obj;
            }
        }
    };

    public static void getRealUrl(String oldUrl){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL url = new URL(oldUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setInstanceFollowRedirects(false);
                    int responseCode = connection.getResponseCode();
                    String reallyUrl ="";
                    if (302 == responseCode){
                        reallyUrl = connection.getHeaderField("Location");
                    }

                    if (reallyUrl!=null&&!reallyUrl.equals("")) {
                        Message message = new Message();
                        message.obj = reallyUrl;
                        sHandler.sendMessage(message);
                    }
                    connection.disconnect();
                }catch (Exception e){

                }
            }
        }).start();
    };
}
