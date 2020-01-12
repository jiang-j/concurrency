package com.jiangj.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jiang Jian
 * @since 2020/1/12
 */
@Slf4j
public class Synchronized2Test {

    /**
     * 修饰类
     */
    public static void test1(int j) {
        synchronized (Synchronized2Test.class) {
            for (int i = 0; i < 10; i++) {
                log.info("test1, {} - {}",j , i);
            }
        }
    }

    /**
     * 修饰静态方法
     */
    public synchronized static void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2, {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(()->{
            Synchronized2Test.test2(1);
        });

        executorService.execute(()->{
            Synchronized2Test.test2(2);
        });
        executorService.shutdown();
    }
}
