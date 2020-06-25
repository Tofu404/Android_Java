package com.tianze.xiangting.utils;

public class Constants {

    /*Fragment数量*/
    public static final int FRAGMENT_NUMBER = 3;

    /*猜你喜欢每次返回的数据条数*/
    public static final String LIKE_COUNT = "50";

    /*获取专辑音轨的页数*/
    public static final String TRACK_PAGE = "1";

    /*背景颜色变化时间*/
    public static final int BG_CHANGE_DURATION = 800;

    public static final String HOT_WORLD_COUNT = "10";

    public static final String PAGE_SIZE = "20";


    //数据库字段
    public static final String DB_NAME = "mydb.db";
    public static final int DB_VERSION = 1;

    //订阅数据表属性
    public static final String MY_TB_NAME = "tb_subscription";
    public static final String MY_ID = "_id";
    public static final String MY_COVER_URL = "coverUrl";
    public static final String MY_TITLE = "title";
    public static final String MY_DESCRIPTION = "description";
    public static final String MY_TRACKS_COUNT = "tracksCount";
    public static final String MY_PLAY_COUNT = "playCount";
    public static final String MY_AUTHOR_NAME = "authorName";
    public static final String MY_ALBUM_ID = "albumId";

    //历史记录表属性
    public static final String HIS_TB_NAME = "tb_history";
    public static final String HIS_ID = "_id";
    public static final String HIS_TRACK_ID = "track_id";
    public static final String HIS_TRACK_TITLE = "track_title";
    public static final String HIS_TRACK_PLAY_COUNT = "track_play_count";
    public static final String HIS_DURATION = "track_duration";
    public static final String HIS_UPDATE_TIME = "update_time";
    public static final String HIS_AUTHOR = "author";
    public static final String HIS_TRACK_COVER = "track_cover";
    public static final String HIS_TRACK_KIND = "kind";
    public static final String HIS_TRACK_IS_PAID = "isPaid";
    public static final String HIS_TRACK_IS_FREE = "isFree";

    /**
     * Me页面
     */
    public static final int HEADER_ITEM = 0;
    public static final int VERSION_ITEM = 1;
    public static final int ABOUT_APP_ITEM = 2;
    public static final int CUT_OFF_RULE = 3;
}
