package com.xmchx.art;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * @see BankWaterService 应用场景
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class CyclicBarrierTest {
	static CyclicBarrier cb = new CyclicBarrier(2);

	// 设置2个屏障，await告知已到达屏障，当所有屏障已到达则 同时继续运行
	public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					cb.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
				System.out.println(1);
			}
		}).start();

		cb.await();
		System.out.println(2);
	}
}
