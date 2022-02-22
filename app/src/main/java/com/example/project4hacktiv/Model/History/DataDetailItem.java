package com.example.project4hacktiv.Model.History;

import com.google.gson.annotations.SerializedName;

public class DataDetailItem{

	@SerializedName("nama_kotatujuan")
	private String namaKotatujuan;

	@SerializedName("harga_ticket")
	private String hargaTicket;

	@SerializedName("kursi")
	private String kursi;

	@SerializedName("id_bus")
	private String idBus;

	@SerializedName("waktu_sampai")
	private String waktuSampai;

	@SerializedName("nama_bus")
	private String namaBus;

	@SerializedName("id_transaksi")
	private String idTransaksi;

	@SerializedName("id_user")
	private String idUser;

	@SerializedName("no_kursi")
	private String noKursi;

	@SerializedName("waktu_berangkat")
	private String waktuBerangkat;

	@SerializedName("name")
	private String name;

	@SerializedName("tanggal")
	private String tanggal;

	@SerializedName("nama_kotaasal")
	private String namaKotaasal;

	public void setNamaKotatujuan(String namaKotatujuan){
		this.namaKotatujuan = namaKotatujuan;
	}

	public String getNamaKotatujuan(){
		return namaKotatujuan;
	}

	public void setHargaTicket(String hargaTicket){
		this.hargaTicket = hargaTicket;
	}

	public String getHargaTicket(){
		return hargaTicket;
	}

	public void setKursi(String kursi){
		this.kursi = kursi;
	}

	public String getKursi(){
		return kursi;
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

	public void setIdTransaksi(String idTransaksi){
		this.idTransaksi = idTransaksi;
	}

	public String getIdTransaksi(){
		return idTransaksi;
	}

	public void setIdUser(String idUser){
		this.idUser = idUser;
	}

	public String getIdUser(){
		return idUser;
	}

	public void setNoKursi(String noKursi){
		this.noKursi = noKursi;
	}

	public String getNoKursi(){
		return noKursi;
	}

	public void setWaktuBerangkat(String waktuBerangkat){
		this.waktuBerangkat = waktuBerangkat;
	}

	public String getWaktuBerangkat(){
		return waktuBerangkat;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setTanggal(String tanggal){
		this.tanggal = tanggal;
	}

	public String getTanggal(){
		return tanggal;
	}

	public void setNamaKotaasal(String namaKotaasal){
		this.namaKotaasal = namaKotaasal;
	}

	public String getNamaKotaasal(){
		return namaKotaasal;
	}
}