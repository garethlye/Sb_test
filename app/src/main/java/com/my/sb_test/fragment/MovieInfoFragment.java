package com.my.sb_test.fragment;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.my.sb_test.MovieInfoActivity;
import com.my.sb_test.R;
import com.my.sb_test.Util.CustomOnSlidingTouchListener;
import com.my.sb_test.data.Movie;
import com.my.sb_test.databinding.FragmentMovieInfoBinding;
import com.my.sb_test.viewModel.MovieInfoFragmentViewModel;
import com.my.sb_test.viewModel.MovieInfoFragmentViewModelImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieInfoFragment extends DialogFragment {

    public static final  String EXTRA_PREFIX = MovieInfoFragment.class.getName() + ".";
    private static final String EXTRA_MOVIE  = EXTRA_PREFIX + "EXTRA_MOVIE";

    @BindView(R.id.container)
    View container;

    private Movie mMovie;

    public MovieInfoFragment() {

    }

    public static MovieInfoFragment newInstance(final Movie movie) {
        MovieInfoFragment fragment = new MovieInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovie = getArguments().getParcelable(EXTRA_MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie_info, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        bindData();
        view.setOnTouchListener(new CustomOnSlidingTouchListener(getActivity()) {
            @Override
            public boolean onSlideDown() {
                MovieInfoFragment.this.dismiss();
                return true;
            }
        });
    }

    private void bindData() {
        MovieInfoFragmentViewModel viewModel = new MovieInfoFragmentViewModelImpl(getActivity(), mMovie);
        FragmentMovieInfoBinding binding = DataBindingUtil.bind(container);
        binding.setViewModel(viewModel);
    }

    @Override
    public void onStart() {
        super.onStart();
        final Dialog dialog = getDialog();
        final FragmentActivity activity = getActivity();
        if (dialog != null && activity != null) {
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        }
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final Dialog dialog = getDialog();
        final Window window = dialog == null ? null : dialog.getWindow();
        if (window != null) {
            window.getAttributes().windowAnimations = R.style.SlideDialogAnimation;
        }
    }
}
