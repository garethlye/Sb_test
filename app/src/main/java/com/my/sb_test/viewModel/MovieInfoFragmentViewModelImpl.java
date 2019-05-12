package com.my.sb_test.viewModel;

import android.content.Context;

import com.my.sb_test.AppConstants;
import com.my.sb_test.data.Movie;

/**
 * Created by G-Man garethlye on 2019-05-12
 */
public class MovieInfoFragmentViewModelImpl implements MovieInfoFragmentViewModel {

    private Context context;
    private Movie   mMovie;

    public MovieInfoFragmentViewModelImpl(final Context context, final Movie movie) {
        this.context = context;
        mMovie = movie;
    }

    @Override
    public String getTitle() {
        return mMovie.title();
    }

    @Override
    public String getOverview() {
        return mMovie.overview();
    }

    @Override
    public String getRating() {
        return String.valueOf(mMovie.vote_average()).concat(AppConstants.overTen);
    }

    @Override
    public String getReleaseDate() {
        return mMovie.release_date();
    }
}
