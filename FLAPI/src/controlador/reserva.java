package controlador;

import java.util.ArrayList;

public class reserva {
	
	String nombreHuesped;
	String paisHuesped;
	String direccionHuesped;
	String ciudadHuesped;
	String documentoHuesped;
	double montoTotalFactura;
	double montoTotalHabitaciones;
	double montoTotalAdicionales;
	double impuestoBasica;
	double impuestoMinima;
	String tipoDocumentoHuesped;
	String tipoDocumentoCfe;
	String fechaComprobante;
	String tipoIva;
	String nrocompra;
	String nombrePagador;
	String documentoPagador;
	String ciudadPagador;
	String paisPagador;
	double totalIvaTasaBasicaReserva;
	double totalIvaTasaMinimaReserva;
	double totalIvaExcentoReserva;
	double totalSubTotalReserva;
	double totalMontoTotalReserva;
	Boolean impuestoIncluido;
	
	
	
	
	
	
	
	
	
	public Boolean getImpuestoIncluido() {
		return impuestoIncluido;
	}
	public void setImpuestoIncluido(Boolean impuestoIncluido) {
		this.impuestoIncluido = impuestoIncluido;
	}
	
	
	
	
	public double getTotalIvaTasaBasicaReserva() {
		return totalIvaTasaBasicaReserva;
	}
	public void setTotalIvaTasaBasicaReserva(double totalIvaTasaBasicaReserva) {
		this.totalIvaTasaBasicaReserva = totalIvaTasaBasicaReserva;
	}
	public double getTotalIvaTasaMinimaReserva() {
		return totalIvaTasaMinimaReserva;
	}
	public void setTotalIvaTasaMinimaReserva(double totalIvaTasaMinimaReserva) {
		this.totalIvaTasaMinimaReserva = totalIvaTasaMinimaReserva;
	}
	public double getTotalIvaExcentoReserva() {
		return totalIvaExcentoReserva;
	}
	public void setTotalIvaExcentoReserva(double totalIvaExcentoReserva) {
		this.totalIvaExcentoReserva = totalIvaExcentoReserva;
	}
	public double getTotalSubTotalReserva() {
		return totalSubTotalReserva;
	}
	public void setTotalSubTotalReserva(double totalSubTotalReserva) {
		this.totalSubTotalReserva = totalSubTotalReserva;
	}
	public double getTotalMontoTotalReserva() {
		return totalMontoTotalReserva;
	}
	public void setTotalMontoTotalReserva(double totalMontoTotalReserva) {
		this.totalMontoTotalReserva = totalMontoTotalReserva;
	}
	public String getNombrePagador() {
		return nombrePagador;
	}
	public void setNombrePagador(String nombrePagador) {
		this.nombrePagador = nombrePagador;
	}
	public String getDocumentoPagador() {
		return documentoPagador;
	}
	public void setDocumentoPagador(String documentoPagador) {
		this.documentoPagador = documentoPagador;
	}
	public String getCiudadPagador() {
		return ciudadPagador;
	}
	public void setCiudadPagador(String ciudadPagador) {
		this.ciudadPagador = ciudadPagador;
	}
	public String getPaisPagador() {
		return paisPagador;
	}
	public void setPaisPagador(String paisPagador) {
		this.paisPagador = paisPagador;
	}
	public String getNrocompra() {
		return nrocompra;
	}
	public void setNrocompra(String nrocompra) {
		this.nrocompra = nrocompra;
	}
	public String getTipoIva() {
		return tipoIva;
	}
	public void setTipoIva(String tipoIva) {
		this.tipoIva = tipoIva;
	}
	public double getImpuestoBasica() {
		return impuestoBasica;
	}
	public void setImpuestoBasica(double impuestoBasica) {
		this.impuestoBasica = impuestoBasica;
	}
	public double getImpuestoMinima() {
		return impuestoMinima;
	}
	public void setImpuestoMinima(double impuestoMinima) {
		this.impuestoMinima = impuestoMinima;
	}
	public String getFechaComprobante() {
		return fechaComprobante;
	}
	public void setFechaComprobante(String fechaComprobante) {
		this.fechaComprobante = fechaComprobante;
	}
	public String getTipoDocumentoCfe() {
		return tipoDocumentoCfe;
	}
	public void setTipoDocumentoCfe(String tipoDocumentoCfe) {
		this.tipoDocumentoCfe = tipoDocumentoCfe;
	}
	public String getTipoDocumentoHuesped() {
		return tipoDocumentoHuesped;
	}
	public void setTipoDocumentoHuesped(String tipoDocumentoHuesped) {
		this.tipoDocumentoHuesped = tipoDocumentoHuesped;
	}

	Double descuento = 0.0;
	public Double getDescuento() {
		return descuento;
	}
	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	String inicioEstadia;
	String finEstadia;
	ArrayList<SRProducto_oracle> productos;
	public reserva() {
		super();
	}
	public String getNombreHuesped() {
		return nombreHuesped;
	}
	public void setNombreHuesped(String nombreHuesped) {
		this.nombreHuesped = nombreHuesped;
	}
	public String getPaisHuesped() {
		return paisHuesped;
	}
	public void setPaisHuesped(String paisHuesped) {
		this.paisHuesped = paisHuesped;
	}
	public String getDireccionHuesped() {
		return direccionHuesped;
	}
	public void setDireccionHuesped(String direccionHuesped) {
		this.direccionHuesped = direccionHuesped;
	}
	public String getCiudadHuesped() {
		return ciudadHuesped;
	}
	public void setCiudadHuesped(String ciudadHuesped) {
		this.ciudadHuesped = ciudadHuesped;
	}
	public String getDocumentoHuesped() {
		return documentoHuesped;
	}
	public void setDocumentoHuesped(String documentoHuesped) {
		this.documentoHuesped = documentoHuesped;
	}
	public double getMontoTotalFactura() {
		return montoTotalFactura;
	}
	public void setMontoTotalFactura(double montoTotalFactura) {
		this.montoTotalFactura = montoTotalFactura;
	}
	public double getMontoTotalHabitaciones() {
		return montoTotalHabitaciones;
	}
	public void setMontoTotalHabitaciones(double montoTotalHabitaciones) {
		this.montoTotalHabitaciones = montoTotalHabitaciones;
	}
	public double getMontoTotalAdicionales() {
		return montoTotalAdicionales;
	}
	public void setMontoTotalAdicionales(double montoTotalAdicionales) {
		this.montoTotalAdicionales = montoTotalAdicionales;
	}
	public String getInicioEstadia() {
		return inicioEstadia;
	}
	public void setInicioEstadia(String inicioEstadia) {
		this.inicioEstadia = inicioEstadia;
	}
	public String getFinEstadia() {
		return finEstadia;
	}
	public void setFinEstadia(String finEstadia) {
		this.finEstadia = finEstadia;
	}
	public ArrayList<SRProducto_oracle> getProductos() {
		return productos;
	}
	public void setProductos(ArrayList<SRProducto_oracle> productos) {
		this.productos = productos;
	}
	
	

}
