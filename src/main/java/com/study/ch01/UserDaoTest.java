package com.study.ch01;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

// UserDao의 클라이언트라고 볼 수 있다.
public class UserDaoTest {
    // main test
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao dao = new UserDao(connectionMaker);

        UserDao dao = new DaoFactory().userDao();

//        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("test103");
        user.setName("yanghanna");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + "등록");

        User user2 = dao.get(user.getId());

        System.out.println(user2.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회");




//        DaoFactory factory = new DaoFactory();
//        UserDao dao1 = factory.userDao();
//        UserDao dao2 = factory.userDao();
//
//        System.out.println(dao1);
//        System.out.println(dao2);
//
//        UserDao dao3 = context.getBean("userDao", UserDao.class);
//        UserDao dao4 = context.getBean("userDao", UserDao.class);
//
//        System.out.println(dao3);
//        System.out.println(dao4);
    }
}
