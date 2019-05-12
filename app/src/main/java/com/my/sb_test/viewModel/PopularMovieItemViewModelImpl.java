package com.my.sb_test.viewModel;

import android.view.View;

import com.my.sb_test.MainActivity;
import com.my.sb_test.MovieInfoActivity;
import com.my.sb_test.data.Movie;

/**
 * Created by G-Man garethlye on 2019-05-11
 */
public class PopularMovieItemViewModelImpl implements PopularMovieItemViewModel {

    private Movie        mMovie;
    private MainActivity mContext;

    public PopularMovieItemViewModelImpl(final MainActivity context, final Movie movie) {
        mContext = context;
        mMovie = movie;
    }

    @Override
    public void onItemClicked(final View view) {
        MovieInfoActivity.start(mContext, mMovie);
    }

}
