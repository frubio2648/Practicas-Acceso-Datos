package ceu.dam.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ceu.dam.dao.RegistroDao;
import ceu.dam.db.OpenConnection;
import ceu.dam.exception.FCTException;
import ceu.dam.modelo.Registro;

public class RegistroService {

	private OpenConnection connProvider;

	public RegistroService() {
		connProvider = new OpenConnection();
	}

	public List<Registro> consultarRegistroUsuario(Long idUsuario) throws FCTException {
		RegistroDao daoRegistro = new RegistroDao();
		Connection conn = null;

		try {
			conn = connProvider.getNewConnection();

			if (daoRegistro.consultarRegistro(conn, idUsuario) != null) {
				return daoRegistro.consultarRegistro(conn, idUsuario);
			} else {
				throw new SQLException();
			}

		} catch (SQLException e) {
			throw new FCTException("No existe ningun registro", e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

	public void crearRegistro(Registro registro) throws FCTException {

		RegistroDao daoRegistro = new RegistroDao();
		Connection conn = null;

		try {
			conn = connProvider.getNewConnection();

			List<Registro> registros = new ArrayList<>();
			registros = daoRegistro.consultarRegistroFecha(conn, registro.getidUsuario(), registro.getFecha());

			for (Registro regis : registros) {
				if (regis.getFecha() != registro.getFecha()) {
					daoRegistro.insertarRegistro(conn, registro);
					System.out.println("Registro insertado");
				} else {
					System.out.println("Error al insertar registro");
					throw new FCTException();
				}
			}

		} catch (SQLException e) {
			throw new FCTException("No se puede crear ese registro", e);
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
	}

}
