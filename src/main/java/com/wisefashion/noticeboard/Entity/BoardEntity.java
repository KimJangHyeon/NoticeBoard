package com.wisefashion.noticeboard.Entity;

public class BoardEntity {

    int id;
    String name;
    String title;
    String text;

    public void setVariable(int id, String name, String title, String text) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.text = text;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
