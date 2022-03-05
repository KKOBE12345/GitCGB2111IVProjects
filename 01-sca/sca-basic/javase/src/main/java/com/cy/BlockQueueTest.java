package com.cy;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

//阻塞队列  先进先出
public class BlockQueueTest {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> bq=new ArrayBlockingQueue<>(3);
        bq.put(100);
        bq.put(200);
        bq.put(300);
        System.out.println(bq);
        bq.put(400);
        System.out.println(bq);
    }
}
