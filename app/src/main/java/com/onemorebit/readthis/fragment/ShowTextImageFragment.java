package com.onemorebit.readthis.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.onemorebit.readthis.R;
import com.onemorebit.readthis.databinding.FragmentShowTextImageBinding;
import com.onemorebit.readthis.model.ImageTextModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowTextImageFragment extends Fragment {

    ImageTextModel model;

    public ShowTextImageFragment() {
        // Required empty public constructor
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.model = getArguments().getParcelable("imageTextModel");
    }

    public static ShowTextImageFragment newInstance(ImageTextModel model) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("imageTextModel", model);
        final ShowTextImageFragment showTextImageFragment = new ShowTextImageFragment();
        showTextImageFragment.setArguments(bundle);
        return showTextImageFragment;
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentShowTextImageBinding view = DataBindingUtil.inflate(inflater, R.layout.fragment_show_text_image, container, false);
        trimEnterCharAndSpace();
        view.setModel(model);
        return view.getRoot();
    }

    private void trimEnterCharAndSpace() {
        /* trim out last \n */
        while (model.text.length() > 0 && model.text.lastIndexOf("\n") == model.text.length() - 1) {
            model.text = model.text.substring(0, model.text.length() - 1);
        }

        /* trim out first \n */
        while (model.text.length() > 0 && model.text.indexOf("\n") == 0) {
            model.text = model.text.substring(1, model.text.length());
        }

        /* trim out last space */
        while (model.text.length() > 0 && model.text.lastIndexOf(" ") == model.text.length() - 1) {
            model.text = model.text.substring(0, model.text.length() - 1);
        }

        /* trim out first space */
        while (model.text.length() > 0 && model.text.indexOf(" ") == 0) {
            model.text = model.text.substring(1, model.text.length());
        }
    }
}
