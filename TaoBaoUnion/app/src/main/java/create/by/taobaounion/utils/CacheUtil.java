package create.by.taobaounion.utils;

import com.google.gson.Gson;

import create.by.taobaounion.modle.Categories;

public class CacheUtil {

    /**
     * 缓存分类信息
     * 将商品分类信息进行拼装，以进行缓存；
     * 缓存逻辑：缓存时间 + 分类信息，时间和分类信息
     */
    public static void cacheCategories(Categories categories) {
        String saveTime = String.valueOf(System.currentTimeMillis());
        Gson gson = new Gson();
        //Categories对象转换成json数据
        String toJson = gson.toJson(categories);
        //将保存时间和json数据拼接
        String buffer = saveTime + "AV=VA" + toJson;
        SpUtil.saveBySp("categoriesCache", buffer);
    }


}
