package com.xmchx.art;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class ExchangerTest {
	static final Exchanger<String> exgr = new Exchanger<>();
	static ExecutorService threadPool = Executors.newFixedThreadPool(2);

	public static void main(String[] args) {
		threadPool.execute(() -> {
			String A = "银行流水A";
			try {
				exgr.exchange(A);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		threadPool.execute(() -> {
			String B = "银行流水B";
			try {
				String A = exgr.exchange(B);
				System.out.println("A和B数据是否一致：" + A.equals(B) + "，A录入的是："
						+ A + "，B录入的是：" + B);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		threadPool.shutdown();
	}
}
