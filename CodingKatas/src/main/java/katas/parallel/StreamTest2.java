package katas.parallel;

import java.util.stream.IntStream;

public class StreamTest2 {
    public static void main(String[] args) throws InterruptedException {
        IntStream.range(1,1001).boxed().parallel().forEach( i -> {
                new Thread(() -> work("hallo " + i)).start();
        });
        System.out.println("the end");
    }

    static void work(String arg) {
        System.out.println(arg);
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
