package edu.bookinsert;

import java.sql.*;

public class BookInsertPre {
    public static void main(String[] args) {
        insert("0", "Harry potter", "Bloomsbury", "mola");
        insert("1", "The Lord of the Rings", "Allen & Unwin", "molaa");
        insert("2", "Pride and Prejudice", "T. Egerton Kingdom", "molaaa");
    }
    public static void insert(String col1, String col2, String col3, String col4) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/bookdb?useSSL=false&serverTimezone=Asia/Seoul",
                    "root", "datatest"
            );
            pstmt = conn.prepareStatement("insert into book (id, title, publisher, auther) values (?, ?, ?, ?);");
            pstmt.setString(1, col1);
            pstmt.setString(2, col2);
            pstmt.setString(3, col3);
            pstmt.setString(4, col4);
            int result = pstmt.executeUpdate();
            if (result == 0) {
                System.out.println("데이터입력안대");
            }
            else {
                System.out.println("데이터입력대");
            }
            System.out.println("--저장됨?--" + result);
            printData(pstmt);
        } catch (
                ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 오류"); //첨에 찾는데 없으면 나오는 오류. 드라이버를 아예 못찾은 경우.
        } catch (
                SQLException ex) {
            System.out.println(ex.getMessage()); //아이디나 패스워드가 틀리면 나오는 오류
        }
        finally {
            try {
                pstmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } //finally 끝
    }

    private static void printData(Statement stmt) throws SQLException {
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


