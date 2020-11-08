package com.example.ansanewsapp.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.ansanewsapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private final List<String> listFragmentsTitles = new ArrayList<>();
    private final List<Fragment> listFragments = new ArrayList<>();
    private final Context mContext;

    public SectionsPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return listFragments.get(position);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listFragmentsTitles.get(position);
    }

    public void addFragment(String fragmentTitle, Fragment fragment) {
        listFragmentsTitles.add(fragmentTitle);
        listFragments.add(fragment);
    }

    @Override
    public int getCount() {
        return listFragments.size();
    }
}