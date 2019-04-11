package com.knight.taiepizoo.ui.areadetail;

import com.knight.taiepizoo.api.ApiRequestType;
import com.knight.taiepizoo.api.ApiResponse;
import com.knight.taiepizoo.api.response.GetPlantListResponse;
import com.knight.taiepizoo.model.AreaInfo;
import com.knight.taiepizoo.model.PlantInfo;
import com.knight.taiepizoo.model.Resource;
import com.knight.taiepizoo.viewmodel.LoadMoreViewModel;

public class AreaDetailViewModel extends LoadMoreViewModel<PlantInfo> {
    private AreaInfo areaInfo;

    public AreaDetailViewModel() {
        areaInfo = new AreaInfo();
    }

    @Override
    public void loadPageData(int limit, int offset) {
        addCall(getApiServiceManager().getPlantList(getAreaInfo().getTitle(), limit, offset), ApiRequestType.GET_PLANT_LIST);
    }

    @Override
    public Resource handleApiResponse(ApiResponse response) {
        switch (response.getRequestType()) {
            case GET_PLANT_LIST:
                if (response.isSuccessful()) {
                    GetPlantListResponse listResponse = ((GetPlantListResponse) response.getBody());
                    onDataLoaded(listResponse.getResult(), listResponse.getResult().getResults());
                    return null;
                }
                break;
        }
        return super.handleApiResponse(response);
    }

    public void setInfo(AreaInfo info) {
        this.areaInfo = info;
    }

    public AreaInfo getAreaInfo() {
        return areaInfo;
    }
}
