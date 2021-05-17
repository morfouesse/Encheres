package fr.eni.javaee.encheres.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class ConnectionProvider {

	private static boolean test = true;
	
	private static DataSource dataSource;
	
	private static final String urldb = Settings.getProperty("urldb");
	private static final String userdb = Settings.getProperty("userdb");
	private static final String passworddb = Settings.getProperty("passworddb");
	private static String driverdb = Settings.getProperty("driverdb");
	
	static {
		try {
			//Lecture du XML et récupération de la ressource
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/pool_cnx");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param test true si on execute un fichier de test qui est "run as java app", false si "run on server"
	 * @return connection à la bdd
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		
		if (!test) {
			connection = dataSource.getConnection();
		} else {
			try {
				// Chargement du pilote JDBC
				// Méthode recommandée par Oracle
				//DriverManager.registerDriver(new SQLServerDriver());
				try {
					Class.forName(driverdb);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				// Obtenir une connexion
				connection = DriverManager.getConnection(urldb, userdb, passworddb);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return connection;
	}
	
}
