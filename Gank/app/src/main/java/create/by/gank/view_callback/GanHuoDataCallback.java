package create.by.gank.view_callback;

import java.util.List;

import create.by.gank.base.BaseCallback;
import create.by.gank.bean.GanHuoBean;

public interface GanHuoDataCallback extends BaseCallback {

    default void loaded(List<GanHuoBean.DataBean> data){}

    void loadMore(List<GanHuoBean.DataBean> data);

    void loadError();

    void loadFinish();
}
