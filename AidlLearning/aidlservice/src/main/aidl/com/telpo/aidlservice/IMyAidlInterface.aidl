package com.telpo.aidlservice;
import com.telpo.aidlservice.ICallback;
interface IMyAidlInterface {
    void printLog(String info);
    void registCallBack(ICallback callback);
    void unRegistCallBack(ICallback callback);
}
