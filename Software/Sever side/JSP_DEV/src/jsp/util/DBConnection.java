package jsp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.NamingException;


// Ŀ�ؼ��� ������ Ŭ���� - JNDI
public class DBConnection 
{
	public static Connection getConnection() throws SQLException, NamingException, 
	ClassNotFoundException{
		Connection conn = null;
		Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/doorlockdb" , "root", "root1234");
			return conn;
	}
}	