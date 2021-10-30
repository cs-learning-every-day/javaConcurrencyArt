package com.xmchx.art;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class FairAndUnfairTest {
	private static Lock fairLock = new ReentrantLock2(true);
	private static Lock unfairLock = new ReentrantLock2(false);

	@Test
	public void fair() {
		testLock(fairLock);
	}

	@Test
	public void unfair() {
		testLock(unfairLock);
	}

	private void testLock(Lock lock) {
		for (int i = 0; i < 5; i++) {
			Thread thread = new Job(lock);
			thread.setName("" + i);
			thread.start();
		}
	}

	private static class Job extends Thread {
		private final Lock lock;

		public Job(Lock lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			for (int i = 0; i < 2; i++) {
				lock.lock();
				try {
					System.out.println("Lock by [" + getName() + "], Waiting by " + ((ReentrantLock2) lock).getQueuedThreads());
				} finally {
					lock.unlock();
				}
			}
		}
	}


	private static class ReentrantLock2 extends ReentrantLock {
		public ReentrantLock2(boolean fair) {
			super(fair);
		}

		@Override
		protected Collection<Thread> getQueuedThreads() {
			List<Thread> arrayList = new ArrayList<>(super.getQueuedThreads());
			Collections.reverse(arrayList);
			return arrayList;
		}
	}
}
