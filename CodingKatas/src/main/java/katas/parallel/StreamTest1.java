package katas.parallel;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

public class StreamTest1 {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        System.out.println("main thread:" + Thread.currentThread().getName());
        ForkJoinPool forkJoinPool = new ForkJoinPool(1000);
        CompletableFuture.supplyAsync(() -> {
                    //parallel task here, for example
                    IntStream.range(1, 1001).boxed().parallel().forEach(i -> {
                        work("hallo " + i);
                    });
                    return "done";
                },
                forkJoinPool
        ).get();
        /*
        IntStream.range(1,1001).boxed().parallel().forEach( i -> {
                work("hallo " + i);
        });

         */
        System.out.println("the end");
    }

    static void work(String arg) {
        System.out.println(Thread.currentThread().getName() + ": " + arg);
        String s = "hallo";
        for(int i=0; i<1000; i++) {
            s += "asdoqwdo";
        }
        // Thread.sleep(10_000);
    }
}
