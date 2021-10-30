package com.xmchx.art;

import java.util.concurrent.TimeUnit;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Profiler {
	// 第一get()方法会进行初始化(如果set方法没调用)，第个线程会调用一次
	private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<>(){
		@Override
		protected Long initialValue() {
			return System.currentTimeMillis();
		}
	};

	public static void begin() {
		TIME_THREADLOCAL.set(System.currentTimeMillis());
	}

	public static long end() {
		return System.currentTimeMillis() - TIME_THREADLOCAL.get();
	}

	public static void main(String[] args) throws InterruptedException {
		Profiler.begin();
		TimeUnit.SECONDS.sleep(1);
		System.out.println("Cost: " + Profiler.end() + " mils");
	}

}
