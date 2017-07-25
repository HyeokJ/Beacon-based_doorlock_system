package jsp.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

import jsp.util.DBConnection;


/* Data Access Object
 * 테이블 당 한개의 DAO를 작성.
 * 
 * guest_inf 테이블과 연관된 DAO로
 * 회원 데이터를 처리하는 클래스.
 */

public class MemberDAO 
{
	
	private static MemberDAO instance;
	
	// 싱글톤 패턴
	private MemberDAO(){}
	public static MemberDAO getInstance(){
		if(instance==null)
			instance=new MemberDAO();
		return instance;
	}
	
	// 존재하는 방문자번호로 로그인 시도할때 해당 튜플에 imei가 등록되있지 않으면 해당기기의 imei번호를 테이블에 저장하는 메서드
	public void insertImei(String id, String imei) throws SQLException
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// 커넥션을 가져옴
			conn = DBConnection.getConnection();
			
			// 자동 커밋을 false로
			conn.setAutoCommit(false);
			
			// 쿼리 생성
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE guest_inf SET IMEInum=? WHERE Gnumber=?");		
			/* 
			 * StringBuffer에 담긴 값을 얻으려면 toString()메서드를
			 * 이용해야 함.
			 */
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, imei);
			pstmt.setString(2, id);
			
			// 쿼리 실행
			pstmt.executeUpdate();
			// 완료시 커밋
			conn.commit(); 
			
		} catch (ClassNotFoundException | NamingException | SQLException sqle) {
			// 오류시 롤백
			conn.rollback(); 
			
			throw new RuntimeException(sqle.getMessage());
		} finally {
			// Connection, PreparedStatement 닫기
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		} // end try~catch 
	} // end insertMember()
	
	
	// 로그인시 방문자번호, imei 체크 메서드
	// 방문자번호, imei를 인자로 받는다.
	public int loginCheck(String id, String imei) 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		int x = -1;

		try {
			StringBuffer query1 = new StringBuffer();
			query1.append("SELECT Gnumber FROM guest_inf WHERE Gnumber=?");

			conn = DBConnection.getConnection();
			pstmt = conn.prepareStatement(query1.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				
			
			
				x=imeiCheck(id,imei);
			} else {
				x = -1;
			}
			return x;

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	} // end loginCheck()
	
	public int imeiCheck(String id, String imei) 
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String dbImei = ""; // db에서 꺼낸 imei를 담을 변수
		int x = -1;

		try {
			
				// 쿼리 - 먼저 입력된 방문자번호로 DB에서 imei를 조회한다.
				StringBuffer query2 = new StringBuffer();
				query2.append("SELECT COALESCE(IMEInum,0) FROM guest_inf WHERE Gnumber=?");

				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(query2.toString());
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if (rs.next()) // 입력된 방문자번호 튜플에 해당하는 imei번호가 있을경우
				{
					
					dbImei = rs.getString("COALESCE(IMEInum,0)"); // imei를 변수에 넣기

					if (dbImei.equals(imei)) 
						x = 1; // 넘겨받은 imei와 꺼내온 imei를 비교. 같으면 인증성공
					else if(dbImei.equals("0"))
					{
						insertImei(id,imei);
						x=1;
					}
					else 				 
						x = 0; // DB의 imei와 기기의 imei가 다를때 , 인증실패
					
				}
				else{}
				
			return x;

		} catch (Exception sqle) {
			throw new RuntimeException(sqle.getMessage());
		} finally {
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
}

