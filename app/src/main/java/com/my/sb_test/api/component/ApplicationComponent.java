package com.my.sb_test.api.component;

import com.my.sb_test.MainActivity;
import com.my.sb_test.SBApplication;
import com.my.sb_test.api.module.APIModule;
import com.my.sb_test.api.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by G-Man garethlye on 2019-05-11
 */

@Singleton
@Component(modules = {AppModule.class, APIModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);

    void inject(SBApplication sbApplication);
}
