package ceu.dam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ceu.dam.modelo.Usuario;

public class UsuarioDao {

	public Long insertarUsuario(Connection conn, Usuario usuario) throws SQLException {

		PreparedStatement stmt = null;

		try {
			String sql = "insert into usuarios (email, password, nombre, apellidos, ciclo, activo) values (?, ?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, usuario.getEmail());
			stmt.setString(2, usuario.getPassword());
			stmt.setString(3, usuario.getNombre());
			stmt.setString(4, usuario.getApellidos());
			stmt.setString(5, usuario.getCiclo());
			stmt.setBoolean(6, usuario.getActivo());
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.next();
			return rs.getLong(1);
		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
	}

	public Usuario consultarUsuario(Connection conn, String email) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		Usuario usuario = new Usuario();

		try {

			stmt = conn.prepareStatement("select * from usuarios where email = ?");
			stmt.setString(1, email);
			rs = stmt.executeQuery();

			if (rs.next()) {
				usuario.setEmail(rs.getString("email"));
				usuario.setPassword(rs.getString("password"));
				usuario.setNombre(rs.getString("nombre"));
				usuario.setApellidos(rs.getString("apellidos"));
				usuario.setCiclo(rs.getString("ciclo"));
				usuario.setActivo(rs.getBoolean("activo"));

				return usuario;
			} else {
				return null;
			}
		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
	}

}
