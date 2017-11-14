package com.itheima.day49.user.utils;

import java.util.UUID;

public class UUIDUtils {

	/**
	 * 获得32长度的UUID字符串
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 获得64长度的UUID字符串
	 * @return
	 */
	public static String getUUID64() {
		return getUUID() + getUUID();
	}

}
