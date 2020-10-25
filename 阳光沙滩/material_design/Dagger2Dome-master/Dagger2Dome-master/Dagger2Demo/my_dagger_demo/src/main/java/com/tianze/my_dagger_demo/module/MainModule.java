package com.tianze.my_dagger_demo.module;

import android.content.Context;

import com.tianze.my_dagger_demo.presenter.MainPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private final Context context;

    public MainModule(Context context){
        this.context = context;
    }

    @Provides
    MainPresenter providesPresenter(){
        return new MainPresenter(this.context);
    }
}
