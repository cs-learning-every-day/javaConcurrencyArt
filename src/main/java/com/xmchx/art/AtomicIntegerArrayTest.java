package com.xmchx.art;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class AtomicIntegerArrayTest {
	static int[] values = new int[]{1, 2, 3};
	static AtomicIntegerArray ai = new AtomicIntegerArray(values);

	public static void main(String[] args) {
		ai.set(0, 3);
		System.out.println(ai.get(0));
		System.out.println(values[0]);
	}
}
