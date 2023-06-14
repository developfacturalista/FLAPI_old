package com.facturalista.ws;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import bd.DBDriverPostgreSQL;
import bd.DBDriverPostgreSQLOpera;
import bd.DBDriverPostgreSQLWS;
import controlador.InterfazFL_Opera;
//import jakarta.ws.rs.core.Response;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/Opera")
public class Opera {
	@GET
	@Path("/authors")
	@Produces(MediaType.APPLICATION_JSON)
	public List getTrackInJSON() {
		List listPerson = new ArrayList();
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("Santiago");
		Person p2 = new Person();
		p2.setId(2);
		p2.setName("Rodrigo");
		listPerson.add(p1);
		listPerson.add(p2);
		return listPerson;
	}

	/*
	 * @GET
	 * 
	 * @Path("/setFacturasNuevas/{z},{a},{b},{c},{d},{e}")
	 * 
	 * @Produces({MediaType.APPLICATION_JSON}) public String
	 * saludoJsonPP(@PathParam("z") String idHotel,@PathParam("a") Boolean
	 * bandera,@PathParam("b") String fecha,@PathParam("c") String
	 * puestoRecepcion, @PathParam("d") String operacion,@PathParam("e") String
	 * nroInterno) throws Exception {
	 * 
	 * String respuesta=insertarBD(idHotel, bandera, fecha, puestoRecepcion,
	 * operacion, nroInterno);
	 * 
	 * 
	 * return
	 * "ID Hotel: "+idHotel+"\n"+"Bandera: "+bandera+"\n"+"Puesto de recepcion: "
	 * +puestoRecepcion+"\n"+"Fecha: "+fecha+"\n"+"Operacion: "+operacion+
	 * "\n"+"Nro interno venice: "+nroInterno+"\n"+"Respuesta web service: "
	 * +respuesta; //Boolean b = Boolean.valueOf(bandera); //actualizarBD(b);
	 * 
	 * //return idHotel+", "+ b +
	 * ", "+puestoRecepcion+", "+fecha+", "+operacion+", "+nroInterno;
	 * 
	 * }
	 */

	/*
	 * @POST
	 * 
	 * @Path("/GetHrMsg/json_data")
	 * 
	 * @Consumes(MediaType.APPLICATION_JSON)
	 * 
	 * @Produces(MediaType.APPLICATION_JSON) public void gethrmessage(RequestBody
	 * requestBody) { System.out.println(requestBody.hello);
	 * System.out.println(requestBody.foo); System.out.println(requestBody.count); }
	 */

	@POST
	@Path("/setFacturasNuevas")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response gethrmessage(InputStream incomingData) {
		String respuesta = "";
		String jsonstring = "";
		int result = 0;
		int codigoRespuesta=0;
		StringBuilder crunchifyBuilder = new StringBuilder();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				crunchifyBuilder.append(line);
			}

			System.out.println("Data Received: " + crunchifyBuilder.toString());
			// try {
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(crunchifyBuilder.toString());
			jsonstring = crunchifyBuilder.toString();

			JSONObject docinfo = (JSONObject) json.get("DocumentInfo");
			String idReserva = docinfo.get("FiscalFolioId").toString();
			String idhotel = "";

			if (docinfo.get("HotelCode").toString() != null) {
				idhotel = docinfo.get("HotelCode").toString();
			} else {
				idhotel = "S/N";
			}

			result = insertarBDOpera(jsonstring);

			updateIdReserva(result, idReserva, idhotel);

			InterfazFL_Opera i = new InterfazFL_Opera();
			String[] arguments = new String[] { idReserva };
			respuesta = InterfazFL_Opera.main(arguments);
			if(respuesta.contains("Error")) {
				codigoRespuesta=402;
			}
			else {
				codigoRespuesta=200;
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			respuesta = e.getMessage();
			e.printStackTrace();
			return Response.status(402).entity("Catch "+e.getMessage()).build();
		}

