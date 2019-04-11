package com.knight.taiepizoo.ui.areadetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.knight.taiepizoo.R;
import com.knight.taiepizoo.databinding.RecyclerViewFragmentBinding;
import com.knight.taiepizoo.model.AreaInfo;
import com.knight.taiepizoo.model.PlantInfo;
import com.knight.taiepizoo.ui.common.BaseFragment;
import com.knight.taiepizoo.ui.common.LoadMoreViewController;
import com.knight.taiepizoo.ui.plantdetail.PlantDetailFragment;
import com.knight.taiepizoo.ui.webview.WebViewFragment;

public class AreaDetailFragment extends BaseFragment<AreaDetailViewModel, RecyclerViewFragmentBinding> {
    private static final String KEY_AREA_INFO = "KEY_AREA_INFO";

    public static Bundle buildArgument(AreaInfo info) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_AREA_INFO, info);
        return bundle;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recycler_view_fragment;
    }

    @Override
    public AreaDetailViewModel onCreateViewModel() {
        return new AreaDetailViewModel();
    }

    @Override
    protected void initView() {
        AreaDetailViewAdapter adapter = new AreaDetailViewAdapter(getContext());
        getDataBinding().recyclerView.setAdapter(adapter);
        getDataBinding().recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        getDataBinding().recyclerView.addOnScrollListener(new LoadMoreViewController<>(getViewModel()));
        adapter.getClickEventObserver().observe(this, clickEvent -> {
            switch (clickEvent.getType()) {
                case LIST_ITEM_CLICK:
                    commitFragment(new PlantDetailFragment(),  "", false, PlantDetailFragment.buildArgument((PlantInfo) clickEvent.getEventData()));
                    break;
                case OPEN_URL:
                    commitFragment(new WebViewFragment(),  "", false, WebViewFragment.buildArgument((String) clickEvent.getEventData()));
                    break;
            }

        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getArguments() != null) {
            getViewModel().setInfo((AreaInfo)getArguments().getSerializable(KEY_AREA_INFO));
        }
        getViewModel().onLoadMore(true);
        getViewModel().getDataList().observe(this, list -> {
            ((AreaDetailViewAdapter)getDataBinding().recyclerView.getAdapter()).setPlantList(list);
        });
        ((AreaDetailViewAdapter)getDataBinding().recyclerView.getAdapter()).setAreaInfo(getViewModel().getAreaInfo());
        setTitle(getViewModel().getAreaInfo().getTitle());
        showBackBtn(true);
    }
}
