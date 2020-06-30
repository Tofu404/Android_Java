package com.tianze.mybrowser;

import android.Manifest;
import android.graphics.Point;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.mybrowser.db.entity.BookMark;
import com.tianze.mybrowser.db.entity.BookMarkMng;

public class HomeActivity extends BaseActivity implements BookMarkAdapter.OnBookMarkClickLinsener {

    private static final int DOUBLE_CLICK_TIME = 2000;
    private static int clickLogoCount = 0;
    private static long CURRENT_TIME = 0;
    private EditText mHomeSearchBar;
    private ImageView mLogo;
    private BookMark currnetBookMark;
    private BookMarkAdapter mAdapter;
    private boolean hasDoDelay = false;
    private MyPopupWindow mMyPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initEvent();
        //申请权限
        String[] permissions = new String[]{
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        MyUtils.requestPermission(HomeActivity.this, permissions);
    }

    @Override
    public int setLayoutId() {
        return R.layout.activity_home;
    }

    private void initEvent() {

        mHomeSearchBar.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (mHomeSearchBar.getText().toString().length() > 0) {
                    //点击搜索按钮的时候会触发“up”和“down”事件，要将事件过滤，否则MainActivity会启动两次
                    if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                        MainActivity.start(HomeActivity.this, mHomeSearchBar.getText().toString());
                    }
                } else if (keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP) {
                    Toast toast = Toast.makeText(HomeActivity.this, "", Toast.LENGTH_SHORT);
                    toast.setText("请输入搜索关键字");
                    toast.show();
                }
                return false;
            }
        });

        //连续点击logo五次进入设置
        mLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLogoCount++;
                if (clickLogoCount >= 5) {
                    if (clickLogoCount < 6) {
                        MyUtils.MyToast(HomeActivity.this, "已经进入设置");
                    }
                    //获取屏幕的大小
                    Display defaultDisplay = getWindowManager().getDefaultDisplay();
                    Point outSize = new Point();
                    defaultDisplay.getSize(outSize);

                    //计算收索栏到底部slogen的高度
                    int[] logoLocation = new int[2];
                    mLogo.getLocationOnScreen(logoLocation);
                    int[] slogenLocation = new int[2];
                    findViewById(R.id.slogen).getLocationOnScreen(slogenLocation);
                    int i = slogenLocation[1] - logoLocation[1] - mLogo.getHeight();

                    //创建PopupWindow
                    mMyPopupWindow = new MyPopupWindow(HomeActivity.this, outSize.x, i);

                    if (mMyPopupWindow.isShowing()) {
                        mMyPopupWindow.dismiss();
                        return;
                    }

                    mMyPopupWindow.showAsDropDown(mLogo);
                } else {
                    if (5 - clickLogoCount > 0) {
                        MyUtils.MyToast(HomeActivity.this, "再点击" + (5 - clickLogoCount) + "次，进入设置");
                    }
                }

                if (!hasDoDelay) {
                    //触发延迟操作,一分钟之后要从新点击5次之后才能再次进入
                    mLogo.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            clickLogoCount = 0;
                            hasDoDelay = false;
                        }
                    }, 1000 * 60);
                }
                hasDoDelay = true;
            }
        });

    }

    private void initView() {
        mHomeSearchBar = findViewById(R.id.home_search_bar);
        mLogo = findViewById(R.id.logo);
        RecyclerView bookMarkList = findViewById(R.id.book_mark_list);
        bookMarkList.setLayoutManager(new GridLayoutManager(this, 4));
        mAdapter = new BookMarkAdapter();
        mAdapter.setOnBookMarkClickLinsener(this);
        bookMarkList.setAdapter(mAdapter);
    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - CURRENT_TIME >= DOUBLE_CLICK_TIME) {
            Toast toast = Toast.makeText(HomeActivity.this, "", Toast.LENGTH_SHORT);
            toast.setText("再按一次退出程序");
            toast.show();
            CURRENT_TIME = System.currentTimeMillis();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHomeSearchBar.setText("");
        mHomeSearchBar.clearFocus();
        //刷新书签列表
        mAdapter.setData(BookMarkMng.findAll());
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(1, 1, 1, "编辑");
        menu.add(1, 2, 1, "删除");
        menu.add(1, 3, 1, "删除全部");
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "编辑", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                BookMarkMng.deleteBookMark(currnetBookMark);
                mAdapter.setData(BookMarkMng.findAll());
                break;
            case 3:
                BookMarkMng.deleteAllBookMark();
                mAdapter.setData(BookMarkMng.findAll());
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(BookMark bookMark) {
        MainActivity.start(HomeActivity.this, bookMark.getMBookMarkUrl());
    }

    @Override
    public void onLongClick(View view, BookMark bookMark) {
        registerForContextMenu(view);
        this.currnetBookMark = bookMark;
    }
}