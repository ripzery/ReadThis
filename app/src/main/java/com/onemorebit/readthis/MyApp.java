package com.onemorebit.readthis;

import android.app.Application;
import com.crashlytics.android.Crashlytics;
import com.onemorebit.readthis.component.AppComponent;
import com.onemorebit.readthis.component.DaggerAppComponent;
import com.onemorebit.readthis.module.AppModule;
import io.fabric.sdk.android.Fabric;
import timber.log.Timber;

/**
 * Created by Euro on 1/29/16 AD.
 */
public class MyApp extends Application {

    private AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();

        setupTimber();
    }

    private void setupTimber() {
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}
