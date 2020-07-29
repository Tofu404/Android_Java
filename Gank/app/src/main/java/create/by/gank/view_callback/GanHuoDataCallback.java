package create.by.gank.view_callback;

import java.util.List;

import create.by.gank.base.BaseCallback;
import create.by.gank.bean.GanHuoCategory;

public interface GanHuoDataCallback extends BaseCallback {

    void loaded(List<GanHuoCategory.DataBean> data);

    void loadMore();

    void loadError();
}
