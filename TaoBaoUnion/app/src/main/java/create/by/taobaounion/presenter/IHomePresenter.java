package create.by.taobaounion.presenter;

import create.by.taobaounion.view_callback.IHomeViewCallback;

public interface IHomePresenter {

    void loadCategories();

    void registerViewCallBack(IHomeViewCallback viewCallback);

    void nuRegisterViewCallBack();
}
