package com.heung.household.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.base.library.fragment.BaseFragment;

import java.util.List;

/**
 * 作者：王东一
 * 创建时间：2017/7/20.
 */

public class MyTableViewAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> pagerList;
    private List<String> titleList;

    public MyTableViewAdapter(FragmentManager fm, List<BaseFragment> pagerList, List<String> titleList) {
        super(fm);
        this.pagerList = pagerList;
        this.titleList = titleList;
    }

    @Override
    public int getCount() {
        return pagerList != null ? pagerList.size() : 0;
    }

    @Override
    public Fragment getItem(int position) {
        return pagerList.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }
}
