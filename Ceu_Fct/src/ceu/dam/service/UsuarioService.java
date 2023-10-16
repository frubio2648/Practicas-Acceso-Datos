package ceu.dam.service;

import java.sql.Connection;
import java.sql.SQLException;

import ceu.dam.dao.UsuarioDao;
import ceu.dam.db.OpenConnection;
import ceu.dam.exception.AutenticacionException;
import ceu.dam.exception.FCTException;
import ceu.dam.modelo.Usuario;

public class UsuarioService {

	private OpenConnection connProvider;

	public UsuarioService() {
		connProvider = new OpenConnection();
	}

	public Usuario loginUsuario(String email, String password) throws AutenticacionException, FCTException {
		UsuarioDao daoUsuario = new UsuarioDao();
		Connection conn = null;
		Usuario usuario = new Usuario();
		try {
			conn = connProvider.getNewConnection();

			if (daoUsuario.consultarUsuario(conn, email).getEmail() == null) {
				throw new AutenticacionException("No existe el email");
			} else if (!daoUsuario.consultarUsuario(conn, email).getPassword().equals(password)) {
				throw new AutenticacionException("Error al introducir la contraseña");
			}

			return usuario;

		} catch (SQLException e) {
			throw new FCTException("No existe ningun usuario", e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	public void altaUsuario(Usuario usuario) throws FCTException, AutenticacionException {
		UsuarioDao daoUsuario = new UsuarioDao();
		Connection conn = null;
		try {
			conn = connProvider.getNewConnection();

			if (daoUsuario.consultarUsuario(conn, usuario.getEmail()) != null) {
				throw new AutenticacionException();
			} else {
				daoUsuario.insertarUsuario(conn, usuario);
			}

		} catch (SQLException e) {
			throw new FCTException("Error en la base de datos", e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}
}
