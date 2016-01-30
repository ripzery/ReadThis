package com.onemorebit.readthis.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.onemorebit.readthis.fragment.WordFragment;

/**
 * Created by Euro on 1/30/16 AD.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private String word;
    private String[] splittedWord;

    public MyPagerAdapter(FragmentManager fm, String word) {
        super(fm);
        this.word = word;
        splittedWord = word.split(" ");
    }

    @Override public int getCount() {
        return splittedWord.length;
    }

    @Override public Fragment getItem(int position) {
        return WordFragment.newInstance(splittedWord[position]);
    }
}
