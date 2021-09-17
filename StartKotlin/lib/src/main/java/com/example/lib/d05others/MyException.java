package com.example.lib.d05others;
// 配套 D0508KotlinCallJava3

import java.io.IOException;

public class MyException {
    public void myMethod() throws IOException {
        throw new IOException("I/O 异常");
    }

    // 可以看到在Java内部调用会抛出异常的Java方法 需要对异常进行处理
    public void callExceptionMethod() {
        try {
            this.myMethod();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
