package com.knight.taiepizoo.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;

import com.knight.taiepizoo.api.ApiRequestType;
import com.knight.taiepizoo.api.ApiResponse;
import com.knight.taiepizoo.api.ApiResponseObserver;
import com.knight.taiepizoo.api.ApiServiceManager;
import com.knight.taiepizoo.model.Resource;
import com.knight.taiepizoo.model.ResourceType;
import com.knight.taiepizoo.model.Status;

public class BaseViewModel implements ApiResponseObserver {
    private MediatorLiveData<ApiResponse> apiResponseObserver;
    private MutableLiveData<Resource> resourceObserver;

    public BaseViewModel() {
        initObserver();
    }


    private void initObserver() {
        apiResponseObserver = new MediatorLiveData<>();
        resourceObserver = (MutableLiveData<Resource>) Transformations.switchMap(apiResponseObserver, apiResponse -> {
                Resource resource = this.handleApiResponse(apiResponse);
                MutableLiveData<Resource> data = new MutableLiveData();
                if (resource != null) {
                    data.setValue(resource);
                }
                return data;
            }
        );
    }
    public <T> void addCall(LiveData<ApiResponse<T>> call, ApiRequestType type) {
        resourceObserver.setValue(new Resource(ResourceType.SHOW_LOADING_PROGRESS, Status.SUCCESS, true, null));

        apiResponseObserver.addSource(call, apiResponse -> {
            apiResponseObserver.removeSource(call);
            resourceObserver.setValue(new Resource(ResourceType.SHOW_LOADING_PROGRESS, Status.SUCCESS, false, null));
            apiResponseObserver.setValue(apiResponse.setType(type));
        });
    }

    @Override
    public Resource handleApiResponse(ApiResponse response) {

        if (!response.isSuccessful()) {
            return new Resource(ResourceType.API_REQUEST, Status.ERROR, null, response.errorMessage);
        }
        return null;
    }

    public ApiServiceManager getApiServiceManager() {
        return ApiServiceManager.getInstance();
    }

    public LiveData<Resource> getResourceObserver() {
        return resourceObserver;
    }
}
