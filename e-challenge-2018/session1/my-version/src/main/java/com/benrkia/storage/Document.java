package com.benrkia.storage;

public class Document {

    private String name;
    private final double USED_DISK = 0.1;

    public Document(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUSED_DISK() {
        return USED_DISK;
    }
}
