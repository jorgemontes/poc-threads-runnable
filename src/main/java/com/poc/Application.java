package com.poc;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application {
	/**
	 * A Executor service consist of a queue and a pool with a core pool size
	 * and a max pool size. Based on the api and its behaviour
	 * https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/ThreadPoolExecutor.html
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int taskCreated = 30;

		ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(10);
		System.out.println(Instant.now());
		for (int i = 0; i <= taskCreated; i++) {
			Runnable runnable = () -> {
				try {
					UUID threadId = UUID.randomUUID();
					System.out.println("Start Thread-" + threadId);
					TimeUnit.SECONDS.sleep((long) Math.random() * 1000);
					System.out.println("End Thread-" + threadId + " at " + Instant.now());
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			};

			threadPoolExecutor.execute(runnable);
		}
		threadPoolExecutor.shutdown();
	}
}
