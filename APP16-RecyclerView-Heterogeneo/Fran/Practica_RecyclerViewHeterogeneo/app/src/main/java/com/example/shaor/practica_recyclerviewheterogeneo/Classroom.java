package com.example.shaor.practica_recyclerviewheterogeneo;

public class Classroom implements ListElement {

    private String name;

    public Classroom(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getType() {
        return R.layout.activity_main_classroomitem;
    }
}
