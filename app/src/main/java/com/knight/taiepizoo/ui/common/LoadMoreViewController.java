package com.knight.taiepizoo.ui.common;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class LoadMoreViewController<HANDLER extends LoadMoreHandler> extends RecyclerView.OnScrollListener {
    LoadMoreHandler loadMoreHandler;

    public LoadMoreViewController(LoadMoreHandler loadMoreHandler) {
        this.loadMoreHandler = loadMoreHandler;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);


        if (!loadMoreHandler.isLoading() && isScrollToEnd(recyclerView) && loadMoreHandler.moreToLoad()) {
            loadMoreHandler.onLoadMore(false);
        }
    }



    public boolean isScrollToEnd(RecyclerView recyclerView) {
        int visibleCount = recyclerView.getChildCount();
        int itemCount = recyclerView.getLayoutManager().getItemCount();
        int firstVisibleItem = findFirstVisibleItemPos(recyclerView.getLayoutManager());
        return firstVisibleItem + visibleCount >= itemCount;
    }

    private int findFirstVisibleItemPos(RecyclerView.LayoutManager layoutManager) {
        if(layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition();
        }
        return 0;
    }
}
