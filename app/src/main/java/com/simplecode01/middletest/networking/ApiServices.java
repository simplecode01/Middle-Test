package com.simplecode01.middletest.networking;


import com.simplecode01.middletest.model.ResponseLogin;
import com.simplecode01.middletest.model.ResponseRegister;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {
    @FormUrlEncoded
    @POST("signup.php")
    Call<ResponseRegister> registerUser(
            @Field("kode_user") int str_kode,
            @Field("namalengkap_user") String str_namalengkap,
            @Field("username_user") String jeniskelamin,
            @Field("password_user") String str_username,
            @Field("email_user") String str_password,
            @Field("phone_user") String str_email,
            @Field("jeniskelamin_user") String str_kelamin
    );

    @FormUrlEncoded
    @POST("signin.php")
    Call<ResponseLogin> loginUser(
            @Field("username_user") String str_username,
            @Field("password_user") String str_password,
            @Field("kode_user") String str_code
    );

}