package com.simplecode01.middletest.model;

import com.google.gson.annotations.SerializedName;

public class DataUserItem{

	@SerializedName("kode_user")
	private String kodeUser;

	@SerializedName("jeniskelamin_user")
	private String jeniskelaminUser;

	@SerializedName("password_user")
	private String passwordUser;

	@SerializedName("email_user")
	private String emailUser;

	@SerializedName("phone_user")
	private String phoneUser;

	@SerializedName("namalengkap_user")
	private String namalengkapUser;

	@SerializedName("username_user")
	private String usernameUser;

	public String getKodeUser(){
		return kodeUser;
	}

	public String getJeniskelaminUser(){
		return jeniskelaminUser;
	}

	public String getPasswordUser(){
		return passwordUser;
	}

	public String getEmailUser(){
		return emailUser;
	}

	public String getPhoneUser(){
		return phoneUser;
	}

	public String getNamalengkapUser(){
		return namalengkapUser;
	}

	public String getUsernameUser(){
		return usernameUser;
	}
}