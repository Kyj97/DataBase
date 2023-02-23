package edu.jdbcexsecond;

import java.sql.*;

public class JDBCExSecond {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sampledb?useSSL=false&serverTimezone=Asia/Seoul",
                    "root", "datatest"
            );
            if (conn != null) {
                System.out.println("DB 연결 완료");
            }
            stmt = conn.prepareStatement("insert into book (?, ? ,?)");
            printData(stmt);
            System.out.println("레코드 삽입 후");
//           stmt.executeUpdate("insert into student (name, dept, id) values('강호동', '기계공학', '3333333');");
           stmt.executeUpdate("insert into student(name, dept, id) values('유재석', '잠와공학', '12345467');");
           stmt.executeUpdate("Update student set dept='체육학과' id='3333333';");
           printData(stmt);


        } catch (ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 오류"); //첨에 찾는데 없으면 나오는 오류. 드라이버를 아예 못찾은 경우.
        } catch (SQLException e) {
            System.out.println("DB 연결 오류"); //아이디나 패스워드가 틀리면 나오는 오류
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } //finally 끝

    }

    private static void printData(Statement stmt) throws SQLException{
        ResultSet srs = stmt.executeQuery("select * from student");
        while (srs.next()) {
             String name = srs.getString("name");
            String id = srs.getString("id");
            String dept = srs.getString("dept");
            System.out.println(name + "\t|\t" + id + "\t|\t" + dept);
        }//while 끝

    }
}

