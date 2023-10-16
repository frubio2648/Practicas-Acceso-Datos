package ceu.dam.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ceu.dam.modelo.Fecha;

public class FechaDao {

	public List<Fecha> consultarFechas(Connection conn, Integer anho, Integer eval) throws SQLException {

		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Fecha> fechas = new ArrayList<>();
		Fecha fecha = new Fecha();

		try {
			stmt = conn.prepareStatement("select * from fechas where año = ? and evaluacion = ?");
			stmt.setInt(1, anho);
			stmt.setInt(2, eval);
			rs = stmt.executeQuery();

			while (rs.next()) {
				fecha.setFecha(rs.getDate("fecha").toLocalDate());
				fecha.setAnho(rs.getInt("año"));
				fecha.setEvaluacion(rs.getInt("evaluacion"));
				fecha.setDisponibilidad(rs.getBoolean("disponibilidad"));

				fechas.add(fecha);

			}

			return fechas;

		} finally {
			try {
				stmt.close();
			} catch (Exception ignore) {
			}
		}
	}

}
