package com.kevin.algorithm.algosummary.javaDemo;

import com.google.common.collect.ImmutableBiMap;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * @ClassName: Test
 * @Description:
 * @Author: Kevin
 * @Date: 2018/7/4 09:53
 */
public class Test {

    public static void main(String[] args) {
        //testForkJoin();
        //testExecutorException();
//        testOne();
        //testTwo();

        int val = 1;
        testRefInt(val);
        System.out.println("====:" + val);

        Integer integer = 200;
        testRefInteger(integer);
        System.out.println("====:" + integer);

        String str = "aaa";
        testRefString(str);
        System.out.println("====:" + str);

        StringBuilder sb = new StringBuilder("11111111");
        testRefStrBuilder(sb);
        System.out.println("====:" + sb.toString());

        List<String> list = new ArrayList<>();
        list.add("11111111111");
        testRefList(list);
        System.out.println("====:" + list.toString());

        Map<String, String> testMap = new HashMap<>();
        testMap.put("1", "a");
        System.out.println("====:" + testMap.get("2"));

        int a = 1;
        int b = 1;

        System.out.println("====:" + Objects.equals(a, b));
        getFilePath("sdf");

        testList();
        testMapReplaceAll();
    }

    public static void testBase64() {
        System.out.println("end~~~~~~~");

        String fileId = "Z3JvdXAxL00wMC9BMi8xNi9yQkFVVFZ1SkU3ZUFlcVlPQUFCYXp5Q20wM1E5OTcuanBn";
        fileId = new String(Base64.getDecoder().decode(fileId));
        System.out.println("fileId=" + fileId);
    }

    public static void testExecutorException() {
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future> futures = new ArrayList<>();
        try {

            //>excel文件处理
            futures.add(executor.submit(() -> {
                try {

                } catch (Exception e) {
                    throw new Exception(e);
                } finally {
                }
                return null;
            }));
            //>处理人员图片
            futures.add(executor.submit(() -> {
                long s = System.currentTimeMillis();
                try {
                    int a = 1 / 0;
                } catch (Exception e) {
                    throw new Exception(e);
                } finally {
                }
                return null;

            }));
            executor.shutdown();
            for (Future future : futures) {
                future.get();
            }
        } catch (Exception e) {
            System.out.println("e.getmessage=" + e.getMessage());

        }

    }

    public static void testOne() {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(() -> {
            try {
                Thread.currentThread().setName("aaaaa");
                int count = 0;
                while (true) {
                    System.out.println("aaaaa----: " + count);
                    Thread.sleep(2000);
                    count++;
                }
            } catch (Exception e) {

            }
        });
        executor.shutdown();
        System.gc();
    }

