package com.onemorebit.readthis.fragment;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.bumptech.glide.Glide;
import com.onemorebit.readthis.FullscreenActivity;
import com.onemorebit.readthis.R;
import com.onemorebit.readthis.databinding.FragmentEnterTextBinding;
import timber.log.Timber;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnterTextFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnterTextFragment extends Fragment {

    private static final int PICK_FROM_FILE = 1;
    // TODO: Rename and change types of parameters
    String word = "";
    private String imgDecodableString;
    private FragmentEnterTextBinding enterTextBinding;

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
        enterTextBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_enter_text, container, false);

        initInstance(enterTextBinding);

        return enterTextBinding.getRoot();
    }

    private void initInstance(final FragmentEnterTextBinding enterTextBinding) {
        enterTextBinding.etWord.setHorizontallyScrolling(false);

        enterTextBinding.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                word = enterTextBinding.etWord.getText().toString();
                Timber.i("word :  %s", word);
                startActivity(new Intent(getActivity(), FullscreenActivity.class).putExtra("word", word));
            }
        });

        enterTextBinding.btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                takePictureFromGallery();
            }
        });
    }

    private void takePictureFromGallery() {
        startActivityForResult(Intent.createChooser(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), "Choose an image"), PICK_FROM_FILE);
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == Activity.RESULT_OK) {

                switch (requestCode) {
                    case PICK_FROM_FILE:
                        Timber.i("Pick From file");
                        Uri selectedImage = data.getData();
                        Glide.with(this).load(selectedImage).into(enterTextBinding.ivImageTextBox);
                        break;
                    default:
                }
            } else {
                Timber.i("You haven't pick an image");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
