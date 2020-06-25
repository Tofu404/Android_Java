package com.tianze.xiangting.fragments;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tianze.xiangting.PlayerActivity;
import com.tianze.xiangting.R;
import com.tianze.xiangting.adapters.TrackListAdapter;
import com.tianze.xiangting.base.BaseApplication;
import com.tianze.xiangting.interfaces.IHistoryViewCallback;
import com.tianze.xiangting.presenters.HistoryPresenter;
import com.tianze.xiangting.presenters.PlayerPresenter;
import com.tianze.xiangting.views.UILoader;
import com.ximalaya.ting.android.opensdk.model.track.Track;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryFragment extends Fragment implements IHistoryViewCallback, TrackListAdapter.OnTrackListItemClickListener {


    @BindView(R.id.container)
    FrameLayout container;
    private UILoader mUiLoader;
    private RecyclerView mHistoryTrackList;
    private TrackListAdapter mTrackListAdapter;
    private HistoryPresenter mHistoryPresenter;
    private List<Track> historyTracks = null;
    private PlayerPresenter mPlayerPresenter;
    private int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        ButterKnife.bind(this, view);
        initUILoader(container, view);
        initPresenter();
        return view;
    }

    private void initPresenter() {
        mPlayerPresenter = PlayerPresenter.getInstance();
        mHistoryPresenter = HistoryPresenter.getInstance();
        mHistoryPresenter.registerViewCallback(this);
        //查询数据库中的历史记录；
        mHistoryPresenter.queryHistory();
    }

    private void initUILoader(@Nullable ViewGroup container, View view) {
        if (mUiLoader == null) {
            mUiLoader = new UILoader(BaseApplication.context) {
                @Override
                public View getSuccessView(ViewGroup container) {
                    return createSuccessView(container);
                }

                @Override
                protected View getEmptyView() {
                    View emptyView = super.getEmptyView();
                    TextView withoutDataTv = emptyView.findViewById(R.id.without_data_tv);
                    withoutDataTv.setText("听一听！就有历史记录了~");
                    return emptyView;
                }
            };
        }
        if (mUiLoader.getParent() instanceof ViewGroup) {
            ((ViewGroup) mUiLoader.getParent()).removeView(mUiLoader);
        }
        this.container.addView(mUiLoader);
    }

    private View createSuccessView(ViewGroup container) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_history_list, container, false);
        mHistoryTrackList = view.findViewById(R.id.history_track_list);
        mHistoryTrackList.setLayoutManager(new LinearLayoutManager(getContext()));
        mTrackListAdapter = new TrackListAdapter();
        mTrackListAdapter.setOnTrackListItemClickListener(this);
        mHistoryTrackList.setAdapter(mTrackListAdapter);
        registerForContextMenu(mHistoryTrackList);
        return view;
    }


    @Override
    public void queryResultViewCallback(List<Track> tracks) {
        this.historyTracks = tracks;
        if (tracks.size() > 0) {
            mUiLoader.setCurrentState(UILoader.UIState.SUCCESS);
            mTrackListAdapter.setTracksData(tracks);
        } else {
            mUiLoader.setCurrentState(UILoader.UIState.EMPTY);
        }
    }

    @Override
    public void delResultViewCallback() {
        //刷新一下列表
        mHistoryPresenter.queryHistory();
        mTrackListAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearResultViewCallback() {
        //刷新一下列表
        mHistoryPresenter.queryHistory();
        mTrackListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHistoryPresenter.unregisteredViewCallback(this);
    }

    @Override
    public void onTrackListItemClick(int position) {
        if (this.historyTracks.size()>0) {
            mPlayerPresenter.mySetTrackList(this.historyTracks, position);
            PlayerActivity.start(getContext());
        }
    }

    @Override
    public void onTrackListItemLongClick(int position) {
        this.currentItem = position;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0,1,0,"删除");
        menu.add(0,2,0,"删除全部");
    }


    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 1:
                //删除该item项的数据
                mHistoryPresenter.delHistory(this.currentItem);
                break;
            case 2:
                //删除全部数据
                mHistoryPresenter.clearHistory();
                break;
        }
        return super.onContextItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHistoryPresenter.queryHistory();
    }
}
