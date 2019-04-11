package com.knight.taiepizoo.api.response;

import com.knight.taiepizoo.model.AreaInfo;

import java.util.List;

public class GetAreaInfoResponse {
    PageResponse<List<AreaInfo>> result;

    public PageResponse<List<AreaInfo>> getResult() {
        return result;
    }
}
