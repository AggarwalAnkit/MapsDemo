package com.example.aa.mapsdemo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by aa on 26/02/17.
 */

public interface RestApi {

    @FormUrlEncoded
    @POST("/tracking/device-location/")
    Call<BaseResponse> postLocation(@Field("latitude") double latitude,
                                    @Field("longitude") double longitude,
                                    @Field("created_at") String createdAt);

}
