package com.xmchx.art;

import java.util.Map;
import java.util.concurrent.*;

/**
 * 银行流水处理服务类
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public class BankWaterService implements Runnable {
	private CyclicBarrier c = new CyclicBarrier(4, this);
	private ExecutorService executor = Executors.newFixedThreadPool(4);
	private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

	private void count() {
		for (int i = 0; i < 4; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
					// 模拟计算当前sheet的流水数据
					sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
					try {
						c.await();
					} catch (InterruptedException | BrokenBarrierException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	@Override
	public void run() {
		int result = 0;
		for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
			result += sheet.getValue();
		}
		sheetBankWaterCount.put("result", result);
		System.out.println(result);


		// 这里没有执行完没有结束，不知道为什么
//		System.exit(1);
		// 原因：线程池没关闭
		executor.shutdown();
	}

	public static void main(String[] args) {
		var bankWaterCount = new BankWaterService();
		bankWaterCount.count();
	}
}
