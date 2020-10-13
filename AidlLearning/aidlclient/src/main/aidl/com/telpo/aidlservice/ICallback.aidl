package com.telpo.aidlservice;
interface ICallback {
    void succeed(String info);
    void fail(String info);
}
