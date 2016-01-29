package com.onemorebit.readthis.component;

import android.app.Activity;
import com.onemorebit.readthis.module.AppModule;
import dagger.Component;

/**
 * Created by Euro on 1/29/16 AD.
 */
@Component(modules = { AppModule.class }) public interface AppComponent {
    void inject(Activity activity);
}
