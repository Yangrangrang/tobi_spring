package com.study.ch01;

import java.sql.SQLException;

// UserDao의 클라이언트라고 볼 수 있다.
public class UserDaoTest {
    // main test
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ConnectionMaker connectionMaker = new DConnectionMaker();
//        UserDao dao = new UserDao(connectionMaker);

        UserDao dao = new DaoFactory().userDao();

        User user = new User();
        user.setId("test8");
        user.setName("yanghanna");
        user.setPassword("1234");

        dao.add(user);

        System.out.println(user.getId() + "등록");

        User user2 = dao.get(user.getId());

        System.out.println(user2.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회");
    }
}
