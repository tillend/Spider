package com.lin.test;

import static org.junit.Assert.assertNotEquals;

import java.io.UnsupportedEncodingException;

import org.junit.Ignore;
import org.junit.Test;

import com.lin.utils.TranscodeUtil;

public class TranscodeUtilTest {
	

	@Test
	public void test() throws UnsupportedEncodingException {
		String beforeTran = "\\u3010\\u5408\\u96c6\\u3011\\u9ed1\\u6267\\u4e8b\\u3010\\u72ec\\u5bb6\\u6b63\\u7248\\u3011";
		String afterTran = TranscodeUtil.transcode(beforeTran);
//		String afterTran = new String(beforeTran.getBytes("utf-8"));
		System.out.println(afterTran);
		assertNotEquals(beforeTran, afterTran);
	}
	
	@Ignore
	@Test
	public void test_decode() {
		String beforeTran = "【合集】黑执事【独家正版】";
		String afterTran = TranscodeUtil.transcode(beforeTran, "UTF-8", "UTF-8");
		System.out.println(afterTran);
		assertNotEquals(beforeTran, afterTran);
	}

}
