package com.galaxyNstudio.veggies.api;

import com.galaxyNstudio.veggies.responseWrapper.MobileExist;
import com.galaxyNstudio.veggies.responseWrapper.VegetableWrapper;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {


    /*@FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
            @Field("email") String email,
            @Field("password") String password,
            @Field("name") String name,
            @Field("school") String school
    );

    @FormUrlEncoded
    @POST("userlogin")
    Call<LoginResponse> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

    @GET("allusers")
    Call<UsersResponse> getUsers();

    @FormUrlEncoded
    @PUT("updateuser/{id}")
    Call<LoginResponse> updateUser(
            @Path("id") int id,
            @Field("email") String email,
            @Field("name") String name,
            @Field("school") String school
    );

    @FormUrlEncoded
    @PUT("updatepassword")
    Call<DefaultResponse> updatePassword(
            @Field("currentpassword") String currentpassword,
            @Field("newpassword") String newpassword,
            @Field("email") String email
    );

    @DELETE("deleteuser/{id}")
    Call<DefaultResponse> deleteUser(@Path("id") int id);*/

    @FormUrlEncoded
    @POST("checkVMobileExists")
    Call<MobileExist> checkUserMobileExists(
            @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST("checkVEmailExists")
    Call<MobileExist> checkVEmailExists(
            @Field("email") String email
    );


    @FormUrlEncoded
    @POST("createVuser")
    Call<MobileExist> createVuser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile
    );

    @FormUrlEncoded
    @POST("getVVegetablesByCategory")
    Call<VegetableWrapper> getVVegetablesByCategory(
            @Field("category") String category
    );

}
