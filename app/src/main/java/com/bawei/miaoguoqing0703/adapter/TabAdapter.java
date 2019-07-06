package com.bawei.miaoguoqing0703.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class TabAdapter extends FragmentPagerAdapter {
    private List<String> list;
    private List<Fragment> list1;

    public TabAdapter(FragmentManager fm, List<String> list, List<Fragment> list1) {
        super(fm);
        this.list = list;
        this.list1 = list1;
    }

    @Override
    public Fragment getItem(int i) {
        return list1.get(i);
    }

    @Override
    public int getCount() {
        return list1.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position);
    }
}
