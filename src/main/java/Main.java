import java.util.Arrays;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Создание потоков...");
        Callable<Integer> myCallable1 = new MyCallable("Stream 1");
        Callable<Integer> myCallable2 = new MyCallable("Stream 2");
        Callable<Integer> myCallable3 = new MyCallable("Stream 3");
        Callable<Integer> myCallable4 = new MyCallable("Stream 4");
        ExecutorService threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        Future<Integer> task1 = threadPool.submit(myCallable1);
        Future<Integer> task2 = threadPool.submit(myCallable2);
        Future<Integer> task3 = threadPool.submit(myCallable3);
        Future<Integer> task4 = threadPool.submit(myCallable4);
        try {
            System.out.println(myCallable1 + " задача выполнена  " + task1.get() + " раз(а).");
            System.out.println(myCallable2 + " задача выполнена " + task2.get() + " раз(а).");
            System.out.println(myCallable3 + " задача выполнена " + task3.get() + " раз(а).");
            System.out.println(myCallable4 + " задача выполнена " + task4.get() + " раз(а).");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        try {
            Integer result = threadPool.invokeAny(Arrays.asList(myCallable1, myCallable2, myCallable3, myCallable4));
            System.out.println("Самое быстрое выполнение задачи: " + result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Завершение всех потоков...");
        threadPool.shutdown();
    }
}
