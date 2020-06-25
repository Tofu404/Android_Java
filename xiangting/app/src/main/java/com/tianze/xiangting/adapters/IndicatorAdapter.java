package com.tianze.xiangting.adapters;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.view.View;

import androidx.annotation.RequiresApi;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.SimplePagerTitleView;

import java.util.ArrayList;
import java.util.List;


@RequiresApi(api = Build.VERSION_CODES.M)
public class IndicatorAdapter extends CommonNavigatorAdapter {

    List<String> indicatorTitle = new ArrayList<>();

    private OnsimplePagerTitleViewClickListener onsimplePagerTitleViewClickListener = null;

    @Override
    public int getCount() {
        if (indicatorTitle!=null){
            return indicatorTitle.size();
        }
        return 0;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
        simplePagerTitleView.setNormalColor(Color.WHITE);
        simplePagerTitleView.setSelectedColor(Color.YELLOW);
        simplePagerTitleView.setText(indicatorTitle.get(index));
        simplePagerTitleView.setTextSize(23);
        simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onsimplePagerTitleViewClickListener!=null){
                    onsimplePagerTitleViewClickListener.onsimplePagerTitleViewClick(index);
                }
            }
        });
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator linePagerIndicator = new LinePagerIndicator(context);
        linePagerIndicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
        linePagerIndicator.setColors(Color.BLACK);
        return linePagerIndicator;
    }

    public void setTitleData(List<String> mTitleDataList) {
        if (mTitleDataList != null) {
            this.indicatorTitle.clear();
            this.indicatorTitle = mTitleDataList;
            notifyDataSetChanged();
        }
    }

    public interface OnsimplePagerTitleViewClickListener{
        void onsimplePagerTitleViewClick(int index);
    }

    public void setOnsimplePagerTitleViewClickListener(OnsimplePagerTitleViewClickListener listener){
        this.onsimplePagerTitleViewClickListener = listener;
    }
}
