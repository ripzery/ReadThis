package com.onemorebit.readthis;

import android.app.Application;
import com.onemorebit.readthis.component.AppComponent;
import com.onemorebit.readthis.component.DaggerAppComponent;
import com.onemorebit.readthis.module.AppModule;

/**
 * Created by Euro on 1/29/16 AD.
 */
public class MyApp extends Application {

    private AppComponent appComponent;

    @Override public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent getAppComponent(){
        return appComponent;
    }
}