package com.knight.taiepizoo.api.response;

import com.knight.taiepizoo.model.PlantInfo;

import java.util.List;

public class GetPlantListResponse {
    PageResponse<List<PlantInfo>> result;

    public PageResponse<List<PlantInfo>> getResult() {
        return result;
    }
}
