package com.onemorebit.readthis.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import com.onemorebit.readthis.FullscreenActivity;
import com.onemorebit.readthis.R;
import com.onemorebit.readthis.adapter.EnterTextPagerAdapter;
import com.onemorebit.readthis.databinding.FragmentEnterTextBinding;
import com.onemorebit.readthis.model.ImageTextModel;
import java.util.ArrayList;
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
    private EnterTextPagerAdapter adapter;
    private MenuItem menuRemove;

    public EnterTextFragment() {
        // Required empty public constructor
    }

    public static EnterTextFragment newInstance() {
        EnterTextFragment fragment = new EnterTextFragment();
        return fragment;
    }

    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        enterTextBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_enter_text, container, false);

        initInstance();
        initListener();

        return enterTextBinding.getRoot();
    }

    private void initListener() {

        /* set onclick when click btnGo */
        enterTextBinding.btnGo.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                /* update word in each fragment */
                for (int i = 0; i < adapter.getModelList().size(); i++) {
                    final EnterTextSlideFragment registeredFragment = (EnterTextSlideFragment) adapter.getRegisteredFragment(i);
                    if (registeredFragment != null) adapter.getModelList().get(i).text = registeredFragment.getText();
                }

                startActivity(new Intent(getActivity(), FullscreenActivity.class).putExtra("imageTextModel", adapter.getModelList()));
            }
        });

        /* take picture from gallery from storage */
        enterTextBinding.btnPickImage.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                takePictureFromGallery();
            }
        });

        /* set onclick event when click btnAddPage */
        enterTextBinding.btnAddPage.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

                /* add new model to adapter */
                adapter.addModel(new ImageTextModel(null, ""));

                /* set visible whenever add fragment */
                menuRemove.setVisible(true);

                /* set swipable whenever add page */
                if (adapter.getCount() == 2) enterTextBinding.viewPager.setSwipeable(true);

                enterTextBinding.viewPager.setCurrentItem(adapter.getCount(), true);
            }
        });

        enterTextBinding.rootLayout.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                View view = getActivity().getCurrentFocus();
                if (view != null) {
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
        });

    }

    private void initInstance() {
        /* initial 2 pages */
        ArrayList<ImageTextModel> modelArrayList = new ArrayList<>();
        modelArrayList.add(new ImageTextModel(null, ""));
        modelArrayList.add(new ImageTextModel(null, ""));

        /* setup adapter */
        adapter = new EnterTextPagerAdapter(getChildFragmentManager(), modelArrayList);

        /* set adapter to viewpager */
        enterTextBinding.viewPager.setAdapter(adapter);

        /* show adjacent page*/
        enterTextBinding.viewPager.setClipToPadding(false);
        enterTextBinding.viewPager.setPageMargin(12);
    }

    /* start activity to take an image from storage  */
    private void takePictureFromGallery() {
        startActivityForResult(Intent.createChooser(new Intent(Intent.ACTION_GET_CONTENT).setType("image/*"), "Choose an image"), PICK_FROM_FILE);
    }

    @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == Activity.RESULT_OK) {

                switch (requestCode) {
                    case PICK_FROM_FILE:
                        /* Uri selected image */
                        Uri selectedImage = data.getData();

                        /* current fragment */
                        final EnterTextSlideFragment registeredFragment = (EnterTextSlideFragment) adapter.getRegisteredFragment(
                            enterTextBinding.viewPager.getCurrentItem());

                        /* set Uri image to model */
                        adapter.getModelList().get(registeredFragment.getPosition()).imageResUri = selectedImage;

                        /* set image to current fragment */
                        registeredFragment.setImage(selectedImage);
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

    @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);

        /* initiate menu remove button */
        menuRemove = menu.findItem(R.id.actionRemove);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionRemove:
                /* get current fragment */
                final EnterTextSlideFragment registeredFragment = (EnterTextSlideFragment) adapter.getRegisteredFragment(
                    enterTextBinding.viewPager.getCurrentItem());

                /* reset image text model */
                registeredFragment.resetImageTextModel();

                /* remove model from current position */
                adapter.removeModel(enterTextBinding.viewPager.getCurrentItem());

                /* when have only one page hide trash button */
                if (adapter.getCount() == 1) {
                    item.setVisible(false);
                    enterTextBinding.viewPager.setSwipeable(false);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
