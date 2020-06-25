package com.tianze.mybrowser.db.entity;

import com.tianze.mybrowser.MyApplication;

import org.greenrobot.greendao.query.DeleteQuery;
import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

public class BookMarkMng {

    private static DaoSession sDaoSession = MyApplication.sDaoSession;

    //查找所有的书签数据
    public static List<BookMark> findAll() {
        return sDaoSession.loadAll(BookMark.class);
    }

    //新增书签
    public static boolean insertBookMark(BookMark bookMark) {
        return sDaoSession.insert(bookMark) > 0;
    }

    //更新书签
    public static void updateBookMark(BookMark bookMark) {
        sDaoSession.update(bookMark);
    }

    //通过url删除书签
    public static void deleteBookMark(String url) {
        QueryBuilder<BookMark> where = sDaoSession.queryBuilder(BookMark.class).where(BookMarkDao.Properties.MBookMarkUrl.eq(url));
        DeleteQuery<BookMark> bookMarkDeleteQuery = where.buildDelete();
        bookMarkDeleteQuery.executeDeleteWithoutDetachingEntities();
    }

    //删除书签
    public static void deleteBookMark(BookMark bookMark) {
        sDaoSession.delete(bookMark);
    }

    //删除所有书签
    public static void deleteAllBookMark() {
        sDaoSession.deleteAll(BookMark.class);
    }

    //判断书签是否存在
    public static boolean isBookMarkExist(String url) {
        QueryBuilder<BookMark> builder = sDaoSession.queryBuilder(BookMark.class);
        List<BookMark> list = builder.where(BookMarkDao.Properties.MBookMarkUrl.eq(url)).list();
        return list.size() > 0;
    }
}
