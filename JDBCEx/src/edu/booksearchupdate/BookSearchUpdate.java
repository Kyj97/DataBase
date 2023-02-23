package edu.booksearchupdate;

import java.sql.*;

public class BookSearchUpdate {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookdb?useSSL=false&serverTimezone=Asia/Seoul",
                    "root", "datatest"
            );
            if (conn != null) {
                System.out.println("DB 연결 완료");
            }
            stmt = conn.createStatement();
            System.out.println("초기 버전");
            printData(stmt);
            System.out.println("업데이트");
            stmt.executeUpdate("insert into book (id, title, publisher, auther) values ('3', 'animal Farm', 'publish', 'mola4')");
            printData(stmt);
            System.out.println("데이터 수정");
            stmt.executeUpdate("update book set auther='me' where id = '2'");
            printData(stmt);




        } catch (
                ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 오류");
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } //finally 끝
    }



        private static void printData (Statement stmt) throws SQLException {
            ResultSet srs = stmt.executeQuery("select * from book");
            while (srs.next()) {
                String id = srs.getString("id");
                String title = srs.getString("title");
                String publisher = srs.getString("publisher");
                String auther = srs.getString("auther");
                System.out.println(id + "\t|\t" + title + "\t|\t" + publisher + "\t|\t" + auther);
            }//while 끝

        }
    }