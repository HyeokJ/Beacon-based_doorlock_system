package jsp.destination.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

import jsp.member.model.MemberDAO;
import jsp.util.DBConnection;

public class DestinationDAO {

private static DestinationDAO instance;
	
	// ΩÃ±€≈Ê ∆–≈œ
	private DestinationDAO(){}
	public static DestinationDAO getInstance(){
		if(instance==null)
			instance=new DestinationDAO();
		return instance;
	}
	
	public void searchDst(String search) throws SQLException{
		
	}
	
}