    static class MyThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.currentThread().setName("aaaaa");
                int count = 0;
                while (true) {
                    System.out.println("aaaaa----: " + count);
                    Thread.sleep(2000);
                    count++;
                }
            } catch (Exception e) {
            }
        }

        /**
         * Cannot instantiate.
         */
        @Override
        public void finalize() throws Throwable {
            System.out.println("MyThread finalize");
            super.finalize();
        }
    }

    public static void testTwo() {
        MyThread thread = new MyThread();
        thread.setName("thread- ----aaa");
        thread.start();
        thread = null;


    }


    /**
     * 验证forkjoinpool
     */
    public static void testForkJoin() {
        class TestTask extends RecursiveTask<Long> {
            private static final int Threshold = 10;
            private int startIndex;
            private int endIndex;

            public TestTask(int startIndex, int endIndex) {
                this.startIndex = startIndex;
                this.endIndex = endIndex;

            }

            @Override
            protected Long compute() {
                long sum = 0;
                if ((endIndex - startIndex) < Threshold) {
                    for (int i = startIndex; i < endIndex; i++) {
                        sum += i;
                        System.out.println("index =" + i);
                    }

                } else {
                    int midle = (startIndex + endIndex) >> 1;
                    TestTask left = new TestTask(startIndex, midle);
                    TestTask right = new TestTask(midle, endIndex);
                    left.fork();
                    right.fork();
                    sum = left.join() + right.join();
                }

                return sum;
            }

        }


        try {
            int max = 47;
            ForkJoinPool forkJoinPool = new ForkJoinPool();
            TestTask testTask = new TestTask(1, max);
            Future<Long> result = forkJoinPool.submit(testTask);
            // 关闭线程池
            forkJoinPool.shutdown();
            System.out.println("result , sum=" + result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void testRefInt(int val) {
        // val = 1;
        val++;
        System.out.println("====:" + val);
    }

    public static void testRefInteger(Integer integer) {
        integer++;
        System.out.println("====:" + integer);
    }

    public static void testRefString(String str) {
        //str = null;
        str = "adsf";
        System.out.println("====:" + str);
    }

    public static void testRefStrBuilder(StringBuilder sb) {
        //sb = new StringBuilder();
        sb.append("aaaa");
        System.out.println("====:" + sb.toString());
    }

    public static void testRefList(List<String> list) {
        list = Lists.newArrayList();
        list.add("aaaa");
        System.out.println("====:" + list.toString());
    }


    public static String getFilePath(String path) {
        path = "http";
        path.startsWith("http");
        if (path.indexOf("http://") == 0 || path.indexOf("https://") == 0) {
            path = path.substring(path.indexOf("/", 8), path.length());
        }
        System.out.println("====path:" + path);
        return path;
    }

    public static void testList() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("aaa" + i);
        }
        list = list.stream().map(str -> {
            String s = str.toUpperCase();
            return s;
        }).collect(Collectors.toList());
        list.replaceAll(str -> str.toLowerCase());
        System.out.println("====list:" + Arrays.toString(list.toArray()));
    }

    public static void testListToMap(){
       List<Integer> list = Lists.newArrayList();
        for (int i = 1; i < 50; i++) {
            list.add(i);
        }
        list = list.subList(0, 20);
        System.out.println("list.size="+list.size()+" ,list=" + Arrays.toString(list.toArray()));
        Map<Integer, Integer> followedMap = list.stream().collect(Collectors.toMap(id->id, o -> o, (a, b) -> a));
        System.out.println("followedMap.size="+followedMap.size()+" ,followedMap=" + Arrays.toString(followedMap.keySet().toArray()));
    }

    public static void testMapReplaceAll() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("a",  new ArrayList<>(Arrays.asList("a", "aa", "aaa")));
        map.put("b",  new ArrayList<>(Arrays.asList("b", "bb")));
        map.put("null",  null);
        map.put("empty",  new ArrayList<>());
        map.replaceAll((k, list) -> {
            if (!CollectionUtils.isEmpty(list)) {
                list.replaceAll(str -> str.toUpperCase());
            }
            return list;
        });

        map.forEach((k, list) -> {
            if (!CollectionUtils.isEmpty(list)) {
                System.out.println("k = " + k + ", list=" + Arrays.toString(list.toArray()));
            }else{
                System.out.println("k = " + k );
            }
        });


        List<String> list = new ArrayList<>(Arrays.asList("a", "aa", "aaa"));
        list.forEach(str -> str.toUpperCase());
        System.out.println("list = " + Arrays.toString(list.toArray()));


        ImmutableBiMap<String, Integer> map1 =ImmutableBiMap.of("1", 11, "2", 22);
        System.out.println(" map1.get(1) = " + map1.get("1"));
        System.out.println("map1.get(22) = " + map1.inverse().get(22));
        ImmutableBiMap<String, Integer> map2 =ImmutableBiMap.of("1", 0, "2", 0);

        final SortedMap<Long, String> circle = new TreeMap<>();
        long hash = 10L;
        if (!circle.containsKey(hash)) {
            //返回此映射的部分视图，其键大于等于 hash
            SortedMap<Long, String> tailMap = circle.tailMap(hash);
            hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
        }
        circle.get(hash);

    }

    public static void testFunction(){
        ArrayList arrayList = new ArrayList();
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
        int a = 10_00_000_9;
        int aa = 0b1100_001;
    }





}
