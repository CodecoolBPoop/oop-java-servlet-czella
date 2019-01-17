package main.java.com.codecool.servlet;

import java.util.ArrayList;
import java.util.List;

public class ItemStore {
    private static List<Item> itemList = new ArrayList<>();

    public static void add(Item item) {
        itemList.add(item);
    }

    public static void remove(Item item) {
        itemList.remove(item);
    }

    public static List<Item> getItemList() {
        return itemList;
    }
}


