package com.example.project4hacktiv.Model.seat;

import com.google.gson.annotations.SerializedName;

public class DataSeatItem{

	@SerializedName("no_kursi")
	private String noKursi;

	public void setNoKursi(String noKursi){
		this.noKursi = noKursi;
	}

	public String getNoKursi(){
		return noKursi;
	}
}