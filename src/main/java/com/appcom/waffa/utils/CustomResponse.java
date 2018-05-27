package com.appcom.waffa.utils;


public class CustomResponse {
	private final boolean success;

	private final Object code;

	private final Object data;

	public boolean isSuccess() {
		return success;
	}

	public Object getCode() {
		return code;
	}

	public Object getData() {
		return data;
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
