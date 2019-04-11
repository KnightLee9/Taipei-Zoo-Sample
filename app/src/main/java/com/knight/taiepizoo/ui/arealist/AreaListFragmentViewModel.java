package com.knight.taiepizoo.ui.arealist;

import com.knight.taiepizoo.api.ApiRequestType;
import com.knight.taiepizoo.api.ApiResponse;
import com.knight.taiepizoo.api.response.GetAreaInfoResponse;
import com.knight.taiepizoo.model.AreaInfo;
import com.knight.taiepizoo.model.Resource;
import com.knight.taiepizoo.viewmodel.LoadMoreViewModel;

public class AreaListFragmentViewModel extends LoadMoreViewModel<AreaInfo> {

    @Override
    public void loadPageData(int limit, int offset) {
        addCall(getApiServiceManager().getAreaInfo(limit, offset), ApiRequestType.GET_AREA_INFO);
    }

    @Override
    public Resource handleApiResponse(ApiResponse response) {
        switch (response.getRequestType()) {
            case GET_AREA_INFO:
                if (response.isSuccessful()) {
                    GetAreaInfoResponse areaInfoResponse = ((GetAreaInfoResponse) response.getBody());
                    onDataLoaded(areaInfoResponse.getResult(), areaInfoResponse.getResult().getResults());
                    return null;
                }
                break;
        }
        return super.handleApiResponse(response);
    }
}
