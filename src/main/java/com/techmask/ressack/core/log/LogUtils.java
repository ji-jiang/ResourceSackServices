package com.techmask.ressack.core.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtils {
	private static final Logger logger = LoggerFactory.getLogger("SystemLog");
	
	public static final void trace(String msg){
		logger.trace(msg);
	}
	
	public static final void debug(String msg){
		logger.debug(msg);
	}
	
	public static final void warn(String msg){
		logger.warn(msg);
	}
	
	public static final void warn(String msg, Throwable t){
		logger.warn(msg,t);
	}
	
	public static final void error(String msg){
		logger.error(msg);
	}
	
	public static final void error(String msg, Throwable t){
		logger.error(msg,t);
	}
}
