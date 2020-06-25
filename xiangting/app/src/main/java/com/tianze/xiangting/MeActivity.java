package com.tianze.xiangting;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.xiangting.adapters.MeItemAdapter;
import com.tianze.xiangting.beans.MeItemInfo;
import com.tianze.xiangting.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeActivity extends AppCompatActivity {

    @BindView(R.id.me_item_list)
    RecyclerView meItemList;
    @BindView(R.id.tool_bar)
    Toolbar toolBar;
    private MeItemAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);
        ButterKnife.bind(this);
        init();
    }

    private List<MeItemInfo> makeItem() {
        List<MeItemInfo> itemInfoList = new ArrayList<>();
        itemInfoList.add(new MeItemInfo(Constants.HEADER_ITEM, null, null));
        itemInfoList.add(new MeItemInfo(Constants.CUT_OFF_RULE, null, null));
        itemInfoList.add(new MeItemInfo(Constants.ABOUT_APP_ITEM, null, null));
        itemInfoList.add(new MeItemInfo(Constants.ABOUT_APP_ITEM, null, null));
        itemInfoList.add(new MeItemInfo(Constants.ABOUT_APP_ITEM, null, null));
        itemInfoList.add(new MeItemInfo(Constants.ABOUT_APP_ITEM, null, null));
        itemInfoList.add(new MeItemInfo(Constants.ABOUT_APP_ITEM, null, null));
        itemInfoList.add(new MeItemInfo(Constants.CUT_OFF_RULE, null, null));
        itemInfoList.add(new MeItemInfo(Constants.VERSION_ITEM, null, null));
        return itemInfoList;
    }

    private void init() {

        setSupportActionBar(toolBar);
        toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setDisplayShowTitleEnabled(false);

        meItemList.setLayoutManager(new LinearLayoutManager(MeActivity.this));
        meItemList.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                outRect.top = 5;
            }
        });
        mAdapter = new MeItemAdapter();
        meItemList.setAdapter(mAdapter);
        mAdapter.setItemData(makeItem());
    }

    public static void start(Context context) {
        Intent starter = new Intent(context, MeActivity.class);
        context.startActivity(starter);
    }
}
