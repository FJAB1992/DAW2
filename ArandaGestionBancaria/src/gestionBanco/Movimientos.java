package gestionBanco;

import java.time.LocalDate;

/**
 * 
 * @author Francisco Javier Aranda Barba
 * @version 16/05/2023 1.0 Objetivo:Clase Movimientos
 *
 */

public class Movimientos {

	private int numCuenta;
	private double importe;
	private LocalDate fechaHora;
	private String tipoMovimiento;
	private String numCuentaTransferencia;
	private String concepto;

	/* Constructor */

	public Movimientos(int numCuenta, double importe, LocalDate fechaHora, String tipoMovimiento,
			String numCuentaTransferencia, String concepto) {
		this.numCuenta = numCuenta;
		this.importe = importe;
		this.fechaHora = fechaHora;
		this.tipoMovimiento = tipoMovimiento;
		this.numCuentaTransferencia = numCuentaTransferencia;
		this.concepto = concepto;
	}

	/* Getters y setters */

	public int getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(int numCuenta) {
		this.numCuenta = numCuenta;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public LocalDate getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(LocalDate fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public String getNumCuentaTransferencia() {
		return numCuentaTransferencia;
	}

	public void setNumCuentaTransferencia(String numCuentaTransferencia) {
		this.numCuentaTransferencia = numCuentaTransferencia;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	/* Método toString */

	@Override
	public String toString() {
		return "Movimientos [numCuenta = " + numCuenta + " , importe = " + importe + " , fechaHora = " + fechaHora
				+ " , tipoMovimiento = " + tipoMovimiento + " , numCuentaTransferencia = " + numCuentaTransferencia
				+ " , concepto = " + concepto + " ]";
	}

}
