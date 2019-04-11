package com.knight.taiepizoo.ui.common.viewholder;


import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;

import com.knight.taiepizoo.model.ClickEvent;
import com.knight.taiepizoo.model.ClickEventType;



abstract public class BaseRecyclerViewHolder<T, BV extends ViewDataBinding> extends RecyclerView.ViewHolder implements OnClickListener {
    private MutableLiveData<ClickEvent> itemClickObserver;
    private T data;
    private BV itemBinding;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
    }
    public BaseRecyclerViewHolder(BV itemBinding) {
        super(itemBinding.getRoot());
        this.itemBinding = itemBinding;
    }

    public BV getItemBinding() {
        return itemBinding;
    }

    public BaseRecyclerViewHolder<T, BV> itemClickObserver(MutableLiveData<ClickEvent> itemClickObserver) {
        this.itemClickObserver = itemClickObserver;
        return this;
    }

    public Context getContext() {
        return itemView == null ? null : itemView.getContext();
    }

    @Override
    public void onClick(View view) {
        if (itemClickObserver == null) return;
        ClickEvent event = getClickEventInfo(view, getAdapterPosition());
        if(event == null) return;
        itemClickObserver.setValue(event);
    }

    public void setClickEvent(ClickEvent event) {
        if (itemClickObserver == null) return;
        itemClickObserver.setValue(event);
    }

    protected ClickEvent getClickEventInfo(View view, int position ){
         return new ClickEvent(ClickEventType.LIST_ITEM_CLICK, position);
    }

    public void bind(T data){
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public MutableLiveData<ClickEvent> getItemClickObserver() {
        return itemClickObserver;
    }
}
