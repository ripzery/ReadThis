package com.onemorebit.readthis;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.onemorebit.readthis.databinding.ActivityMainBinding;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject Application application;
    ActivityMainBinding mainBinding;
    private static final String TAG = "MainActivity";

    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mainBinding.container.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }

        ((MyApp) getApplication()).getAppComponent().inject(this);

        initFragment();
    }

    private void initFragment() {
        Log.d(TAG, "initFragment: ");
        if (getSupportFragmentManager().findFragmentByTag("PortraitFragment") == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, PortraitFragment.newInstance(), "PortraitFragment").commit();
        }
    }

    @Override public void onBackPressed() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        } else {
            super.onBackPressed();
        }
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }
}
