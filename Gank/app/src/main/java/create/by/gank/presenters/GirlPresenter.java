package create.by.gank.presenters;

import create.by.gank.base.BasePresenter;

public interface GirlPresenter extends BasePresenter {

    void load(int currentPage, int dataNum);

    void loadMore(int pageNum,int countNum);

    void refresh(int currentPage, int dataNum);
}
