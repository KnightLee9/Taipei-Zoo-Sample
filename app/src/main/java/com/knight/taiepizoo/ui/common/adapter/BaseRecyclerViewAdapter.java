package com.knight.taiepizoo.ui.common.adapter;

import android.arch.lifecycle.MutableLiveData;
import android.support.v7.widget.RecyclerView;

import com.knight.taiepizoo.model.ClickEvent;
import com.knight.taiepizoo.ui.common.viewholder.BaseRecyclerViewHolder;

import java.util.List;



abstract public class BaseRecyclerViewAdapter<DATA, VH extends BaseRecyclerViewHolder> extends RecyclerView.Adapter<VH> {
    private List<DATA> dataList;
    private MutableLiveData<ClickEvent> itemClickObserver = new MutableLiveData<>();

    public MutableLiveData<ClickEvent> getItemClickObserver() {

        return itemClickObserver;
    }

    public BaseRecyclerViewAdapter setItemClickObserver(MutableLiveData<ClickEvent> itemClickObserver) {
        this.itemClickObserver = itemClickObserver;
        return this;
    }

    public void setData(List<DATA> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    public DATA getData(int index) {
        if (dataList == null || index >= dataList.size()) return null;

        return dataList.get(index);
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.bind(getData(position));
    }

    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

}
