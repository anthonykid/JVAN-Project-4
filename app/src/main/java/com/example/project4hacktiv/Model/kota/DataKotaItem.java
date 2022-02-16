package com.example.project4hacktiv.Model.kota;

import com.google.gson.annotations.SerializedName;

public class DataKotaItem{

	@SerializedName("id_kota")
	private String idKota;

	@SerializedName("nama_kota")
	private String namaKota;

	public void setIdKota(String idKota){
		this.idKota = idKota;
	}

	public String getIdKota(){
		return idKota;
	}

	public void setNamaKota(String namaKota){
		this.namaKota = namaKota;
	}

	public String getNamaKota(){
		return namaKota;
	}
}