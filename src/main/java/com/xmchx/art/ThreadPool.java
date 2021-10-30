package com.xmchx.art;

/**
 * @author xmchx (sunhuayangak47@gmail.com)
 */
public interface ThreadPool<Job extends Runnable> {
	void execute(Job job);
	void shutdown();
	void addWorkers(int num);
	void removeWorker(int num);
	int getJobSize();
}
