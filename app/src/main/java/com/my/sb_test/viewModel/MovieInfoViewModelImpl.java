package com.my.sb_test.viewModel;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;

import com.my.sb_test.MovieInfoActivity;
import com.my.sb_test.data.Movie;
import com.my.sb_test.fragment.MovieInfoFragment;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;


/**
 * Created by G-Man garethlye on 2019-05-12
 */
public class MovieInfoViewModelImpl implements MovieInfoViewModel {

    private MovieInfoActivity mContext;
    private Movie             mMovie;
    private CarouselView      mCarouselView;
    private MovieInfoFragment fragment;

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageBitmap(mContext.getImageBitmap());
        }
    };

    public MovieInfoViewModelImpl(final MovieInfoActivity context,
                                  final Movie movie,
                                  final CarouselView carouselView) {
        mContext = context;
        mMovie = movie;
        mCarouselView = carouselView;

        setupCarouselImageView();
    }

    private void setupCarouselImageView() {
        mCarouselView.setImageListener(imageListener);
        mCarouselView.setPageCount(5);
    }

    private void showMovieInfoFragment(Movie movie) {
        final FragmentManager manager = mContext.getSupportFragmentManager();
        fragment = MovieInfoFragment.newInstance(movie);
        try {
            fragment.show(manager, MovieInfoFragment.EXTRA_PREFIX);
        } catch (IllegalStateException e) {
            //in case of stateloss
        }
    }

    @Override
    public void onShowMoreClicked(final View view) {
        showMovieInfoFragment(mMovie);
    }
}
