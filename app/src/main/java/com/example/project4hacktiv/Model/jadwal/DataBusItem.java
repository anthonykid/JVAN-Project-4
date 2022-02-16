package com.example.project4hacktiv.Model.jadwal;

import com.google.gson.annotations.SerializedName;

public class DataBusItem{

	@SerializedName("harga_ticket")
	private String hargaTicket;

	@SerializedName("waktu_berangkat")
	private String waktuBerangkat;

	@SerializedName("id_bus")
	private String idBus;

	@SerializedName("waktu_sampai")
	private String waktuSampai;

	@SerializedName("nama_bus")
	private String namaBus;

	public void setHargaTicket(String hargaTicket){
		this.hargaTicket = hargaTicket;
	}

	public String getHargaTicket(){
		return hargaTicket;
	}

	public void setWaktuBerangkat(String waktuBerangkat){
		this.waktuBerangkat = waktuBerangkat;
	}

	public String getWaktuBerangkat(){
		return waktuBerangkat;
	}

	public void setIdBus(String idBus){
		this.idBus = idBus;
	}

	public String getIdBus(){
		return idBus;
	}

	public void setWaktuSampai(String waktuSampai){
		this.waktuSampai = waktuSampai;
	}

	public String getWaktuSampai(){
		return waktuSampai;
	}

	public void setNamaBus(String namaBus){
		this.namaBus = namaBus;
	}

	public String getNamaBus(){
		return namaBus;
	}
}