package ceu.dam.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ceu.dam.dao.FechaDao;
import ceu.dam.db.OpenConnection;
import ceu.dam.modelo.Fecha;

public class FechaService {

	private OpenConnection connProvider;

	public FechaService() {
		connProvider = new OpenConnection();
	}

	public List<Fecha> consultarFechasActuales() {

		FechaDao daoFecha = new FechaDao();
		Connection conn = null;
		List<Fecha> fechas = new ArrayList<Fecha>();
		Integer evaluacion = 0;

		try {
			conn = connProvider.getNewConnection();

			Integer anhoActual = LocalDate.now().getYear();

			if (LocalDate.now().getMonthValue() >= 9 && LocalDate.now().getMonthValue() <= 11) {
				evaluacion = 1;
			} else if (LocalDate.now().getMonthValue() >= 12 && LocalDate.now().getMonthValue() <= 2) {
				evaluacion = 2;
			} else {
				evaluacion = 3;
			}

			fechas = daoFecha.consultarFechas(conn, anhoActual, evaluacion);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (Exception e) {
			}
		}
		
		return fechas;

	}

}
