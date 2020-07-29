package create.by.gank.presenters;

import create.by.gank.base.BasePresenter;

public interface GanHuoDataPresenter extends BasePresenter {

    void load(String type,int pageNum,int countNum);

    void loadMore();

}
