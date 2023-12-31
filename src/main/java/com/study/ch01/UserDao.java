package com.study.ch01;

import com.mysql.jdbc.Driver;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    // 1.6 싱글톤
//    private static UserDao INSTANCE;
    // 1.3 DAO확장 (독립된 Class)
//    private SimpleConnectionMaker simpleConnectionMaker;

    // 초기에 설정하면 사용 중에는 바뀌지 않는 읽기 전용 인스턴스 변수
    // 의존관계 주입을 위한 코드
//    private ConnectionMaker connectionMaker;

    // 매번 새로운 값으로 바귀는 정보를 담은 인스턴스 변수. (심각한 문제가 발생한다.)
//    private Connection c;
//    private User user;

    // 의존관계 주입을 위한 코드
    //public UserDao(ConnectionMaker connectionMaker){
//        simpleConnectionMaker = new SimpleConnectionMaker();
//        connectionMaker = new DConnectionMaker();

    //    this.connectionMaker = connectionMaker;

        // 의존관계 검색을 이용하는 UserDao 생성자
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//        this.connectionMaker = context.getBean("connectionMaker", ConnectionMaker.class);
    //}

    // 수정자 메소드
//    public void setConnectionMaker(ConnectionMaker connectionMaker){
//        this.connectionMaker = connectionMaker;
//    }

    // DataSource 사용
    private DataSource dataSource;
    public void setDataSource(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        // Class.forName : DB 드라이버 로드
//        Class.forName("com.mysql.cj.jdbc.Driver");
        // DB 연결을 위한 Connection
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/SPRINGSTUDYDB", "root","root");
//        Connection c = getConnection();
//        Connection c = simpleConnectionMaker.makeNewConnection();
//        Connection c = connectionMaker.makeConnection();

        // DataSource 사용
        Connection c = dataSource.getConnection();

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
//        Class.forName("com.mysql.cj.jdbc.Driver");
//        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/SPRINGSTUDYDB","root","root");
//        Connection c = getConnection();
//        Connection c = simpleConnectionMaker.makeNewConnection();
//        Connection c = connectionMaker.makeConnection();

        // DataSource 사용
        Connection c = dataSource.getConnection();

//        this.c = connectionMaker.makeConnection();

        PreparedStatement ps = c.prepareStatement(
                "SELECT * FROM users where id = ?"
        );

        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();

        User user = null;
        if(rs.next()){
            user = new User();
            user.setId(rs.getString("id"));
            user.setName(rs.getString("name"));
            user.setPassword(rs.getString("password"));
        }
//        this.user = new User();
//        this.user.setId(rs.getString("id"));
//        this.user.setName(rs.getString("name"));
//        this.user.setPassword(rs.getString("password"));

        rs.close();
        ps.close();
        c.close();

        if(user == null) throw new EmptyResultDataAccessException(1);

        return user;
//        return this.user;
    }

    public void deleteAll() throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("delete from users");

        ps.executeUpdate();

        ps.close();
        c.close();
    }

    public int getCount() throws SQLException {
        Connection c = dataSource.getConnection();

        PreparedStatement ps = c.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        c.close();

        return count;
    }

    // connection 메소드
//    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

}

/*
* UserDao class 의 문제점은 뭘까?
*
* 정상작동은 되는데 뭐가 문제야?
* 스프링의 컨셉이 뭐였지? > 객체지향
* 1장의 중점은 오브젝트
* 나눠져있지 않아 통으로 되어 있어
* 그래서 내가 원하는거만 따로 못해
*
* */


