package com.arecmetafora.leilaoreceitafederal.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.arecmetafora.leilaoreceitafederal.view.HomeActivity;
import com.arecmetafora.leilaoreceitafederal.view.HomeActivityModule;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity homeActivity();
}
