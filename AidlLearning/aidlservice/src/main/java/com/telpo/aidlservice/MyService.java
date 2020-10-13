package com.telpo.aidlservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

public class MyService extends Service {

    private RemoteCallbackList<ICallback> mCallbackList = new RemoteCallbackList<>();

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new IMyAidlInterface.Stub() {
            @Override
            public void printLog(String info) throws RemoteException {
                Log.e("nihao", "收到的信息 == > " + info);
                mCallbackList.beginBroadcast();
                for (int i = 0; i < mCallbackList.getRegisteredCallbackCount(); i++) {
                    mCallbackList.getBroadcastItem(i).succeed(info);
                }
                mCallbackList.finishBroadcast();
            }

            @Override
            public void registCallBack(ICallback callback) throws RemoteException {
                Log.e("nihao", "registCallBack: 注册回调");
                mCallbackList.register(callback);
            }

            @Override
            public void unRegistCallBack(ICallback callback) throws RemoteException {
                Log.e("nihao", "unRegistCallBack: quxiaozhuce");
            }
        };
    }
}
