package com.jiangj.concurrency.example.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Jiang Jian
 * @since 2020/1/12
 */
@Slf4j
public class SynchronizedTest {

    /**
     * 修饰代码块
     */
    public void test1(int j) {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                log.info("test1, {} - {}",j , i);
            }
        }
    }

    /**
     * 修饰方法
     */
    public synchronized void test2(int j) {
        for (int i = 0; i < 10; i++) {
            log.info("test2, {} - {}", j, i);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SynchronizedTest synchronizedTest1 = new SynchronizedTest();
        SynchronizedTest synchronizedTest2 = new SynchronizedTest();

        executorService.execute(()->{
            synchronizedTest1.test1(1);
        });

        executorService.execute(()->{
            synchronizedTest2.test1(2);
        });
        executorService.shutdown();
    }
}
