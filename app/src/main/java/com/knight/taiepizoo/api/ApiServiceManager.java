package com.knight.taiepizoo.api;

import android.arch.lifecycle.LiveData;

import com.knight.taiepizoo.BuildConfig;
import com.knight.taiepizoo.api.response.GetAreaInfoResponse;
import com.knight.taiepizoo.api.response.GetPlantListResponse;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiServiceManager {
    private final static int CONNECTION_TIMEOUT_MS = 15 * 1000;
    private static ApiServiceManager mInstance;
    private ApiService apiService;

    public static ApiServiceManager getInstance() {
        if(mInstance == null) {
            mInstance = new ApiServiceManager();
        }
        return mInstance;
    }

    public ApiServiceManager() {
        apiService = getApiService(getOkHttpClient(), ServerConfig.getBaseUrl());
    }

    public OkHttpClient getOkHttpClient() {

        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
            builder.networkInterceptors().add(new UserAgentInterceptor(BuildConfig.APPLICATION_ID));
        }

        builder.connectTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS)
            .readTimeout(CONNECTION_TIMEOUT_MS, TimeUnit.MILLISECONDS);

        return builder.build();
    }

    public ApiService getApiService(OkHttpClient okHttpClient, String baseUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(okHttpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(new LiveDataCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create());
        return builder.build().create(ApiService.class);
    }


    public LiveData<ApiResponse<GetAreaInfoResponse>> getAreaInfo(int limit, int offset) {
        return apiService.getAreaInfo(limit, offset);
    }

    public LiveData<ApiResponse<GetPlantListResponse>> getPlantList(String keyword, int limit, int offset) {
        return apiService.getPlantList(keyword, limit, offset);
    }
}
