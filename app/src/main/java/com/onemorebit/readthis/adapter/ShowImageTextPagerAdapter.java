package com.onemorebit.readthis.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.onemorebit.readthis.fragment.ShowTextImageFragment;
import com.onemorebit.readthis.model.ImageTextModel;
import java.util.ArrayList;

/**
 * Created by Euro on 1/30/16 AD.
 */
public class ShowImageTextPagerAdapter extends SmartFragmentStatePagerAdapter {

    private ArrayList<ImageTextModel> modelArrayList;

    public ShowImageTextPagerAdapter(FragmentManager fm, ArrayList<ImageTextModel> word) {
        super(fm);
        this.modelArrayList = word;
    }

    @Override public int getCount() {
        return modelArrayList.size();
    }

    @Override public Fragment getItem(int position) {
        return ShowTextImageFragment.newInstance(modelArrayList.get(position));
    }
}
