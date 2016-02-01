package com.onemorebit.readthis.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onemorebit.readthis.R;
import com.onemorebit.readthis.databinding.FragmentWordBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowTextFragment extends Fragment {

    String word;

    public ShowTextFragment() {
        // Required empty public constructor
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        word = getArguments().getString("word").trim();
    }

    public static ShowTextFragment newInstance(String word) {
        Bundle bundle = new Bundle();
        bundle.putString("word", word);
        final ShowTextFragment showTextFragment = new ShowTextFragment();
        showTextFragment.setArguments(bundle);
        return showTextFragment;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentWordBinding view = DataBindingUtil.inflate(inflater, R.layout.fragment_show_text, container, false);
        view.tvText.setText(word);
        return view.getRoot();
    }
}
