package com.knight.taiepizoo.ui.arealist;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.knight.taiepizoo.R;
import com.knight.taiepizoo.databinding.ListItemAreaInfoBinding;
import com.knight.taiepizoo.model.AreaInfo;
import com.knight.taiepizoo.ui.common.adapter.BaseRecyclerViewAdapter;

public class AreaListViewAdapter extends BaseRecyclerViewAdapter<AreaInfo, AreaListItemViewHolder> {

    @NonNull
    @Override
    public AreaListItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater =
            LayoutInflater.from(parent.getContext());
        ListItemAreaInfoBinding itemBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.list_item_area_info, parent, false);
        return (AreaListItemViewHolder) new AreaListItemViewHolder(itemBinding).itemClickObserver(getItemClickObserver());
    }
}
