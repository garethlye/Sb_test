package com.my.sb_test.Util;

import android.databinding.ObservableBoolean;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.my.sb_test.viewModel.MainViewModel;

import java.util.List;

/**
 * Created by G-Man garethlye on 2019-05-12
 */
public abstract class EndlessOnScrollRecyclerViewListener  extends RecyclerView.OnScrollListener {

    // The minimum amount of mItems to have below your current scroll position
    // before loading more.
    private static final int VISIBLE_THRESHOLD      = 1;

    // Sets the starting page index
    private static final int STARTING_PAGE_INDEX    = 1;

    // The current offset index of data you have loaded
    private              int currentPage            = 1;

    // The total number of items in the dataset after the last load
    private              int previousTotalItemCount = 0;

    // True if has more data
    private boolean mHasMore = true;

    private StaggeredGridLayoutManager mLayoutManager;

    // check if we are still waiting for the last set of data to load.
    private MainViewModel mViewModel;

    public EndlessOnScrollRecyclerViewListener(StaggeredGridLayoutManager layoutManager,
                                               MainViewModel mainViewModel) {
        this.mLayoutManager = layoutManager;
        this.mViewModel = mainViewModel;
    }

    public int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int maxSize = 0;
        for (int i = 0; i < lastVisibleItemPositions.length; i++) {
            if (i == 0) {
                maxSize = lastVisibleItemPositions[i];
            } else if (lastVisibleItemPositions[i] > maxSize) {
                maxSize = lastVisibleItemPositions[i];
            }
        }
        return maxSize;
    }

    @Override
    public void onScrolled(RecyclerView view, int dx, int dy) {
        //int lastVisibleItem = mLayoutManager.get();
        int[] lastVisibleItemPositions = mLayoutManager.findLastVisibleItemPositions(null);
        // get maximum element within the list
        int lastVisibleItemPosition = getLastVisibleItem(lastVisibleItemPositions);
        int totalItemCount = mViewModel.getMovieList().size();

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount == previousTotalItemCount - 1) {
            mHasMore = false;
        }

        if (totalItemCount <= 1) {
            currentPage = STARTING_PAGE_INDEX;
            mHasMore = true;
        }
        previousTotalItemCount = totalItemCount;

        // If it isn’t currently loading, we check to see if we have breached
        // the visibleThreshold and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        if (totalItemCount > 1
                && mHasMore
                && totalItemCount == lastVisibleItemPosition + 1
                && !mViewModel.getIsLoading()
                && dy > 0) {
            currentPage++;
            onLoadNextPage(currentPage, totalItemCount);
        }
    }

    public abstract void onLoadNextPage(int page, int totalItemsCount);
}
