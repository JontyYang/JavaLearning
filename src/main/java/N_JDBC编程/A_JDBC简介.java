package N_JDBC编程;

import java.sql.*;

/*
Statment和ResultSet都是需要关闭的资源，因此嵌套使用try (resource)确保及时关闭；

rs.next()用于判断是否有下一行记录，如果有，将自动把当前行移动到下一行（一开始获得ResultSet时当前行不是第一行）；

ResultSet获取列时，索引从1开始而不是0；

必须根据SELECT的列的对应位置来调用getLong(1)，getString(2)这些方法，否则对应位置的数据类型不对，将报错。
 */
public class A_JDBC简介 {
    public static void main(String[] args) throws SQLException {
        //`JDBC连接
//        先了解什么是Connection。Connection代表一个JDBC连接，它相当于Java程序到数据库的连接（通常是TCP连接）。
//        打开一个Connection时，需要准备URL、用户名和口令，才能成功连接到数据库。
        // JDBC连接的URL, 不同数据库有不同的格式:
        String JDBC_URL = "jdbc:mysql://localhost:3306/learnjdbc?serverTimezone=GMT%2B8&useSSL=false&characterEncoding=utf8";
        String JDBC_USER = "root";
        String JDBC_PASSWORD = "123456";
// 获取连接:
        Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
// TODO: 访问数据库...
        try (Statement stmt = conn.createStatement()) {
            try (ResultSet rs = stmt.executeQuery("SELECT id, grade, name, gender FROM students WHERE gender=1")) {
                while (rs.next()) {
                    long id = rs.getLong(1); // 注意：索引从1开始
                    long grade = rs.getLong(2);
                    String name = rs.getString(3);
                    int gender = rs.getInt(4);
                    System.out.println(id);
                }
            }
        }


//        核心代码是DriverManager提供的静态方法getConnection()。DriverManager会自动扫描classpath，找到所有的JDBC驱动，然后根据我们传入的URL自动挑选一个合适的驱动。
//
//        因为JDBC连接是一种昂贵的资源，所以使用后要及时释放。使用try (resource)来自动释放JDBC连接是一个好方法
//        try (Connection conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
//    ...
//        }

        // 关闭连接:
        conn.close();

    }
}
