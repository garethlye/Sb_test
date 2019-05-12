package com.my.sb_test.api;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by G-Man garethlye on 2019-05-11
 */
public interface DataApi {

    @GET("/3/movie/popular")
    Observable<PopularMovieResponse> getData(@Query("api_key") String key,
                                             @Query("language") String language,
                                             @Query("page") Integer page);


}
