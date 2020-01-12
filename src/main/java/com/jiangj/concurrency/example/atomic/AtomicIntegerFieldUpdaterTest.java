package com.jiangj.concurrency.example.atomic;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author Jiang Jian
 * @since 2020/1/12
 */
@Slf4j
public class AtomicIntegerFieldUpdaterTest {

    private static AtomicIntegerFieldUpdater<AtomicIntegerFieldUpdaterTest> updater = AtomicIntegerFieldUpdater.newUpdater(AtomicIntegerFieldUpdaterTest.class, "count");

    @Getter
    public volatile int count = 100;

    private static AtomicIntegerFieldUpdaterTest updaterTest = new AtomicIntegerFieldUpdaterTest();

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 2; i++) {
            executorService.execute(() -> {
                cas(100, 120);
            });
        }
    }

    private static void cas(int expect, int update) {
        if (updater.compareAndSet(updaterTest, expect, update)) {
            log.info("update success, count:{}, thread:{}", updaterTest.getCount(), Thread.currentThread().getName());
        } else {
            log.info("update failed, count:{}, thread:{}", updaterTest.getCount(), Thread.currentThread().getName());
        }
    }

}
