package com.study.ch01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
//    public UserDao userDao(){
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao userDao = new UserDao(connectionMaker);
//        return userDao;
//    }

    @Bean
    public UserDao userDao(){

        //return new UserDao(connectionMaker());

        // 수정자 메소드
        UserDao userDao = new UserDao();
        userDao.setConnectionMaker(connectionMaker());
        return userDao;
    }
    @Bean
    public ConnectionMaker connectionMaker(){
        return new DConnectionMaker();
    }
}
