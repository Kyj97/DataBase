package edu.goodsinfo;

import java.sql.*;

public class GoodsInfoInsert {
    public static void main(String[] args) {
        //code, name, int price, maker
        GoodsInfoInsert.insert("10004", "김치냉장고", 800000, "DE");
        GoodsInfoInsert.insert("10005", "세탁기", 540000, "SS");
        GoodsInfoInsert.insert("10006", "컴퓨터", 260000, "LG");
        GoodsInfoInsert.insert("10007", "노트북", 180000, "DE");
        GoodsInfoInsert.insert("10008", "김치냉장고", 640000, "SS");
        GoodsInfoInsert.insert("10009", "노트북", 280000, "LG");
        GoodsInfoInsert.insert("10010", "식기세척기", 360000, "LG");
        GoodsInfoInsert.insert("10011", "세탁기", 420000, "DE");
    }

    public static void insert(String col1, String col2, int col3, String col4) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/malldb?useSSL=false&serverTimezone=Asia/Seoul",
                    "root", "datatest"
            );
            stmt = conn.prepareStatement("insert into goodsinfo (code, name, price, maker) values (?, ?, ?, ?);");
            stmt.setString(1, col1);
            stmt.setString(2, col2);
            stmt.setInt(3, col3);
            stmt.setString(4, col4);
            int result = stmt.executeUpdate();

            if (result == 0) {
                System.out.println("데이터 입력 오류");
            } else {
                System.out.println("데이터 입력 완료");
            }
            System.out.println("--저장됨--");
        } catch (
                ClassNotFoundException e) {
            System.out.println("JDBC 드라이버 로드 오류"); //첨에 찾는데 없으면 나오는 오류. 드라이버를 아예 못찾은 경우.
        } catch (
                SQLException ex) {
            System.out.println(ex.getMessage()); //아이디나 패스워드가 틀리면 나오는 오류
        } finally {
            try {
                stmt.close();
                conn.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } //finally 끝
    }

}


