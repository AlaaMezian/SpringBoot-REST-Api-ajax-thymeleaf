package com.waffa.utils;


public class CustomResponse {
	private final boolean success;

	private final Object infoCode;

	private final Object data;

	public boolean isSuccess() {
		return success;
	}

	public Object getInfoCode() {
		return infoCode;
	}

	public Object getData() {
		return data;
	}

	public CustomResponse(Object infoCode, Object data, boolean success) {
		this.success = success;
		this.infoCode = infoCode.toString();
		this.data = data;
	}

	public CustomResponse(Object infoCode, Object data) {
		this.success = true;
		this.infoCode = infoCode.toString();
		this.data = data;
	}

	public CustomResponse(Object infoCode) {
		this(infoCode, null);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("<");
		builder.append(this.infoCode.toString());
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
