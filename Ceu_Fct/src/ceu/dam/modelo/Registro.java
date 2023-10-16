package ceu.dam.modelo;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Registro {

	private Long idRegistro;
	private Long idUsuario;
	private LocalDate fecha;
	private BigDecimal num_horas;
	private String descripcion;

	public Long getidRegistro() {
		return idRegistro;
	}

	public void setidRegistro(Long idRegistro) {
		this.idRegistro = idRegistro;
	}

	public Long getidUsuario() {
		return idUsuario;
	}

	public void setidUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getNum_horas() {
		return num_horas;
	}

	public void setNum_horas(BigDecimal num_horas) {
		this.num_horas = num_horas;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "Registro [idRegistro=" + idRegistro + ", idUsuario=" + idUsuario + ", fecha=" + fecha
				+ ", num_horas=" + num_horas + ", descripcion=" + descripcion + "]";
	}

}
