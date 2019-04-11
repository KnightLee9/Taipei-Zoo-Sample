package com.knight.taiepizoo.ui.arealist;

import android.text.TextUtils;
import android.view.View;

import com.knight.taiepizoo.databinding.ListItemAreaInfoBinding;
import com.knight.taiepizoo.model.AreaInfo;
import com.knight.taiepizoo.model.ClickEvent;
import com.knight.taiepizoo.model.ClickEventType;
import com.knight.taiepizoo.ui.common.viewholder.BaseRecyclerViewHolder;
import com.knight.taiepizoo.utils.ViewUtils;

public class AreaListItemViewHolder extends BaseRecyclerViewHolder<AreaInfo, ListItemAreaInfoBinding> {
    public AreaListItemViewHolder(ListItemAreaInfoBinding itemBinding) {
        super(itemBinding);
    }

    @Override
    public void bind(AreaInfo data) {
        super.bind(data);
        ViewUtils.loadImage(getItemBinding().imgArea, data.getImgUrl());
        getItemBinding().textTitle.setText(data.getTitle());
        getItemBinding().textSubTitle.setText(data.getInfo());
        if (!TextUtils.isEmpty(data.getMemo())) {
            getItemBinding().textMemo.setText(data.getMemo());
        }
        getItemBinding().rootView.setOnClickListener(this);
    }

    @Override
    protected ClickEvent getClickEventInfo(View view, int position) {
        return new ClickEvent(ClickEventType.LIST_ITEM_CLICK, getData());
    }
}
