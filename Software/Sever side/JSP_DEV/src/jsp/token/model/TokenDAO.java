package jsp.token.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;

import jsp.destination.model.DestinationDAO;
import jsp.member.model.MemberDAO;
import jsp.util.DBConnection;

public class TokenDAO {
	
	private static TokenDAO instance;
	
	// ΩÃ±€≈Ê ∆–≈œ
	private TokenDAO(){}
	public static TokenDAO getInstance(){
		if(instance==null)
			instance=new TokenDAO();
		return instance;
	}
	
	
}
