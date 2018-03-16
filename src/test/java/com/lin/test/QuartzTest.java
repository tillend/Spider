package com.lin.test;

import org.junit.Test;

import com.lin.controller.trigger.TestTrigger;

public class QuartzTest {
	
	@Test
	public void test() {
		new TestTrigger().start();
	}

}
