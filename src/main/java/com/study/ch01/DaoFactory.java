package com.study.ch01;

public class DaoFactory {
//    public UserDao userDao(){
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//        return userDao;
//    }
    public UserDao userDao(){
        return new UserDao(connectionMaker());
    }
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }
}
