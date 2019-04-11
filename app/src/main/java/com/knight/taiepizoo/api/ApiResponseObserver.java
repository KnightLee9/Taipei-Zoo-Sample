package com.knight.taiepizoo.api;

import com.knight.taiepizoo.model.Resource;


public interface ApiResponseObserver {
    Resource handleApiResponse(ApiResponse response);
}
