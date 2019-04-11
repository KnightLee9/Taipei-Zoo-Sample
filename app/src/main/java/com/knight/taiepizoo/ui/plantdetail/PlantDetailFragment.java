package com.knight.taiepizoo.ui.plantdetail;

import android.os.Bundle;

import com.knight.taiepizoo.R;
import com.knight.taiepizoo.databinding.PlantDetailFragmentBinding;
import com.knight.taiepizoo.model.PlantInfo;
import com.knight.taiepizoo.ui.common.BaseFragment;
import com.knight.taiepizoo.utils.ViewUtils;

public class PlantDetailFragment extends BaseFragment<PlantDetailFragmentViewModel, PlantDetailFragmentBinding> {
    private static final String KEY_PLANT_INFO = "KEY_PLANT_INFO";

    public static Bundle buildArgument(PlantInfo info) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY_PLANT_INFO, info);
        return bundle;
    }

    @Override
    public int getLayoutId() {
        return R.layout.plant_detail_fragment;
    }

    @Override
    public PlantDetailFragmentViewModel onCreateViewModel() {
        return new PlantDetailFragmentViewModel();
    }

    @Override
    protected void initView() {
        if(getArguments() == null) return;

        PlantInfo plantInfo = (PlantInfo) getArguments().getSerializable(KEY_PLANT_INFO);
        setTitle(plantInfo.getName());
        showBackBtn(true);

        ViewUtils.loadImage(getDataBinding().imgPlant, plantInfo.getImgUrl());
        getDataBinding().textName.setText(plantInfo.getName());
        getDataBinding().textNameEn.setText(plantInfo.getNameEn());
        getDataBinding().textAlsoKnown.setText(plantInfo.getAlsoKnown());
        getDataBinding().textBrief.setText(plantInfo.getBrief());
        getDataBinding().textFeature.setText(plantInfo.getFeature());
        getDataBinding().textFunction.setText(plantInfo.getFunction());
        getDataBinding().textLastUpdate.setText(plantInfo.getLastUpdate());
    }


}
