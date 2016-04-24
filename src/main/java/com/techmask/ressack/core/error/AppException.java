package com.techmask.ressack.core.error;

import com.techmask.ressack.core.log.LogUtils;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 7832349361923399645L;

	/**
	 * The default Message Key if none is supplied.
	 */
	public static final String UNEXPECTED_ERROR = "appException.unexpected.error";
	public static final String PERMISSION_DENIED_ERROR = "appException.permission.error";

	public AppException(String msg) {
		super(msg);
	}

	public AppException(String errMsg, Throwable cause) {
		super(errMsg, cause);
		LogUtils.error(errMsg + ": " + cause.getStackTrace(),cause);
	}

	
}
