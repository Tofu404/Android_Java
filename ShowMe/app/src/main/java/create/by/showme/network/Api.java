package create.by.showme.network;

import create.by.showme.beans.Categories;
import create.by.showme.beans.CategoriesContent;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("discovery/categories")
    Call<Categories> getCategories();

    @GET("discovery/{materialId}/{page}")
    Call<CategoriesContent> getCategoriesContent(@Path("materialId") int materialId,@Path("page") int page);
}
