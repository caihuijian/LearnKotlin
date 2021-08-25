package com.example.lib.d00testCode;

public class BMWEngineJava {
    private EngineJava bMWEngine = new EngineJava("BWM");

    public String getEngine() {
        // 同一包内可以访问protected方法
        return bMWEngine.engineType();
    }
}
