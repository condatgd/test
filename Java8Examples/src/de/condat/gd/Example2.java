package de.condat.gd;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class Example2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		parallelTest1();
	}

	private static void parallelTest1() {
		IntStream s = IntStream.rangeClosed(1, 10).parallel();
		System.out.println("parallel1: ");
		s.forEach((int taskNr) -> task(taskNr));
	}

	private static void parallelTest2() throws InterruptedException, ExecutionException {
		ForkJoinPool customThreadPool = new ForkJoinPool(2);
		IntStream s = IntStream.rangeClosed(1, 10).parallel();
		System.out.println("parallel2: ");
		customThreadPool.submit(() -> s.parallel().forEach(e -> task(e))).get();
	}

	public static void task(int taskNr) {
		IntStream is = IntStream.rangeClosed(1, 10);
		is.forEach((int i) -> {
			System.out.println("Task " + taskNr + ": " + i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
	}
}
