package edu.goodsinfo;

import java.sql.*;


public class GoodsInfoSelect {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/malldb?useSSL=false&serverTimezone=Asia/Seoul",
                    "root", "datatest"
            );
            if (conn != null) {
                System.out.println("DB 연결 완료");
            }
            stmt = conn.createStatement();

            ResultSet srs = stmt.executeQuery
                    (seletData("*", "goodsinfo"));
            printData(srs);
            srs = stmt.executeQuery
                    (seletData("code, name", "goodsinfo where maker = 'ss'"));
            printData(srs);
            srs = stmt.executeQuery
                    (seletData("avg(price) as 'price average'", "goodsinfo"));
            printData(srs);
            srs = stmt.executeQuery
                    (seletData("sum(price) as 'price sum'", "goodsinfo"));
            printData(srs);
            srs = stmt.executeQuery
                    (seletData("count(maker) as 'sell maker'", "goodsinfo where maker = 'ss'"));
            printData(srs);
            srs = stmt.executeQuery
                    (seletData("maker, count(maker) as 'maker sell'", "goodsinfo group by maker"));
            printData(srs);
            srs = stmt.executeQuery
                    (seletData("maker, count(maker) as 'maker sell'", "goodsinfo group by maker having count(maker) > 3"));
            printData(srs);
            srs = stmt.executeQuery
                    (seletData("maker, count(maker), avg(price)", "goodsinfo group by maker"));
            printData(srs);
            srs = stmt.executeQuery
                    (seletData("maker, count(maker), avg(price)", "goodsinfo group by maker having avg(price) >= 400000"));
            printData(srs);




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

    private static String seletData(String c1, String c2){
        String sel = "select " + c1 + " from " + c2;
        return sel;
    }

    private static void printData(ResultSet srs) throws SQLException {
        ResultSetMetaData srsm = srs.getMetaData();
        int cols = srsm.getColumnCount();
        while (srs.next()) {
            System.out.print(srs.getString(srsm.getColumnName(1)));
            for (int i = 2; i <= cols; i++) {
                    System.out.print("\t|\t" + srs.getString(srsm.getColumnName(i)));
            }//for 끝
            System.out.println();
        }//while 끝
        System.out.println();
        }
    }



