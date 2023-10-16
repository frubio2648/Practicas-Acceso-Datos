package ceu.dam.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ceu.dam.modelo.Registro;

public class RegistroDao {

	public Long insertarRegistro(Connection conn, Registro registro) throws SQLException {

		PreparedStatement stmt = null;

		try {

			String sql = "insert into registros (id_usuario, fecha, num_horas, descripcion) values (?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setLong(1, registro.getidUsuario());
			stmt.setDate(2, Date.valueOf(registro.getFecha()));
			stmt.setBigDecimal(3, registro.getNum_horas());
			stmt.setString(4, registro.getDescripcion());
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

	public List<Registro> consultarRegistro(Connection conn, Long idUsuario) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Registro> registros = new ArrayList<>();
		Registro registro = new Registro();

		try {

			stmt = conn.prepareStatement("select * from registros where id_usuario = ?");
			stmt.setLong(1, idUsuario);
			rs = stmt.executeQuery();

			while (rs.next()) {
				registro.setidRegistro(rs.getLong("id_registro"));
				registro.setidUsuario(rs.getLong("id_usuario"));
				registro.setFecha(rs.getDate("id_registro").toLocalDate());
				registro.setNum_horas(rs.getBigDecimal("num_horas"));
				registro.setDescripcion(rs.getString("descripcion"));

				registros.add(registro);
			}

			return registros;

		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
	}

	public List<Registro> consultarRegistroFecha(Connection conn, Long idUsuario, LocalDate fecha) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Registro> registros = new ArrayList<>();
		Registro registro = new Registro();

		try {

			stmt = conn.prepareStatement("select * from registros where id_usuario = ? and fecha = ?");
			stmt.setLong(1, idUsuario);
			stmt.setDate(2, Date.valueOf(fecha));
			rs = stmt.executeQuery();

			while (rs.next()) {
				registro.setidRegistro(rs.getLong("id_registro"));
				registro.setidUsuario(rs.getLong("id_usuario"));
				registro.setFecha(rs.getDate("id_registro").toLocalDate());
				registro.setNum_horas(rs.getBigDecimal("num_horas"));
				registro.setDescripcion(rs.getString("descripcion"));

				registros.add(registro);
			}

			return registros;

		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}

	}
}
