package com.xmchx.art;

import com.xmchx.util.SleepUtils;
import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class TwinsLockTest {
	@Test
	public void test() {
		final Lock lock = new TwinsLock();
		class Worker extends Thread {
			@Override
			public void run() {
				while (true) {
					lock.lock();
					try {
						SleepUtils.second(1);
						System.out.println(Thread.currentThread().getName());
						SleepUtils.second(1);
					} finally {
						lock.unlock();
					}
				}
			}
		}

		for (int i = 0; i < 10; i++) {
			Worker worker = new Worker();
			worker.setDaemon(true);
			worker.start();
		}

		for (int i = 0; i < 10; i++) {
			SleepUtils.second(1);
			System.out.println();
		}
	}
}
