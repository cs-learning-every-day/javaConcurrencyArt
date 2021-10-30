package com.xmchx.util;

import java.util.concurrent.TimeUnit;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class SleepUtils {
	public static final void second(long seconds) {
		try {
			TimeUnit.SECONDS.sleep(seconds);
		} catch (InterruptedException e) {
		}
	}
}
