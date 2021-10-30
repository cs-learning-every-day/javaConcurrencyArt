package com.xmchx.art;

import java.sql.Connection;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class ConnectionPoolTest {
	static ConnectionPool pool = new ConnectionPool(5);
	static CountDownLatch start = new CountDownLatch(1);
	static CountDownLatch end;

	public static void main(String[] args) throws InterruptedException {
		int threadCount = 10;
		end = new CountDownLatch(threadCount);
		int count = 20;
		AtomicInteger got = new AtomicInteger();
		AtomicInteger notGot = new AtomicInteger();
		for (int i = 0; i < threadCount; i++) {
			Thread thread = new Thread(new ConnectionRunner(count, got, notGot),
					"ConnectionRunnerThread");
			thread.start();
		}
		// 用于 所有ConnectionRunnerThread同时开始
		start.countDown();
		// 用于 等待所有结束
		end.await();
		System.out.println("total invoke: " + (threadCount * count));
		System.out.println("got connection: " + got);
		System.out.println("not got connection " + notGot);
	}

	static class ConnectionRunner implements Runnable {
		int count;
		AtomicInteger got;
		AtomicInteger notGot;

		public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot) {
			this.count = count;
			this.got = got;
			this.notGot = notGot;
		}

		@Override
		public void run() {
			try {
				start.await();
			} catch (Exception ignored) {
			}

			while (count > 0) {
				Connection connection = null;
				try {
					connection = pool.fetchConnection(1000);
					if (connection != null) {
						try {
							connection.createStatement();
							connection.commit();
						} finally {
							pool.releaseConnection(connection);
							got.incrementAndGet();
						}
					} else {
						notGot.incrementAndGet();
					}
				} catch (Exception ignored) {
				} finally {
					count--;
				}
			}
			end.countDown();
		}
	}
}
