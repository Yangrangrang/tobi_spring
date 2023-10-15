package com.review.ch01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    /*
    public AccountDao accountDao() {
        return new AccountDao(connectionMaker());
    }

    public MessageDao messageDao() {
        return new MessageDao(connectionMaker());
    }
     */

    @Bean // --------------------------------------------> <bean
    public ConnectionMaker connectionMaker() { // ------->     id="connectionMaker"
        return new DConnectionMaker(); // ---->     class="review.ch01.DConnectionMaker" />
//        return new LocalDBConnectionMaker();
//        return new ProductionDBConnectionMaker();
    }
}
