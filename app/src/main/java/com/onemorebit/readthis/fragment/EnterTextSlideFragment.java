package com.onemorebit.readthis.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onemorebit.readthis.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterTextSlideFragment extends Fragment {

    public EnterTextSlideFragment() {
        // Required empty public constructor
    }

    public static EnterTextSlideFragment newInstance() {
        Bundle args = new Bundle();
        EnterTextSlideFragment fragment = new EnterTextSlideFragment();
        // put variable to bundle here
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_enter_text_slide, container, false);
    }
}
