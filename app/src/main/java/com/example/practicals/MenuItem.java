package com.example.practicals;

public class MenuItem {
    private String text;
    private int image;

    public MenuItem(String text, int image) {
        this.text = text;
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public int getImage() {
        return image;
    }
}
