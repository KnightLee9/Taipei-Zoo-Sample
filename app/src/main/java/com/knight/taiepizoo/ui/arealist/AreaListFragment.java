package com.knight.taiepizoo.ui.arealist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.knight.taiepizoo.R;
import com.knight.taiepizoo.databinding.RecyclerViewFragmentBinding;
import com.knight.taiepizoo.model.AreaInfo;
import com.knight.taiepizoo.ui.areadetail.AreaDetailFragment;
import com.knight.taiepizoo.ui.common.BaseFragment;
import com.knight.taiepizoo.ui.common.LoadMoreViewController;
import com.knight.taiepizoo.utils.ViewUtils;

public class AreaListFragment extends BaseFragment<AreaListFragmentViewModel, RecyclerViewFragmentBinding> {
    public int getLayoutId() {
        return R.layout.recycler_view_fragment;
    }

    @Override
    public AreaListFragmentViewModel onCreateViewModel() {
        return new AreaListFragmentViewModel();
    }

    @Override
    protected void initView() {
        AreaListViewAdapter adapter = new AreaListViewAdapter();
        getDataBinding().recyclerView.setAdapter(adapter);
        getDataBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getDataBinding().recyclerView.addOnScrollListener(new LoadMoreViewController<>(getViewModel()));
        adapter.getItemClickObserver().observe(this, clickEvent -> {
            commitFragment(new AreaDetailFragment(),  "", false, AreaDetailFragment.buildArgument((AreaInfo)clickEvent.getEventData()));
        });

        setTitle(R.string.title_area_list);
        showBackBtn(false);


    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getViewModel().onLoadMore(true);
        getViewModel().getDataList().observe(this, list -> {
            ((AreaListViewAdapter)getDataBinding().recyclerView.getAdapter()).setData(list);
        });

    }


}
