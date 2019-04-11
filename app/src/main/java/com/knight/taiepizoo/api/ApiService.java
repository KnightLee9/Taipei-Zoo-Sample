package com.knight.taiepizoo.api;

import android.arch.lifecycle.LiveData;

import com.knight.taiepizoo.api.response.GetAreaInfoResponse;
import com.knight.taiepizoo.api.response.GetPlantListResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET(ApiPath.GET_AREA_INFO)
    LiveData<ApiResponse<GetAreaInfoResponse>> getAreaInfo(@Query("limit") int limit, @Query("offset") int offset);

    @GET(ApiPath.GET_PLAN_LIST)
    LiveData<ApiResponse<GetPlantListResponse>> getPlantList( @Query("q") String keyword,  @Query("limit") int limit, @Query("offset") int offset);
}
