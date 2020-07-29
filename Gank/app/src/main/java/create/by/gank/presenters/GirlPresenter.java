package create.by.gank.presenters;

import create.by.gank.base.BasePresenter;
import create.by.gank.view_callback.GirlCallback;

public interface GirlPresenter extends BasePresenter {

    void load();

    void loadMore();

    void refresh();
}
