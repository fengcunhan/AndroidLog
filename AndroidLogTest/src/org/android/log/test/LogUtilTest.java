package org.android.log.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.Assert;

import org.android.log.LogUtil;

import android.test.AndroidTestCase;

public class LogUtilTest extends AndroidTestCase {
	public void testI(){
		LogUtil.i("tag", "testI");
	}
	
	public void testStoreLog(){
		LogUtil.deleteLog();
		LogUtil.storeLog();
		
		LogUtil.e("tag", "testStoreLog");
		
		File file=new File("/sdcard/log_util.log");
		try {
			BufferedReader reader=new  BufferedReader(new FileReader(file));
			String content=reader.readLine();
			assertEquals(true, content.startsWith("e"));
			assertEquals(true, content.contains("tag"));
			assertEquals(true, content.contains("testStoreLog"));
			assertEquals(true, content.endsWith("testStoreLog"));
			LogUtil.deleteLog();
		} catch (FileNotFoundException e) {
			Assert.fail();
		} catch (IOException e) {
			Assert.fail();
		}
		
	}
}
