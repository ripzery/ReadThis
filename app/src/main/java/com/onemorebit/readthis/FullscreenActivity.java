package com.onemorebit.readthis;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.onemorebit.readthis.adapter.ShowImageTextPagerAdapter;
import com.onemorebit.readthis.databinding.ActivityFullscreenBinding;
import com.onemorebit.readthis.model.ImageTextModel;
import java.util.ArrayList;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class FullscreenActivity extends AppCompatActivity {
    private ActivityFullscreenBinding fullscreenBinding;
    private String word;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fullscreenBinding = DataBindingUtil.setContentView(this, R.layout.activity_fullscreen);

        //word = getIntent().getStringExtra("word");
        ArrayList<ImageTextModel> imageTextModelArrayList = getIntent().getParcelableArrayListExtra("imageTextModel");

        /* set up view pager */
        ShowImageTextPagerAdapter pagerAdapter = new ShowImageTextPagerAdapter(getSupportFragmentManager(), imageTextModelArrayList);
        fullscreenBinding.viewPager.setAdapter(pagerAdapter);

        toggleFullScreen();

    }

    private void toggleFullScreen() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        fullscreenBinding.viewPager.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION|
            View.SYSTEM_UI_FLAG_LOW_PROFILE | View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }
}
