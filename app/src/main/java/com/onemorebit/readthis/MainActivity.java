package com.onemorebit.readthis;

import android.app.Application;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;
import com.onemorebit.readthis.databinding.ActivityMainBinding;
import com.onemorebit.readthis.fragment.EnterTextFragment;
import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject Application application;
    ActivityMainBinding mainBinding;
    private static final String TAG = "MainActivity";

    @Override protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        ((MyApp) getApplication()).getAppComponent().inject(this);

        /* Firstly created this activity*/
        if (savedInstanceState == null) {
            initFragment();
        }
    }

    private void initFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, EnterTextFragment.newInstance(), "PortraitFragment").commit();
    }

}
