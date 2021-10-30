package com.xmchx;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 自定义callable
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class Task implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		Thread.sleep(1000);
		return 2;
	}

	public static void main(String args[]) throws Exception {
		// 使用
		ExecutorService executor = Executors.newCachedThreadPool();
		FutureTask<Integer> futureTask = new FutureTask<>(new Task());
		executor.submit(futureTask);
		System.out.println(futureTask.get());
	}
}
