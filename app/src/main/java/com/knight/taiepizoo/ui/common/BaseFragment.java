package com.knight.taiepizoo.ui.common;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.knight.taiepizoo.R;
import com.knight.taiepizoo.model.Resource;
import com.knight.taiepizoo.ui.MainActivity;
import com.knight.taiepizoo.utils.ViewUtils;
import com.knight.taiepizoo.viewmodel.BaseViewModel;




public abstract class BaseFragment<VIEW_MODEL extends BaseViewModel, DATA_BINDING extends ViewDataBinding> extends Fragment implements ResourceObserver {

    DATA_BINDING dataBinding;
    VIEW_MODEL viewModel;
    private Observer<Resource> resourceObserver;

    public abstract int getLayoutId();

    public BaseFragment() {
        resourceObserver = resource -> onResourceChange(resource);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        dataBinding = DataBindingUtil
            .inflate(inflater, getLayoutId(), container, false,
                null);
        dataBinding.setLifecycleOwner(this);
        viewModel = onCreateViewModel();
        viewModel.getResourceObserver().observe(this, resourceObserver);

        return dataBinding.getRoot();
    }

    public abstract VIEW_MODEL onCreateViewModel();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    protected abstract void initView();

    public DATA_BINDING getDataBinding() {
        return dataBinding;
    }

    public VIEW_MODEL getViewModel() {
        return viewModel;
    }


    protected void showLoadingProgress(boolean show) {
       Activity activity = getActivity();
       if(activity == null) return;
       if(activity instanceof MainActivity) {
           ((MainActivity) activity).showLoading(show);
       }

    }


    public void onResourceChange(Resource resource) {
        switch (resource.getType()) {
            case SHOW_LOADING_PROGRESS:
                showLoadingProgress((Boolean) resource.getData());
                break;
        }
        if (!resource.isSuccess()) {
            String errorMessage = String.format("%s:\n%s ", resource.getType().toString(), resource.getMessage());
            ViewUtils.showToast(getContext(), errorMessage);
        }
    }

    public void commitFragment(Fragment fragment, String name, boolean isRoot, Bundle argument) {
        if (argument != null) {
            fragment.setArguments(argument);
        }
        boolean firstToCommit = getFragmentManager().getFragments() == null ? true : getFragmentManager().getFragments().size() > 0 ? false : true;
        if (isRoot) {
            clearBackStack();
        }

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        if (isRoot | firstToCommit) {
            ft.replace(R.id.container, fragment)
                .commitAllowingStateLoss();
        } else {
            ft
                .replace(R.id.container, fragment)
                .addToBackStack(name)
                .commitAllowingStateLoss();
        }
    }

    private void clearBackStack() {
        if (getFragmentManager().getFragments() == null || getFragmentManager().getBackStackEntryCount() <= 0)
            return;
        FragmentManager.BackStackEntry entry = getFragmentManager().getBackStackEntryAt(
            0);
        getFragmentManager().popBackStack(entry.getId(),
            FragmentManager.POP_BACK_STACK_INCLUSIVE);
        getFragmentManager().executePendingTransactions();
    }

    public void setTitle( int stringId) {
        Activity activity = getActivity();
        if(activity == null) return;
        activity.setTitle(stringId);
    }

    public void setTitle(String title) {
        Activity activity = getActivity();
        if(activity == null) return;
        activity.setTitle(title);
    }

    public void showBackBtn(boolean show) {
        Activity activity = getActivity();
        if(activity == null) return;
        ((AppCompatActivity)activity).getSupportActionBar().setDisplayHomeAsUpEnabled(show);
    }
}
