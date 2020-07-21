package create.by.taobaounion.presenter.Impl;

import android.util.Log;

import com.google.gson.Gson;

import java.net.HttpURLConnection;

import create.by.taobaounion.MyApplication;
import create.by.taobaounion.modle.Categories;
import create.by.taobaounion.net_work.Api;
import create.by.taobaounion.net_work.RetrofitBuilder;
import create.by.taobaounion.presenter.IHomePresenter;
import create.by.taobaounion.view_callback.IHomeViewCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements IHomePresenter {

    private IHomeViewCallback mHomeViewCallback = null;
    private Gson mGson = new Gson();

    @Override
    public void loadCategories() {
        Api api = RetrofitBuilder.getApi();
        api.loadCategories().enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (mHomeViewCallback != null) {
                    if (response.code() == HttpURLConnection.HTTP_OK) {
                        Categories categories = response.body();
                        if (categories != null && categories.getData().size() > 0) {
                            mHomeViewCallback.onLoadFinish(categories);
                        }else {
                            mHomeViewCallback.loadError();
                        }
                    } else {
                        mHomeViewCallback.loadError();
                    }
                }
            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Log.e(MyApplication.TAG, "onFailure: 加载分类信息失败 == > "+t.getMessage() );
                if (mHomeViewCallback != null) {
                    mHomeViewCallback.loadError();
                }
            }
        });
    }

    @Override
    public void registerViewCallBack(IHomeViewCallback viewCallback) {
        if (viewCallback != null) {
            mHomeViewCallback = viewCallback;
        }
    }

    @Override
    public void nuRegisterViewCallBack() {
        mHomeViewCallback = null;
    }

}
