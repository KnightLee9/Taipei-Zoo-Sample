package com.knight.taiepizoo.api.response;

public class PageResponse<T> {
    private int limit;
    private int offset;
    private int count;
    private T results;

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }

    public T getResults() {
        return results;
    }
}
