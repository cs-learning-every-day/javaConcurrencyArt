package com.xmchx;

import java.math.BigInteger;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class PrimeProducer extends Thread {
	private final BlockingQueue<BigInteger> queue;

	public PrimeProducer(BlockingQueue<BigInteger> queue) {
		this.queue = queue;
	}

	@Override
	public void run() {
		try {
			BigInteger p = BigInteger.ONE;
			while (!Thread.currentThread().isInterrupted())
				queue.put(p = p.nextProbablePrime());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public void cancel() {
		interrupt();
	}

	public static void main(String[] args) {
		final LinkedBlockingQueue<BigInteger> primes = new LinkedBlockingQueue<>(50);
		final PrimeProducer producer = new PrimeProducer(primes);
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
