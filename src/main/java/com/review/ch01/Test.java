package com.review.ch01;

import com.review.ch01.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);

        UserDao dao = context.getBean("userDao", UserDao.class);

        User user = new User();
        user.setId("userTest");
        user.setName("yang");
        user.setPassword("yang");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회 성공");

        dao.delete(user2);
        System.out.println("DB 삭제");

        DaoFactory factory = new DaoFactory();
        UserDao dao1 = factory.userDao();
        UserDao dao2 = factory.userDao();

        System.out.println(dao1);
        System.out.println(dao2);
    }
}
