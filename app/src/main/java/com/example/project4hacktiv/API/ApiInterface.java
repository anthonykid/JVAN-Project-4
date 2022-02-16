package com.example.project4hacktiv.API;

import com.example.project4hacktiv.Model.jadwal.ResponseJadwal;
import com.example.project4hacktiv.Model.kota.DataKotaItem;
import com.example.project4hacktiv.Model.kota.Kota;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @GET("getkota.php")
    Call<Kota> getKota();

    @FormUrlEncoded
    @POST("getJadwal.php")
    Call<ResponseJadwal> getJadwal(
           @Field("kota_asal") String kota_asal,
            @Field("kota_tujuan") String kota_tujuan
    );

}
