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
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class WordFragment extends Fragment {

    String word;

    public WordFragment() {
        // Required empty public constructor
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        word = getArguments().getString("word").trim();
    }

    public static WordFragment newInstance(String word){
        Bundle bundle = new Bundle();
        bundle.putString("word", word);
        final WordFragment wordFragment = new WordFragment();
        wordFragment.setArguments(bundle);
        return wordFragment;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentWordBinding view = DataBindingUtil.inflate(inflater, R.layout.fragment_word, container, false);
        view.tvText.setText(word);
        return view.getRoot();
    }
}
