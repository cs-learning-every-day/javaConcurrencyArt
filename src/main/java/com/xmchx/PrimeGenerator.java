package com.xmchx;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class PrimeGenerator implements Runnable {

	private final List<BigInteger> primes
			= new ArrayList<>();
	private volatile boolean cancelled;

	@Override
	public void run() {
		BigInteger p = BigInteger.ONE;
		while (!cancelled) {
			p = p.nextProbablePrime();
			synchronized (this) {
				primes.add(p);
			}
		}
	}

	public void cancel() {
		cancelled = true;
	}

	public synchronized List<BigInteger> get() {
		return new ArrayList<>(primes);
	}

	public static void main(String[] args) {
		final PrimeGenerator generator = new PrimeGenerator();
		new Thread(generator).start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			generator.cancel();
		}
		generator.get().forEach(System.out::println);
	}
}
