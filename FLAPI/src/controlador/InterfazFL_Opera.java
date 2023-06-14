package controlador;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.activation.DataHandler;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.HashPrintServiceAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.PrintServiceAttributeSet;
import javax.print.attribute.standard.PrinterName;
import javax.swing.JOptionPane;

import org.apache.axiom.attachments.utils.IOUtils;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

//import org.eclipse.persistence.platform.database.oracle.NCharacter;

import servicios.FacturaElectronicaImplServiceStub;
import servicios.verPdf;
import servicios.FacturaElectronicaImplServiceStub.CodigoItem;
import servicios.FacturaElectronicaImplServiceStub.Descuento;
import servicios.FacturaElectronicaImplServiceStub.Documento;
import servicios.FacturaElectronicaImplServiceStub.Factura;
import servicios.FacturaElectronicaImplServiceStub.GenerarFactura;
import servicios.FacturaElectronicaImplServiceStub.GenerarFacturaResponse;
import servicios.FacturaElectronicaImplServiceStub.GetImprimible;
import servicios.FacturaElectronicaImplServiceStub.InfoDescuentoRecargo;
import servicios.FacturaElectronicaImplServiceStub.InfoReferencia;
import servicios.FacturaElectronicaImplServiceStub.Persona;
import servicios.FacturaElectronicaImplServiceStub.Producto;
import servicios.FacturaElectronicaImplServiceStub.Respuesta;
import servicios.FacturaElectronicaImplServiceStub.Subtotal;
//import bd.DBDriverAccess;
import bd.DBDriverPostgreSQL;
import bd.DBDriverPostgreSQLFacturaLista;
import bd.DBDriverPostgreSQLWS;
import bd.Direccionador;
//import bd.JackAccess;
import gui.Receptor_OfiHotel;
import gui.envioEmail;

public class InterfazFL_Opera {

	public static boolean TEST = false;
	private static final Logger log = Logger.getLogger(InterfazFL_Opera.class.getName());
	private boolean cancelado;
	private int idE;
	static String ultimo;
	String facturaAnular = "";
	Properties properties;
	File file;
	OutputStream out;
	InterfazFL_Opera o;
	String documentoCliente = " ";
	String nombreCliente = " ";
	Boolean banderaCliente = false;
	String fechaIngreso = "";
	String fechaEgreso = "";
	int cantidadImpresiones = 0;
	double descontar = 0.0;
	static String idhotel = "";
	static String idreserva = "";
	servicios.FacturaElectronicaImplServiceStub.GenerarFacturaResponse ress = null;

	static String mensaje = "";

	public boolean isCancelado() {
		return cancelado;
	}

	public void setCancelado(boolean cancelado) {
		this.cancelado = cancelado;
	}

