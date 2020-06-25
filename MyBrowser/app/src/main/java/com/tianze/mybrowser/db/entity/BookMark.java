package com.tianze.mybrowser.db.entity;

import android.content.Context;
import android.content.Intent;
import android.os.Environment;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Entity
public class BookMark {

    @Id(autoincrement = true)
    private Long id;
    private String mTitle;
    private String mBookMarkUrl;
    @Generated(hash = 543488834)
    public BookMark(Long id, String mTitle, String mBookMarkUrl) {
        this.id = id;
        this.mTitle = mTitle;
        this.mBookMarkUrl = mBookMarkUrl;
    }
    @Generated(hash = 1704575762)
    public BookMark() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getMTitle() {
        return this.mTitle;
    }
    public void setMTitle(String mTitle) {
        this.mTitle = mTitle;
    }
    public String getMBookMarkUrl() {
        return this.mBookMarkUrl;
    }
    public void setMBookMarkUrl(String mBookMarkUrl) {
        this.mBookMarkUrl = mBookMarkUrl;
    }

}
