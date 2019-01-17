package main.java.com.codecool.servlet;

public class Item {
    private String name;
    private Integer id;
    private double price;
    private static int itemCount = 0;

    public Item(String name, double price) {
        itemCount++;
        this.name = name;
        this.price = price;
        this.id = itemCount;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
