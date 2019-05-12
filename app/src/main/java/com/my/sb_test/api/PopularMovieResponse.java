package com.my.sb_test.api;

import android.support.annotation.Nullable;

import com.my.sb_test.data.Movie;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

import java.util.List;

/**
 * Created by G-Man garethlye on 2019-05-11
 */

@Gson.TypeAdapters
@Value.Immutable
public interface PopularMovieResponse {

    @Nullable
    List<Movie> results();

}
