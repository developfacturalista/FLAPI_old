package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.InterfazFL_Opera;
//import controlador.InterfazFL_SPicasso_BORRADOR;
import servicios.FacturaElectronicaImplServiceStub.Documento;
import servicios.FacturaElectronicaImplServiceStub.Factura;
import servicios.FacturaElectronicaImplServiceStub.Persona;

public class Receptor_OfiHotel extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField direccion;
	private JTextField documento;
	private JTextField nombre;
	private JComboBox<String> documentoTipo;
	private JComboBox<String> documentoPais;
	private JComboBox<String> pais;
	private Persona persona = new Persona();
	public static InterfazFL_Opera interfaz;
	public static Factura factura;
	private JTextField txtDepartamento;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Receptor_OfiHotel frame = new Receptor_OfiHotel(null,"",null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	//select 'paises.put("'||codigo||'","'||upper(nombre)||'");' from pais
	public static HashMap<String, String> getHashPaises(){
		HashMap<String, String> paises = new HashMap<String, String>();
		paises.put("AF","AFGANISTN");
		paises.put("AX","ISLAS GLAND");
		paises.put("AL","ALBANIA");
		paises.put("DE","ALEMANIA");
		paises.put("AD","ANDORRA");
		paises.put("AO","ANGOLA");
		paises.put("AI","ANGUILLA");
		paises.put("AQ","ANTRTIDA");
		paises.put("AG","ANTIGUA Y BARBUDA");
		paises.put("AN","ANTILLAS HOLANDESAS");
		paises.put("SA","ARABIA SAUD");
		paises.put("DZ","ARGELIA");
		paises.put("AR","ARGENTINA");
		paises.put("AM","ARMENIA");
		paises.put("AW","ARUBA");
		paises.put("AU","AUSTRALIA");
		paises.put("AT","AUSTRIA");
		paises.put("AZ","AZERBAIYN");
		paises.put("BS","BAHAMAS");
		paises.put("BH","BAHRIN");
		paises.put("BD","BANGLADESH");
		paises.put("BB","BARBADOS");
		paises.put("BY","BIELORRUSIA");
		paises.put("BE","BÉLGICA");
		paises.put("BZ","BELICE");
		paises.put("BJ","BENIN");
		paises.put("BM","BERMUDAS");
		paises.put("BT","BHUTN");
		paises.put("BO","BOLIVIA");
		paises.put("BA","BOSNIA Y HERZEGOVINA");
		paises.put("BW","BOTSUANA");
		paises.put("BV","ISLA BOUVET");
		paises.put("BR","BRASIL");
		paises.put("BN","BRUNÉI");
		paises.put("BG","BULGARIA");
		paises.put("BF","BURKINA FASO");
		paises.put("BI","BURUNDI");
		paises.put("CV","CABO VERDE");
		paises.put("KY","ISLAS CAIM�N");
		paises.put("KH","CAMBOYA");
		paises.put("CM","CAMERÚN");
		paises.put("CA","CANAD�");
		paises.put("CF","REPÚBLICA CENTROAFRICANA");
		paises.put("TD","CHAD");
		paises.put("CZ","REPÚBLICA CHECA");
		paises.put("CL","CHILE");
		paises.put("CN","CHINA");
		paises.put("CY","CHIPRE");
		paises.put("CX","ISLA DE NAVIDAD");
		paises.put("VA","CIUDAD DEL VATICANO");
		paises.put("CC","ISLAS COCOS");
		paises.put("CO","COLOMBIA");
		paises.put("KM","COMORAS");
		paises.put("CD","REPÚBLICA DEMOCR�TICA DEL CONGO");
		paises.put("CG","CONGO");
		paises.put("CK","ISLAS COOK");
		paises.put("KP","COREA DEL NORTE");
		paises.put("KR","COREA DEL SUR");
		paises.put("CI","COSTA DE MARFIL");
		paises.put("CR","COSTA RICA");
		paises.put("HR","CROACIA");
		paises.put("CU","CUBA");
		paises.put("DK","DINAMARCA");
		paises.put("DM","DOMINICA");
		paises.put("DO","REPÚBLICA DOMINICANA");
		paises.put("EC","ECUADOR");
		paises.put("EG","EGIPTO");
		paises.put("SV","EL SALVADOR");
		paises.put("AE","EMIRATOS �RABES UNIDOS");
		paises.put("ER","ERITREA");
		paises.put("SK","ESLOVAQUIA");
		paises.put("SI","ESLOVENIA");
		paises.put("ES","ESPAÑA");
		paises.put("UM","ISLAS ULTRAMARINAS DE ESTADOS UNIDOS");
		paises.put("US","ESTADOS UNIDOS");
		paises.put("EE","ESTONIA");
		paises.put("ET","ETIOP�A");
		paises.put("FO","ISLAS FEROE");
		paises.put("PH","FILIPINAS");
		paises.put("FI","FINLANDIA");
		paises.put("FJ","FIYI");
		paises.put("FR","FRANCIA");
		paises.put("GA","GABÓN");
		paises.put("GM","GAMBIA");
		paises.put("GE","GEORGIA");
		paises.put("GS","ISLAS GEORGIAS DEL SUR Y SANDWICH DEL SUR");
		paises.put("GH","GHANA");
		paises.put("GI","GIBRALTAR");
		paises.put("GD","GRANADA");
		paises.put("GR","GRECIA");
		paises.put("GL","GROENLANDIA");
		paises.put("GP","GUADALUPE");
		paises.put("GU","GUAM");
		paises.put("GT","GUATEMALA");
		paises.put("GF","GUAYANA FRANCESA");
		paises.put("GN","GUINEA");
		paises.put("GQ","GUINEA ECUATORIAL");
		paises.put("GW","GUINEA-BISSAU");
		paises.put("GY","GUYANA");
		paises.put("HT","HAIT�");
		paises.put("HM","ISLAS HEARD Y MCDONALD");
		paises.put("HN","HONDURAS");
		paises.put("HK","HONG KONG");
		paises.put("HU","HUNGR�A");
		paises.put("IN","INDIA");
		paises.put("ID","INDONESIA");
		paises.put("IR","IR�N");
		paises.put("IQ","IRAQ");
		paises.put("IE","IRLANDA");
		paises.put("IS","ISLANDIA");
		paises.put("IL","ISRAEL");
		paises.put("IT","ITALIA");
		paises.put("JM","JAMAICA");
		paises.put("JP","JAPÓN");
		paises.put("JO","JORDANIA");
		paises.put("KZ","KAZAJST�N");
		paises.put("KE","KENIA");
		paises.put("KG","KIRGUIST�N");
		paises.put("KI","KIRIBATI");
		paises.put("KW","KUWAIT");
		paises.put("LA","LAOS");
		paises.put("LS","LESOTHO");
		paises.put("LV","LETONIA");
		paises.put("LB","L�BANO");
		paises.put("LR","LIBERIA");
		paises.put("LY","LIBIA");
		paises.put("LI","LIECHTENSTEIN");
		paises.put("LT","LITUANIA");
		paises.put("LU","LUXEMBURGO");
		paises.put("MO","MACAO");
		paises.put("MK","ARY MACEDONIA");
		paises.put("MG","MADAGASCAR");
		paises.put("MY","MALASIA");
		paises.put("MW","MALAWI");
		paises.put("MV","MALDIVAS");
		paises.put("ML","MAL�");
		paises.put("MT","MALTA");
		paises.put("FK","ISLAS MALVINAS");
		paises.put("MP","ISLAS MARIANAS DEL NORTE");
		paises.put("MA","MARRUECOS");
		paises.put("MH","ISLAS MARSHALL");
		paises.put("MQ","MARTINICA");
		paises.put("MU","MAURICIO");
		paises.put("MR","MAURITANIA");
		paises.put("YT","MAYOTTE");
		paises.put("MX","MÉXICO");
		paises.put("FM","MICRONESIA");
		paises.put("MD","MOLDAVIA");
		paises.put("MC","MÓNACO");
		paises.put("MN","MONGOLIA");
		paises.put("MS","MONTSERRAT");
		paises.put("MZ","MOZAMBIQUE");
		paises.put("MM","MYANMAR");
		paises.put("NA","NAMIBIA");
		paises.put("NR","NAURU");
		paises.put("NP","NEPAL");
		paises.put("NI","NICARAGUA");
		paises.put("NE","N�GER");
		paises.put("NG","NIGERIA");
		paises.put("NU","NIUE");
		paises.put("NF","ISLA NORFOLK");
		paises.put("NO","NORUEGA");
		paises.put("NC","NUEVA CALEDONIA");
		paises.put("NZ","NUEVA ZELANDA");
		paises.put("OM","OM�N");
		paises.put("NL","PA�SES BAJOS");
		paises.put("PK","PAKIST�N");
		paises.put("PW","PALAU");
		paises.put("PS","PALESTINA");
		paises.put("PA","PANAM�");
		paises.put("PG","PAPÚA NUEVA GUINEA");
		paises.put("PY","PARAGUAY");
		paises.put("PE","PERÚ");
		paises.put("PN","ISLAS PITCAIRN");
		paises.put("PF","POLINESIA FRANCESA");
		paises.put("PL","POLONIA");
		paises.put("PT","PORTUGAL");
		paises.put("PR","PUERTO RICO");
		paises.put("QA","QATAR");
		paises.put("GB","REINO UNIDO");
		paises.put("RE","REUNIÓN");
		paises.put("RW","RUANDA");
		paises.put("RO","RUMANIA");
		paises.put("RU","RUSIA");
		paises.put("EH","SAHARA OCCIDENTAL");
		paises.put("SB","ISLAS SALOMÓN");
		paises.put("WS","SAMOA");
		paises.put("AS","SAMOA AMERICANA");
		paises.put("KN","SAN CRISTÓBAL Y NEVIS");
		paises.put("SM","SAN MARINO");
		paises.put("PM","SAN PEDRO Y MIQUELÓN");
		paises.put("VC","SAN VICENTE Y LAS GRANADINAS");
		paises.put("SH","SANTA HELENA");
		paises.put("LC","SANTA LUC�A");
		paises.put("ST","SANTO TOMÉ Y PR�NCIPE");
		paises.put("SN","SENEGAL");
		paises.put("CS","SERBIA Y MONTENEGRO");
		paises.put("SC","SEYCHELLES");
		paises.put("SL","SIERRA LEONA");
		paises.put("SG","SINGAPUR");
		paises.put("SY","SIRIA");
		paises.put("SO","SOMALIA");
		paises.put("LK","SRI LANKA");
		paises.put("SZ","SUAZILANDIA");
		paises.put("ZA","SUD�FRICA");
		paises.put("SD","SUD�N");
		paises.put("SE","SUECIA");
		paises.put("CH","SUIZA");
		paises.put("SR","SURINAM");
		paises.put("SJ","SVALBARD Y JAN MAYEN");
		paises.put("TH","TAILANDIA");
		paises.put("TW","TAIW�N");
		paises.put("TZ","TANZANIA");
		paises.put("TJ","TAYIKIST�N");
		paises.put("IO","TERRITORIO BRIT�NICO DEL OCÉANO �NDICO");
		paises.put("TF","TERRITORIOS AUSTRALES FRANCESES");
		paises.put("TL","TIMOR ORIENTAL");
		paises.put("TG","TOGO");
		paises.put("TK","TOKELAU");
		paises.put("TO","TONGA");
		paises.put("TT","TRINIDAD Y TOBAGO");
		paises.put("TN","TÚNEZ");
		paises.put("TC","ISLAS TURCAS Y CAICOS");
		paises.put("TM","TURKMENIST�N");
		paises.put("TR","TURQU�A");
		paises.put("TV","TUVALU");
		paises.put("UA","UCRANIA");
		paises.put("UG","UGANDA");
		paises.put("UY","URUGUAY");
		paises.put("UZ","UZBEKIST�N");
		paises.put("VU","VANUATU");
		paises.put("VE","VENEZUELA");
		paises.put("VN","VIETNAM");
		paises.put("VG","ISLAS V�RGENES BRIT�NICAS");
		paises.put("VI","ISLAS V�RGENES DE LOS ESTADOS UNIDOS");
		paises.put("WF","WALLIS Y FUTUNA");
		paises.put("YE","YEMEN");
		paises.put("DJ","YIBUTI");
		paises.put("ZM","ZAMBIA");
		paises.put("ZW","ZIMBABUE");

		return paises;
	}
	public static HashMap<String, String> getHashPaisesInverso(){
		HashMap<String, String> salida = new HashMap<String, String>();
		HashMap<String, String> paises = getHashPaises();
		for(String k:paises.keySet()){
			salida.put(paises.get(k), k);
		}
		return salida;
	}
	//select *,'paises.put("'||codigolargo||'","'||nombre||'");' from pais
	public static ArrayList<String> getPaises(){
		ArrayList<String> paises = new ArrayList<String>();
		paises.add("Afganistán");
		paises.add("Islas Gland");
		paises.add("Albania");
		paises.add("Alemania");
		paises.add("Andorra");
		paises.add("Angola");
		paises.add("Anguilla");
		paises.add("Antártida");
		paises.add("Antigua y Barbuda");
		paises.add("Antillas Holandesas");
		paises.add("Arabia Saudí");
		paises.add("Argelia");
		paises.add("Argentina");
		paises.add("Armenia");
		paises.add("Aruba");
		paises.add("Australia");
		paises.add("Austria");
		paises.add("Azerbaiyán");
		paises.add("Bahamas");
		paises.add("Bahréin");
		paises.add("Bangladesh");
		paises.add("Barbados");
		paises.add("Bielorrusia");
		paises.add("Bélgica");
		paises.add("Belice");
		paises.add("Benin");
		paises.add("Bermudas");
		paises.add("Bhután");
		paises.add("Bolivia");
		paises.add("Bosnia y Herzegovina");
		paises.add("Botsuana");
		paises.add("Isla Bouvet");
		paises.add("Brasil");
		paises.add("Brunéi");
		paises.add("Bulgaria");
		paises.add("Burkina Faso");
		paises.add("Burundi");
		paises.add("Cabo Verde");
		paises.add("Islas Caimán");
		paises.add("Camboya");
		paises.add("Camerún");
		paises.add("Canadá");
		paises.add("República Centroafricana");
		paises.add("Chad");
		paises.add("República Checa");
		paises.add("Chile");
		paises.add("China");
		paises.add("Chipre");
		paises.add("Isla de Navidad");
		paises.add("Ciudad del Vaticano");
		paises.add("Islas Cocos");
		paises.add("Colombia");
		paises.add("Comoras");
		paises.add("República Democrática del Congo");
		paises.add("Congo");
		paises.add("Islas Cook");
		paises.add("Corea del Norte");
		paises.add("Corea del Sur");
		paises.add("Costa de Marfil");
		paises.add("Costa Rica");
		paises.add("Croacia");
		paises.add("Cuba");
		paises.add("Dinamarca");
		paises.add("Dominica");
		paises.add("República Dominicana");
		paises.add("Ecuador");
		paises.add("Egipto");
		paises.add("El Salvador");
		paises.add("Emiratos �rabes Unidos");
		paises.add("Eritrea");
		paises.add("Eslovaquia");
		paises.add("Eslovenia");
		paises.add("España");
		paises.add("Islas ultramarinas de Estados Unidos");
		paises.add("Estados Unidos");
		paises.add("Estonia");
		paises.add("Etiopía");
		paises.add("Islas Feroe");
		paises.add("Filipinas");
		paises.add("Finlandia");
		paises.add("Fiyi");
		paises.add("Francia");
		paises.add("Gabón");
		paises.add("Gambia");
		paises.add("Georgia");
		paises.add("Islas Georgias del Sur y Sandwich del Sur");
		paises.add("Ghana");
		paises.add("Gibraltar");
		paises.add("Granada");
		paises.add("Grecia");
		paises.add("Groenlandia");
		paises.add("Guadalupe");
		paises.add("Guam");
		paises.add("Guatemala");
		paises.add("Guayana Francesa");
		paises.add("Guinea");
		paises.add("Guinea Ecuatorial");
		paises.add("Guinea-Bissau");
		paises.add("Guyana");
		paises.add("Haití");
		paises.add("Islas Heard y McDonald");
		paises.add("Honduras");
		paises.add("Hong Kong");
		paises.add("Hungría");
		paises.add("India");
		paises.add("Indonesia");
		paises.add("Irán");
		paises.add("Iraq");
		paises.add("Irlanda");
		paises.add("Islandia");
		paises.add("Israel");
		paises.add("Italia");
		paises.add("Jamaica");
		paises.add("Japón");
		paises.add("Jordania");
		paises.add("Kazajstán");
		paises.add("Kenia");
		paises.add("Kirguistán");
		paises.add("Kiribati");
		paises.add("Kuwait");
		paises.add("Laos");
		paises.add("Lesotho");
		paises.add("Letonia");
		paises.add("Líbano");
		paises.add("Liberia");
		paises.add("Libia");
		paises.add("Liechtenstein");
		paises.add("Lituania");
		paises.add("Luxemburgo");
		paises.add("Macao");
		paises.add("ARY Macedonia");
		paises.add("Madagascar");
		paises.add("Malasia");
		paises.add("Malawi");
		paises.add("Maldivas");
		paises.add("Malí");
		paises.add("Malta");
		paises.add("Islas Malvinas");
		paises.add("Islas Marianas del Norte");
		paises.add("Marruecos");
		paises.add("Islas Marshall");
		paises.add("Martinica");
		paises.add("Mauricio");
		paises.add("Mauritania");
		paises.add("Mayotte");
		paises.add("México");
		paises.add("Micronesia");
		paises.add("Moldavia");
		paises.add("Mónaco");
		paises.add("Mongolia");
		paises.add("Montserrat");
		paises.add("Mozambique");
		paises.add("Myanmar");
		paises.add("Namibia");
		paises.add("Nauru");
		paises.add("Nepal");
		paises.add("Nicaragua");
		paises.add("Níger");
		paises.add("Nigeria");
		paises.add("Niue");
		paises.add("Isla Norfolk");
		paises.add("Noruega");
		paises.add("Nueva Caledonia");
		paises.add("Nueva Zelanda");
		paises.add("Omán");
		paises.add("Países Bajos");
		paises.add("Pakistán");
		paises.add("Palau");
		paises.add("Palestina");
		paises.add("Panamá");
		paises.add("Papúa Nueva Guinea");
		paises.add("Paraguay");
		paises.add("Perú");
		paises.add("Islas Pitcairn");
		paises.add("Polinesia Francesa");
		paises.add("Polonia");
		paises.add("Portugal");
		paises.add("Puerto Rico");
		paises.add("Qatar");
		paises.add("Reino Unido");
		paises.add("Reunión");
		paises.add("Ruanda");
		paises.add("Rumania");
		paises.add("Rusia");
		paises.add("Sahara Occidental");
		paises.add("Islas Salomón");
		paises.add("Samoa");
		paises.add("Samoa Americana");
		paises.add("San Cristóbal y Nevis");
		paises.add("San Marino");
		paises.add("San Pedro y Miquelón");
		paises.add("San Vicente y las Granadinas");
		paises.add("Santa Helena");
		paises.add("Santa Lucía");
		paises.add("Santo Tomé y Príncipe");
		paises.add("Senegal");
		paises.add("Serbia y Montenegro");
		paises.add("Seychelles");
		paises.add("Sierra Leona");
		paises.add("Singapur");
		paises.add("Siria");
		paises.add("Somalia");
		paises.add("Sri Lanka");
		paises.add("Suazilandia");
		paises.add("Sudáfrica");
		paises.add("Sudán");
		paises.add("Suecia");
		paises.add("Suiza");
		paises.add("Surinam");
		paises.add("Svalbard y Jan Mayen");
		paises.add("Tailandia");
		paises.add("Taiwán");
		paises.add("Tanzania");
		paises.add("Tayikistán");
		paises.add("Territorio Británico del Océano �ndico");
		paises.add("Territorios Australes Franceses");
		paises.add("Timor Oriental");
		paises.add("Togo");
		paises.add("Tokelau");
		paises.add("Tonga");
		paises.add("Trinidad y Tobago");
		paises.add("Túnez");
		paises.add("Islas Turcas y Caicos");
		paises.add("Turkmenistán");
		paises.add("Turquía");
		paises.add("Tuvalu");
		paises.add("Ucrania");
		paises.add("Uganda");
		paises.add("Uruguay");
		paises.add("Uzbekistán");
		paises.add("Vanuatu");
		paises.add("Venezuela");
		paises.add("Vietnam");
		paises.add("Islas Vírgenes Británicas");
		paises.add("Islas Vírgenes de los Estados Unidos");
		paises.add("Wallis y Futuna");
		paises.add("Yemen");
		paises.add("Yibuti");
		paises.add("Zambia");
		paises.add("Zimbabue");

		return paises;
	}
	/**
	 * Create the frame.
	 */
	public Receptor_OfiHotel(Factura factura,String error,InterfazFL_Opera interfaz) {
		super((Window)null);
		Receptor_OfiHotel.interfaz = interfaz;
		Receptor_OfiHotel.interfaz.setCancelado(false);
		setModal(true);
		Receptor_OfiHotel.factura = factura;
		if(Receptor_OfiHotel.factura.getReceptor()==null){
			Receptor_OfiHotel.factura.setReceptor(new Persona());
		}
		if(Receptor_OfiHotel.factura.getReceptor().getDocumento()==null){
			Receptor_OfiHotel.factura.getReceptor().setDocumento(new Documento());
		}
		persona = factura.getReceptor();
		
		
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 243);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblDepartamento = new JLabel("Departamento");
		lblDepartamento.setBounds(10, 11, 224, 14);
		contentPane.add(lblDepartamento);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(10, 38, 224, 14);
		contentPane.add(lblDireccion);
		
		direccion = new JTextField();
		direccion.setBounds(273, 38, 448, 20);
		contentPane.add(direccion);
		if(persona.getDireccion()!=null){
			direccion.setText(persona.getDireccion());
		}
		direccion.setColumns(10);
		
		JLabel lblDocumento = new JLabel("Documento");
		lblDocumento.setBounds(10, 63, 224, 14);
		contentPane.add(lblDocumento);
		
		documentoTipo = new JComboBox<String>();
		documentoTipo.setBounds(273, 63, 75, 20);
		documentoTipo.addItem("RUT");
		documentoTipo.addItem("CI");
		if(persona.getDocumento()!=null){
			switch (persona.getDocumento().getTipo()) {
			case 2:
				documentoTipo.setSelectedItem("RUT");
				break;
			case 3:
				documentoTipo.setSelectedItem("CI");
				break;

			default:
				break;
			}
		}
		contentPane.add(documentoTipo);
		
		String txtdocumento = "";
		if(persona.getDocumento()!=null){
			txtdocumento = persona.getDocumento().getDocumento(); 
		}
		documento = new JTextField();
		documento.setBounds(358, 63, 240, 20);
		documento.setText(txtdocumento);
		contentPane.add(documento);
		documento.setColumns(10);
		
		documentoPais = new JComboBox<String>();
		ArrayList<String> paises = getPaises();
		HashMap<String, String> hpaises = getHashPaises();
		for(String spais: paises){
			documentoPais.addItem(spais.toUpperCase());
		}
		String documentopaisdefecto = "UY";
		if(persona.getDocumento()!=null && persona.getDocumento().getPais()!=null && !persona.getDocumento().getPais().equals("")){
			documentopaisdefecto = persona.getPais().toUpperCase();
		}
		if(documentopaisdefecto.length()==2){
			documentopaisdefecto = hpaises.get(documentopaisdefecto.toUpperCase());
		}
		documentoPais.setSelectedItem(documentopaisdefecto);
		documentoPais.setBounds(608, 63, 113, 20);
		contentPane.add(documentoPais);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 88, 224, 14);
		contentPane.add(lblNombre);
		
		nombre = new JTextField();
		nombre.setBounds(273, 88, 448, 20);
		nombre.setText(persona.getNombre());
		contentPane.add(nombre);
		nombre.setColumns(10);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(10, 113, 224, 14);
		contentPane.add(lblPais);
		
		pais = new JComboBox<String>();
		pais.setBounds(273, 113, 448, 20);
		for(String spais: paises){
			pais.addItem(spais.toUpperCase());
		}
		String paisdefecto = "UY";
		if(persona.getPais()!=null && !persona.getPais().equals("")){
			paisdefecto = persona.getPais().toUpperCase();
		}
		if(paisdefecto.length()==2){
			paisdefecto = hpaises.get(paisdefecto.toUpperCase());
		}
		pais.setSelectedItem(paisdefecto);
		
		contentPane.add(pais);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Receptor_OfiHotel.factura.getReceptor().setDepartamento(txtDepartamento.getText());
				Receptor_OfiHotel.factura.getReceptor().setCiudad(txtDepartamento.getText());
				Receptor_OfiHotel.factura.getReceptor().setDireccion(direccion.getText());
				String dt = (String)documentoTipo.getSelectedItem();
				switch (dt) {
				case "RUT":
					Receptor_OfiHotel.factura.getReceptor().getDocumento().setTipo(2);
					if(Receptor_OfiHotel.factura.getTipo()==101){
						Receptor_OfiHotel.factura.setTipo(111);
					}
					if(Receptor_OfiHotel.factura.getTipo()==102){
						Receptor_OfiHotel.factura.setTipo(112);
					}
					if(Receptor_OfiHotel.factura.getTipo()==103){
						Receptor_OfiHotel.factura.setTipo(113);
					}
					break;
				case "CI":
					Receptor_OfiHotel.factura.getReceptor().getDocumento().setTipo(3);
					if(Receptor_OfiHotel.factura.getTipo()==111){
						Receptor_OfiHotel.factura.setTipo(101);
					}
					if(Receptor_OfiHotel.factura.getTipo()==112){
						Receptor_OfiHotel.factura.setTipo(102);
					}
					if(Receptor_OfiHotel.factura.getTipo()==113){
						Receptor_OfiHotel.factura.setTipo(103);
					}
					break;

				default:
					break;
				}
				Receptor_OfiHotel.factura.getReceptor().getDocumento().setDocumento(documento.getText());
				String dp = (String)documentoPais.getSelectedItem();
				HashMap<String, String> paises = Receptor_OfiHotel.getHashPaisesInverso();
				dp = paises.get(dp);
				Receptor_OfiHotel.factura.getReceptor().getDocumento().setPais(dp);
				Receptor_OfiHotel.factura.getReceptor().setNombre(nombre.getText());
				String p = (String)pais.getSelectedItem();
				p = paises.get(p);
				Receptor_OfiHotel.factura.getReceptor().setPais(p);
				dispose();
			}
		});
		btnAceptar.setBounds(632, 167, 89, 23);
		contentPane.add(btnAceptar);
		
		txtDepartamento = new JTextField();
		txtDepartamento.setBounds(273, 8, 448, 20);
		txtDepartamento.setText(persona.getDepartamento());
		contentPane.add(txtDepartamento);
		txtDepartamento.setColumns(10);
		
		JLabel lblError = new JLabel(error);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblError.setForeground(Color.RED);
		lblError.setBounds(273, 144, 448, 14);
		contentPane.add(lblError);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Receptor_OfiHotel.interfaz.setCancelado(true);
				dispose();
			}
		});
		btnCancelar.setBounds(273, 167, 89, 23);
		contentPane.add(btnCancelar);
	}
}