	public InterfazFL_Opera() {
		properties = Direccionador.getInstance().getArchivoConfiguracion();
		file = new File("C://FLOpera//configuracion.txt");
		try (InputStream in = new FileInputStream(file)) {
			if (in == null) {
				throw new FileNotFoundException();
			}
			properties.load(in);

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Handler fh = new FileHandler(properties.getProperty("log"), true);
			SimpleFormatter formatterTxt = new SimpleFormatter();
			fh.setFormatter(formatterTxt);
			log.addHandler(fh);
			log.setLevel(Level.INFO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Factura getFactura(String id, String prefijo, boolean ocultardetalle, String nrocompra) throws Exception {
		properties = Direccionador.getInstance().getArchivoConfiguracion();
		Documento documento = new Documento();
		double tasabasicaiva = 22.0;
		double tasaminimaiva = 10.0;
		boolean esNotadeCreditoXNegativo = false;
		double iva2210 = 0;
		double porcentajeIva2210 = 0;
		double totalConIva2210 = 0;
		double totalSinIva2210 = 0;
		double totalIva2210 = 0;
		double totalConIva2210xIva10 = 0;
		double totalSinIva2210xIva10 = 0;
		double totalIva2210xIva10 = 0;
		boolean tieneDosImpuestos = false;

		reserva reserva = new reserva();
		reserva = ejemploRequest2.getDatosReserva(id);
		// String preserva = ejemploRequest.getReservationInvoiceById(id);

		// id=id.subString(1);
		String numeroFactura = id;
		idreserva = numeroFactura;

		String fechaComprobante = " ";

		String direccionCliente = " ";
		String departamentoCliente = " ";
		String paisCliente = " ";
		String cif = " ";
		String nombrecif = " ";
		int habitacion = 0;
		double montoTotal = 0;
		double montoSinIva = 0;
		double ivaMinimo = 0;
		double ivaBasica = 0;
		int tipoIva = 0;

		fechaComprobante = reserva.getFechaComprobante();
		// fechaIngreso = reserva.getInicioEstadia();
		// fechaEgreso = reserva.getFinEstadia();

		documentoCliente = reserva.getDocumentoHuesped();
		// documentoCliente = "216819440018";
		nombreCliente = reserva.getNombreHuesped();
		direccionCliente = reserva.getDireccionHuesped();
		departamentoCliente = reserva.getCiudadHuesped();
		// paisCliente = rsEncabezado.getString(15);
		paisCliente = reserva.getPaisHuesped();
		// habitacion = rsEncabezado.getInt(4);
		montoTotal = reserva.getMontoTotalFactura();
		montoSinIva = montoTotal;
		ivaMinimo = reserva.getImpuestoMinima();
		ivaBasica = reserva.getImpuestoBasica();
		// tipoIva = reserva.getImpuestos();

		if (!documentoCliente.equalsIgnoreCase(cif)) {
			banderaCliente = true;
		}

		Double cambio = 0.0;

		String nropersonas = "0";
//		double cargo = 0.0;
		double descuento = 0.0;
		String idclientefacturacion = "12";

//		double subtotal2 = 0.0;
		String orden = "orden";
//		double propina = 0.0;
//		String fecha = rs.getString(13);
		Calendar fechacmp = Calendar.getInstance();

		/*
		 * rsEncabezado.close(); psEncabezado.close();
		 */

		double totalmontoivaotratasa = 0;
		double totalmontoivatasabasica = 0;
		double totalmontoivatasaminima = 0;
		double totalmontonogravado = 0;
		double totalmontoexportacion = 0;

		Factura factura = new Factura();

		factura.setNroCompra(numeroFactura);

		if (factura.getReceptor() != null && factura.getReceptor().getDocumento() != null
				&& (factura.getReceptor().getDocumento().getDocumento() == null
						|| factura.getReceptor().getDocumento().getDocumento().trim().isEmpty())) {

			factura.setReceptor(null);
			if (esNotadeCreditoXNegativo) {
				factura.setTipo(102);
				InfoReferencia nd = new InfoReferencia();
				Calendar c = Calendar.getInstance();
				nd.setFechaReferencia(c);
				nd.setNroLinea(1);
				nd.setReferenciaGlobal(true);
				nd.setRazonReferencia("NOTA DE CREDITO");

				InfoReferencia[] referencias = new InfoReferencia[1];
				referencias[0] = nd;
				factura.setInfoReferencias(referencias);
			} else {
				factura.setTipo(101);
			}

		} else {
			// factura.setTipo(101);
		}

		ArrayList<String> ad = new ArrayList<>();
		String ade = properties.getProperty("adenda1");
		if (ade != null && !ade.equals("")) {
			ad.add(ade);
		}
		ade = properties.getProperty("adenda2");
		if (ade != null && !ade.equals("")) {
			ad.add(ade);
		}
		ade = properties.getProperty("adenda3");
		if (ade != null && !ade.equals("")) {
			ad.add(ade);
		}

		ad.add(nropersonas);
		ad.add(orden);
		String textocambio = properties.getProperty("textocambio");
		ad.add(textocambio + cambio);

		factura.setDestino(null);

		factura.setFechaVencimiento(fechacmp);
		factura.setFechaComprobante(fechacmp);

		int formapago = 1;
		factura.setFormaPago(formapago);

		ArrayList<InfoDescuentoRecargo> idrs = new ArrayList<>();
		int nlinea = 1;

		ArrayList<Producto> productos = new ArrayList<>();

		ArrayList<SRProducto_oracle> srproductos = reserva.getProductos();

		System.out.println("****************Despues de las dos consultas:");
		this.imprimirproductos(srproductos);

		int indicadorfacturaciondescuento = -1;

		/**
		 * @Marcel: Aca comienzo la recorrida de los productos cargados en la factura
		 *          factura
		 */
		// Aca agrupo
		// HAGO UN FOR CON PRODUCTOS
		// DENTRO HAGO OTRO FOR CON LOS CODIGOS DEL ARRAY CARGADO
		// SI ES IGUAL LO AGREGO

		// recorro array de productos y si es igual al codigo lo meto a arraycodigo
		ejemploRequest2 e = new ejemploRequest2();
		ArrayList<SRProducto_oracle> srproductosagrupados = new ArrayList<SRProducto_oracle>();
		JsonArray arrayCodigos = e.getArrayCodigos();

		// ArrayList<Integer> codigosHijos = new ArrayList<Integer>();
		// for (int o = 0; o < arrayCodigos.size(); o++) {

		// }

		for (int r = 0; r < arrayCodigos.size(); r++) {

			JsonObject objectHijo = arrayCodigos.get(r).getAsJsonObject();
			JsonArray codigosHijos = objectHijo.getAsJsonArray("TrxCode");
			SRProducto_oracle srp = new SRProducto_oracle();

			for (int o = 0; o < codigosHijos.size(); o++) {

				for (int z = 0; z < srproductos.size(); z++) {

					if (srproductos.get(z).getCodigoProducto().equalsIgnoreCase(codigosHijos.get(o).getAsString())) {
						srp.setCantidad(srproductos.get(z).getCantidad() + srp.getCantidad());
						srp.setDescripcion(srproductos.get(z).getDescripcion());
						srp.setIva_0(srproductos.get(z).getIva_0() + srp.getIva_0());
						srp.setIva_10(srproductos.get(z).getIva_10() + srp.getIva_10());
						srp.setIva_22(srproductos.get(z).getIva_22() + srp.getIva_22());
						srp.setPrecio(srproductos.get(z).getPrecio() + srp.getPrecio());
						srp.setCodigoLinea(String.valueOf(z));
						// srp.setImpuestoIncluido(true);
					}

				}

			}

			if (srp.getDescripcion() != null) {
				srproductosagrupados.add(srp);
			}

			// System.out.println(srp+"");
			// pregunto si producto es igual a codigo
		}

		for (SRProducto_oracle srproducto : srproductosagrupados) {
			if (srproducto.getCantidad() != 0 && srproducto.getDescripcion() != null) {
				Producto p = new Producto();
				Producto p2 = new Producto();
				ArrayList<CodigoItem> codigos = new ArrayList<>();
				CodigoItem codigo = new CodigoItem();

				p.setAgenteResponsable(false);
				// TENEMOS QUE SETEAR CANTIDAD DESPUES
				// p.setCantidad(srproducto.getCantidad());
				codigo.setCodigo(srproducto.getCodigoLinea());
				codigo.setTipo("FLORACLE");
				codigos.add(codigo);
				p.setCodigos(codigos.toArray(new CodigoItem[codigos.size()]));
				p.setDescripcion(srproducto.getDescripcion());
				p.setNombre(srproducto.getDescripcion());

				ArrayList<Descuento> descuentos = new ArrayList<>();

				if (srproducto.getDescuento() > 0) {
					Descuento descuentop = new Descuento();
					descuentop.setTipo("%");
					// antes mandava el %, ahora va el valor
					double desc = srproducto.getDescuento() * (srproducto.getPrecio() * srproducto.getCantidad());
					desc = desc * 100;
					desc = Math.round(desc);
					desc = desc / 100.0;
					descuentop.setValor(desc);
					descuentos.add(descuentop);
					if (srproducto.getDescuento() == 100)
						p.setNombre(p.getNombre() + " " + properties.getProperty("textoredescuentolinea100"));

				}
				p.setDescuentos(descuentos.toArray(new Descuento[descuentos.size()]));

				/*
				 * for (int i = 0; i < srproductos.size(); i++) { srproductos }
				 */

//			srproducto.getCantidad() lo agergo como absoluto porque en caso de ser negativo se carga en la cantidad arriba
				// double monto = srproducto.getPrecio() * srproducto.getCantidad();
				double monto = srproducto.getPrecio();

				if (reserva.getTipoDocumentoCfe() == "102") {
					if (srproducto.getPrecio() < 0) {
						monto = monto * -1;
						srproducto.setPrecio(monto);
						srproducto.setIva_10(srproducto.getIva_10() * -1);
						srproducto.setIva_0(srproducto.getIva_0() * -1);
						srproducto.setIva_22(srproducto.getIva_22() * -1);
						srproducto.setPrecioUnitario(srproducto.getPrecioUnitario() * -1);
					}
					if (srproducto.getCantidad() > 0) {
						srproducto.setCantidad(srproducto.getCantidad() * -1);
					} else if (srproducto.getCantidad() < 0) {
						srproducto.setCantidad(srproducto.getCantidad() * -1);
					}
					//monto = srproducto.getPrecio() * srproducto.getCantidad();
				}

//			@Marcel: Se agrega el caso que tenga 2 impuestos. En Uruguay No Pasa. Ojo con el Inidicador que dejamos cualquiera
//			Orcale lo que hace es partir siempre del subtotal para cada impuesto y despues lo suma
				if (srproducto.getIva_22() > 0 && srproducto.getIva_10() > 0) {
					iva2210 = srproducto.getIva_22() + srproducto.getIva_10();
//				Tengo el Total Iva Incluido
					totalIva2210 = iva2210 + monto;
//				Saco el porcentaje de IVA 22% que esta dentro del total del iva2210 )
					porcentajeIva2210 = (srproducto.getIva_22() * 100) / iva2210;
					totalConIva2210 = totalIva2210 * (porcentajeIva2210 / 100);
					totalSinIva2210 = totalConIva2210 / 1.22;

//				totalIva2210 = monto * (porcentajeIva2210/100);
					totalmontoivatasabasica = totalSinIva2210 + totalmontoivatasabasica;
					totalIva2210xIva10 = totalIva2210 - totalConIva2210;
					totalSinIva2210xIva10 = totalIva2210xIva10 / 1.10;
					totalmontoivatasaminima = totalSinIva2210xIva10 + totalmontoivatasaminima;
//				p.setIndicadorFacturacion(3);
					tieneDosImpuestos = true;

				} else if (srproducto.getIva_22() != 0) {
					totalmontoivatasabasica = monto + totalmontoivatasabasica;
					p.setIndicadorFacturacion(3);
				} else if (srproducto.getIva_10() != 0) {
					totalmontoivatasaminima = monto + totalmontoivatasaminima;
					p.setIndicadorFacturacion(2);
				} else if (srproducto.getIva_0() == 0.0 && srproducto.getIva_22() == 0.0
						&& srproducto.getIva_10() == 0.0) {
					totalmontonogravado = monto + totalmontonogravado;
					p.setIndicadorFacturacion(1);
				}

				String nombre = srproducto.getDescripcion();

				// if(srproducto.getDescripcion())

				p.setMonto(monto);
				p.setCantidad(srproducto.getCantidad());

				if (srproducto.getPrecio() == 0) {
					System.out.println("PRECIO 0");
					p.setIndicadorFacturacion(5);
				}

				p.setNroLinea(nlinea);
				nlinea++;
				p.setPorcentajeDescuento(srproducto.getDescuento() * 100);
				p.setPorcentajeRecargo(0);
				p.setPrecioUnitario(srproducto.getPrecio());
				p.setUnidadDeMedida("N/A");
				if (!tieneDosImpuestos)
					productos.add(p);
				else {
//				@Marcel : Agrego 2 lineas uno con iva 22 y otra al 10
					p.setMonto(totalSinIva2210);
					p.setPrecioUnitario(totalSinIva2210);
					p.setIndicadorFacturacion(3);
					productos.add(p);

					p2 = new Producto();

					p2.setAgenteResponsable(false);
					p2.setCantidad(srproducto.getCantidad());
					p2.setCodigos(codigos.toArray(new CodigoItem[codigos.size()]));
					p2.setDescripcion(srproducto.getDescripcion());
					p2.setNombre(srproducto.getDescripcion());
					p2.setPorcentajeDescuento(srproducto.getDescuento() * 100);
					p2.setPorcentajeRecargo(0);
					p2.setPrecioUnitario(totalSinIva2210xIva10);
					p2.setUnidadDeMedida("N/A");
					p2.setNroLinea(nlinea);
					nlinea++;
					p2.setMonto((totalSinIva2210xIva10));
					p2.setIndicadorFacturacion(2);
					productos.add(p2);

					tieneDosImpuestos = false;

				}
			}

		}

		if (descuento > 0) {
			InfoDescuentoRecargo idr = new InfoDescuentoRecargo();
			idr.setCodigo(1);
			if (descuento == 100)
				idr.setGlosa(properties.getProperty("textoredescuento100"));
			else
				idr.setGlosa(properties.getProperty("textoredescuento"));
			idr.setIndicadorFacturacion(indicadorfacturaciondescuento);
			idr.setNroLinea(1);
			nlinea++;
			idr.setTipoDescuentoRecargo("%");
			idr.setTipoMovimiento("D");
			idr.setValor(descuento);
			idrs.add(idr);
		}

		factura.setInfoDescuentosRecargos(idrs.toArray(new InfoDescuentoRecargo[idrs.size()]));

		double totalmontonofacturable = 0;

		/*
		 * for (int r = 0; r < srproductos.size(); r++) { SRProducto_oracle srp = new
		 * SRProducto_oracle(descripcion, cantidad, precio, iva_10, iva_22, iva_0,
		 * codigoLinea, impuestoIncluido, precioUnitario, codigoProducto, grupoProducto)
		 * 
		 * String code = srproductos.get(r).getCodigoProducto();
		 * while(Integer.parseInt(code)==arrayCodigos.get(r)) {
		 * 
		 * ArrayList<CodigoItem> codigos = new ArrayList<>(); CodigoItem codigo = new
		 * CodigoItem();
		 * 
		 * p.setAgenteResponsable(false); //TENEMOS QUE SETEAR CANTIDAD DESPUES
		 * //p.setCantidad(srproducto.getCantidad());
		 * codigo.setCodigo(srproductos.get(r).getCodigoLinea());
		 * codigo.setTipo("FLORACLE"); codigos.add(codigo);
		 * 
		 * Producto p3 = new Producto(); p3.setAgenteResponsable(false);
		 * p3.setCantidad(1); p3.setCodigos(codigos.toArray(new
		 * CodigoItem[codigos.size()]));
		 * p3.setDescripcion(srproductos.get(r).getDescripcion());
		 * p3.setNombre(srproducto.getDescripcion());
		 * p3.setPorcentajeDescuento(srproducto.getDescuento() * 100);
		 * p3.setPorcentajeRecargo(0); p3.setPrecioUnitario(totalSinIva2210xIva10);
		 * p3.setUnidadDeMedida("N/A"); p3.setNroLinea(nlinea); nlinea++;
		 * p2.setMonto((totalSinIva2210xIva10)); p2.setIndicadorFacturacion(2);
		 * productos.add(p2); }
		 * 
		 * }
		 */

		factura.setProductos(productos.toArray(new Producto[productos.size()]));
		factura.setMontoBruto(true);
		factura.setMontoNoFacturable(totalmontonofacturable);
		factura.setNroCompra(nrocompra);
		Persona persona = null;
		String referencia = "";
		if (idclientefacturacion != null && !idclientefacturacion.equals("")) {
			persona = new Persona();

			persona.setNombre(nombreCliente);
			if (direccionCliente != null || !direccionCliente.equalsIgnoreCase(" ")) {
				String direccion = direccionCliente;
				persona.setDireccion(direccion);
			} else {
				persona.setDireccion("Direccion");
			}
//				Dentro del telefono se guarda el RUT
			String telefono = documentoCliente;
			if (telefono.length() == 13) {
				telefono = telefono.substring(1);
			}
			if (telefono != null && !telefono.trim().equals(""))
				ad.add(properties.getProperty("textotelefono") + telefono);
			else {
				if (telefono != null && !telefono.trim().equals(""))
					ad.add(properties.getProperty("textotelefono") + telefono);
				else
					ad.add("");
			}

			String codPais = getCodPais(paisCliente);

			if (departamentoCliente.equalsIgnoreCase("")) {
				persona.setDepartamento("Departamento");
				persona.setCiudad("Ciudad");

			} else {
				persona.setDepartamento(departamentoCliente);
				persona.setCiudad(departamentoCliente);
			}

			// persona.setDocumento(telefono);
			if (paisCliente.contentEquals("")) {
				codPais = getCodPais("UY");
				persona.setPais(codPais);
				documento.setPais(codPais);

			} else {
				codPais = getCodPais(paisCliente);
				persona.setPais(codPais);
				documento.setPais(codPais);
			}
			if (direccionCliente.contentEquals("")) {
				persona.setDireccion("Direccion");
			} else {
				persona.setDireccion(direccionCliente);
			}

			if (reserva.getTipoDocumentoCfe().equalsIgnoreCase("111")
					&& reserva.getTipoDocumentoHuesped().equalsIgnoreCase("RUT")) {

				documento.setDocumento(documentoCliente);
				documento.setTipo(2);
				persona.setPais("UY");
				documento.setPais("UY");
				factura.setTipo(111);
				persona.setDocumento(documento);
			} else if (reserva.getTipoDocumentoCfe().equalsIgnoreCase("101")
					&& reserva.getTipoDocumentoHuesped().equalsIgnoreCase("CI")) {

				documento.setDocumento(reserva.getDocumentoHuesped());
				// documento.setDocumento(cedula);
				documento.setTipo(3);
				persona.setPais("UY");
				documento.setPais("UY");
				factura.setTipo(101);
				persona.setDocumento(documento);

			} else if (reserva.getTipoDocumentoCfe().equalsIgnoreCase("101")
					&& reserva.getTipoDocumentoHuesped().equalsIgnoreCase("PASS")) {

				documento.setDocumento(reserva.getDocumentoHuesped());
				// documento.setDocumento(pas);
				documento.setTipo(5);
				persona.setPais(codPais);
				documento.setPais(codPais);
				factura.setTipo(101);
				persona.setDocumento(documento);
			}

			else if (reserva.getTipoDocumentoCfe().equalsIgnoreCase("101")
					&& reserva.getTipoDocumentoHuesped().equalsIgnoreCase("RG")) {

				documento.setDocumento(reserva.getDocumentoHuesped());
				// documento.setDocumento(rg);
				documento.setTipo(4);
				persona.setPais("BR");
				documento.setPais("BR");
				factura.setTipo(101);
				persona.setDocumento(documento);
			} else if (reserva.getTipoDocumentoCfe().equalsIgnoreCase("101")
					&& reserva.getTipoDocumentoHuesped().equalsIgnoreCase("DNI")) {

				documento.setDocumento(reserva.getDocumentoHuesped());
				// documento.setDocumento(dni);
				documento.setTipo(6);
				persona.setPais("AR");
				documento.setPais("AR");
				factura.setTipo(101);
				persona.setDocumento(documento);
			} else if (reserva.getTipoDocumentoCfe().equalsIgnoreCase("101")
					&& reserva.getTipoDocumentoHuesped().equalsIgnoreCase("OTRO")) {

				documento.setDocumento(reserva.getDocumentoHuesped());
				// documento.setDocumento(otro);
				documento.setTipo(4);
				persona.setPais(codPais);
				documento.setPais(codPais);
				factura.setTipo(101);
				persona.setDocumento(documento);
			}

			factura.setTipo(Integer.parseInt(reserva.getTipoDocumentoCfe()));
			if (reserva.getTipoDocumentoCfe().equalsIgnoreCase("102")
					|| reserva.getTipoDocumentoCfe().equalsIgnoreCase("112")) {
				InfoReferencia nd = new InfoReferencia();
				nd.setNroLinea(1);
				nd.setReferenciaGlobal(true);
				nd.setRazonReferencia("NOTA DE CREDITO");

				InfoReferencia[] referencias = new InfoReferencia[1];
				referencias[0] = nd;
				factura.setInfoReferencias(referencias);
			}

//				SI ES IGUAL A 12 ES RUT Y CREA EFACTURA
			/*
			 * if (telefono.length() == 12 && documento.getTipo()==2) { // PRUEBA NC if
			 * (esNotadeCreditoXNegativo) { factura.setTipo(112); InfoReferencia nd = new
			 * InfoReferencia(); nd.setNroLinea(1); nd.setReferenciaGlobal(true);
			 * nd.setRazonReferencia("NOTA DE CREDITO"); InfoReferencia[] referencias = new
			 * InfoReferencia[1]; referencias[0] = nd;
			 * factura.setInfoReferencias(referencias); documento.setTipo(2); } else {
			 * factura.setTipo(111); documento.setTipo(2); } } else if (telefono.length() ==
			 * 13) { telefono = telefono.substring(1); // PRUEBA NC if
			 * (esNotadeCreditoXNegativo) { factura.setTipo(112); InfoReferencia nd = new
			 * InfoReferencia(); nd.setNroLinea(1); nd.setReferenciaGlobal(true);
			 * nd.setRazonReferencia("NOTA DE CREDITO"); InfoReferencia[] referencias = new
			 * InfoReferencia[1]; referencias[0] = nd;
			 * factura.setInfoReferencias(referencias); documento.setTipo(2); } else {
			 * factura.setTipo(111); documento.setTipo(2); } } // SI NO CREA ETICKET else if
			 * (telefono.length() >= 9) { // PRUEBA NC // if(totalFactura<0) { if
			 * (esNotadeCreditoXNegativo) { factura.setTipo(102); InfoReferencia nd = new
			 * InfoReferencia(); nd.setNroLinea(1); nd.setReferenciaGlobal(true);
			 * nd.setRazonReferencia("NOTA DE CREDITO");
			 * 
			 * InfoReferencia[] referencias = new InfoReferencia[1]; referencias[0] = nd;
			 * factura.setInfoReferencias(referencias); } else { factura.setTipo(101); } }
			 * else if (telefono.length() == 0) { // PRUEBA NC if (esNotadeCreditoXNegativo)
			 * { factura.setTipo(102); InfoReferencia nd = new InfoReferencia();
			 * nd.setNroLinea(1); nd.setReferenciaGlobal(true);
			 * nd.setRazonReferencia("NOTA DE CREDITO");
			 * 
			 * InfoReferencia[] referencias = new InfoReferencia[1]; referencias[0] = nd;
			 * factura.setInfoReferencias(referencias); } else { //factura.setTipo(101); } }
			 */
		}

		factura.setReceptor(persona);
		ad.add("");
		ad.add(referencia);
		if (banderaCliente = true) {
			ad.add("Nombre pasajero: " + nombreCliente);
			ad.add("Documento pasajero: " + documentoCliente);
		}
		ad.add("Fecha ingreso: " + fechaIngreso);
		ad.add("Fecha egreso: " + fechaEgreso);
		factura.setAdenda(ad.toArray(new String[ad.size()]));

		ArrayList<Subtotal> subtotales = new ArrayList<>();
		Subtotal st1 = new Subtotal();
		st1.setNumero(1);
		st1.setOrden(1);
		st1.setValor(montoTotal / 1.22);
		st1.setTitulo(properties.getProperty("textosubtotal1"));
		subtotales.add(st1);
		Subtotal st2 = new Subtotal();
		st2.setNumero(2);
		st2.setOrden(2);
		st2.setValor(montoTotal);
		st2.setTitulo(properties.getProperty("textosubtotal2"));
		subtotales.add(st2);
		factura.setSubtotales(subtotales.toArray(new Subtotal[subtotales.size()]));

		factura.setTasaBasicaIva(tasabasicaiva);
		factura.setTasaMinimaIva(tasaminimaiva);
		factura.setTipoCambio(Double.valueOf(properties.getProperty("tipocambio")));
		// factura.setTipoCambio(1);
		factura.setTipoMoneda(properties.getProperty("tipomoneda"));
		factura.setTotalMontoExportacion(Math.abs(totalmontoexportacion));
		factura.setTotalMontoImpuestoPercibido(0);
		factura.setTotalMontoIvaEnSuspenso(0);
		factura.setTotalMontoIvaOtraTasa(Math.abs((Math.round(totalmontoivaotratasa * 100) + 0.00) / 100));
		factura.setTotalMontoIvaTasaBasica(Math.abs((Math.round(totalmontoivatasabasica * 100) + 0.00) / 100));
		factura.setTotalMontoIvaTasaMinima(Math.abs((Math.round(totalmontoivatasaminima * 100) + 0.00) / 100));
		factura.setTotalMontoIvaOtraTasa(totalmontoivaotratasa);
		if (esNotadeCreditoXNegativo == true) {
			factura.setTotalMontoIvaTasaBasica(Math.abs(totalmontoivatasabasica));
		} else {
			factura.setTotalMontoIvaTasaBasica(Math.abs(totalmontoivatasabasica));
			factura.setTotalMontoIvaTasaMinima(Math.abs(totalmontoivatasaminima));
			factura.setTotalMontoExportacion(Math.abs(totalmontoexportacion));
			factura.setTotalMontoNoGravado(Math.abs(totalmontonogravado));
		}

		System.out.println(properties.getProperty("endpoint"));
		System.out.println(properties.getProperty("servidorbd"));
		System.out.println("iva tasa basica x: " + totalmontoivatasabasica);
		System.out.println("iva tasa basica: " + factura.getTotalMontoIvaTasaBasica());
		// factura.setTotalMontoNoGravado(0);
		factura.setModalidadVenta(0);
		factura.setViaTransporte(0);

		// factura.setTipoMoneda("USD");
		// factura.setTipoCambio(Double.valueOf(properties.getProperty("tipoCambio")));

		// bdAccess.desconectar();

		return factura;

	}

	public Respuesta generarFactura(String id, String nrocompra, String prefijo) throws Exception {
		InterfazFL_Opera interfaz = new InterfazFL_Opera();
		Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();
		FacturaElectronicaImplServiceStub fstub = new FacturaElectronicaImplServiceStub(
				propiedades.getProperty("endpoint"));
		int ocultardetalle = Integer.parseInt(propiedades.getProperty("ocultardetalle"));
		System.out.println("OCULTAR DETALLE " + ocultardetalle);
		GenerarFactura gf = new GenerarFactura();
		Factura factura = null;
		if (facturaAnular == null || facturaAnular.trim().isEmpty()) {
			factura = interfaz.getFactura(id, prefijo, ocultardetalle == 1, id);
		} else if (!facturaAnular.trim().isEmpty()) {
			factura = interfaz.getFactura(facturaAnular, prefijo, ocultardetalle == 1, id);
			InfoReferencia ir = new InfoReferencia();
			cancelarFactura(facturaAnular, ir, nrocompra, prefijo);
			facturaAnular = "";
		}
		// POP UP DE RUT INVALIDO
		String error = null;
		while (error != null) {
			Receptor_OfiHotel receptor = new Receptor_OfiHotel(factura, error, this);
			receptor.setVisible(true);
			if (this.cancelado) {
				Respuesta r = new Respuesta();
				r.setCodigo(-1);
				r.setDescripcion("Cancelado por el usuario.");
				return r;
			}
			if (factura.getReceptor() != null && factura.getReceptor().getDocumento() != null
					&& (factura.getReceptor().getDocumento().getDocumento() == null
							|| factura.getReceptor().getDocumento().getDocumento().trim().isEmpty())) {

				factura.setReceptor(null);
				factura.setTipo(101);
			}
		}

		gf.setArg0(factura);
		gf.setArg1(propiedades.getProperty("impresora"));
		gf.setArg1("");
		boolean seguir = true;
		servicios.FacturaElectronicaImplServiceStub.GenerarFacturaResponse res = null;
		servicios.FacturaElectronicaImplServiceStub.GetImprimibleResponse resImprimible = null;

		while (seguir) {
			seguir = false;
			res = fstub.generarFactura(gf);
			if (res.get_return().getCodigo() == 0 && res.get_return().getDescripcion().equalsIgnoreCase("Exito")) {
				Respuesta r = interfaz.mandarImprimir(res.get_return().getDocumento().getSerie(),
						res.get_return().getDocumento().getNumero(), res.get_return().getTipoCFE());
				/*if (r.getCodigo() > 0) {
					return r;
				}*/
				if (propiedades.getProperty("compartir").equalsIgnoreCase("1")) {
					int dialogButton = JOptionPane.YES_NO_OPTION;
					int dialogResult = JOptionPane.showConfirmDialog(null, "Desea compartir la factura por email?",
							"Compartir factura", dialogButton);
					if (dialogResult == JOptionPane.YES_OPTION) {
						try {
							String numero = String.valueOf(res.get_return().getDocumento().getNumero());
							String tipocfe = String.valueOf(res.get_return().getTipoCFE());
							verPdf ver = new verPdf();
							try {
								resImprimible = ver.generarPdf("A", numero, tipocfe, "");
								error = "exito";
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								error = "error";
								e1.printStackTrace();
							}
							new envioEmail(numero).setVisible(true);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							// JOptionPane.showMessageDialog(null, e1.getMessage(), "Factura Electronica",
							// 1);
						}
					}
				}
			} else {
				// insertarError(res, nrocompra);
				// JOptionPane.showMessageDialog(null, res.get_return().getDescripcion(),
				// "Factura Electronica", 1);
				ArrayList<Integer> codigosEsperados = new ArrayList<Integer>();
				codigosEsperados.add(21);
				codigosEsperados.add(22);
				codigosEsperados.add(23);
				codigosEsperados.add(25);
				codigosEsperados.add(26);
				codigosEsperados.add(49);
				codigosEsperados.add(64);
				codigosEsperados.add(65);
				codigosEsperados.add(66);
				codigosEsperados.add(67);
				codigosEsperados.add(74);
				codigosEsperados.add(75);
				codigosEsperados.add(90);
				codigosEsperados.add(91);
				codigosEsperados.add(94);
				codigosEsperados.add(95);
				/*
				 * if (codigosEsperados.contains(res.get_return().getCodigo())) { seguir = true;
				 * if (res.get_return().getCodigo() == 90) {
				 * factura.getReceptor().setDireccion(factura.getReceptor().getDireccion().
				 * substring(0, 69)); } if (res.get_return().getCodigo() == 91) {
				 * factura.getReceptor().setNombre(factura.getReceptor().getNombre().substring(
				 * 0, 149)); }
				 * 
				 * Receptor_OfiHotel receptor = new Receptor_OfiHotel(factura,
				 * res.get_return().getDescripcion(), this); receptor.setVisible(true); if
				 * (this.cancelado) { Respuesta r = new Respuesta(); r.setCodigo(-1);
				 * r.setDescripcion("Cancelado por el usuario."); return r; }
				 * 
				 * }
				 */
				System.out.println("codigo error" + res.get_return().getCodigo());
			}
		}
		Respuesta r = new Respuesta();
		r.setCodigo(res.get_return().getCodigo());
		r.setDescripcion(res.get_return().getDescripcion());
		r.setDocumento(res.get_return().getDocumento());
		r.setReferencia(res.get_return().getReferencia());
		return r;

	}

	String obtenerIP() throws UnknownHostException {
		InetAddress ip = InetAddress.getLocalHost();
		return ip.getHostAddress();
	}

	private void insertarError(GenerarFacturaResponse res, String nroCompra) {
		// TODO Auto-generated method stub
		try {
			Date date = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			String strDate = dateFormat.format(date);
			int contador = 0;
			DBDriverPostgreSQL bd = null;
			String ip = obtenerIP();
			bd = abrirConexionPos(bd, contador);
			String consultaR1 = "insert into registrocliente (registro_id,usuario,mensaje,fecha,estado) values (default, '"
					+ ip + "','" + res.get_return().getDescripcion() + "','" + strDate + "',false)";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			psR1.executeUpdate();

			cerrarConexionPos(bd, contador);

			psR1.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public PrintService getImpresora(String impresora) throws Exception {
		PrintServiceAttributeSet printServiceAttributeSet = new HashPrintServiceAttributeSet();
		printServiceAttributeSet.add(new PrinterName(impresora, null));

		PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, printServiceAttributeSet);
		if (printServices.length == 0) {
			throw new Exception("No se encontro la impresora " + impresora);
		}
		return printServices[0];
	}

	private void imprimirPDF(String documento, String impresora, String urlsumatra) throws Exception {
		Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();
		PrintService p = this.getImpresora(impresora);
		if (p == null)
			throw new Exception("No se encontro la impresora");
		System.out.println("IMPRESORA: " + impresora);
		PrintService pr = this.getImpresora(impresora);
		if (pr == null)
			throw new Exception("No se encontro la impresora");
		String command = "\"" + urlsumatra + "\" -exit-on-print -print-settings \"fit\" -print-to \"" + impresora
				+ "\" \"" + documento + "\"";
		System.out.println(command);
		if(propiedades.getProperty("habilitarImpresion").equalsIgnoreCase("1")) {
			Process pp = Runtime.getRuntime().exec(command);
			getExitCMD(pp);
		}

	}

	private void getExitCMD(Process p) {
		InputStream stdoutStream;
		try {
			stdoutStream = new BufferedInputStream(p.getInputStream());
			StringBuffer buffer = new StringBuffer();
			for (;;) {
				int c = stdoutStream.read();
				if (c == -1)
					break;
				buffer.append((char) c);

			}

			String outputText = buffer.toString();
			stdoutStream.close();
			System.out.println(outputText);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Respuesta mandarImprimir(String serie, Integer numero, Integer tipodocumento) throws Exception {
		Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();
		String impresora = propiedades.getProperty("impresora");
		String template = propiedades.getProperty("template");
		String patharchivo = propiedades.getProperty("imprimibles");
		String urlsumatra = propiedades.getProperty("urlsumatra");

		FacturaElectronicaImplServiceStub fstub = new FacturaElectronicaImplServiceStub(
				propiedades.getProperty("endpoint"));
		GetImprimible gi = new GetImprimible();
		gi.setArg0(serie);
		gi.setArg1(numero);
		gi.setArg2(tipodocumento);
		gi.setArg3(template);

		servicios.FacturaElectronicaImplServiceStub.GetImprimibleResponse rr = fstub.getImprimible(gi);
		
		if (rr.get_return().getCodigo() == 0) {
			
			System.out.println("codigo 0 exito");
			Respuesta r = new Respuesta();
			r.setCodigo(0);
			r.setDescripcion(rr.get_return().getDescripcion());
			DataHandler handler = rr.get_return().getImprimible();
			if (rr.get_return().getTipo().equals("pdf")) {
				System.out.println("entro a if de pdf");
				String archivo = patharchivo + numero + ".pdf";
				OutputStream os = new FileOutputStream(new File(archivo));
				handler.writeTo(os);

				/*
				 * for(int i=0; i<cantidadImpresiones; i++) { }
				 */
				this.imprimirPDF(archivo, impresora, urlsumatra);

				os.close();
				File fichero = new File(archivo);
				if (!fichero.delete()) {
					log.log(Level.SEVERE, "El fichero " + archivo + " no puede ser borrado");
				}
			} else {
				String archivo = patharchivo + numero + ".txt";
				OutputStream os = new FileOutputStream(new File(archivo));
				handler.writeTo(os);
				Path path = Paths.get(archivo);
				byte[] by = Files.readAllBytes(path);

				DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
				Doc doc = new SimpleDoc(by, flavor, null);

				PrintService p = this.getImpresora(impresora);
				if (p == null)
					throw new Exception("No se encontro la impresora");

				PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
				DocPrintJob job = p.createPrintJob();
				for (int i = 0; i < cantidadImpresiones; i++) {
					System.out.println("entro a for impresion");
					job.print(doc, printRequestAttributeSet);
					System.out.println("impresion: " + i + "de " + cantidadImpresiones);
				}
				os.close();
				File fichero = new File(archivo);
				if (!fichero.delete()) {
					log.log(Level.SEVERE, "El fichero " + archivo + " no puede ser borrado");
				}
			}
			return r;
		} else {
			Respuesta r = new Respuesta();
			r.setCodigo(rr.get_return().getCodigo());
			r.setDescripcion(rr.get_return().getDescripcion());
			return r;
		}
	}

	public Respuesta reimprimirFactura(String serie, Integer numero, Integer tipodocumento) throws Exception {
		return this.mandarImprimir(serie, numero, tipodocumento);
	}

	private static DBDriverPostgreSQL abrirConexionPos(DBDriverPostgreSQL bd, int contador) throws Exception {
		if (bd == null) {
			bd = new DBDriverPostgreSQL();
			bd.conectar();
			// System.out.println("Abro conexion " + contador);
		}
		return bd;
	}

	private static DBDriverPostgreSQLWS abrirConexionPosWS(DBDriverPostgreSQLWS bd, int contador) throws Exception {
		if (bd == null) {
			bd = new DBDriverPostgreSQLWS();
			bd.conectar();
			// System.out.println("Abro conexion " + contador);
		}
		return bd;
	}

	private static DBDriverPostgreSQL cerrarConexionPos(DBDriverPostgreSQL bd, int contador) throws Exception {
		if (bd != null) {
			bd.desconectar();
			// System.out.println("Cerrar conexion " + contador);
		}
		return bd;
	}

	private static DBDriverPostgreSQLWS cerrarConexionPosWS(DBDriverPostgreSQLWS bd, int contador) throws Exception {
		if (bd != null) {
			bd.desconectar();
			// System.out.println("Cerrar conexion " + contador);
		}
		return bd;
	}

	public String getCodPais(String pais) throws Exception {
		int contador = 0;
		String codigo = "UY";
		DBDriverPostgreSQL bd = null;
		bd = abrirConexionPos(bd, contador);
		String consultaR1 = "select codigo from pais where nombre = (INITCAP('" + pais + "'))";
		PreparedStatement psR1 = bd.prepareStatement(consultaR1);
		ResultSet rsR1 = null;
		rsR1 = psR1.executeQuery();
		while (rsR1.next()) {
			codigo = rsR1.getString(1);
		}

		psR1.close();
		cerrarConexionPos(bd, contador);

		return codigo;
	}

	public String setPropertie(String setear) throws IOException {
		properties.setProperty("ultimo", setear);
		out = new FileOutputStream(file);
		properties.store(out, "some comment");
		return "a";
	}

	public static String main(String[] args) throws Exception {
		InterfazFL_Opera i = new InterfazFL_Opera();
		int contador = 0;
		int codRes = 10;
		String respuestaRes = "";

		while (true) {
			try {
				/*
				 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
				 * LocalDateTime now = LocalDateTime.now(); System.out.println("Nada nuevo..." +
				 * dtf.format(now));
				 * 
				 * //Thread.sleep(10000); Thread.sleep(2000);
				 */

				String idreserva = args[0];

				// Boolean respuestaWS = chequearWs();
				// Boolean respuestaWS = true;
				// if (respuestaWS == true) {
				ejemploRequest2 e = new ejemploRequest2();
				ArrayList<String> facturas = new ArrayList<String>();
				facturas = ejemploRequest2.getReservations(idreserva);
				System.out.println("Facturas capturadas: " + facturas.size());
				Respuesta res = null;

				// for (int f = 0; f < facturas.size(); f++) {
				String jsonString = facturas.get(0);
				JSONObject row = new JSONObject(jsonString);
				JSONObject docinfo = row.getJSONObject("DocumentInfo");
				String idReserva = docinfo.get("FiscalFolioId").toString();
				idhotel = docinfo.get("HotelCode").toString();
				boolean respuesta = false;
				/*
				 * boolean respuesta = i.chequearDb(idReserva); boolean respuesta = false;
				 * boolean respuestaOperacion = getOperacion(idReserva); boolean
				 * respuestaOperacion = true;
				 * 
				 * if (respuesta == false || respuestaOperacion != false) {
				 */
				try {
					res = null;
					// i.guardoUltimo(idReserva);

					String numero = idReserva;
					String nrocompra = "0";

					ArrayList<Object> ss = new ArrayList<>();
					ss.add(numero);
					ss.add("temp");

					String id = idReserva;
					String prefijo = (String) ss.get(1);
					String operacion = "";
					// InfoReferencia referencia = i.existeFactura(id);
					InfoReferencia referenciaWS = i.existeFacturaWS(id);
					if (referenciaWS != null) {
						res = i.reimprimirFactura(referenciaWS.getSerieCFEReferencia(),
								referenciaWS.getNroCFEReferencia(), referenciaWS.getTipoCFEReferencia());
						operacion = "Reimprimir";
						cambiarBandera(numero);
						respuestaRes="Documento re impreso";
						codRes=res.getCodigo();
						String jsonReturn = responderRestOpera(codRes, respuestaRes, idReserva, referenciaWS.getSerieCFEReferencia(),String.valueOf(referenciaWS.getNroCFEReferencia()));
						return jsonReturn;
					} else {
						res = i.generarFactura(id, nrocompra, prefijo);
						operacion = "Generar";
						codRes = res.getCodigo();
						respuestaRes = res.getDescripcion();
						if (codRes == 0) {
							cambiarBandera(numero);
							String jsonReturn = responderRestOpera(codRes, respuestaRes, idReserva, res.getDocumento().getSerie(),String.valueOf(res.getDocumento().getNumero()));
							return jsonReturn;
						} else {
							System.out.println("Error");
							String jsonReturn = responderRestOpera(codRes, respuestaRes, idReserva,"","");
							insertarNoEmitida(idReserva, idhotel, respuestaRes);
							return jsonReturn;
						}
					}
					//System.out.println(res.getDescripcion() + "[" + operacion + "]" + " " + respuesta);
					//log.log(Level.INFO, res.getDescripcion() + "[" + operacion + "]" + " " + respuesta);
					// mensaje=res.getDescripcion();

					//ultimo = idReserva;
				} catch (Exception e1) {
					String mensaje = e1.getMessage();
					System.out.println(mensaje);
					String jsonReturn = responderRestOpera(codRes, mensaje, idReserva,"","");

					insertarNoEmitida(idReserva, idhotel, respuestaRes);
					log.log(Level.SEVERE, e1.getMessage() + " ", e1);

					return jsonReturn;

					// JOptionPane.showMessageDialog(null, e1.getMessage(), "Factura Electronica",
					// JOptionPane.WARNING_MESSAGE);
					// ultimo = "0";
					// i.setPropertie(ultimo);
				}
				// } else {

				// }
				// }

			} catch (Exception e) {
				// TODO: handle exception
				insertarNoEmitida(idreserva, idhotel, respuestaRes);
				System.out.println(e.getMessage());
			}
		}

	}

	private static String responderRestOpera(int codRes, String respuestaRes, String idreserva, String serieCfe, String numeroCfe) {
		// TODO Auto-generated method stub

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		String fecha = now.format(dtf);
		
		String jsonReturn = "";

		if (codRes == 0) {
			jsonReturn = "{\"FiscalFolioNo\":\"" + idreserva + "\",\"FiscalBillGenerationDateTime\":\"" + fecha
					+ "\",\"FiscalOutputs\":{\"Output\":[{\"Name\":\"NumFacDgi\",\"Value\":\""+serieCfe+""+numeroCfe+"\"}]},\"StatusMessages\":null}";
		} else if (codRes != 0) {
			jsonReturn = "{\"FiscalFolioNo\":\"" + idreserva + "\",\"FiscalBillGenerationDateTime\":\"" + fecha
					+ "\",\"FiscalOutputs\":{\"Output\":[{\"Name\":\"NumFacDgi\",\"Null\":\"\"}]},\"StatusMessages\":{\"Messages\":[{\"Description\":\""
					+ respuestaRes + "\",\"Type\":\"Error\"}]}}";

		}
		return jsonReturn;

	}

	private static void cambiarBandera(String nrocompra) throws Exception {
		// TODO Auto-generated method stub
		DBDriverPostgreSQLWS bd = null;
		int contador = 1;
		bd = abrirConexionPosWS(bd, contador);
		String consulta = "update operawss set procesado=TRUE where idreserva= ?";
		PreparedStatement ps = bd.prepareStatement(consulta);
		ps.setString(1, nrocompra);
		ps.executeUpdate();
		ps.close();
		cerrarConexionPosWS(bd, contador);
	}

	private static boolean getOperacion(String nrocomprap) throws Exception {
		// TODO Auto-generated method stub
		String nrocompra = String.valueOf(nrocomprap);
		DBDriverPostgreSQLWS bd = null;
		int contador = 1;
		bd = abrirConexionPosWS(bd, contador);
		String consulta = "select operacion from venicewebservice where nrointernovenice= ?";
		PreparedStatement ps = bd.prepareStatement(consulta);
		ps.setString(1, nrocompra);
		ResultSet rs = ps.executeQuery();
		String respuesta = "";
		while (rs.next()) {
			respuesta = rs.getString(1);
		}
		ps.close();
		cerrarConexionPosWS(bd, contador);

		if (respuesta.equalsIgnoreCase("generar") || respuesta.equalsIgnoreCase("GENERAR")) {
			return false;
		} else {
			return true;
		}

	}

	private static Boolean chequearWs() {
		// TODO Auto-generated method stub
		boolean existe = false;

		try {
			int contador = 0;
			DBDriverPostgreSQLWS bd = null;
			bd = abrirConexionPosWS(bd, contador);
			String consultaR1 = "select * from operawss where procesado=false";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			ResultSet rsR1 = null;
			rsR1 = psR1.executeQuery();
			existe = rsR1.next();
			// String ultimo="0";
			cerrarConexionPosWS(bd, contador);

			rsR1.close();
			psR1.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return existe;
	}

	private static void insertarNoEmitida(String reserva, String message, String idhotel) throws Exception {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String hoy = dateFormat.format(now);

		int contador = 0;
		DBDriverPostgreSQL bd = null;
		bd = abrirConexionPos(bd, contador);
		String consultaR1 = "insert into operanoemitidas (idFactura, fecha, mensaje)" + "values ('" + reserva + "','"
				+ hoy + "','" + message + "')";
		PreparedStatement psR1 = bd.prepareStatement(consultaR1);
		psR1.executeUpdate();
		psR1.close();
		cerrarConexionPos(bd, 1);

		contador = 0;
		DBDriverPostgreSQLFacturaLista bdFl = null;
		bdFl = abrirConexionPosFacturaLista(bdFl, contador);
		String consultaR2 = "insert into erroresintegraciones (idFactura, fecha, idhotel, software, coderror, mensaje, solucionado)"
				+ "" + "values ('" + reserva + "','" + hoy + "','" + idhotel + "','FLOPERA','052023','" + message
				+ "',false)";
		PreparedStatement psR2 = bdFl.prepareStatement(consultaR2);
		psR2.executeUpdate();
		psR2.close();
		cerrarConexionPosFacturaLista(bdFl, 1);

	}

	private static DBDriverPostgreSQLFacturaLista abrirConexionPosFacturaLista(DBDriverPostgreSQLFacturaLista bd,
			int contador) throws Exception {
		if (bd == null) {
			bd = new DBDriverPostgreSQLFacturaLista();
			bd.conectar();
			// System.out.println("Abro conexion " + contador);
		}
		return bd;
	}

	private static DBDriverPostgreSQLFacturaLista cerrarConexionPosFacturaLista(DBDriverPostgreSQLFacturaLista bd,
			int contador) throws Exception {
		if (bd != null) {
			bd.desconectar();
			// System.out.println("Cerrar conexion " + contador);
		}
		return bd;
	}

	public boolean chequearDb(String reserva) throws Exception {
		boolean respuesta = obtengoUltimo(reserva);
		return respuesta;
	}

	private void guardoUltimo(String idReserva) {
		// TODO Auto-generated method stub
		try {
			int contador = 0;
			DBDriverPostgreSQL bd = null;
			bd = abrirConexionPos(bd, contador);
			String consultaR1 = "insert into venicereservas values ('" + idReserva + "',FALSE)";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			psR1.executeUpdate();

			cerrarConexionPos(bd, contador);
			psR1.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	private boolean obtengoUltimo(String idReserva) throws Exception {
		// TODO Auto-generated method stub
		boolean existe = false;

		try {
			int contador = 0;
			DBDriverPostgreSQL bd = null;
			bd = abrirConexionPos(bd, contador);
			String consultaR1 = "select * from factura where nrocompra='" + idReserva + "'";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			ResultSet rsR1 = null;
			rsR1 = psR1.executeQuery();
			existe = rsR1.next();
			// String ultimo="0";
			cerrarConexionPos(bd, contador);

			rsR1.close();
			psR1.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return existe;
	}

	public Respuesta cancelarFactura(String id, InfoReferencia referencia, String nrocompra, String prefijo)
			throws Exception {
		Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();

		int ocultardetalle = Integer.parseInt(propiedades.getProperty("ocultardetalle"));
		Factura factura = this.getFactura(id, prefijo, ocultardetalle == 1, nrocompra);
		if (factura.getTipo() == 101) {
			factura.setTipo(102);
		} else {
			factura.setTipo(112);
			factura.setTipo(102);
			InfoReferencia nd = new InfoReferencia();
			nd.setNroLinea(1);
			nd.setReferenciaGlobal(true);
			nd.setRazonReferencia("NOTA DE CREDITO");

			InfoReferencia[] referencias = new InfoReferencia[1];
			referencias[0] = nd;
			factura.setInfoReferencias(referencias);
		}
		GenerarFactura gf = new GenerarFactura();
		gf.setArg0(factura);
		// gf.setArg1(propiedades.getProperty("impresora"));
		gf.setArg1(propiedades.getProperty(""));
		FacturaElectronicaImplServiceStub fstub = new FacturaElectronicaImplServiceStub(
				propiedades.getProperty("endpoint"));
		servicios.FacturaElectronicaImplServiceStub.GenerarFacturaResponse res = fstub.generarFactura(gf);
		if (res.get_return().getCodigo() == 0) {
			Respuesta r = this.mandarImprimir(res.get_return().getDocumento().getSerie(),
					res.get_return().getDocumento().getNumero(), res.get_return().getTipoCFE());
			if (r.getCodigo() > 0) {
				return r;
			}
		}
		Respuesta r = new Respuesta();
		r.setCodigo(res.get_return().getCodigo());
		r.setDescripcion(res.get_return().getDescripcion());
		r.setDocumento(res.get_return().getDocumento());
		r.setReferencia(res.get_return().getReferencia());

		return r;
	}

	/**
	 * Metodo creado para aplicar las infoReferencias cuando es una Facutra en
	 * negativo
	 * 
	 * @return
	 * @throws Exception
	 */
	public Factura cancelarFacturaXNotaCredito(Factura factura) throws Exception {

		InfoReferencia nd = new InfoReferencia();
		Calendar c = Calendar.getInstance();
		nd.setFechaReferencia(c);
		nd.setNroLinea(1);
		nd.setReferenciaGlobal(true);
		nd.setRazonReferencia("NOTA DE CREDITO");

		InfoReferencia[] referencias = new InfoReferencia[1];
		referencias[0] = nd;
		factura.setInfoReferencias(referencias);

		return factura;
	}

	public boolean cancelada(int id, String prefijo) throws Exception {
		boolean salida = false;

		return salida;
	}

	public InfoReferencia existeFactura(String nrocompra) throws Exception {
		DBDriverPostgreSQL bd = new DBDriverPostgreSQL();
		bd.conectar();

		InfoReferencia salida = bd.existeFactura(nrocompra);

		bd.desconectar();

		return salida;
	}

	public InfoReferencia existeFacturaWS(String nrocompra) throws Exception {
		DBDriverPostgreSQLWS bd = new DBDriverPostgreSQLWS();
		bd.conectar();

		InfoReferencia salida = bd.existeFacturaWS(nrocompra);

		bd.desconectar();

		return salida;
	}

	private void imprimirproductos(ArrayList<SRProducto_oracle> p) {
		for (SRProducto_oracle pp : p) {
			System.out.println(pp.getCantidad() + " -- " + pp.getDescripcion());
		}
	}
}
