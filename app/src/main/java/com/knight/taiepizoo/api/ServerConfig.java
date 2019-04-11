package com.knight.taiepizoo.api;

import android.util.Log;


public class ServerConfig  {
    private static final String TAG = ServerConfig.class.getSimpleName();


    private static final String BASE_URL = "https://data.taipei/";


    public static String getBaseUrl() {
        String baseUrl = BASE_URL;
        Log.e(TAG, "Base url = " + baseUrl);

        return baseUrl;
    }

}
