package com.example.lab9_18056521_hahuyhoang;

public class User {
    private String name;
    private String email;
    private int happy;
    private int normal;
    private int sad;

    public User(String name, String email, int happy, int normal, int sad) {
        this.name = name;
        this.email = email;
        this.happy = happy;
        this.normal = normal;
        this.sad = sad;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", happy=" + happy +
                ", normal=" + normal +
                ", sad=" + sad +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getHappy() {
        return happy;
    }

    public void setHappy(int happy) {
        this.happy = happy;
    }

    public int getNormal() {
        return normal;
    }

    public void setNormal(int normal) {
        this.normal = normal;
    }

    public int getSad() {
        return sad;
    }

    public void setSad(int sad) {
        this.sad = sad;
    }
}
