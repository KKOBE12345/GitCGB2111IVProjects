package com.cy;

import org.openjdk.jol.info.ClassLayout;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Basic {
    public static void main(String[] args) {
        String dateStr = DateTimeFormatter.ofPattern("yyyy/MM/dd/")
                .format(LocalDate.now());
        System.out.println(dateStr);

        Object o="kobe james";
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        Integer a1=100;
        Integer a2=100;
        Integer a3=8888;
        Integer a4=8888;
        System.out.println(a1==a2);
        System.out.println(a3==a4);
    }
}
