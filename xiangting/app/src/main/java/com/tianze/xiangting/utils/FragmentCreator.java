package com.tianze.xiangting.utils;

import androidx.fragment.app.Fragment;

import com.tianze.xiangting.fragments.HistoryFragment;
import com.tianze.xiangting.fragments.RecommendFragment;
import com.tianze.xiangting.fragments.SubscriptionFragment;

import java.util.HashMap;
import java.util.Map;

public class FragmentCreator {

    public static final int FRAGMENT_NUMBER = Constants.FRAGMENT_NUMBER;

    public static Map<Integer, Fragment> fragmentsMap = new HashMap<>();


    public static Fragment getFragment(int index) {
        Fragment fragment = null;
        if (fragmentsMap.get(index) == null) {
            switch (index) {
                case 0:
                    fragment = new RecommendFragment();
                    fragmentsMap.put(index, fragment);
                    break;
                case 1:
                    fragment = new SubscriptionFragment();
                    fragmentsMap.put(index, fragment);
                    break;
                case 2:
                    fragment = new HistoryFragment();
                    fragmentsMap.put(index, fragment);
                    break;
            }
        } else {
            fragment = fragmentsMap.get(index);
        }
        return fragment;
    }
}
