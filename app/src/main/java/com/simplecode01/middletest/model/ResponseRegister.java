package com.simplecode01.middletest.model;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ResponseRegister{

	@SerializedName("code")
	private int code;

	@SerializedName("Message")
	private String message;

	@SerializedName("data_user")
	private List<DataUserItem> dataUser;

	@SerializedName("status")
	private boolean status;

	public int getCode(){
		return code;
	}

	public String getMessage(){
		return message;
	}

	public List<DataUserItem> getDataUser(){
		return dataUser;
	}

	public boolean isStatus(){
		return status;
	}
}