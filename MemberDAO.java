package jsp.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	private MemberDAO(){}
	public static MemberDAO getInstance(){
		if(instance==null)
			instance=new MemberDAO();
		return instance;
	}
		
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ArrayList<MemberDTO> select(){
		ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
		
		try {
			conn = DBConnection.getConnection();
			StringBuffer query_1 = new StringBuffer();
			query_1.append("SELECT * FROM user_inf");
			pstmt = conn.prepareStatement(query_1.toString());
			rs = pstmt.executeQuery();
		
			while(rs.next()) {
			
			MemberDTO dto = new MemberDTO();
			dto.setMem_id(rs.getString("id"));
			dto.setMem_pw(rs.getString("pw"));
			dto.setMem_pCode(rs.getString("pcode"));
			dto.setMem_name(rs.getString("name"));
			dto.setMem_email(rs.getString("email"));
			list.add(dto);
			}
			
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try {if(rs != null)rs.close();}catch(SQLException s) {s.printStackTrace();}
				try {if(pstmt != null)pstmt.close();}catch(SQLException s) {s.printStackTrace();}
				try {if(conn != null)conn.close();}catch(SQLException s) {s.printStackTrace();}
			}
			return list;
	}
	
	
	public void insert(MemberDTO dto) 
	{
		try{
			conn = DBConnection.getConnection();		
			StringBuffer query_2 = new StringBuffer();
			query_2.append("insert into user_inf values(?,?,?,?,?)");
			pstmt = conn.prepareStatement(query_2.toString());
			
			pstmt.setString(1, dto.getMem_id());
			pstmt.setString(2, dto.getMem_pw());
			pstmt.setString(3, dto.getMem_pCode());
			pstmt.setString(4, dto.getMem_name());
			pstmt.setString(5, dto.getMem_email());
			pstmt.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {if(pstmt != null)pstmt.close();}catch(SQLException s) {s.printStackTrace();}
			try {if(conn != null)conn.close();}catch(SQLException s) {s.printStackTrace();}
		}
	}

	
	public boolean loginCheck(String id, String pw){

		
		boolean Bresult = false;
		;
		
		try {
			conn = DBConnection.getConnection();
			StringBuffer query_3 = new StringBuffer();
			query_3.append("select * from user_inf where mem_id = '" + id + "'and password ='" + pw + "'");
			pstmt = conn.prepareStatement(query_3.toString());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("id"));
				Bresult = true;
			}
		}catch (Exception e){}
			
		finally {
			try {
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch (Exception e) {}
			
		}
		
		return Bresult;
	}
}
	
	
	
	
	
	
	
	
	
	
}