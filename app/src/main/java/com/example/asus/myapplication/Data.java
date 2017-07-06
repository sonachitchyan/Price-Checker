package com.example.asus.myapplication;


public class Data {
    private String barcode, name;
    private int count;
    private double price;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Data(String barcode, String name, int count, double price) {
        this.barcode = barcode;
        this.name = name;
        this.count = count;
        this.price = price;
    }
}
