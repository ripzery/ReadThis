package com.onemorebit.readthis.fragment;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onemorebit.readthis.R;
import com.onemorebit.readthis.databinding.FragmentShowImageBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowImageFragment extends Fragment {

    public ShowImageFragment() {
        // Required empty public constructor
    }

    public static ShowImageFragment newInstance(int imageDrawable) {
        Bundle args = new Bundle();
        ShowImageFragment fragment = new ShowImageFragment();
        args.putInt("image", imageDrawable);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null) {
            Drawable image = ContextCompat.getDrawable(getActivity(), savedInstanceState.getInt("image"));
        }
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final FragmentShowImageBinding inflate = DataBindingUtil.inflate(inflater, R.layout.fragment_show_image, container, false);
        return inflate.getRoot();
    }
}
