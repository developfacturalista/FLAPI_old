package controlador;

import java.awt.EventQueue;
import javax.swing.JFrame;
import org.apache.axis2.AxisFault;
//import org.jdesktop.swingx.JXDatePicker;
import bd.Direccionador;
import bd.DBDriverPostgreSQL;
//import clasesApoyo.JModalDialog;
import servicios.FacturaElectronicaImplServiceStub;
import servicios.FacturaElectronicaImplServiceStub.GetImprimible;
import servicios.FacturaElectronicaImplServiceStub.GetImprimibleResponse;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.Properties;
import javax.activation.DataHandler;
import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class services {

	private static final long serialVersionUID = 1L;
	private String ruta;
	private ArrayList<Integer> ids = null;
	private int contador = 0;
	private int totalFactura = 0;
	private int ciPersona = 0;
	private String nombrePersona = null;
	private Properties properties = null;
	private int numeroProceso = 0;
	private int contador2 = 0;
	String consultaNro = null;
	int alerta;
	int nlinea = 0;
	String nombreCliente=null;
	String rutCliente =null;
	private String msjRes = null;
	private int codigoRes = 0;
	String idFacturaActual="";

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					services frame = new services();
					// frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public services() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// setBounds(0, 0, 530, 338);
		// this.setLocationRelativeTo(null);
		// this.setResizable(false);

		properties = Direccionador.getInstance().getArchivoConfiguracion();
		ruta = "";
		ids = new ArrayList<Integer>();

	}

	public void setIds(ArrayList<Integer> idsE) {
		ids = idsE;
	}

	private FacturaElectronicaImplServiceStub inicializar() throws AxisFault {
		Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();
		FacturaElectronicaImplServiceStub fstub = new FacturaElectronicaImplServiceStub(
				propiedades.getProperty("endpoint"));

		return fstub;

	}


	private DBDriverPostgreSQL abrirConexion(DBDriverPostgreSQL bd, int contador) throws Exception {
		if (bd == null) {
			bd = new DBDriverPostgreSQL();
			bd.conectar();
			System.out.println("Abro conexion " + contador);
		}
		return bd;
	}

	private DBDriverPostgreSQL cerrarConexion(DBDriverPostgreSQL bd, int contador) throws Exception {
		if (bd != null) {
			bd.desconectar();
			System.out.println("Cerrar conexion " + contador);
		}
		return bd;
	}

	
	public GetImprimibleResponse getImprimible(String serie, String numero, String tipocfe,FacturaElectronicaImplServiceStub fstub) throws IOException {
		
		Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();
		String ruta = propiedades.getProperty("imprimibles");
		
		GetImprimible gi = new GetImprimible();

		gi.setArg0(serie);
		gi.setArg1(Integer.parseInt(numero));
		gi.setArg2(Integer.parseInt(tipocfe));
		gi.setArg3("pdf");

		boolean seguir = true;

		servicios.FacturaElectronicaImplServiceStub.GetImprimibleResponse res = null;

		while (seguir) {
			seguir = false;
			res = fstub.getImprimible(gi);
			codigoRes = res.get_return().getCodigo();
			if (res.get_return().getCodigo() == 0) {
				//updateProcesado();
				DataHandler handler = res.get_return().getImprimible();
				String archivo = ruta+"/Factura"+numero+".pdf"; 
				OutputStream os = new FileOutputStream(new File(archivo));
				handler.writeTo(os);
				//String stringToWrite = IOUtils.toString(res.get_return().getImprimible().getInputStream(), "UTF-8");
				//System.out.println(stringToWrite);
		} else {
			switch (codigoRes) {
			case 22:
				msjRes = "Documento - Tipo de documento debe ser RUT para eFactura";
				mostrarError(msjRes);
				break;
			case 49:
				msjRes = "Validar el codigo del pais enviado debe ser de dos caracteres, ejemplo: UY";
				mostrarError(msjRes);
				break;
			case 69:
				msjRes = "El comprobante no tiene lineas de productos";
				mostrarError(msjRes);
				break;
			case 74: 
				msjRes = "El monto excede el permitido sin un receptor,deben ingresar el documento de la persona";
				mostrarError(msjRes);
				break;
			case 76: 
				msjRes = "RUT INVALIDO";
				break;
			case 94: 
				msjRes = "RUT INVALIDO";
				break;
			case 90:
				msjRes = "La dirección no puede superar los 70 caracteres";
				//corregirDireccionInput(msjRes);
				break;
			case 96: 
				msjRes = "RUT o CEDULA INCORRECTO";
				break;
			case 99: 
				msjRes = "Error de conexión - COD: 99";
				mostrarError(msjRes);
				break;
			case 1013: 
				msjRes = "ERROR EN EL CODIGO DE PAÍS";
				mostrarError(msjRes);
				break;
			case 1024: 
				msjRes = "NO QUEDA RANGO DISPONIBLE";
				mostrarError(msjRes);
				break;
				
			default:
				msjRes = "Revisar el log";
				mostrarError(msjRes);
				break;
			}
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
			}
			
		}
		return res;
	}

	
	
	
	public void mostrarError(String text){
	    JOptionPane.showMessageDialog(new JFrame(), text, "ERROR",JOptionPane.CLOSED_OPTION);
	    System.exit(0);
	}
	
	public String corregirRutInput(String rutNuevo){
		String name = JOptionPane.showInputDialog(null,"Verifique y coloque el RUT o CEDULA nuevamente","RUT o CEDULA INCORRECTO",JOptionPane.ERROR_MESSAGE);
		return name;
		//JOptionPane.showMessageDialog(null, "Hello " + name);
	}

	private void updateProcesado() {

		try {

			DBDriverPostgreSQL bd = null;
			bd = abrirConexion(bd, contador);
			PreparedStatement preparedStmt = null;

			for (int i = 0; i < ids.size(); i++) {

				String query = "update remitos set estado = ? where remito_id = ?";

				preparedStmt = bd.prepareStatement(query);
				preparedStmt.setBoolean(1, true);
				preparedStmt.setInt(2, ids.get(i));

				preparedStmt.executeUpdate();

			}

			preparedStmt.close();
			cerrarConexion(bd, contador);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void eliminarProceso() throws Exception {
		// TODO Auto-generated method stub

		DBDriverPostgreSQL bd = new DBDriverPostgreSQL();
		bd.conectar();

		try {
			String sqlProceso = "SELECT min(pid) as proceso FROM pg_stat_activity where query = 'select count(*) from pais where codigo=$1'";
			PreparedStatement psProceso = bd.prepareStatement(sqlProceso);

			ResultSet rsProceso = psProceso.executeQuery();

			if (rsProceso.next()) {
				numeroProceso = rsProceso.getInt(1);
			}
			String sqlKill = " SELECT pg_terminate_backend (?)";
			PreparedStatement psKill = bd.prepareStatement(sqlKill);
			psKill.setInt(1, numeroProceso);
			ResultSet rskill = psKill.executeQuery();
			System.out.println("funciona");
			rsProceso.close();
			psProceso.close();
			rskill.close();
			psKill.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("no funciona");
			System.out.println(e.getMessage());
		}
		bd.desconectar();

	}

}
