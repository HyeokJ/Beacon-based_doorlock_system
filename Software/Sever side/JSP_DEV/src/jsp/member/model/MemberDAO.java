package jsp.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

import jsp.util.DBConnection;


/* Data Access Object
 * ���̺� �� �Ѱ��� DAO�� �ۼ�.
 * 
 * guest_inf ���̺�� ������ DAO��
 * ȸ�� �����͸� ó���ϴ� Ŭ����.
 */

public class MemberDAO 
{
	
	private static MemberDAO instance;
	
	// �̱��� ����
	private MemberDAO(){}
	public static MemberDAO getInstance(){
		if(instance==null)
			instance=new MemberDAO();
		return instance;
	}
	
	// �����ϴ� �湮�ڹ�ȣ�� �α��� �õ��Ҷ� �ش� Ʃ�ÿ� imei�� ��ϵ����� ������ �ش����� imei��ȣ�� ���̺� �����ϴ� �޼���
	public void insertImei(String id, String imei) throws SQLException
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			// Ŀ�ؼ��� ������
			conn = DBConnection.getConnection();
			
			// �ڵ� Ŀ���� false��
			conn.setAutoCommit(false);
			
			// ���� ����
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE guest_inf SET IMEInum=? WHERE Gnumber=?");		
			/* 
			 * StringBuffer�� ��� ���� �������� toString()�޼��带
			 * �̿��ؾ� ��.
			 */
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, imei);
			pstmt.setString(2, id);
			
			// ���� ����
			pstmt.executeUpdate();
			// �Ϸ�� Ŀ��
			conn.commit(); 
			
		} catch (ClassNotFoundException | NamingException | SQLException sqle) {
			// ������ �ѹ�
			conn.rollback(); 
			
			throw new RuntimeException(sqle.getMessage());
		} finally {
			// Connection, PreparedStatement �ݱ�
			try{
				if ( pstmt != null ){ pstmt.close(); pstmt=null; }
				if ( conn != null ){ conn.close(); conn=null;	}
			}catch(Exception e){
				throw new RuntimeException(e.getMessage());
			}
		} // end try~catch 
	} // end insertMember()
	
	
	// �α��ν� �湮�ڹ�ȣ, imei üũ �޼���
	// �湮�ڹ�ȣ, imei�� ���ڷ� �޴´�.
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

		String dbImei = ""; // db���� ���� imei�� ���� ����
		int x = -1;

		try {
			
				// ���� - ���� �Էµ� �湮�ڹ�ȣ�� DB���� imei�� ��ȸ�Ѵ�.
				StringBuffer query2 = new StringBuffer();
				query2.append("SELECT COALESCE(IMEInum,0) FROM guest_inf WHERE Gnumber=?");

				conn = DBConnection.getConnection();
				pstmt = conn.prepareStatement(query2.toString());
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				if (rs.next()) // �Էµ� �湮�ڹ�ȣ Ʃ�ÿ� �ش��ϴ� imei��ȣ�� �������
				{
					
					dbImei = rs.getString("COALESCE(IMEInum,0)"); // imei�� ������ �ֱ�

					if (dbImei.equals(imei)) 
						x = 1; // �Ѱܹ��� imei�� ������ imei�� ��. ������ ��������
					else if(dbImei.equals("0"))
					{
						insertImei(id,imei);
						x=1;
					}
					else 				 
						x = 0; // DB�� imei�� ����� imei�� �ٸ��� , ��������
					
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

