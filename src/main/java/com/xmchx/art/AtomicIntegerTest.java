package com.xmchx.art;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class AtomicIntegerTest {
	public static void main(String[] args) {
		AtomicInteger ai = new AtomicInteger(1);
		System.out.println(ai.getAndSet(2));
		System.out.println(ai.get());
	}
}
