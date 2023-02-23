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
            stmt = conn.createStatement();
            ResultSet srs = stmt.executeQuery("select * from student");
            printData(srs, "name", "id", "dept");
            stmt.executeQuery("insert ")



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

    private static void printData(ResultSet srs, String col1, String col2, String col3) throws SQLException{
        while (srs.next()) {
            if (!col1.equals(""))
                System.out.print(srs.getString("name"));
            if (!col2.equals(""))
                System.out.print("\t|\t" + srs.getString("id"));
            if (!col3.equals(""))
                System.out.println("\t|\t" + srs.getString("dept"));
            else System.out.println();
        }//while 끝

    }
}

