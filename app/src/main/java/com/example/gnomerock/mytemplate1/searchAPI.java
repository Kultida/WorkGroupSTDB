package com.example.gnomerock.mytemplate1;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by gnomerock on 9/17/15.
 */
public interface searchAPI{

    @GET("/scientist.json")
    Call<Scientist> dummy();

    @FormUrlEncoded
    @POST("/search")
    Call<ArrayList<Scientist>> searchScientist(
            @Field("name") String name,
            @Field("org") String org
    );
}
