package create.by.gank.network;

import create.by.gank.bean.GirlBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("data/category/Girl/type/Girl/page/${pageNum}/count/${countNum}")
    Call<GirlBean> girlInfo(@Path("pageNum") int pageNum,@Path("countNum") int countNum);

}
