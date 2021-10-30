package com.xmchx;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class BrokenPrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;
	private volatile boolean cancelled = false;

	public BrokenPrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!cancelled) {
				queue.put(p = p.nextProbablePrime());
			}
			super.run();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void cancel() {
		cancelled = true;
	}

	public static void main(String[] args) {
		BlockingQueue<BigInteger> primes = new LinkedBlockingQueue<>(50);
		final BrokenPrimeProducer producer = new BrokenPrimeProducer(primes);
		producer.start();


		try {
			System.out.println(primes.take());
			Thread.sleep(1000);
			producer.cancel();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
