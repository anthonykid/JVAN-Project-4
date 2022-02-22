package com.example.project4hacktiv.Model.History;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseHistory{

	@SerializedName("Status")
	private int status;

	@SerializedName("msg")
	private String msg;

	@SerializedName("data_detail")
	private List<DataDetailItem> dataDetail;

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	public void setMsg(String msg){
		this.msg = msg;
	}

	public String getMsg(){
		return msg;
	}

	public void setDataDetail(List<DataDetailItem> dataDetail){
		this.dataDetail = dataDetail;
	}

	public List<DataDetailItem> getDataDetail(){
		return dataDetail;
	}
}