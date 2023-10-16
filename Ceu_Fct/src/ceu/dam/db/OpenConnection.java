package ceu.dam.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OpenConnection {

	public Connection getNewConnection() throws SQLException {
		String usuario = "Ceu-FCT";
		String password = "Ceu-FCT";
		String driverClass = "org.mariadb.jdbc.Driver";
		String url = "jdbc:mariadb://localhost:3306/Ceu-FCT";

		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException e) {
			System.err.println("No se encuentra el driver JDBC. Revisa su configuración");
			throw new RuntimeException(e);
		}

		Connection conn = DriverManager.getConnection(url, usuario, password);
		return conn;
	}

}
