package N_JDBC编程;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class A_JDBC简介 {
    public static void main(String[] args) throws SQLException {
        //`JDBC连接
//        先了解什么是Connection。Connection代表一个JDBC连接，它相当于Java程序到数据库的连接（通常是TCP连接）。
//        打开一个Connection时，需要准备URL、用户名和口令，才能成功连接到数据库。
        // JDBC连接的URL, 不同数据库有不同的格式:
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "123456";
// 获取连接:
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
// TODO: 访问数据库...
// 关闭连接:
        conn.close();

    }
}
