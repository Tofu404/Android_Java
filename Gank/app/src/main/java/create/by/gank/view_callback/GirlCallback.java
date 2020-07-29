package create.by.gank.view_callback;

import java.util.List;

import create.by.gank.base.BaseCallback;
import create.by.gank.bean.GirlBean;

public interface GirlCallback extends BaseCallback {

    void loaded(List<GirlBean.DataBean> data);

    void loadError();

    void loadEmpty();
}
