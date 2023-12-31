package com.study.ch02;

import com.study.ch01.User;
import com.study.ch01.UserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class UserDaoTest {
    //    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
//
//        UserDao dao = context.getBean("userDao", UserDao.class);
//
//        User user = new User();
//        user.setId("testtest01");
//        user.setName("yanghanna");
//        user.setPassword("1234");
//
//        dao.add(user);
//
//        System.out.println(user.getId() + "등록");
//
//        User user2 = dao.get(user.getId());
//
//        System.out.println(user2.getName());
//        System.out.println(user2.getPassword());
//
//        System.out.println(user2.getId() + "조회");
//
//        if(!user.getName().equals(user2.getName())){
//            System.out.println("테스트 실패 (name)");
//        } else if (!user.getPassword().equals(user2.getPassword())) {
//            System.out.println("테스트 실패 (password)");
//        } else {
//            System.out.println("조회 테스트 성공");
//        }
//
//    }
    private UserDao dao;

    private User user1;
    private User user2;
    private User user3;


    @BeforeEach
    void setUp() {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");
        this.dao = context.getBean("userDao", UserDao.class);

        this.user1 = new User("test1", "test1", "test1");
        this.user2 = new User("test2", "test2", "test2");
        this.user3 = new User("test3", "test3", "test3");
    }

    @Test
    public void addAndGet() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertThat(dao.getCount()).isSameAs(0);

//        User user = new User();
//        user.setId("gyumee2");
//        user.setName("김성철");
//        user.setPassword("springno1");

//        User user = new User("test", "testUser", "testPw");

        dao.add(user1);
        dao.add(user2);
        assertThat(dao.getCount()).isSameAs(2);

        User userget1 = dao.get(user1.getId());
        assertThat(userget1.getName()).isEqualTo(user1.getName());
        assertThat(userget1.getPassword()).isEqualTo(user1.getPassword());

        User userget2 = dao.get(user2.getId());
        assertThat(userget2.getName()).isEqualTo(user2.getName());
        assertThat(userget2.getPassword()).isEqualTo(user2.getPassword());

    }

    @Test
    public void count() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertThat(dao.getCount()).isSameAs(0);

        dao.add(user1);
        assertThat(dao.getCount()).isSameAs(1);

        dao.add(user2);
        assertThat(dao.getCount()).isSameAs(2);

        dao.add(user3);
        assertThat(dao.getCount()).isSameAs(3);

    }

    @Test
    public void getUserFailure() throws SQLException, ClassNotFoundException {

        dao.deleteAll();
        assertThat(dao.getCount()).isSameAs(0);
//
        Assertions.assertThrows(EmptyResultDataAccessException.class, ()->{
            dao.get("unknown_id");
        });
    }

//    public static void main(String[] args) {
//        JUnitCore.main("com.study.ch02.UserDaoTest");
//    }

}
