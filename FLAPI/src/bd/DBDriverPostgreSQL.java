package bd;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Properties;

import servicios.FacturaElectronicaImplServiceStub.InfoReferencia;


public class DBDriverPostgreSQL{

	
	private Connection conexion = null;
	private Statement stmt = null;

	private String url;
	private String user;
	private String password;
	
	public DBDriverPostgreSQL() throws IOException {
		Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();
		url = propiedades.getProperty("servidorbd");
		user = propiedades.getProperty("userbd");
		password = propiedades.getProperty("clavebd");
	}
	synchronized public void conectar() throws Exception {
		conexion = DriverManager.getConnection(url, user, password);
		conexion.setAutoCommit(false);
		stmt = conexion.createStatement();

	}


	synchronized public void desconectar() throws Exception {
		conexion.commit();
		stmt.close();
		conexion.close();
	}

	synchronized public void abortar() throws Exception {
		conexion.rollback();
		stmt.close();
		conexion.close();
	}

	
	public void commit() throws SQLException {
		conexion.commit();
	}

	public PreparedStatement prepareStatement(String sql) {
		try {
			return conexion.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public InfoReferencia existeFactura(String nrocomprap) throws SQLException{
		String nrocompra = String.valueOf(nrocomprap);
		String consulta = "select count(*) from factura where nrocompra= ?";
		PreparedStatement ps = this.prepareStatement(consulta);
		ps.setString(1, nrocompra);
		ResultSet rsS = ps.executeQuery();
		rsS.next();
		if(rsS.getInt(1)==0)
			return null;
		else{
			consulta = "select numero,serie,tipo,fechacreada from factura where nrocompra=?";
			ps = this.prepareStatement(consulta);
			ps.setString(1, nrocompra);
			rsS = ps.executeQuery();
			rsS.next();
			InfoReferencia nd = new InfoReferencia();
			Calendar c = Calendar.getInstance();
			c.setTime(rsS.getDate(4));
			nd.setFechaReferencia(c);
			nd.setNroCFEReferencia(rsS.getInt(1));
			nd.setNroLinea(1);
			nd.setReferenciaGlobal(true);
			nd.setSerieCFEReferencia(rsS.getString(2));
			nd.setTipoCFEReferencia(rsS.getInt(3));
			nd.setRazonReferencia(nrocompra+"r");
			return nd;
		}
	}

	public InfoReferencia existeFacturaWS(String nrocomprap) throws SQLException{
		String nrocompra = String.valueOf(nrocomprap);
		String consulta = "select operacion from venicewebservice where nrointernovenice= ?";
		PreparedStatement ps = this.prepareStatement(consulta);
		ps.setString(1, nrocompra);
		ResultSet rs = ps.executeQuery();
		rs.next();
		String respuesta=rs.getString(1);
		if(respuesta.equalsIgnoreCase("generar")|| respuesta.equalsIgnoreCase("GENERAR")) {
			return null;
		}
		else{
			consulta = "select numero,serie,tipo,fechacreada from factura where nrocompra=?";
			ps = this.prepareStatement(consulta);
			ps.setString(1, nrocompra);

			rs = ps.executeQuery();
			rs.next();
			InfoReferencia nd = new InfoReferencia();
			Calendar c = Calendar.getInstance();
			c.setTime(rs.getDate(4));
			nd.setFechaReferencia(c);
			nd.setNroCFEReferencia(rs.getInt(1));
			nd.setNroLinea(1);
			nd.setReferenciaGlobal(true);
			nd.setSerieCFEReferencia(rs.getString(2));
			nd.setTipoCFEReferencia(rs.getInt(3));
			nd.setRazonReferencia(nrocompra);
			return nd;
			
		}
	}
	
}
