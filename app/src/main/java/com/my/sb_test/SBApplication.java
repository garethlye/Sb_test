package com.my.sb_test;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.Looper;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.my.sb_test.api.component.ApplicationComponent;
import com.my.sb_test.api.component.DaggerApplicationComponent;
import com.my.sb_test.api.module.APIModule;
import com.my.sb_test.api.module.AppModule;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by G-Man garethlye on 2019-05-11
 */
public class SBApplication extends Application {

    private static volatile SBApplication singleton;

    private ApplicationComponent mApplicationComponent;

    public SBApplication() {
    }

    @Override
    public void onCreate() {
        SBApplication.initSingleton(this);
        initializeApplicationComponent();
        //initialize le fresco image library
        Fresco.initialize(this);
        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(this));
        super.onCreate();
    }

    public static SBApplication getInstance() {
        if (singleton == null) {
            try {
                if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                    // Current Thread is Main Thread.
                    @SuppressLint("PrivateApi") final Class<?> clazz = Class.forName("android.app.ActivityThread");
                    final android.app.Application application = (android.app.Application)
                            clazz.getDeclaredMethod("currentApplication").invoke(null);
                    if (application instanceof SBApplication) {
                        SBApplication.initSingleton((SBApplication) application);
                        return singleton;
                    }
                }
            } catch (Exception e) {
                Log.e("ERROR", e.toString());
            }
        }

        return singleton;
    }

    private void initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent.builder()
                .appModule(new AppModule(this))
                .aPIModule(new APIModule())
                .build();
        mApplicationComponent.inject(this);
    }

    private static void initSingleton(final SBApplication application) {
        if (application != null) {
            singleton = application;
        }
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
