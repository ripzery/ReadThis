package com.onemorebit.readthis.module;

import android.app.Application;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

/**
 * Created by Euro on 1/29/16 AD.
 */

@Module public class AppModule {
    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides @Singleton Application providesApplication() {
        return mApplication;
    }
}
