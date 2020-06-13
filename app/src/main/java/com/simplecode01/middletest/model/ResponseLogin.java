package com.simplecode01.middletest.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseLogin{

	@SerializedName("code")
	private int code;

	@SerializedName("Message")
	private String message;

	@SerializedName("informasi_user")
	private List<InformasiUserItem> informasiUser;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}

	public List<InformasiUserItem> getInformasiUser(){
		return informasiUser;
	}

	public boolean isStatus(){
		return status;
	}
}