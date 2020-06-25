package com.tianze.xiangting.beans;

import android.app.Activity;
import android.view.View;

public class MeItemInfo {

    private int itemType;

    private View mView;

    private Activity clickAction;

    public MeItemInfo(int itemType, View view, Activity clickAction) {
        this.itemType = itemType;
        mView = view;
        this.clickAction = clickAction;
    }

    public int getItemType() {
        return itemType;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public View getView() {
        return mView;
    }

    public void setView(View view) {
        mView = view;
    }

    public Activity getClickAction() {
        return clickAction;
    }

    public void setClickAction(Activity clickAction) {
        this.clickAction = clickAction;
    }
}
