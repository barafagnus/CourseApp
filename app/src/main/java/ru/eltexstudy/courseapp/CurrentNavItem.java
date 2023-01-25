package ru.eltexstudy.courseapp;

public class CurrentNavItem {
    private int id;
    private String name;
    private String color;
    public CurrentNavItem(String name, String color) {
        ENV.ID++;
        this.id = ENV.ID;
        this.name = name;
        this.color = color;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
