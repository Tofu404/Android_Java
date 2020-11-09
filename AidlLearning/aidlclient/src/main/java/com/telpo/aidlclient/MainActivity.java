package com.telpo.aidlclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.telpo.aidlservice.ICallback;
import com.telpo.aidlservice.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    private String infoStr = "";
    private IMyAidlInterface mIMyAidlInterface;

    ICallback.Stub mStub = new ICallback.Stub() {
        @Override
        public void succeed(String info) throws RemoteException {
            Log.e("nihao", "我是从服务器返回的信息: == > " + info);
        }

        @Override
        public void fail(String info) throws RemoteException {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindAidlService();
        final EditText info = findViewById(R.id.info);
        info.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                infoStr = s.toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        Button sendInfoToService = findViewById(R.id.send_to_service);
        sendInfoToService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mIMyAidlInterface.printLog(infoStr);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void bindAidlService() {
        Intent intent = new Intent();
        intent.setAction("com.telpo.aidlservice.MyService");
        intent.setPackage("com.telpo.aidlservice");
        ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mIMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                try {
                    mIMyAidlInterface.registCallBack(mStub);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                if (mIMyAidlInterface != null) {
                    try {
                        mIMyAidlInterface.unRegistCallBack(mStub);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
}
