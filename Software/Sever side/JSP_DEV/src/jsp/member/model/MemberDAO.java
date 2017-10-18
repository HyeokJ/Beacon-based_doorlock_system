package jsp.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import jsp.util.DBConnection;


/* Data Access Object
 * ���̺� �� �Ѱ��� DAO�� �ۼ�.
 * 
 * user_inf ���̺�� ������ DAO��
 * ȸ�� �����͸� ó���ϴ� Ŭ����.
 */

public class MemberDAO 
{
	private static MemberDAO instance;
	
	// �̱��� ����
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
	            // Connection, PreparedStatement�� �ݴ´�.
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
			if (rs.next()) { x = true;} // �ش� ���̵� ����
			
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
				if (rs.next()) { x = true;} //�ش� ȯ�ڹ�ȣ ����
				
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
			conn = DBConnection.getConnection(); // Ŀ�ؼ��� �����´�
			conn.setAutoCommit(false); // �ڵ� Ŀ���� False �� ����
			
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
				// ������ �ѹ�
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

		String DBpw = ""; // DB���� ���� ��й�ȣ�� ���� ����
		int x = -1;

		try {
			
			conn = DBConnection.getConnection();
			StringBuffer query_3 = new StringBuffer();
			query_3.append("SELECT mem_pw FROM user_inf where mem_id = ?");
			pstmt = conn.prepareStatement(query_3.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if (rs.next()) // �Էµ� ���̵� �ش��ϴ� ��й�ȣ�� ���� ���
			{
				DBpw = rs.getString("mem_pw");
				
				if(DBpw.equals(pw))
					x = 1; // �Ѱܹ��� ��й�ȣ�� ������ ��й�ȣ,  �� ������ ���� ����
				else
					x = 0; // DB�� ��й�ȣ�� �Է¹��� ��й�ȣ�� �ٸ� ���
			}
			else {
				x = -1; // �ش� ���̵� ���� ���
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
