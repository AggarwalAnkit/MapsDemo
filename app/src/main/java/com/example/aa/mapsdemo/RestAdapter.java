package com.example.aa.mapsdemo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by aa on 26/02/17.
 */

public class RestAdapter {

    private static final String API_ROOT = "http://sourabh.me";
    private static final int TIMEOUT_MILLISECONDS = 60000;
    private static RestApi mRestApi;

    private RestAdapter() {
    }

    public static RestApi getRestAdapter() {
        if (mRestApi == null) {
            synchronized (RestAdapter.class) {
                if (mRestApi == null) {
                    mRestApi = new Retrofit.Builder()
                            .baseUrl(API_ROOT)
                            .client(getOkHttpClient())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                            .create(RestApi.class);
                }
            }
        }
        return mRestApi;
    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT_MILLISECONDS, TimeUnit.MILLISECONDS)
                .addInterceptor(getHttpLoggingInterceptor())
                .build();
    }

    private static HttpLoggingInterceptor getHttpLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }
}
