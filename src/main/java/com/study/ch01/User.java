package com.study.ch01;

// 사용자 정보 저장용 자바빈
public class User {
    private String id;
    private String name;
    private String password;

    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User() {
    }

    public void setId(String id){this.id = id;}
    public String getId(){return id;}
    public void setName(String name){this.name = name;}
    public String getName(){return name;}
    public void setPassword(String password){this.password = password;}
    public String getPassword(){return password;}
}
