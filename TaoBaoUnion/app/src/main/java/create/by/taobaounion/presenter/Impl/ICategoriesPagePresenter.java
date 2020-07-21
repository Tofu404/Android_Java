package create.by.taobaounion.presenter.Impl;

public interface ICategoriesPagePresenter {
    /**
     * 加载分类数据详情
     * @param materialId
     */
    void loadMaterial(int materialId);

    /**
     * 加载错误
     */
    void loadError();

    /**
     * 加载数据为空
     */
    void loadEmpty();

    /**
     * 下拉刷新
     * @param materialId
     */
    void pullDownLoadMore(int materialId);

    /**
     * 上拉刷新
     * @param materialId
     * @param page
     */
    void pullUpLoadMore(int materialId, int page);
}
