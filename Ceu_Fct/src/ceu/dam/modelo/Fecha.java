package ceu.dam.modelo;

import java.time.LocalDate;

public class Fecha {

	private LocalDate fecha;
	private Integer anho;
	private Integer evaluacion;
	private Boolean disponibilidad;

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Integer getAnho() {
		return anho;
	}

	public void setAnho(Integer anho) {
		this.anho = anho;
	}

	public Integer getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Integer evaluacion) {
		this.evaluacion = evaluacion;
	}

	public Boolean getDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(Boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}

	@Override
	public String toString() {
		return "Registro [fecha=" + fecha + ", anho=" + anho + ", evaluacion=" + evaluacion + ", disponibilidad="
				+ disponibilidad + "]";
	}

}
