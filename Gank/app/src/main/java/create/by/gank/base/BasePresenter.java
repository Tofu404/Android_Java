package create.by.gank.base;

import create.by.gank.view_callback.GirlCallback;

public interface BasePresenter {

    void registerCallback(BaseCallback callback);

    void nuRegisterCallback();
}
