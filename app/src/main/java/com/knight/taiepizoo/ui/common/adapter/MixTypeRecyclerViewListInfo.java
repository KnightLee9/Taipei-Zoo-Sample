package com.knight.taiepizoo.ui.common.adapter;

import android.arch.lifecycle.MutableLiveData;

public class MixTypeRecyclerViewListInfo<VT extends Enum<VT>>{
    VT viewType ;
    int orgIndex = 0;
    Object data = null;
    MutableLiveData<Integer> itemClickOberver;
    public VT getViewType() {
        return viewType;
    }

    public MixTypeRecyclerViewListInfo setViewType(VT viewType) {
        this.viewType = viewType;
        return this;
    }

    public int getOrgIndex() {
        return orgIndex;
    }

    public MixTypeRecyclerViewListInfo setOrgIndex(int orgIndex) {
        this.orgIndex = orgIndex;
        return this;
    }

    public MutableLiveData<Integer> getItemClickOberver() {
        return itemClickOberver;
    }


    public Object getData() {
        return data;
    }

    public MixTypeRecyclerViewListInfo setData(Object data) {
        this.data = data;
        return this;
    }

    public MixTypeRecyclerViewListInfo setItemClickOberver(MutableLiveData<Integer> itemClickOberver) {
        this.itemClickOberver = itemClickOberver;
        return this;
    }
}
