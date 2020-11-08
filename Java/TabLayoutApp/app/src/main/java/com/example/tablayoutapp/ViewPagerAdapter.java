package com.example.tablayoutapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private final List<String> listFragmentsTitles = new ArrayList<>();
    private final List<Fragment> listFragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }

    public void addFragment(String fragmentTitle, Fragment fragment) {
        listFragmentsTitles.add(fragmentTitle);
        listFragments.add(fragment);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listFragmentsTitles.get(position);
    }
}
