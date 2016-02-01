package com.onemorebit.readthis.fragment;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onemorebit.readthis.FullscreenActivity;
import com.onemorebit.readthis.R;
import com.onemorebit.readthis.databinding.FragmentPortraitBinding;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnterTextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnterTextFragment extends Fragment {

    // TODO: Rename and change types of parameters
    String word = "";

    public EnterTextFragment() {
        // Required empty public constructor
    }

    public static EnterTextFragment newInstance() {
        EnterTextFragment fragment = new EnterTextFragment();
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final FragmentPortraitBinding portraitBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_enter_text, container, false);

        portraitBinding.etWord.setHorizontallyScrolling(false);

        if (portraitBinding.etWord != null) {
            portraitBinding.btnGo.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    word = portraitBinding.etWord.getText().toString();
                    Timber.i("word :  %s", word);
                    startActivity(new Intent(getActivity(), FullscreenActivity.class).putExtra("word", word));
                }
            });
        }
        return portraitBinding.getRoot();
    }
}
