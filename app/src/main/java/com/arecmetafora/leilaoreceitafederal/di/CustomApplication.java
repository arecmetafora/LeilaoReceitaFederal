package com.arecmetafora.leilaoreceitafederal.di;

import android.app.Application;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

/**
 * We create a custom {@link Application} class that extends  {@link DaggerApplication}.
 * We then override applicationInjector() which tells Dagger how to make our @Singleton Component
 * We never have to call `component.inject(this)` as {@link DaggerApplication} will do that for us.
 */
public class CustomApplication extends DaggerApplication {

    /**
     * Application main component.
     */
    private AppComponent appComponent;

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        appComponent = DaggerAppComponent.builder().application(this).build();
        return appComponent;
    }

    /**
     * @return The application main component.
     */
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
