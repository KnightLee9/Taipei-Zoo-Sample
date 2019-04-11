package com.knight.taiepizoo.ui.common;

import android.arch.lifecycle.LiveData;

import com.knight.taiepizoo.api.response.PageResponse;

import java.util.List;


public interface LoadMoreHandler<DATA> {
    void onLoadMore(boolean isInitLoading);
    void setPageSize(int pageSize);
    int getPageSize();
    void onDataLoaded(PageResponse pageResponse, List<DATA> dataList);
    void onLoadError(String message);
    void loadPageData(int limit, int offset);
    boolean isLoading();
    boolean moreToLoad();
    LiveData<List<DATA>> getDataList();
}
