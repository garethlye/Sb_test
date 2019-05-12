package com.my.sb_test.api.module;

import android.content.Context;

import com.google.gson.Gson;
import com.my.sb_test.SBApplication;
import com.my.sb_test.api.DataApi;
import com.my.sb_test.api.Gson.GsonUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by G-Man garethlye on 2019-05-11
 */

@Singleton
@Module
public class APIModule {

    @Provides
    @Singleton
    DataApi provideNewDataApi(Retrofit retrofit) {
        return retrofit.create(DataApi.class);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkhttpClient(Cache cache) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.cache(cache);
        return client.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("https://api.themoviedb.org")
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return GsonUtils.getGson();
    }

    @Provides
    @Singleton
    Cache provideHttpCache(SBApplication application) {
        return new Cache(application.getCacheDir(), 10 * 1024 * 1024);
    }
}
