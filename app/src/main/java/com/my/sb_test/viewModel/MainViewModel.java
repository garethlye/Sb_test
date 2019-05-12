package com.my.sb_test.viewModel;

import com.my.sb_test.data.Movie;

import java.util.List;

/**
 * Created by G-Man garethlye on 2019-05-11
 */
public interface MainViewModel {

    void getListData(final Integer page);

    void onRefresh();

    boolean getIsLoading();

    List<Movie> getMovieList();
}
