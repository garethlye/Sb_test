package com.my.sb_test.viewModel;

import android.content.Context;

import com.my.sb_test.data.Movie;

/**
 * Created by G-Man garethlye on 2019-05-12
 */
public interface MovieInfoFragmentViewModel {

    String getTitle();

    String getOverview();

    String getRating();

    String getReleaseDate();
}
