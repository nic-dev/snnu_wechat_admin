/**
 * Copyright (C) 2014 NC-SNNU All Rights Reserved.		
 * 																								
 *  1.0   vvdeng  Jul 18, 2014  Create	
 */
package snnu.wechat.framework.log;

import java.text.MessageFormat;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class Log {

	private static final String FQCN = Log.class.getName();

	private static Logger msgLogger = Logger.getLogger("message");
	private static Logger keyLogger = Logger.getLogger("key");

	protected Log() {
	}

	public static boolean isDebugEnabled() {
		return msgLogger.isDebugEnabled();
	}

	public static void logDebug(String message) {
		msgLogger.log(FQCN, Level.DEBUG, message, null);
	}

	public static void logDebug(String format, Object... params) {
		if (msgLogger.isDebugEnabled()) {
			String msg = MessageFormat.format(format, params);
			logDebug(msg);
		}
	}

	public static void logInfo(String message) {
		msgLogger.log(FQCN, Level.INFO, message, null);
	}

	public static void logInfo(String format, Object... params) {
		if (msgLogger.isInfoEnabled()) {
			String msg = MessageFormat.format(format, params);
			logInfo(msg);
		}
	}

	public static void logError(String message) {
		msgLogger.log(FQCN, Level.ERROR, message, null);
	}

	public static void logError(String message, Throwable t) {
		msgLogger.log(FQCN, Level.ERROR, message, t);
	}

	public static void logError(String format, Object... params) {
		if (msgLogger.isEnabledFor(Level.ERROR)) {
			String msg = MessageFormat.format(format, params);
			logError(msg);
		}
	}

	public static void logError(String format, Throwable t, Object... params) {
		if (msgLogger.isEnabledFor(Level.ERROR)) {
			String msg = MessageFormat.format(format, params);
			logError(msg, t);
		}
	}

	public static void logKey(String key, Object message) {
		String targetMsg = MessageFormat.format("{0} {1}", key, message);
		keyLogger.log(FQCN, Level.WARN, targetMsg, null);
	}

	public static void logKey(String key, String format, Object... params) {
		String msg = MessageFormat.format(format, params);
		logKey(key, msg);
	}
}
