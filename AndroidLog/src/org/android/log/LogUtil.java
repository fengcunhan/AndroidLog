package org.android.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.util.Log;

/**
 * @author fengcunhan email:fengcunhan@gmail.com
 * 
 */
public class LogUtil {
	private static boolean isDebug = true;

	private static String logFileName = "/sdcard/log_util.log";

	private static boolean isNeedToStore = false;

	/**
	 * 停止调试 stopLog
	 */
	public synchronized static void stopLog() {
		isDebug = false;
	}

	/**
	 * 开启调试 startLog
	 */
	public synchronized static void startLog() {
		isDebug = true;
	}

	/**
	 * store the log in the default file
	 */
	public synchronized static void storeLog() {
		isNeedToStore = true;
	}

	/**
	 * store the log in the file names fileName
	 * 
	 * @param fileName
	 */
	public synchronized static void storeLog(String fileName) {
		logFileName = fileName;
	}

	/**
	 * delete the log file names fileName
	 */
	public synchronized static  void deleteLog(String fileName) {
		File file = new File(logFileName);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * delete the default log
	 */
	public synchronized static void deleteLog() {
		deleteLog(logFileName);
	}

	public static void i(String tag, String msg) {
		if (isDebug) {
			Log.i(tag, msg);
		}
		if (isNeedToStore) {
			writeLog("i", tag, msg);
		}
	}

	public static boolean isDebug() {
		return isDebug;
	}

	public static void setDebug(boolean isDebug) {
		LogUtil.isDebug = isDebug;
	}



	public static String getLogFileName() {
		return logFileName;
	}

	public static void setLogFileName(String logFileName) {
		LogUtil.logFileName = logFileName;
	}

	public static boolean isNeedToStore() {
		return isNeedToStore;
	}

	public static void setNeedToStore(boolean isNeedToStore) {
		LogUtil.isNeedToStore = isNeedToStore;
	}

	public static void e(String tag, String msg) {
		if (isDebug) {
			Log.e(tag, msg);
		}
		if (isNeedToStore) {
			writeLog("e", tag, msg);
		}
	}

	public static void w(String tag, String msg) {
		if (isDebug) {
			Log.w(tag, msg);
		}
		if (isNeedToStore) {
			writeLog("w", tag, msg);
		}
	}

	public static void v(String tag, String msg) {
		if (isDebug) {
			Log.v(tag, msg);
		}
		if (isNeedToStore) {
			writeLog("v", tag, msg);
		}
	}

	public static void d(String tag, String msg) {
		if (isDebug) {
			Log.d(tag, msg);
		}
		if (isNeedToStore) {
			writeLog("d", tag, msg);
		}
	}

	public static void exception(String tag, Exception e) {
		if (isDebug) {
			e.printStackTrace();
			e(tag, e.getMessage());
		}
		
		if(isNeedToStore){
			for(StackTraceElement element:e.getStackTrace()){
				writeLog(element.getClassName()+" "+element.getMethodName()+" "+element.getFileName()+":"+element.getLineNumber());
			}
			
		}
	}

	public static void v(Context context, String msg) {
		v(context.getClass().getName(), msg);
	}

	public static void d(Context context, String msg) {
		d(context.getClass(), msg);
	}

	public static void i(Context context, String msg) {
		i(context.getClass(), msg);
	}

	public static void e(Context context, String msg) {
		e(context.getClass(), msg);
	}

	public static void w(Context context, String msg) {
		w(context.getClass(), msg);
	}
	
	public static void d(Class<? extends Object> clazz,String msg){
		d(clazz.getName(), msg);
	}

	public static void i(Class<? extends Object> clazz,String msg) {
		i(clazz.getName(), msg);
	}

	public static void e(Class<? extends Object> clazz,String msg) {
		e(clazz.getName(), msg);
	}

	public static void w(Class<? extends Object> clazz,String msg) {
		w(clazz.getName(), msg);
	}
	/**
	 * store the log to a text file
	 * 
	 * @param tag
	 * @param msg
	 */
	private static void writeLog(String level, String tag, String msg) {

		try {
			File file = new File(logFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			SimpleDateFormat formate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			FileWriter write = new FileWriter(file, true);
			write.write(level + "   " + formate.format(new Date()) + "   "
					+ tag + "   " + msg + "\n");
			write.flush();
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void writeLog(String content){
		try {
			File file = new File(logFileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			SimpleDateFormat formate = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			FileWriter write = new FileWriter(file, true);
			write.write(formate.format(new Date()) + "   "+
					content+"\n");
			write.flush();
			write.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
