package com.onemorebit.readthis.fragment;

import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.onemorebit.readthis.R;
import com.onemorebit.readthis.databinding.FragmentEnterTextSlideBinding;
import com.onemorebit.readthis.model.ImageTextModel;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterTextSlideFragment extends Fragment {
    private int position;
    private FragmentEnterTextSlideBinding enterTextSlideBinding;
    private ImageTextModel imageTextModel;

    public EnterTextSlideFragment() {
        // Required empty public constructor
    }

    public static EnterTextSlideFragment newInstance(int position, ImageTextModel model) {
        Bundle args = new Bundle();
        EnterTextSlideFragment fragment = new EnterTextSlideFragment();
        // put variable to bundle here
        args.putInt("position", position);
        args.putParcelable("imageTextModel", model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageTextModel = getArguments().getParcelable("imageTextModel");

        if (savedInstanceState != null && imageTextModel == null) {
            //position = savedInstanceState.getInt("position");
            imageTextModel = savedInstanceState.getParcelable("imageTextModel");
        }

        position = getArguments().getInt("position");

        Timber.i("Position : " + position + ", ImageTextModel : " + imageTextModel.toString());
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        enterTextSlideBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_enter_text_slide, container, false);

        //enterTextSlideBinding.setModel(imageTextModel);
        return enterTextSlideBinding.getRoot();
    }

    @Override public void onResume() {
        super.onResume();
        Timber.i("OnResume " + enterTextSlideBinding.etWord.getText());
        enterTextSlideBinding.setModel(imageTextModel);
        enterTextSlideBinding.etWord.setText(imageTextModel.text);
    }

    @Override public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        imageTextModel.text = enterTextSlideBinding.etWord.getText().toString();
        outState.putParcelable("imageTextModel", imageTextModel);
    }

    public void resetImageTextModel() {
        imageTextModel = new ImageTextModel(null, "");
        enterTextSlideBinding.etWord.setText("");
    }

    public void setPosition(int position){
        this.position = position;
    }

    public void setText(String text){
        enterTextSlideBinding.etWord.setText(text);
    }

    public void setImage(Uri imageResId) {
        imageTextModel.imageResUri = imageResId;
        Glide.with(this).load(imageResId).into(enterTextSlideBinding.ivImageTextBox);
    }

    public String getText() {
        return enterTextSlideBinding.etWord.getText().toString();
    }

    public ImageTextModel getModel(){
        return imageTextModel;
    }

    public int getPosition() {
        return position;
    }
}
