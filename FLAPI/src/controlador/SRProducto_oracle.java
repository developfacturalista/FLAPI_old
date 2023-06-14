package controlador;

public class SRProducto_oracle {

	private String descripcion;
	private int cantidad;
	private double precio;
	private double iva_22;
	private double iva_10;
	private double iva_0;
	private Boolean impuestoIncluido;
	private double precioUnitario;
	private String codigoProducto;
	private String grupoProducto;



	private String codigoLinea;

	public SRProducto_oracle(String descripcion, int cantidad, double precio, double iva_10,double iva_22,double iva_0, String codigoLinea, Boolean impuestoIncluido, 
			double precioUnitario, String codigoProducto, String grupoProducto) {
		super();
		this.descripcion = descripcion;
		this.cantidad = cantidad;
		this.precio = precio;
		this.iva_10=iva_10;
		this.iva_22=iva_22;
		this.iva_0=iva_0;
		this.codigoLinea = codigoLinea;
		this.impuestoIncluido=impuestoIncluido;
		this.precioUnitario=precioUnitario;
		this.codigoProducto=codigoProducto;
		this.grupoProducto=grupoProducto;
	}

	


	public SRProducto_oracle() {
		// TODO Auto-generated constructor stub
	}






	public String getCodigoProducto() {
		return codigoProducto;
	}




	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}




	public String getGrupoProducto() {
		return grupoProducto;
	}




	public void setGrupoProducto(String grupoProducto) {
		this.grupoProducto = grupoProducto;
	}




	public double getPrecioUnitario() {
		return precioUnitario;
	}




	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}




	public Boolean getImpuestoIncluido() {
		return impuestoIncluido;
	}




	public void setImpuestoIncluido(Boolean impuestoIncluido) {
		this.impuestoIncluido = impuestoIncluido;
	}




	public double getIva_0() {
		return iva_0;
	}



	public void setIva_0(double iva_0) {
		this.iva_0 = iva_0;
	}
	
	public double getIva_22() {
		return iva_22;
	}



	public void setIva_22(double iva_22) {
		this.iva_22 = iva_22;
	}



	public double getIva_10() {
		return iva_10;
	}



	public void setIva_10(double iva_10) {
		this.iva_10 = iva_10;
	}



	public String getDescripcion() {
		return descripcion;
	}



	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public double getPrecio() {
		return precio;
	}



	public void setPrecio(double precio) {
		this.precio = precio;
	}



	public String getCodigoLinea() {
		return codigoLinea;
	}



	public void setCodigoLinea(String codigoLinea) {
		this.codigoLinea = codigoLinea;
	}



	public int getDescuento() {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
