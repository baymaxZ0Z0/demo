package com.example.util;

public class Struct {
    public Struct(double hit, String filename) {
        this.hit = hit;
        this.filename = filename;
    }

    double hit;
     String filename;


    public void setHit(double hit) {
        this.hit = hit;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public double getHit() {
        return hit;
    }

    public String getFilename() {
        return filename;
    }


}
