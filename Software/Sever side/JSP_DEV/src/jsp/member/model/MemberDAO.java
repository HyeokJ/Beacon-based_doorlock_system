package jsp.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import jsp.util.DBConnection;


/* Data Access Object
 * 테이블 당 한개의 DAO를 작성.
 * 
 * user_inf 테이블과 연관된 DAO로
 * 회원 데이터를 처리하는 클래스.
 */

public class MemberDAO 
{
	private static MemberDAO instance;
	
	// 싱글톤 패턴
	public static MemberDAO getInstance(){
		if(instance==null)
			instance=new MemberDAO();
		return instance;
	}
		
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ArrayList<MemberBean> getMemberList(){
		ArrayList<MemberBean> memberList = new ArrayList<MemberBean>();
		MemberBean member = null;
		
		try {
			conn = DBConnection.getConnection();
			StringBuffer query_1 = new StringBuffer();
			query_1.append("SELECT * FROM user_inf");
			pstmt = conn.prepareStatement(query_1.toString());
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
			
			member = new MemberBean();
			member.setMem_id(rs.getString("id"));
			member.setMem_pw(rs.getString("pw"));
			member.setMem_pCode(rs.getString("pcode"));
			member.setMem_name(rs.getString("name"));
			member.setMem_email(rs.getString("email"));
			memberList.add(member);
			}
			
			return memberList;
			
			}catch(Exception sqle){
				throw new RuntimeException(sqle.getMessage());
	        } finally {
	            // Connection, PreparedStatement를 닫는다.
	            try{
	                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
	                if ( conn != null ){ conn.close(); conn=null;    }
	            }catch(Exception e){
	                throw new RuntimeException(e.getMessage());
	            }
	        }
	}
	
	public boolean duplicateIdCheck(String id) throws ClassNotFoundException, SQLException, NamingException
	{
		boolean x = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			
			conn = DBConnection.getConnection();
			StringBuffer query = new StringBuffer();
			query.append("SELECT mem_id FROM user_inf where mem_id = ?");
			pstmt = conn.prepareStatement(query.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) { x = true;} // 해당 아이디 존재
			
			return x;
			
		}catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
	}
		public boolean duplicatePnCheck(String pCode) throws ClassNotFoundException, SQLException, NamingException
		{
			boolean x = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				
				conn = DBConnection.getConnection();
				StringBuffer query = new StringBuffer();
				query.append("SELECT mem_pCode FROM user_inf where mem_pCode = ?");
				pstmt = conn.prepareStatement(query.toString());
				pstmt.setString(1, pCode);
				rs = pstmt.executeQuery();
				if (rs.next()) { x = true;} //해당 환자번호 있음
				
				return x;
				
			}catch (Exception sqle) {
	            throw new RuntimeException(sqle.getMessage());
	        } finally {
	            try{
	                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
	                if ( conn != null ){ conn.close(); conn=null;    }
	            }catch(Exception e){
	                throw new RuntimeException(e.getMessage());
	            }
	        }
			
	}
	
	
	public void insertMember (MemberBean member) throws SQLException
	{
		try{
			conn = DBConnection.getConnection(); // 커넥션을 가져온다
			conn.setAutoCommit(false); // 자동 커밋을 False 로 지정
			
			StringBuffer query_1 = new StringBuffer();
			query_1.append("insert into user_inf values");
			query_1.append("(?, ?, ?, ?, ?)");

			
			pstmt = conn.prepareStatement(query_1.toString());
			pstmt.setString(1, member.getMem_id());
			pstmt.setString(2, member.getMem_pw());
			pstmt.setString(3, member.getMem_pCode());
			pstmt.setString(4, member.getMem_name());
			pstmt.setString(5, member.getMem_email());
			pstmt.executeUpdate();
			conn.commit();
			
		} catch (ClassNotFoundException | NamingException | SQLException sqle) {
				// 오류시 롤백
				conn.rollback(); 
            
				throw new RuntimeException(sqle.getMessage());
        } finally {
        	try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        } 
    }

	public int loginCheck(String id, String pw){

		String DBpw = ""; // DB에서 꺼낸 비밀번호를 담을 변수
		int x = -1;

		try {
			
			conn = DBConnection.getConnection();
			StringBuffer query_3 = new StringBuffer();
			query_3.append("SELECT mem_pw FROM user_inf where mem_id = ?");
			pstmt = conn.prepareStatement(query_3.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) // 입력된 아이디에 해당하는 비밀번호가 있을 경우
			{
				DBpw = rs.getString("mem_pw");
				
				if(DBpw.equals(pw))
					x = 1; // 넘겨받은 비밀번호와 꺼내온 비밀번호,  비교 같으면 인증 성공
				else
					x = 0; // DB의 비밀번호와 입력받은 비밀번호가 다를 경우
			}
			else {
				x = -1; // 해당 아이디가 없을 경우
			}
			
			return x;
		}catch (Exception sqle) {
            throw new RuntimeException(sqle.getMessage());
        } finally {
            try{
                if ( pstmt != null ){ pstmt.close(); pstmt=null; }
                if ( conn != null ){ conn.close(); conn=null;    }
            }catch(Exception e){
                throw new RuntimeException(e.getMessage());
            }
        }
    } 
}
