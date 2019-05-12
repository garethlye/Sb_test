package com.my.sb_test.api.Gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.my.sb_test.api.GsonAdaptersPopularMovieResponse;
import com.my.sb_test.data.GsonAdaptersMovie;

/**
 * Created by G-Man garethlye on 2019-05-11
 */
public class GsonUtils {

    private static Gson GSON;

    private GsonUtils() {
        // For Singleton
    }

    public static synchronized Gson getGson() {
        if (GSON == null) {
            final GsonBuilder gsonBuilder = new GsonBuilder()
                    .setLenient()
                    .registerTypeAdapter(BigDecimalDeserializer.class, new BigDecimalDeserializer());

            gsonBuilder.registerTypeAdapterFactory(new GsonAdaptersPopularMovieResponse());
            gsonBuilder.registerTypeAdapterFactory(new GsonAdaptersMovie());

            GSON = gsonBuilder.create();
        }
        return GsonUtils.GSON;
    }
}
