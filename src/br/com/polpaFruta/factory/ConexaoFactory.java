package br.com.polpaFruta.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

	
	private static final String  URL = "jdbc:firebirdsql:localhost/3050:D:\\\\BASEPF.fdb?lc_ctype=WIN1252";
	private static final String  USUARIO = "SYSDBA";
	private static final String  SENHA = "masterkey";
	
	
	public static Connection conectar() throws SQLException {
		
		DriverManager.registerDriver(new org.firebirdsql.jdbc.FBDriver());
		Connection con = DriverManager.getConnection(URL, USUARIO, SENHA);
				
		return con;
	}
	
}
