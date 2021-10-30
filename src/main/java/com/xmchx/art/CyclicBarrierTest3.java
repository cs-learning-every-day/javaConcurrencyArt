package com.xmchx.art;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class CyclicBarrierTest3 {
	static CyclicBarrier c = new CyclicBarrier(2);

	public static void main(String[] args) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					c.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
			}
		});
		thread.start();
		thread.interrupt();
		try {
			c.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			System.out.println(c.isBroken());
		}

	}
}
