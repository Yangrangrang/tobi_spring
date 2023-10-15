package com.review.ch01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        UserDao userDao = new UserDao();
        userDao.setDataSource(dataSource());
        return userDao;
    }

    @Bean
    public DataSource dataSource() {
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();

        dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/SPRINGSTUDYDB");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
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
