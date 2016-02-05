package com.onemorebit.readthis.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import com.onemorebit.readthis.fragment.EnterTextSlideFragment;
import com.onemorebit.readthis.model.ImageTextModel;
import java.util.ArrayList;
import timber.log.Timber;

/**
 * Created by Euro on 2/3/16 AD.
 */
public class EnterTextPagerAdapter extends SmartFragmentStatePagerAdapter {

    private ArrayList<ImageTextModel> modelArrayList = new ArrayList<>();

    public EnterTextPagerAdapter(FragmentManager fm, ArrayList<ImageTextModel> modelArrayList) {
        super(fm);
        this.modelArrayList = modelArrayList;
    }

    @Override public int getCount() {
        return modelArrayList.size();
    }

    @Override public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    @Override public float getPageWidth(int position) {
        return 0.93f;
    }

    @Override public Fragment getItem(int position) {
        return EnterTextSlideFragment.newInstance(position, modelArrayList.get(position));
    }

    public void addModel(ImageTextModel imageTextModel){
        modelArrayList.add(imageTextModel);
        notifyDataSetChanged();
    }

    public void removeModel(int position){
        modelArrayList.remove(position);
        notifyDataSetChanged();
    }

    public ArrayList<ImageTextModel> getModelList(){
        return modelArrayList;
    }
}
