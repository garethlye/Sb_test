package com.my.sb_test.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.my.sb_test.AppConstants;
import com.my.sb_test.MainActivity;
import com.my.sb_test.R;
import com.my.sb_test.Util.Util;
import com.my.sb_test.databinding.ViewPopularMovieItemBinding;
import com.my.sb_test.viewModel.MainViewModel;
import com.my.sb_test.viewModel.PopularMovieItemViewModel;
import com.my.sb_test.viewModel.PopularMovieItemViewModelImpl;

/**
 * Created by G-Man garethlye on 2019-05-11
 */
public class PopularMovieAdapter extends RecyclerView.Adapter {

    private final MainActivity  mContext;
    private final MainViewModel mViewModel;

    public PopularMovieAdapter(final MainActivity context, final MainViewModel mainViewModel) {
        mContext = context;
        mViewModel = mainViewModel;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        if (mViewModel.getMovieList().get(i).backdrop_path() != null && !TextUtils.isEmpty(mViewModel.getMovieList().get(i).title())) {
            new Util.DownloadImageTask(((ImageView) viewHolder.itemView.findViewById(R.id.imageView)))
                    .execute(AppConstants.imageUrlPath.concat(mViewModel.getMovieList().get(i).backdrop_path()));
            ((TextView) viewHolder.itemView.findViewById(R.id.title)).setText(mViewModel.getMovieList().get(i).title());

            PopularMovieItemViewModel viewModel = new PopularMovieItemViewModelImpl(mContext, mViewModel.getMovieList().get(i));
            ViewPopularMovieItemBinding binding = DataBindingUtil.bind(viewHolder.itemView.findViewById(R.id.container));
            binding.setViewModel(viewModel);
        }
    }

    @NonNull
    @Override
    public MovieItemViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_popular_movie_item, viewGroup, false);
        return new MovieItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return mViewModel.getMovieList() == null ? 0 : mViewModel.getMovieList().size();
    }

    public class MovieItemViewHolder extends RecyclerView.ViewHolder {
        public MovieItemViewHolder(final View itemView) {
            super(itemView);
        }
    }


}
