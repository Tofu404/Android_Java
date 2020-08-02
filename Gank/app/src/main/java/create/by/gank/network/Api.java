package create.by.gank.network;

import create.by.gank.bean.ArticleBean;
import create.by.gank.bean.GanHuoBean;
import create.by.gank.bean.GanHuoCategory;
import create.by.gank.bean.GirlBean;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    /**
     * 获取妹子信息
     * @param pageNum
     * @param countNum
     * @return
     */
    @GET("data/category/Girl/type/Girl/page/{pageNum}/count/{countNum}")
    Call<GirlBean> girlInfo(@Path("pageNum") int pageNum,@Path("countNum") int countNum);

    /**
     * 获取干货分类
     * @return
     */
    @GET("categories/GanHuo")
    Call<GanHuoCategory> getGanHuoCategoryData();


    /**
     * 获取干货分类内容
     *
     * @return
     */
    @GET("data/category/GanHuo/type/{type}/page/{pageNum}/count/{countNum}")
    Call<GanHuoBean> getGanHuoCategoryData(@Path("type") String type, @Path("pageNum") int pageNum, @Path("countNum") int countNum);


    @GET("post/{post_id}")
    Call<ArticleBean> getGanHuoArticle(@Path("post_id") String id);

}
