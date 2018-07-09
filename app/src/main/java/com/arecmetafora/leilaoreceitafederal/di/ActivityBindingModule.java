package com.arecmetafora.leilaoreceitafederal.di;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import com.arecmetafora.leilaoreceitafederal.view.MainActivity;
import com.arecmetafora.leilaoreceitafederal.view.MainActivityModule;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
