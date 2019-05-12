package com.my.sb_test.api.module;

import com.my.sb_test.SBApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by G-Man garethlye on 2019-05-11
 */
@Module
public class AppModule {

    private final SBApplication mApplication;

    public AppModule(final SBApplication application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    SBApplication provideApplication() {
        return mApplication;
    }

}
