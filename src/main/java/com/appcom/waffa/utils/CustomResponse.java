package com.appcom.waffa.utils;

public class CustomResponse {
	private final boolean success;

	private final Object code;

	private String message;

	private final Object data;

	public CustomResponse(boolean success, Object code, String message, Object data) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
		this.data = data;
	}

	public CustomResponse(Object code, Object data, boolean success) {
		this.success = success;
		this.code = code.toString();
		this.data = data;
	}

	public CustomResponse(Object code, Object data) {
		this.success = true;
		this.code = code.toString();
		this.data = data;
	}

	public CustomResponse(Object infoCode) {
		this(infoCode, null);
	}

	public boolean isSuccess() {
		return success;
	}

	public Object getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("<");
		builder.append(this.code.toString());
		builder.append(',');
		builder.append(this.success);
		builder.append(',');
		Object data = getData();
		if (data != null) {
			builder.append(data);
		}
		builder.append('>');
		return builder.toString();
	}
}
