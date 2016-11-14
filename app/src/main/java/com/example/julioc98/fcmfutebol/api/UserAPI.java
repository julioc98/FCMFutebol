package com.example.julioc98.fcmfutebol.api;

import com.example.julioc98.fcmfutebol.model.ResponseAPI;
import com.example.julioc98.fcmfutebol.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by julioc98 on 11/11/16.
 */

public interface UserAPI {

    @POST("/users/new")
    Call<ResponseAPI> create(@Body User user);
}
