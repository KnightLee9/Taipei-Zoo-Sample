package com.knight.taiepizoo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.knight.taiepizoo.api.response.PageResponse;
import com.knight.taiepizoo.ui.common.LoadMoreHandler;

import java.util.ArrayList;
import java.util.List;


public abstract class LoadMoreViewModel<DATA> extends BaseViewModel implements LoadMoreHandler<DATA> {
    private MutableLiveData<List<DATA>> dataList = new MutableLiveData();

    private int PAGE_SIZE = 10;
    private int totalCount = -1;
    private boolean dataLoading = false;

    private boolean isLoaded = false;

    @Override
    public synchronized void onLoadMore(boolean isInitLoading) {
        if (isLoading()) return;
        if (isInitLoading) {
            resetPage();
        }
        if (moreToLoad()) {
            setDataLoading(true);
            loadPageData(PAGE_SIZE, getListCount());
        }
    }

    private int getListCount() {
        return dataList.getValue() == null ? 0 : dataList.getValue().size();
    }


    @Override
    public boolean moreToLoad() {
        return !isLoaded || getListCount() < totalCount;
    }


    private void resetPage() {
        isLoaded = false;
        totalCount = -1;
        dataList.setValue((List<DATA>) new ArrayList<>());
    }

    @Override
    public void setPageSize(int pageSize) {
        PAGE_SIZE = pageSize;
    }

    @Override
    public int getPageSize() {
        return PAGE_SIZE;
    }

    @Override
    public void onDataLoaded(PageResponse pageResponse, List<DATA> dataList) {
        setDataLoading(false);
        isLoaded = true;
        totalCount = pageResponse.getCount();
        List<DATA> dataListValue = this.dataList.getValue();
        if (dataList != null || dataList.size() > 0) {
            dataListValue.addAll(dataList);
        }

        this.dataList.setValue(dataListValue);
    }

    @Override
    public void onLoadError(String message) {
        setDataLoading(false);
    }

    @Override
    public boolean isLoading() {
        return dataLoading;
    }


    public void setDataLoading(boolean dataLoading) {
//        if(dataLoading) {
//            notifyResourceChange(new Resource(ResourceType.LOAD_PAGE_DATA_START, Status.SUCCESS, null, null));
//        } else {
//            notifyResourceChange(new Resource(ResourceType.LOAD_PAGE_DATA_END, Status.SUCCESS, null, null));
//        }
        this.dataLoading = dataLoading;
    }

    @Override
    public LiveData<List<DATA>> getDataList() {
        return dataList;
    }

    public void updateDataList(List<DATA> list) {
        dataList.setValue(list);
    }


}
