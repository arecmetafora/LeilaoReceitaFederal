package com.arecmetafora.leilaoreceitafederal.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.arecmetafora.leilaoreceitafederal.model.to.InFocus;

class InFocusPagerAdapter extends FragmentPagerAdapter {

    private InFocus[] mData;

    InFocusPagerAdapter(FragmentManager fm, InFocus[] data) {
        super(fm);
        mData = data;
    }

    @Override
    public Fragment getItem(int position) {
        return InFocusFragment.newInstance(mData[position]);
    }

    @Override
    public int getCount() {
        return mData.length;
    }
}
