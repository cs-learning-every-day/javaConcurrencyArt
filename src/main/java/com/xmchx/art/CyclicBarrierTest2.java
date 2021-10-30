package com.xmchx.art;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class CyclicBarrierTest2 {
	// 优先执行 构造函数里的barrierAction
	static CyclicBarrier cb = new CyclicBarrier(2, new A());


	public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
		new Thread(() -> {
			try {
				cb.await();
			} catch (InterruptedException | BrokenBarrierException e) {
				e.printStackTrace();
			}
			System.out.println(1);
		}).start();
		cb.await();
		System.out.println(2);
	}

	static class A implements Runnable {

		@Override
		public void run() {
			System.out.println(3);
		}
	}
}
