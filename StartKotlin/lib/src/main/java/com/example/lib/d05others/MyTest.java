package com.example.lib.d05others;

// 配套在D0508KotlinCallJava2
public class MyTest {
    public static void main(String[] args) {
        Object[] objects = new String[3];
        // Java的数组设计的是有问题的
        // 这里声明的是Object数组 因此可以放入任意类型
        // 然而它实际指向的是一个String数组 因此运行时报错
        objects[0] = 1;
        objects[1] = "aaa";
    }
}
