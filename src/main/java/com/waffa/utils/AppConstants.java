package com.waffa.utils;

public class AppConstants {

	public static final String SUCCESS = "success";
	public static final String FAIL = "fail";
	public static final String TRUE = "true";
	public static final String FALSE = "false";

	public static final String OK = "OK";
	public static final String DATE_FORMAT_DDMMYYYY = "dd/MM/yyyy";
	public static final String DATE_FORMAT_MMDDYYYY = "MM-dd-yyyy";
	public static final String DATE_FORMAT_YYYYMMDD = "yyyy-MM-dd";
	public static final String DATE_FORMAT_YYYYMMDD_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	public static final String DATE_FORMAT_YYYY_MM_DD_HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
	public static final String DATE_FORMAT_MMDDYYYY_WITH_SLASH = "MM/dd/yyyy";
	
	public static final String SECRET = "U2VjcmV0S2V5VG9HZW5KV1Rz";
    public static final long EXPIRATION_TIME = 864_000_000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/api/v1/signup";
    public static final String LOGIN_URL = "/api/v1/login";
}
