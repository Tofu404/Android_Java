package create.by.taobaounion.net_work;

import create.by.taobaounion.modle.Categories;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    /**
     * 获取商品分类
     * @return
     */
    @GET("discovery/categories")
    Call<Categories> loadCategories();
}
