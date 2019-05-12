package com.my.sb_test.viewModel;

import android.databinding.ObservableBoolean;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.my.sb_test.AppConstants;
import com.my.sb_test.MainActivity;
import com.my.sb_test.R;
import com.my.sb_test.Util.EndlessOnScrollRecyclerViewListener;
import com.my.sb_test.adapter.PopularMovieAdapter;
import com.my.sb_test.api.DataApi;
import com.my.sb_test.data.Movie;

import java.util.ArrayList;
import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by G-Man garethlye on 2019-05-11
 */
public class MainViewModelImpl implements MainViewModel {

    private MainActivity       mContext;
    private DataApi            mDataApi;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView       mRecyclerView;
    private List<Movie>        mMovieList = new ArrayList<>();

    private ObservableBoolean mCurrentlyLoading = new ObservableBoolean(false);

    public MainViewModelImpl(final MainActivity context,
                             final SwipeRefreshLayout swipeRefreshLayout,
                             final RecyclerView recyclerView,
                             final DataApi dataApi) {
        mContext = context;
        mSwipeRefreshLayout = swipeRefreshLayout;
        mRecyclerView = recyclerView;
        mDataApi = dataApi;
    }

    private void stopLoading() {
        //delayed loading state transition
        new Handler().postDelayed(() -> mCurrentlyLoading.set(false), 500);
    }

    @Override
    public void getListData(final Integer page) {
        if (!mCurrentlyLoading.get()) {
            mCurrentlyLoading.set(true);
            mDataApi.getData(AppConstants.apiKey,
                    "en-US",
                    page)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnTerminate(() -> stopLoading())
                    .subscribe(popularMovieResponse -> {
                        Toast.makeText(mContext, mContext.getString(R.string.loading_text), Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                        if (page == 1) {
                            setupRecyclerView(popularMovieResponse.results());
                            return;
                        }
                        mMovieList.addAll(popularMovieResponse.results());
                    }, throwable -> {
                        Toast.makeText(mContext, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        mSwipeRefreshLayout.setRefreshing(false);
                    });
        }
    }

    private void setupRecyclerView(final List<Movie> movieList) {
        mMovieList.clear();
        mMovieList.addAll(movieList);
        PopularMovieAdapter adapter = new PopularMovieAdapter(mContext, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new EndlessOnScrollRecyclerViewListener(staggeredGridLayoutManager, this) {
            @Override
            public void onLoadNextPage(final int page, final int totalItemsCount) {
                getListData(page);
            }
        });
    }

    @Override
    public void onRefresh() {
        //remove all views
        //show loader
        getListData(1);
    }

    @Override
    public boolean getIsLoading() {
        return mCurrentlyLoading.get();
    }

    @Override
    public List<Movie> getMovieList() {
        return mMovieList;
    }
}
