package com.ardi.projectuas.api;

/**
 * Created by Robby Dianputra on 10/31/2017.
 */

import com.ardi.projectuas.Model.city.ItemCity;
import com.ardi.projectuas.Model.cost.ItemCost;
import com.ardi.projectuas.Model.province.ItemProvince;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    // Province
    @GET("province")
    @Headers("key:60ea89eb3d827c1e51abcfb3551eea5a")
    Call<ItemProvince> getProvince ();

    // City
    @GET("city")
    @Headers("key:60ea89eb3d827c1e51abcfb3551eea5a")
    Call<ItemCity> getCity (@Query("province") String province);

    // Cost
    @FormUrlEncoded
    @POST("cost")
    Call<ItemCost> getCost (@Field("key") String Token,
                            @Field("origin") String origin,
                            @Field("destination") String destination,
                            @Field("weight") String weight,
                            @Field("courier") String courier);

}
