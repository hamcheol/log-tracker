package com.rp.order.utils;

import org.apache.commons.lang3.RandomUtils;

public class BizUtils {
	public static String generateOrdNo() {
		String curTime = DateUtils.date("yyyyMMddHHmmss");
		int postfix = RandomUtils.nextInt(100000, 999999);
		return curTime + postfix;
	}
}
