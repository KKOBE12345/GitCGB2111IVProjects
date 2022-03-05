package com.cy;

import com.sun.scenario.effect.impl.prism.PrImage;
import lombok.Data;

/**
 * 自定义一个阻塞式队列容器
 * 数据存储结构  数组
 * 存放算法：FIFO(放数据永远放在size位置   取数据永远从 下标为0 的位置开始取数据)
 * 容器是一个有界数据   满了就阻塞
 * 空了也会阻塞
 * */
@Data
class BlockQueueContainer<T>{
    //容器
    private Object[] array;
    //大小
    private int size;

    public BlockQueueContainer(int cap) {
        array=new Object[cap];
    }

    //放数据的方法
    public synchronized void put(T t){
        //加入容器满了  要阻塞
        /**阻塞方法同wait()
         * 唤醒方法用notifyall()
         * 这两个方法必须应用在同步代码块  或者同步方法中
         * 由对象锁来调用*/
        while(size==array.length) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        array[size]=t;
        size++;
        //通知线程可以取数据了   唤醒在阻塞的线程
        this.notifyAll();
    }
    //取数据 的方法
    public synchronized T take(){
        while (size==0)
        {//加入容器没有数据了  让取线程的数据阻塞
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //从下标0开始取
        Object data=array[0];
        //移动元素  将下标0后面的元素一次前移
        System.arraycopy(array, 1, array, 0, array.length-1);
        size--;
        //通知放数据的线程可以继续放了  唤醒放数据时处于阻塞的线程
        //这个notifyAll()  会唤醒所有的线程  因此有缺点
        this.notifyAll();
        return (T)data;
    }
}

public class MyBlackQueueTest {
    public static void main(String[] args) {
        BlockQueueContainer<Integer> kobe=new BlockQueueContainer<>(3);
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                int i=1;
                while(true){
                    kobe.put(i++);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    Object data = kobe.take();
                    System.out.println(data);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        t1.start();
        t2.start();




    }
}
