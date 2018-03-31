package com.example.app.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.app.fragment.BaseFragment;

import java.util.List;

public class ListFragmentStatePagerAdapter extends FragmentStatePagerAdapter {
    private final List<? extends BaseFragment> fragments;

    public ListFragmentStatePagerAdapter(FragmentManager fm, List<? extends BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }



}
