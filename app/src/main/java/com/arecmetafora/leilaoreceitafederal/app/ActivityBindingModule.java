package com.arecmetafora.leilaoreceitafederal.app;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import ui.main.MainActivity;
import ui.main.MainActivityModule;

@Module
public abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity mainActivity();
}
