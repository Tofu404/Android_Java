package com.tianze.xiangting.base;

public interface IBasePresenter<T> {

    /**
     * 注册接口回调
     */
    void registerViewCallback(T t);

    /**
     * 注销回调接口
     */
    void unregisteredViewCallback(T t);
}
