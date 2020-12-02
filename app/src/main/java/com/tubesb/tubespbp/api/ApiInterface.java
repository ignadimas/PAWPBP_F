package com.tubesb.tubespbp.api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("user")
    Call<com.tubesb.tubespbp.api.UserResponse> getAllUser(@Query("data") String data);

    @GET("user/{id}")
    Call<com.tubesb.tubespbp.api.UserResponse> getUserById(@Path("id") String id,
                                                          @Query("data") String data);

    @POST("login")
    @FormUrlEncoded
    Call<com.tubesb.tubespbp.api.UserResponse> loginRequest(@Field("nim") String nim,
                                                           @Field("password") String password);

    @POST("user/{id}")
    @FormUrlEncoded
    Call<com.tubesb.tubespbp.api.UserResponse> updateUser(@Path("id") String id,
                                                                        @Query("data") String data,
                                                                        @Field("email") String email,
                                                                        @Field("password") String password,
                                                                        @Field("name") String nama,
                                                                        @Field("alamat") String alamat,
                                                                        @Field("noTelp") String noTelp);

    @POST("user/delete/{id}")
    Call<com.tubesb.tubespbp.api.UserResponse> deleteUser(@Path("id") String id);

    @POST("register")
    @FormUrlEncoded
    Call<UserResponse> createUser(@Field("email") String email,
                                  @Field("password") String password,
                                  @Field("name") String nama,
                                  @Field("alamat") String alamat,
                                  @Field("noTelp") String noTelp);
}
