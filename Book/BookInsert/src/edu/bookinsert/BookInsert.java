package edu.bookinsert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookInsert {

	public static void main(String[] args) {
		Connection conn = null ;
		Statement  stmt = null;
		
		try {
			Class.forName(
			"com.mysql.cj.jdbc.Driver"); // MySQL 드라이버 로드
			
			//Class.forName("com.mysql.jdbc.Driver"); // MySQL 드라이버 로드
			conn = DriverManager.getConnection(
			"jdbc:mysql://localhost:3306/bookdb?useSSL=false&serverTimezone=Asia/Seoul", "root","datatest"); // JDBC 연결
		
			
			if(conn != null)
			{
				System.out.println("DB 연결 완료");
			}				
			stmt = conn.createStatement(); // SQL문 처리용 Statement 객체 생성
			//여기부터 코드 작성
			
//			int nid = 0;
//			stmt.executeUpdate(
//"insert into book (id, title, publisher, author) "
//+ "values("+ nid++ +",'Harry Potter',"
//+" 'Bloomsbury','J. K. Rowling');"); // 레코드 추가
//			stmt.executeUpdate(
//"insert into book (id, title, publisher, author) "
//+ "values("+ nid++ +",'The Lord of the Rings',"
//+ " 'Allen & Unwin','J. R. R. Tolkein');"); // 레코드 추가
//			stmt.executeUpdate(
//"insert into book (id, title, publisher, author) "
//+ "values("+ nid++ +",'Pride and Prejudice',"
//+ "'T. Egerton Kingdom','제인 오스틴');"); // 레코드 추가
			
			printData(stmt);
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("JDBC 드라이버 로드 오류");
		} catch (SQLException e) {
			System.out.println("DB 연결 오류 "+ e.getMessage());
		}	
		finally
		{			
			try
			{
				stmt.close() ;
				conn.close();
			}			
			catch(Exception   e)
			{
				System.out.println(e.getMessage());
			}
		}//end of  finally block
	}//END of main()
	
	// 레코드의 각 열의 값 화면에 출력
	private static void printData(Statement pstmt) 
					throws SQLException {
				
		System.out.printf("%4s|%-30s|%-30s|%-10s\n",
			"id","title", "publisher", "author");
		
		ResultSet srs = pstmt.executeQuery(
	  "select * from book"); // 테이블의 모든 데이터 검색
		
		try {
			//srs = stmt.executeQuery("select * from book");
			while (srs.next()) {
				System.out.printf("%4s|%-30s|%-30s|%-10s\n", new String(srs.getString("id")), srs.getString("title"), srs.getString("publisher"), srs.getString("author"));
			}
		} catch (SQLException e) {
			System.out.println("SQL 실행 에러");
		}
		
//			while (srs.next()) 
//			{
//				System.out.print(
//					srs.getString("id")); 
//				System.out.print("\t|\t" + 
//					srs.getString("title"));
//				System.out.println("\t|\t" + 
//					srs.getString("publisher"));
//				System.out.println("\t|\t" + 
//					srs.getString("author"));
//				
//			}//end of while(rs.next() )
				
	}//end of printData( )
}





