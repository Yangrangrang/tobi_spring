package com.study.ch01;

import com.mysql.jdbc.Driver;

import java.sql.*;

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        // Class.forName : DB 드라이버 로드
        Class.forName("com.mysql.cj.jdbc.Driver");
        // DB 연결을 위한 Connection
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/SPRINGSTUDYDB", "root","root");
        // SQL 담기 (prepareStatement) : users 테이블 insert문
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id,name,password) value (?,?,?)"
        );
        // 받은 데이터를 입력
        ps.setString(1,user.getId());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());

        // 수행결과로 int 타입 반환
        // select문을 제외한 다른 구문을 수행할 때 사용하는 함수 (executeUpdate)
        ps.executeUpdate();

        // SOL prepareStatement 닫기
        ps.close();
        // Connection 닫기
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/SPRINGSTUDYDB","root","root");

        PreparedStatement ps = c.prepareStatement(
                "SELECT * FROM users where id = ?"
        );

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao = new UserDao();

        User user = new User();
        user.setId("test1");
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


