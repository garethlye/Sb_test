package com.my.sb_test;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.my.sb_test.api.DataApi;
import com.my.sb_test.databinding.ActivityMainBinding;
import com.my.sb_test.viewModel.MainViewModel;
import com.my.sb_test.viewModel.MainViewModelImpl;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.container)
    View container;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @Inject
    DataApi mDataApi;

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SBApplication.getInstance()
                .getApplicationComponent()
                .inject(this);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        bindData();
        setup();
        mViewModel.getListData(1);
    }

    private void bindData() {
        mViewModel = new MainViewModelImpl(MainActivity.this, mSwipeRefreshLayout, mRecyclerView, mDataApi);
        ActivityMainBinding binding = DataBindingUtil.bind(container);
        binding.setViewModel(mViewModel);
    }

    private void setup() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        mViewModel.onRefresh();
    }
}