		// return HTTP response 200 in case of success
		return Response.status(codigoRespuesta).entity(respuesta).build();
	}

	private static String updateIdReserva(int idllamado, String idReserva, String idHotel) {
		// TODO Auto-generated method stub
		String result = "vacio";
		String consultaR1 = "";
		try {
			int contador = 0;
			DBDriverPostgreSQLOpera bd = null;
			bd = abrirConexionOpera(bd, contador);
			consultaR1 = "update operawss set idreserva='" + idReserva + "', idhotel='" + idHotel + "' where idllamado="
					+ idllamado;
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			int resultint = psR1.executeUpdate();
			// String ultimo="0";
			cerrarConexionOpera(bd, 1);

			psR1.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			result = e.getMessage();
		}
		return result + " " + consultaR1;

	}

	private static DBDriverPostgreSQLWS abrirConexionPosWS(DBDriverPostgreSQLWS bd, int contador) throws Exception {
		if (bd == null) {
			bd = new DBDriverPostgreSQLWS();
			bd.conectar();
			// System.out.println("Abro conexion " + contador);
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

	private String getString() {
		// TODO Auto-generated method stub
		String respuesta = "";
		try {
			int contador = 0;
			DBDriverPostgreSQLOpera bd = null;
			bd = abrirConexionOpera(bd, contador);
			String consultaR1 = "select * from operawss";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			ResultSet rs = psR1.executeQuery();

			while (rs.next()) {
				respuesta = rs.getString(3);
			}
			cerrarConexionOpera(bd, 1);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			respuesta = e.getMessage();
		}
		return respuesta;
	}

	private int insertarBDOpera(String json) throws Exception {
		// TODO Auto-generated method stub
		String retorno = "sin error";
		String consultaR1 = "";
		int idllamado = 0000004;
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String hoy = dateFormat.format(now);

		try {
			int contador = 0;
			DBDriverPostgreSQLOpera bd = null;
			bd = abrirConexionOpera(bd, contador);
			consultaR1 = "insert into operawss (idllamado,idreserva, idhotel, json, fecha, procesado"
					+ ") values (default,'','','" + json + "','" + hoy + "',FALSE) RETURNING idllamado;";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			ResultSet idllamadoresulset = psR1.executeQuery();
			if (idllamadoresulset.next()) {
				idllamado = idllamadoresulset.getInt(1);
			}
			cerrarConexionOpera(bd, 1);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			retorno = e.getMessage();
			// retorno="error bd";
		}
		return idllamado;
	}
	/*
	 * @POST
	 * 
	 * @Path("/setFacturasNuevasP")
	 * 
	 * @Produces({MediaType.APPLICATION_JSON}) public String saludoJsonP(RequestBody
	 * string) throws Exception {
	 * 
	 * return "entre";
	 * 
	 * 
	 * /*String body = null; StringBuilder stringBuilder = new StringBuilder();
	 * BufferedReader bufferedReader = null;
	 * 
	 * try { InputStream inputStream = request.getInputStream(); if (inputStream !=
	 * null) { bufferedReader = new BufferedReader(new
	 * InputStreamReader(inputStream)); char[] charBuffer = new char[128]; int
	 * bytesRead = -1; while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
	 * stringBuilder.append(charBuffer, 0, bytesRead); } } else {
	 * stringBuilder.append(""); } } catch (IOException ex) { throw ex; } finally {
	 * if (bufferedReader != null) { try { bufferedReader.close(); } catch
	 * (IOException ex) { throw ex; } } }
	 * 
	 * body = stringBuilder.toString(); return body;
	 */

	/*
	 * JSONParser parser = new JSONParser(); String[] response = new String[5];
	 * 
	 * Object obj = parser.parse(text); JSONObject jsonObject = (JSONObject) obj;
	 * /*offerProcess ofc = new offerProcess(); ofc.setLatitude((double)
	 * jsonObject.get("latitude")); ofc.setLongitude((double)
	 * jsonObject.get("longitude")); ofc.setIMSI((long) jsonObject.get("IMSI"));
	 * 
	 * response = ofc.fetchOffers(); //System.out.println(text);
	 * 
	 * return "entre";
	 * 
	 * /*Boolean b = Boolean.valueOf(bandera); actualizarBD(b);
	 * 
	 * return bandera;
	 * 
	 * }
	 */

	@GET
	@Path("/getFacturasNuevas/{i}")
	@Produces({ MediaType.APPLICATION_JSON })
	public String saludoJsonPP(@PathParam("i") String idHotel) throws Exception {

		return idHotel;

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

	private static DBDriverPostgreSQLOpera abrirConexionOpera(DBDriverPostgreSQLOpera bd, int contador)
			throws Exception {
		if (bd == null) {
			bd = new DBDriverPostgreSQLOpera();
			bd.conectar();
			System.out.println("Abro conexion " + contador);
		}
		return bd;
	}

	private static DBDriverPostgreSQLOpera cerrarConexionOpera(DBDriverPostgreSQLOpera bd, int contador)
			throws Exception {
		if (bd != null) {
			bd.desconectar();
			System.out.println("Cerrar conexion " + contador);
		}
		return bd;
	}

	private String actualizarBD(Boolean bandera) throws Exception {
		// TODO Auto-generated method stub
		String retorno = "";
		try {
			int contador = 0;
			DBDriverPostgreSQL bd = null;
			bd = abrirConexion(bd, contador);
			String consultaR1 = "update veniceWebService set bandera =?";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			psR1.setBoolean(1, bandera);
			psR1.executeUpdate();
			cerrarConexion(bd, 1);
			retorno = "exito";
		} catch (Exception e) {
			System.out.println(e.getMessage());
			retorno = e.getMessage();
		}

		return retorno;
	}

	private String insertarBD(String idHotel, Boolean bandera, String fecha, String puestoRecepcion, String operacion,
			String nroVenice) throws Exception {
		// TODO Auto-generated method stub
		String retorno = "";

		try {
			int contador = 0;
			DBDriverPostgreSQL bd = null;
			bd = abrirConexion(bd, contador);
			String consultaR1 = "insert into venicewebservice (idllamado,idHotel, bandera, fecha, puestoRecepcion, operacion,"
					+ "nroInternoVenice) values (default,'" + idHotel + "','" + bandera + "','" + fecha + "','"
					+ puestoRecepcion + "'," + "'" + operacion + "','" + nroVenice + "')";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			psR1.executeUpdate();
			cerrarConexion(bd, 1);
			retorno = "exito";

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			retorno = e.getMessage();
		}
		return retorno;
	}

}