package com.onemorebit.readthis.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.onemorebit.readthis.R;
import com.onemorebit.readthis.fragment.ShowImageFragment;
import com.onemorebit.readthis.fragment.ShowTextFragment;

/**
 * Created by Euro on 1/30/16 AD.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private String word;
    private String[] splittedWord;

    public MyPagerAdapter(FragmentManager fm, String word) {
        super(fm);
        this.word = word;
        splittedWord = word.split("\n");
    }

    @Override public int getCount() {
        return splittedWord.length + 1;
    }

    @Override public Fragment getItem(int position) {
        if (splittedWord.length == position) {
            return ShowImageFragment.newInstance(R.drawable.wallpaper3);
        } else {
            return ShowTextFragment.newInstance(splittedWord[position]);
        }
    }
}
