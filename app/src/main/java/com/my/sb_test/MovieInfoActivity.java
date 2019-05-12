package com.my.sb_test;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.my.sb_test.data.Movie;
import com.my.sb_test.databinding.ActivityMovieInfoBinding;
import com.my.sb_test.viewModel.MovieInfoViewModel;
import com.my.sb_test.viewModel.MovieInfoViewModelImpl;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.synnapps.carouselview.CarouselView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieInfoActivity extends AppCompatActivity {

    private static final String EXTRA_PREFIX = MovieInfoActivity.class.getName() + ".";
    private static final String EXTRA_MOVIE  = EXTRA_PREFIX + "EXTRA_MOVIE";

    @BindView(R.id.container)
    View container;

    @BindView(R.id.carousel_view)
    CarouselView mCarouselView;

    private Movie  mMovie;
    private Bitmap mImageBitmap;
    private MovieInfoViewModel mViewModel;

    public static void start(final Context context, final Movie movie) {
        Intent intent = new Intent(context, MovieInfoActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_info);
        ButterKnife.bind(this);
        getInputData();
        loadImage();
    }

    private void getInputData() {
        mMovie = getIntent().getParcelableExtra(EXTRA_MOVIE);
    }

    private void loadImage() {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.loadImage(AppConstants.imageUrlPath.concat(mMovie.poster_path()), new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                mImageBitmap = loadedImage;
                bindData();
            }
        });
    }

    private void bindData() {
        new Handler().postDelayed(() -> {
            mViewModel = new MovieInfoViewModelImpl(MovieInfoActivity.this, mMovie, mCarouselView);
            ActivityMovieInfoBinding binding = DataBindingUtil.bind(container);
            binding.setViewModel(mViewModel);
        }, 200);

    }

    public Bitmap getImageBitmap() {
        return mImageBitmap;
    }

    public MovieInfoViewModel getViewModel(){
        return mViewModel;
    }

}
