package com.example.lib.d00testCode;

import org.jetbrains.annotations.NotNull;

public class EngineJava {
    private final String type;

    protected String engineType() {
        return "engine type is " + this.getType();
    }

    public String getType() {
        return this.type;
    }

    public EngineJava(@NotNull String type) {
        this.type = type;
    }
}
