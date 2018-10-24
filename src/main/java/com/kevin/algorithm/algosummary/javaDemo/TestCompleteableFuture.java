package com.kevin.algorithm.algosummary.javaDemo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: TestCompleteableFuture
 * @Description:
 * @Author: Kevin
 * @Date: 2018/8/31 14:15
 */
public class TestCompleteableFuture {

    public static void main(String[] args) {
        thenApply();
        thenAccept();
        thenRun();
        thenCombine();
        thenAcceptBoth();
        applyToEither();
        acceptEither();
        runAfterEither();
        exceptionally();
        whenComplete();
        completeHandle();
    }

    /**
     * 1、进行变换
     * 入参是上一个阶段计算后的结果，返回值是经过转化后结果。
     */
    public static void thenApply() {
        int result = CompletableFuture.supplyAsync(() -> 100).thenApply(s -> s + 900).join();
        System.out.println(result);
    }

    /**
     * 2、进行消耗
     * 有入参无返回值
     */
    public static void thenAccept() {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        CompletableFuture.supplyAsync(() -> "hello", executorService).thenAcceptAsync(s -> {
            System.out.println(s + " world");
        }, executorService);
        executorService.shutdown();
    }

    /**
     * 3、对上一步的计算结果不关心，执行下一个操作
     * thenRun它的入参是一个Runnable的实例，表示当得到上一步的结果时的操作
     */
    public static void thenRun() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("hello");
            return "hello";
        }).thenRun(() -> System.out.println("hello world"));

    }


    /**
     * 4、结合两个CompletionStage的结果，进行转化后返回
     */
    public static void thenCombine() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> s1 + " " + s2).join();
        System.out.println(result);
    }

    /**
     * 5、结合两个CompletionStage的结果，进行消耗
     */
    public static void thenAcceptBoth() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "world";
        }), (s1, s2) -> System.out.println(s1 + " " + s2));

    }

    /**
     * 6.两个CompletionStage，谁计算的快，就用那个CompletionStage的结果进行下一步的转化操作。
     */
    public static void applyToEither() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "aaaaaaaaaaaa";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "bbbbbbbbbbbbbbbbbbbb";
        }), s -> s).join();
        System.out.println(result);
    }

    /**
     * 7、两个CompletionStage，谁计算的快，我就用那个CompletionStage的结果进行下一步的消耗操作
     */
    public static void acceptEither() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s11111111111111";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s222222222222222";
        }), System.out::println);
    }

    /**
     * 8、两个CompletionStage，任何一个完成了都会执行下一步的操作
     */
    public static void runAfterEither() {
        CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s111111111111";
        }).runAfterEither(CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "s22222222222222222";
        }), () -> System.out.println("sssssssssssssssss"));
    }

    /**
     * 9、当运行时出现了异常，可以通过exceptionally进行补偿
     */
    public static void exceptionally() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();
        System.out.println(result);
    }

    /**
     * 10、 当运行完成时，对结果的记录。
     * 这里的完成时有两种情况，一种是正常执行，返回值。另外一种是遇到异常抛出造成程序的中断。
     * 这里为什么要说成记录，因为这几个方法都会返回CompletableFuture，
     * 当Action执行完毕后它的结果返回原始的CompletableFuture的计算结果或者返回异常。
     * 所以不会对结果产生任何的作用。
     */
    public static void whenComplete() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).whenComplete((s, t) -> {
            System.out.println(s);
            System.out.println(t.getMessage());
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return "hello world";
        }).join();
        System.out.println(result);
    }


    /**
     * 11、运行完成时，对结果的处理。
     * 这里的完成时有两种情况，一种是正常执行，返回值。
     * 另外一种是遇到异常抛出造成程序的中断。
     */
    public static void completeHandle() {
        //正常返回
        handle1();
        //出现异常
        handle2();
    }

    public static void handle1() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result);
    }

    public static void handle2() {
        String result = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //出现异常
            if (1 == 1) {
                throw new RuntimeException("测试一下异常情况");
            }
            return "s1";
        }).handle((s, t) -> {
            if (t != null) {
                return "hello world";
            }
            return s;
        }).join();
        System.out.println(result);
    }


}
