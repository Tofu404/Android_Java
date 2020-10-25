package com.tianze.my_dagger_demo.Component;

import com.tianze.my_dagger_demo.module.MainModule;
import com.tianze.my_dagger_demo.views.MainActivity;

import dagger.Component;

@Component(modules = {MainModule.class})
public interface MainCom {
    void Inject(MainActivity mainActivity);
}
