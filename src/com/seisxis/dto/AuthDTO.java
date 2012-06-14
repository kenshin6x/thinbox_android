package com.seisxis.dto;

public class AuthDTO {
	
	private String url;
	public static final String AUTH_URL_INFO = "AUTH_URL_INFO";
	public static final String MOBILE_AUTH_TOKEN_NAME = "auth_token";
	public static final String MOBILE_AUTH_TOKEN_VALUE = "e4bbe5b7a4c1eb55652965aee885dd59bd2ee7f4";
	
	public AuthDTO(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return url;
	}
	
	
}
