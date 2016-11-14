package com.example.julioc98.fcmfutebol.services;

import android.util.Log;
import android.widget.Toast;

import com.example.julioc98.fcmfutebol.api.UserAPI;
import com.example.julioc98.fcmfutebol.model.ResponseAPI;
import com.example.julioc98.fcmfutebol.model.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by julioc98 on 11/11/16.
 */

public class FirebaseIDService extends FirebaseInstanceIdService{
    @Override
    public void onTokenRefresh() {
        String refreshedToken =
                FirebaseInstanceId.getInstance().getToken();

        Log.i("TOKEN", refreshedToken);
        //enviarRegistroParaOServer(refreshedToken);
    }

    private void enviarRegistroParaOServer(String refreshedToken) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.100.59:3000")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        User user = new User();
        user.setName("Julio");
        user.setKey(refreshedToken);

        UserAPI api = retrofit.create(UserAPI.class);
        Call<ResponseAPI> call = api.create(user);

        call.enqueue(new Callback<ResponseAPI>() {
            @Override
            public void onResponse(Call<ResponseAPI> call, Response<ResponseAPI> response) {
                Toast.makeText(FirebaseIDService.this,
                        "Sucesso", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<ResponseAPI> call, Throwable t) {

                Toast.makeText(FirebaseIDService.this,
                        t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
