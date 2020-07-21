package create.by.taobaounion.view_callback;

import create.by.taobaounion.modle.Categories;

public interface IHomeViewCallback {

    void onLoadFinish(Categories categories);

    void loadError();
}
