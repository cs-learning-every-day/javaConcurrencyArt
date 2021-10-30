package com.xmchx.art;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class SemaphoreTest {
	static final int THREAD_COUNT = 30;
	static ExecutorService threadPool = Executors.newFixedThreadPool(THREAD_COUNT);
	static Semaphore s = new Semaphore(10);

	public static void main(String[] args) {
		for (int i = 0; i < THREAD_COUNT; i++) {
			int finalI = i;
			threadPool.execute(() -> {
				try {
					s.acquire();
					System.out.println("save data" + finalI);
					s.release();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		threadPool.shutdown();
	}
}
