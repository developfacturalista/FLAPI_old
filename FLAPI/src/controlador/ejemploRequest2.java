package controlador;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import org.json.JSONObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import bd.DBDriverPostgreSQL;
import bd.DBDriverPostgreSQLFacturaLista;
import bd.DBDriverPostgreSQLOpera;
import bd.DBDriverPostgreSQLWS;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class ejemploRequest2 {

	static String ultimoRefreshToken = "0";
	static String ultimoToken = "0";
	static String codigoIvaMinimo = "";
	static String codigoIvaBasico = "";
	static String codigoIvaExcento = "";
	static String idhotel="";
	static JsonArray revenueBucketInfo;

	public static void main(String args[]) throws Exception {
		// obtenerToken();
		// obtenerCodigo();
		// getReservationsPorFecha("2022-28-10");
		// getReservations();
		getDatosReserva("2");
		// getReservationInvoiceById("6487430274286");
		// getReservationsCheckedOut();

		// getFacturas();
	}

	public static reserva getDatosReserva(String idReserva) throws Exception {

		reserva reserva = new reserva();
		
		String jsonInvoice = capturarJson(idReserva);
		// TODO Auto-generated method stub
		//String jsonInvoice = getReservationInvoiceById(idReserva);
		String jsonInvoiceEfactura = "{\"DocumentInfo\":{\"HotelCode\":\"URYY\",\"BillNo\":\"1\",\"FolioType\":\"111\",\"TerminalId\":\"FLIP\",\"ProgramName\":\"\",\"FiscalFolioId\":\"144044\",\"OperaFiscalBillNo\":\"\",\"Application\":\"OPERA_CLOUD\",\"PropertyTaxNumber\":\"432123456\",\"BankName\":\"\",\"BankCode\":\"\",\"BankIdType\":\"\",\"BankIdCode\":\"\",\"BusinessDate\":\"2020-08-19\",\"BusinessDateTime\":\"2020-08-19T13:28:32\",\"CountryCode\":\"UY\",\"CountryName\":\"Uruguay\",\"Command\":\"INVOICE\",\"FiscalTimeoutPeriod\":\"\"},\"AdditionalInfo\":{\"BeforeSettlement\":{},\"ProfileOptions\":{\"NV\":[{\"Name\":\"eInvoice_Address\",\"Value\":\"araca@gmail.com\"}]},\"MappedValues\":{\"MappingType\":[{\"Type\":\"FISCAL_CNTRY_CITIES\"},{\"Type\":\"FISCAL_TRANSPORT\"}]}},\"UserDefinedFields\":{\"CharacterUDFs\":[{\"UDF\":[{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_DATE\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_NO\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_TIME\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_TERMINAL_ID\"},{\"Name\":\"FLIP_CONFIGMODE\",\"Value\":\"DEFAULT\"},{\"Name\":\"FLIP_DELIVERYLAYOUT\",\"Value\":\"FLIPPAYLOAD\"},{\"Name\":\"FLIP_DEPOSIT_TRANSFER\"},{\"Name\":\"FLIP_LOGLEVEL\",\"Value\":\"DEBUG\"},{\"Name\":\"FLIP_NO_PAYMENT_DESCRIPTION\"},{\"Name\":\"FLIP_NO_PAYMENT_SUBTYPE\"},{\"Name\":\"FLIP_NO_PAYMENT_TYPE\"},{\"Name\":\"FLIP_PARTNER_BRANCH_CODE\"},{\"Name\":\"FLIP_PARTNER_CHARGE_ID\"},{\"Name\":\"FLIP_PARTNER_CONTRIBUTOR\"},{\"Name\":\"FLIP_PARTNER_FISCAL_BILL_NO\",\"Value\":\"Y\"},{\"Name\":\"FLIP_PARTNER_FISCAL_INCENTIVE\"},{\"Name\":\"FLIP_PARTNER_FOLIO_TEXT\"},{\"Name\":\"FLIP_PARTNER_MODE\"},{\"Name\":\"FLIP_PARTNER_NETGROSS\"},{\"Name\":\"FLIP_PARTNER_PATTERN\"},{\"Name\":\"FLIP_PARTNER_SERIAL\"},{\"Name\":\"FLIP_PARTNER_SPECIAL_TAX\"},{\"Name\":\"FLIP_PARTNER_TAX1\"},{\"Name\":\"FLIP_PARTNER_TAX2\"},{\"Name\":\"FLIP_PARTNER_TAX3\"},{\"Name\":\"FLIP_PARTNER_TAX4\"},{\"Name\":\"FLIP_PARTNER_TAX5\"},{\"Name\":\"FLIP_PARTNER_TAX_LEVEL\"},{\"Name\":\"FLIP_PARTNER_TAX_QUALIFICATION\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE2\"},{\"Name\":\"FLIP_PERSON_IN_CHARGE\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS1\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS2\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS3\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS4\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS5\"},{\"Name\":\"FLIP_PROPERTY_CODE\",\"Value\":\"Recepcion1\"},{\"Name\":\"FLIP_PROPERTY_COUNTRY\"},{\"Name\":\"FLIP_PROPERTY_ECONOMIC_ID\",\"Value\":\"HOTELERIA\"},{\"Name\":\"FLIP_PROPERTY_ID\",\"Value\":\"2\"},{\"Name\":\"FLIP_RECEIVER_AUTHORITY\"},{\"Name\":\"FLIP_SENDER_AUTHORITY\"},{\"Name\":\"FLIP_SERVER_ADDRESS\",\"Value\":\"GOTERO-X380YOGA.AR.ORACLE.COM\"},{\"Name\":\"FLIP_TAX_FREE_PROPERTY\"},{\"Name\":\"FLIP_TEMPLATEFILE\",\"Value\":\"FLIPTemplateGenericJSON.xslt\"},{\"Name\":\"FLIP_UNIT_CODE\"}]}]},\"FolioInfo\":{\"FolioHeaderInfo\":{\"BillGenerationDate\":\"2020-08-19T13:28:31\",\"FolioType\":\"EFACTURA\",\"CreditBill\":false,\"FolioNo\":\"916199\",\"BillNo\":\"1\",\"InvoiceCurrencyCode\":\"USD\",\"Window\":\"1\",\"CashierNumber\":\"24\",\"FiscalFolioStatus\":\"OK\",\"LocalBillGenerationDate\":\"2020-08-19T13:28:31\",\"CollectingAgentTaxes\":{}},\"PayeeInfo\":{\"NameId\":1275660,\"LastName\":\"Araca la cana\",\"Passport\":\"\",\"Tax1No\":\"\",\"Tax2No\":\"\",\"BusinessId\":\"2\",\"NameTaxType\":\"RES\",\"PaymentDueDate\":\"2020-08-29\",\"UserDefinedFields\":{},\"IdentificationInfos\":{},\"KeywordInfos\":{},\"ArNumber\":\"ARAC-001\",\"Address\":{\"Address1\":\"Av caseros 3073\",\"AddresseeCountryDesc\":\"Uruguay\",\"Country\":\"UY\",\"IsoCode\":\"UY\",\"Primary\":true,\"Type\":\"AR ADDRESS\"},\"Phone\":{\"Number\":\"Av Caseros 3073\",\"Type\":\"BUSINESS\"},\"NameType\":\"COMPANY\"},\"Postings\":[{\"TrxNo\":1001367635,\"TrxCode\":\"2005\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":500.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:17:10\",\"LocalTrxDateTime\":\"2020-08-19T18:17:10\",\"NetAmount\":378.787878787879,\"GrossAmount\":500.0,\"GuestAccountDebit\":500.0,\"TranActionId\":1374496,\"FinDmlSeqNo\":1099729,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:17:10\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":10.0,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:17:10\",\"TrxNo\":1001367638,\"TrxType\":\"C\",\"UnitPrice\":37.88,\"FinDmlSeqNo\":1099730,\"NetAmount\":37.878787878788,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374496,\"TrxNoAddedBy\":1001367635},{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:17:10\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":22.0,\"TrxCode\":\"8505\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:17:10\",\"TrxNo\":1001367639,\"TrxType\":\"C\",\"UnitPrice\":83.33,\"FinDmlSeqNo\":1099731,\"NetAmount\":83.333333333333,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":1374496,\"TrxNoAddedBy\":1001367635}]}},{\"TrxNo\":1001367645,\"TrxCode\":\"8525\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":0.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:18:01\",\"LocalTrxDateTime\":\"2020-08-19T18:18:01\",\"NetAmount\":0.0,\"TrxNoAddedBy\":1001367643,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":1374500,\"FinDmlSeqNo\":1099735},{\"TrxNo\":1001367638,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":37.88,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:17:10\",\"LocalTrxDateTime\":\"2020-08-19T18:17:10\",\"NetAmount\":37.878787878788,\"TrxNoAddedBy\":1001367635,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374496,\"FinDmlSeqNo\":1099730},{\"TrxNo\":1001367639,\"TrxCode\":\"8505\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":83.33,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:17:10\",\"LocalTrxDateTime\":\"2020-08-19T18:17:10\",\"NetAmount\":83.333333333333,\"TrxNoAddedBy\":1001367635,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":1374496,\"FinDmlSeqNo\":1099731},{\"TrxNo\":1001367643,\"TrxCode\":\"2015\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":300.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:18:01\",\"LocalTrxDateTime\":\"2020-08-19T18:18:01\",\"NetAmount\":300.0,\"GrossAmount\":300.0,\"GuestAccountDebit\":300.0,\"TranActionId\":1374500,\"FinDmlSeqNo\":1099734,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:18:01\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":0.0,\"TrxCode\":\"8525\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:18:01\",\"TrxNo\":1001367645,\"TrxType\":\"C\",\"UnitPrice\":0.0,\"FinDmlSeqNo\":1099735,\"NetAmount\":0.0,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":1374500,\"TrxNoAddedBy\":1001367643}]}},{\"TrxNo\":1001367632,\"TrxCode\":\"1000\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":1000.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:16:50\",\"LocalTrxDateTime\":\"2020-08-19T18:16:50\",\"NetAmount\":1000.0,\"GrossAmount\":1000.0,\"GuestAccountDebit\":1000.0,\"TranActionId\":1374494,\"FinDmlSeqNo\":1099727,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:16:50\",\"Quantity\":1.0,\"TaxInclusive\":false,\"TaxRate\":10.0,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:16:50\",\"TrxNo\":1001367634,\"TrxType\":\"C\",\"UnitPrice\":100.0,\"FinDmlSeqNo\":1099728,\"GrossAmount\":100.0,\"GuestAccountDebit\":100.0,\"NetAmount\":100.0,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374494,\"TrxNoAddedBy\":1001367632}]}},{\"TrxNo\":1001367642,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":36.36,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:17:51\",\"LocalTrxDateTime\":\"2020-08-19T18:17:51\",\"NetAmount\":36.363636363636,\"TrxNoAddedBy\":1001367640,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374498,\"FinDmlSeqNo\":1099733},{\"TrxNo\":1001367634,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":100.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:16:50\",\"LocalTrxDateTime\":\"2020-08-19T18:16:50\",\"NetAmount\":100.0,\"GrossAmount\":100.0,\"TrxNoAddedBy\":1001367632,\"Reference\":\"[Add: 10%Prices.(B)]\",\"GuestAccountDebit\":100.0,\"TranActionId\":1374494,\"FinDmlSeqNo\":1099728},{\"TrxNo\":1001367640,\"TrxCode\":\"3000\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":400.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:17:51\",\"LocalTrxDateTime\":\"2020-08-19T18:17:51\",\"NetAmount\":363.636363636364,\"GrossAmount\":400.0,\"GuestAccountDebit\":400.0,\"TranActionId\":1374498,\"FinDmlSeqNo\":1099732,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:17:51\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":10.0,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:17:51\",\"TrxNo\":1001367642,\"TrxType\":\"C\",\"UnitPrice\":36.36,\"FinDmlSeqNo\":1099733,\"NetAmount\":36.363636363636,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374498,\"TrxNoAddedBy\":1001367640}]}},{\"TrxNo\":1001369131,\"TrxCode\":\"9000\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"FC\",\"UnitPrice\":2300.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T13:28:14\",\"LocalTrxDateTime\":\"2020-08-19T13:28:14\",\"GuestAccountCredit\":2300.0,\"TranActionId\":1375750,\"FinDmlSeqNo\":1100719,\"ReceiptNumber\":1,\"ReceiptType\":\"PAR\"}],\"RevenueBucketInfo\":[{\"BucketCode\":\"CASH\",\"BucketType\":\"FLIP_PAY_SUBTYPE\",\"BucketValue\":\"1\",\"Description\":\"Cash Payments\",\"BucketCodeTotalGross\":2300.0,\"TrxCode\":[\"9000\"]},{\"BucketCode\":\"GROUP_1\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Habitaciones\",\"BucketCodeTotalGross\":1000.0,\"TrxCode\":[\"1000\"]},{\"BucketCode\":\"GROUP_10\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Food Lunch\",\"BucketCodeTotalGross\":500.0,\"TrxCode\":[\"2005\"]},{\"BucketCode\":\"GROUP_4\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Tips\",\"BucketCodeTotalGross\":300.0,\"TrxCode\":[\"2015\"]},{\"BucketCode\":\"GROUP_5\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Beverage Breakfast\",\"BucketCodeTotalGross\":400.0,\"TrxCode\":[\"3000\"]},{\"BucketCode\":\"TAX_2\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"2\",\"Description\":\"Iva Tasa Minima\",\"BucketCodeTotalGross\":100.0,\"TrxCode\":[\"8500\"]},{\"BucketCode\":\"TAX_3\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"3\",\"Description\":\"IVA Tasa Basica\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8505\"]},{\"BucketCode\":\"TAX_5\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"6\",\"Description\":\"Producto o Servicio no facturable\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8525\"]}],\"TotalInfo\":{\"NetAmount\":2042.42424242424,\"GrossAmount\":2300.0,\"NonTaxableAmount\":0.0,\"PaidOut\":0.0,\"Taxes\":{\"Tax\":[{\"Name\":\"1\",\"Value\":174.242424242424,\"NetAmount\":1742.424242424243,\"Percent\":\"10.00\",\"Amount\":\"\"},{\"Name\":\"2\",\"Value\":83.333333333333,\"NetAmount\":378.787878787879,\"Percent\":\"22.00\",\"Amount\":\"\"},{\"Name\":\"6\",\"Value\":0.0,\"NetAmount\":300.0,\"Percent\":\"0.00\",\"Amount\":\"\"}]}},\"TrxInfo\":[{\"HotelCode\":\"URYY\",\"Group\":\"ROOM\",\"SubGroup\":\"ROOM\",\"Code\":\"1000\",\"TrxType\":\"C\",\"Description\":\"Accommodation - Tax Inclusive\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"L\"},{\"HotelCode\":\"URYY\",\"Group\":\"FB\",\"SubGroup\":\"FOOD\",\"Code\":\"2005\",\"TrxType\":\"C\",\"Description\":\"Food Inclusive  - Lunch\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"F\"},{\"HotelCode\":\"URYY\",\"Group\":\"TIPS\",\"SubGroup\":\"TIPS\",\"Code\":\"2015\",\"TrxType\":\"C\",\"Description\":\"Food Inclusive  - Gratuities\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"N\"},{\"HotelCode\":\"URYY\",\"Group\":\"FB\",\"SubGroup\":\"BEVERAGE\",\"Code\":\"3000\",\"TrxType\":\"C\",\"Description\":\"Beverage Inclusive - Breakfast\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"F\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8500\",\"TrxType\":\"C\",\"TaxCodeNo\":1,\"Description\":\"IVA 10%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8505\",\"TrxType\":\"C\",\"TaxCodeNo\":2,\"Description\":\"IVA 22%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8525\",\"TrxType\":\"C\",\"TaxCodeNo\":6,\"Description\":\"No billable\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"PAYMENT\",\"SubGroup\":\"CASH\",\"Code\":\"9000\",\"TrxType\":\"FC\",\"Description\":\"Cash\",\"Articles\":{},\"TranslatedDescriptions\":{}}]},\"HotelInfo\":{\"HotelCode\":\"URYY\",\"HotelName\":\"OPERA Cloud Uruguay Property\",\"Address\":{\"Country\":\"UY\"},\"LocalCurrency\":\"UYU\",\"CurrencySymbol\":\"$U\",\"Decimals\":\"2\",\"ExchangeRates\":{\"ExchangeRateInfo\":[{\"CurrencyCode\":\"USD\"}]},\"PropertyDateTime\":\"2021-06-28T13:28:32\"},\"ReservationInfo\":{\"ConfirmationNo\":\"1522159\",\"ResvNameID\":\"936813\",\"ArrivalDate\":\"2020-08-19\",\"NumberOfNights\":\"1\",\"DepartureDate\":\"2020-08-20\",\"NumAdults\":\"1\",\"NumChilds\":\"0\",\"GuestInfo\":{\"NameId\":1275660,\"LastName\":\"Araca la cana\",\"Passport\":\"\",\"Tax1No\":\"\",\"Tax2No\":\"\",\"BusinessId\":\"2\",\"NameTaxType\":\"RES\",\"PaymentDueDate\":\"2020-08-29\",\"UserDefinedFields\":{},\"IdentificationInfos\":{},\"KeywordInfos\":{},\"ArNumber\":\"ARAC-001\",\"Address\":{\"Address1\":\"Av caseros 3073\",\"AddresseeCountryDesc\":\"Uruguay\",\"Country\":\"UY\",\"IsoCode\":\"UY\",\"Primary\":true,\"Type\":\"AR ADDRESS\"},\"Phone\":{\"Number\":\"Av Caseros 3073\",\"Type\":\"BUSINESS\"}},\"NameTaxType\":\"RES\",\"RoomRate\":1100.0,\"RatePlanCode\":\"RACK\",\"RoomNumber\":\"103\",\"RoomClass\":\"ALL\",\"RoomType\":\"SINGLE\",\"NumberOfRooms\":\"1\",\"Guarantee\":\"CHECKED IN\",\"MarketCode\":\"COR\",\"ResStatus\":\"CHECKED IN\",\"UserDefinedFields\":{}},\"FiscalFolioUserInfo\":{\"AppUser\":\"GASTONVAF@VAFISCAL\",\"AppUserId\":\"51457\",\"EmployeeNumber\":\"\",\"CashierId\":\"\"}}";
		String jsonInvoiceEtixket = "{\"DocumentInfo\":{\"HotelCode\":\"URYY\",\"BillNo\":\"2\",\"FolioType\":\"101\",\"TerminalId\":\"0001\",\"ProgramName\":\"\",\"FiscalFolioId\":\"144047\",\"OperaFiscalBillNo\":\"\",\"Application\":\"OPERA_CLOUD\",\"PropertyTaxNumber\":\"432123456\",\"BankName\":\"\",\"BankCode\":\"\",\"BankIdType\":\"\",\"BankIdCode\":\"\",\"BusinessDate\":\"2020-08-19\",\"BusinessDateTime\":\"2020-08-19T13:53:32\",\"CountryCode\":\"UY\",\"CountryName\":\"Uruguay\",\"Command\":\"INVOICE\",\"FiscalTimeoutPeriod\":\"\"},\"AdditionalInfo\":{\"BeforeSettlement\":{},\"ProfileOptions\":{},\"MappedValues\":{\"MappingType\":[{\"Type\":\"FISCAL_CNTRY_CITIES\"},{\"Type\":\"FISCAL_TRANSPORT\"}]}},\"UserDefinedFields\":{\"CharacterUDFs\":[{\"UDF\":[{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_DATE\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_NO\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_TIME\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_TERMINAL_ID\"},{\"Name\":\"FLIP_CONFIGMODE\",\"Value\":\"DEFAULT\"},{\"Name\":\"FLIP_DELIVERYLAYOUT\",\"Value\":\"FLIPPAYLOAD\"},{\"Name\":\"FLIP_DEPOSIT_TRANSFER\"},{\"Name\":\"FLIP_LOGLEVEL\",\"Value\":\"DEBUG\"},{\"Name\":\"FLIP_NO_PAYMENT_DESCRIPTION\"},{\"Name\":\"FLIP_NO_PAYMENT_SUBTYPE\"},{\"Name\":\"FLIP_NO_PAYMENT_TYPE\"},{\"Name\":\"FLIP_PARTNER_BRANCH_CODE\"},{\"Name\":\"FLIP_PARTNER_CHARGE_ID\"},{\"Name\":\"FLIP_PARTNER_CONTRIBUTOR\"},{\"Name\":\"FLIP_PARTNER_FISCAL_BILL_NO\",\"Value\":\"Y\"},{\"Name\":\"FLIP_PARTNER_FISCAL_INCENTIVE\"},{\"Name\":\"FLIP_PARTNER_FOLIO_TEXT\"},{\"Name\":\"FLIP_PARTNER_MODE\"},{\"Name\":\"FLIP_PARTNER_NETGROSS\"},{\"Name\":\"FLIP_PARTNER_PATTERN\"},{\"Name\":\"FLIP_PARTNER_SERIAL\"},{\"Name\":\"FLIP_PARTNER_SPECIAL_TAX\"},{\"Name\":\"FLIP_PARTNER_TAX1\"},{\"Name\":\"FLIP_PARTNER_TAX2\"},{\"Name\":\"FLIP_PARTNER_TAX3\"},{\"Name\":\"FLIP_PARTNER_TAX4\"},{\"Name\":\"FLIP_PARTNER_TAX5\"},{\"Name\":\"FLIP_PARTNER_TAX_LEVEL\"},{\"Name\":\"FLIP_PARTNER_TAX_QUALIFICATION\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE2\"},{\"Name\":\"FLIP_PERSON_IN_CHARGE\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS1\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS2\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS3\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS4\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS5\"},{\"Name\":\"FLIP_PROPERTY_CODE\",\"Value\":\"Recepcion1\"},{\"Name\":\"FLIP_PROPERTY_COUNTRY\"},{\"Name\":\"FLIP_PROPERTY_ECONOMIC_ID\",\"Value\":\"HOTELERIA\"},{\"Name\":\"FLIP_PROPERTY_ID\",\"Value\":\"2\"},{\"Name\":\"FLIP_RECEIVER_AUTHORITY\"},{\"Name\":\"FLIP_SENDER_AUTHORITY\"},{\"Name\":\"FLIP_SERVER_ADDRESS\",\"Value\":\"GOTERO-X380YOGA.AR.ORACLE.COM\"},{\"Name\":\"FLIP_TAX_FREE_PROPERTY\"},{\"Name\":\"FLIP_TEMPLATEFILE\",\"Value\":\"FLIPTemplateGenericJSON.xslt\"},{\"Name\":\"FLIP_UNIT_CODE\"}]}]},\"FiscalTerminalInfo\":{\"TerminalAddressAndPort\":\"Front1\",\"TerminalID\":\"0001\"},\"FolioInfo\":{\"FolioHeaderInfo\":{\"BillGenerationDate\":\"2020-08-19T13:53:32\",\"FolioType\":\"101\",\"CreditBill\":false,\"FolioNo\":\"916200\",\"BillNo\":\"2\",\"InvoiceCurrencyCode\":\"USD\",\"Window\":\"1\",\"CashierNumber\":\"24\",\"FiscalFolioStatus\":\"OK\",\"LocalBillGenerationDate\":\"2020-08-19T13:53:32\",\"CollectingAgentTaxes\":{}},\"PayeeInfo\":{\"NameId\":1275655,\"FirstName\":\"Sebastian\",\"LastName\":\"Teysera\",\"Passport\":\"\",\"Tax1No\":\"XXXXXXXXXXXXX\",\"Tax2No\":\"XXXXXX\",\"NameTaxType\":\"RTA\",\"PaymentDueDate\":\"2020-08-29\",\"UserDefinedFields\":{},\"IdentificationInfos\":{\"IdentificationInfo\":[{\"IdType\":\"2\",\"IdNumber\":\"XXXXXXXXXXXXX\",\"Primary\":true}]},\"KeywordInfos\":{},\"Email\":\"lavela@gmail.com\",\"Address\":{\"Address1\":\"Calle Perez Castellano 1495\",\"AddresseeCountryDesc\":\"Uruguay\",\"City\":\"Montevideo\",\"Country\":\"UY\",\"IsoCode\":\"UY\",\"Primary\":true,\"Type\":\"HOME\"},\"Phone\":{\"Number\":\"Calle Perez Castellano 1495\",\"Type\":\"HOME\"},\"Language\":\"E\",\"NameType\":\"INDIVIDUAL\"},\"Postings\":[{\"TrxNo\":1001367621,\"TrxCode\":\"1000\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":1000.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:12:49\",\"LocalTrxDateTime\":\"2020-08-19T18:12:49\",\"NetAmount\":1000.0,\"GrossAmount\":1000.0,\"GuestAccountDebit\":1000.0,\"TranActionId\":1374487,\"FinDmlSeqNo\":1099720,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:12:49\",\"Quantity\":1.0,\"TaxInclusive\":false,\"TaxRate\":10.0,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:12:49\",\"TrxNo\":1001367623,\"TrxType\":\"C\",\"UnitPrice\":100.0,\"FinDmlSeqNo\":1099721,\"GrossAmount\":100.0,\"GuestAccountDebit\":100.0,\"NetAmount\":100.0,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374487,\"TrxNoAddedBy\":1001367621}]}},{\"TrxNo\":1001367623,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":100.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:12:49\",\"LocalTrxDateTime\":\"2020-08-19T18:12:49\",\"NetAmount\":100.0,\"GrossAmount\":100.0,\"TrxNoAddedBy\":1001367621,\"Reference\":\"[Add: 10%Prices.(B)]\",\"GuestAccountDebit\":100.0,\"TranActionId\":1374487,\"FinDmlSeqNo\":1099721},{\"TrxNo\":1001367629,\"TrxCode\":\"3015\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":200.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:13:45\",\"LocalTrxDateTime\":\"2020-08-19T18:13:45\",\"NetAmount\":200.0,\"GrossAmount\":200.0,\"GuestAccountDebit\":200.0,\"TranActionId\":1374491,\"FinDmlSeqNo\":1099725,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:13:45\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":0.0,\"TrxCode\":\"8525\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:13:45\",\"TrxNo\":1001367631,\"TrxType\":\"C\",\"UnitPrice\":0.0,\"FinDmlSeqNo\":1099726,\"NetAmount\":0.0,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":1374491,\"TrxNoAddedBy\":1001367629}]}},{\"TrxNo\":1001367631,\"TrxCode\":\"8525\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":0.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:13:45\",\"LocalTrxDateTime\":\"2020-08-19T18:13:45\",\"NetAmount\":0.0,\"TrxNoAddedBy\":1001367629,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":1374491,\"FinDmlSeqNo\":1099726},{\"TrxNo\":1001369146,\"TrxCode\":\"9000\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"FC\",\"UnitPrice\":2100.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T13:51:12\",\"LocalTrxDateTime\":\"2020-08-19T13:51:12\",\"GuestAccountCredit\":2100.0,\"TranActionId\":1375762,\"FinDmlSeqNo\":1100734,\"ReceiptNumber\":2,\"ReceiptType\":\"PAR\"},{\"TrxNo\":1001367624,\"TrxCode\":\"2005\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":800.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:13:02\",\"LocalTrxDateTime\":\"2020-08-19T18:13:02\",\"NetAmount\":606.060606060606,\"GrossAmount\":800.0,\"GuestAccountDebit\":800.0,\"TranActionId\":1374489,\"FinDmlSeqNo\":1099722,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:13:02\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":10.0,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:13:02\",\"TrxNo\":1001367627,\"TrxType\":\"C\",\"UnitPrice\":60.61,\"FinDmlSeqNo\":1099723,\"NetAmount\":60.606060606061,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374489,\"TrxNoAddedBy\":1001367624},{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:13:02\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":22.0,\"TrxCode\":\"8505\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:13:02\",\"TrxNo\":1001367628,\"TrxType\":\"C\",\"UnitPrice\":133.33,\"FinDmlSeqNo\":1099724,\"NetAmount\":133.333333333333,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":1374489,\"TrxNoAddedBy\":1001367624}]}},{\"TrxNo\":1001367627,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":60.61,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:13:02\",\"LocalTrxDateTime\":\"2020-08-19T18:13:02\",\"NetAmount\":60.606060606061,\"TrxNoAddedBy\":1001367624,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374489,\"FinDmlSeqNo\":1099723},{\"TrxNo\":1001367628,\"TrxCode\":\"8505\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":133.33,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:13:02\",\"LocalTrxDateTime\":\"2020-08-19T18:13:02\",\"NetAmount\":133.333333333333,\"TrxNoAddedBy\":1001367624,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":1374489,\"FinDmlSeqNo\":1099724}],\"RevenueBucketInfo\":[{\"BucketCode\":\"CASH\",\"BucketType\":\"FLIP_PAY_SUBTYPE\",\"BucketValue\":\"1\",\"Description\":\"Cash Payments\",\"BucketCodeTotalGross\":2100.0,\"TrxCode\":[\"9000\"]},{\"BucketCode\":\"GROUP_1\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Habitaciones\",\"BucketCodeTotalGross\":1000.0,\"TrxCode\":[\"1000\"]},{\"BucketCode\":\"GROUP_10\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Food Lunch\",\"BucketCodeTotalGross\":800.0,\"TrxCode\":[\"2005\"]},{\"BucketCode\":\"GROUP_4\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Tips\",\"BucketCodeTotalGross\":200.0,\"TrxCode\":[\"3015\"]},{\"BucketCode\":\"TAX_2\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"2\",\"Description\":\"Iva Tasa Minima\",\"BucketCodeTotalGross\":100.0,\"TrxCode\":[\"8500\"]},{\"BucketCode\":\"TAX_3\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"3\",\"Description\":\"IVA Tasa Basica\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8505\"]},{\"BucketCode\":\"TAX_5\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"6\",\"Description\":\"Producto o Servicio no facturable\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8525\"]}],\"TotalInfo\":{\"NetAmount\":1806.06060606061,\"GrossAmount\":2100.0,\"NonTaxableAmount\":0.0,\"PaidOut\":0.0,\"Taxes\":{\"Tax\":[{\"Name\":\"1\",\"Value\":160.606060606061,\"NetAmount\":1606.060606060606,\"Percent\":\"10.00\",\"Amount\":\"\"},{\"Name\":\"2\",\"Value\":133.333333333333,\"NetAmount\":606.060606060606,\"Percent\":\"22.00\",\"Amount\":\"\"},{\"Name\":\"6\",\"Value\":0.0,\"NetAmount\":200.0,\"Percent\":\"0.00\",\"Amount\":\"\"}]}},\"TrxInfo\":[{\"HotelCode\":\"URYY\",\"Group\":\"ROOM\",\"SubGroup\":\"ROOM\",\"Code\":\"1000\",\"TrxType\":\"C\",\"Description\":\"Accommodation - Tax Inclusive\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"L\"},{\"HotelCode\":\"URYY\",\"Group\":\"FB\",\"SubGroup\":\"FOOD\",\"Code\":\"2005\",\"TrxType\":\"C\",\"Description\":\"Food Inclusive  - Lunch\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"F\"},{\"HotelCode\":\"URYY\",\"Group\":\"TIPS\",\"SubGroup\":\"TIPS\",\"Code\":\"3015\",\"TrxType\":\"C\",\"Description\":\"Beverage Inclusive - Gratuities\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"N\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8500\",\"TrxType\":\"C\",\"TaxCodeNo\":1,\"Description\":\"IVA 10%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8505\",\"TrxType\":\"C\",\"TaxCodeNo\":2,\"Description\":\"IVA 22%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8525\",\"TrxType\":\"C\",\"TaxCodeNo\":6,\"Description\":\"No billable\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"PAYMENT\",\"SubGroup\":\"CASH\",\"Code\":\"9000\",\"TrxType\":\"FC\",\"Description\":\"Cash\",\"Articles\":{},\"TranslatedDescriptions\":{}}]},\"HotelInfo\":{\"HotelCode\":\"URYY\",\"HotelName\":\"OPERA Cloud Uruguay Property\",\"Address\":{\"Country\":\"UY\"},\"LocalCurrency\":\"UYU\",\"CurrencySymbol\":\"$U\",\"Decimals\":\"2\",\"ExchangeRates\":{\"ExchangeRateInfo\":[{\"CurrencyCode\":\"USD\"}]},\"PropertyDateTime\":\"2021-06-28T13:53:32\"},\"ReservationInfo\":{\"ConfirmationNo\":\"1522166\",\"ResvNameID\":\"936814\",\"ArrivalDate\":\"2020-08-19\",\"NumberOfNights\":\"2\",\"DepartureDate\":\"2020-08-21\",\"NumAdults\":\"1\",\"NumChilds\":\"0\",\"GuestInfo\":{\"NameId\":1275655,\"FirstName\":\"Sebastian\",\"LastName\":\"Teysera\",\"Passport\":\"\",\"Tax1No\":\"XXXXXXXXXXXXX\",\"Tax2No\":\"XXXXXX\",\"NameTaxType\":\"RTA\",\"PaymentDueDate\":\"2020-08-29\",\"UserDefinedFields\":{},\"IdentificationInfos\":{\"IdentificationInfo\":[{\"IdType\":\"2\",\"IdNumber\":\"XXXXXXXXXXXXX\",\"Primary\":true}]},\"KeywordInfos\":{},\"Email\":\"lavela@gmail.com\",\"Address\":{\"Address1\":\"Calle Perez Castellano 1495\",\"AddresseeCountryDesc\":\"Uruguay\",\"City\":\"Montevideo\",\"Country\":\"UY\",\"IsoCode\":\"UY\",\"Primary\":true,\"Type\":\"HOME\"},\"Phone\":{\"Number\":\"Calle Perez Castellano 1495\",\"Type\":\"HOME\"}},\"NameTaxType\":\"RTA\",\"RoomRate\":1800.0,\"RatePlanCode\":\"BKFINC\",\"RoomNumber\":\"101\",\"RoomClass\":\"ALL\",\"RoomType\":\"SINGLE\",\"NumberOfRooms\":\"1\",\"Guarantee\":\"CHECKED IN\",\"MarketCode\":\"COR\",\"ResStatus\":\"CHECKED IN\",\"UserDefinedFields\":{}},\"FiscalFolioUserInfo\":{\"AppUser\":\"GASTONVAF@VAFISCAL\",\"AppUserId\":\"51457\",\"EmployeeNumber\":\"\",\"CashierId\":\"\"}}";
		String jsonInvoiceNC = "{\"DocumentInfo\":{\"HotelCode\":\"URYY\",\"BillNo\":\"1\",\"FolioType\":\"102\",\"TerminalId\":\"0001\",\"ProgramName\":\"\",\"FiscalFolioId\":\"144049\",\"OperaFiscalBillNo\":\"\",\"Application\":\"OPERA_CLOUD\",\"PropertyTaxNumber\":\"432123456\",\"BankName\":\"\",\"BankCode\":\"\",\"BankIdType\":\"\",\"BankIdCode\":\"\",\"BusinessDate\":\"2020-08-19\",\"BusinessDateTime\":\"2020-08-19T14:13:06\",\"CountryCode\":\"UY\",\"CountryName\":\"Uruguay\",\"Command\":\"INVOICE\",\"FiscalTimeoutPeriod\":\"\"},\"AdditionalInfo\":{\"BeforeSettlement\":{},\"ProfileOptions\":{},\"MappedValues\":{\"MappingType\":[{\"Type\":\"FISCAL_CNTRY_CITIES\"}]}},\"UserDefinedFields\":{\"CharacterUDFs\":[{\"UDF\":[{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_DATE\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_NO\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_TIME\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_TERMINAL_ID\"},{\"Name\":\"FLIP_CONFIGMODE\",\"Value\":\"DEFAULT\"},{\"Name\":\"FLIP_DELIVERYLAYOUT\",\"Value\":\"FLIPPAYLOAD\"},{\"Name\":\"FLIP_DEPOSIT_TRANSFER\"},{\"Name\":\"FLIP_LOGLEVEL\",\"Value\":\"DEBUG\"},{\"Name\":\"FLIP_NO_PAYMENT_DESCRIPTION\"},{\"Name\":\"FLIP_NO_PAYMENT_SUBTYPE\"},{\"Name\":\"FLIP_NO_PAYMENT_TYPE\"},{\"Name\":\"FLIP_PARTNER_BRANCH_CODE\"},{\"Name\":\"FLIP_PARTNER_CHARGE_ID\"},{\"Name\":\"FLIP_PARTNER_CONTRIBUTOR\"},{\"Name\":\"FLIP_PARTNER_FISCAL_BILL_NO\",\"Value\":\"Y\"},{\"Name\":\"FLIP_PARTNER_FISCAL_INCENTIVE\"},{\"Name\":\"FLIP_PARTNER_FOLIO_TEXT\"},{\"Name\":\"FLIP_PARTNER_MODE\"},{\"Name\":\"FLIP_PARTNER_NETGROSS\"},{\"Name\":\"FLIP_PARTNER_PATTERN\"},{\"Name\":\"FLIP_PARTNER_SERIAL\"},{\"Name\":\"FLIP_PARTNER_SPECIAL_TAX\"},{\"Name\":\"FLIP_PARTNER_TAX1\"},{\"Name\":\"FLIP_PARTNER_TAX2\"},{\"Name\":\"FLIP_PARTNER_TAX3\"},{\"Name\":\"FLIP_PARTNER_TAX4\"},{\"Name\":\"FLIP_PARTNER_TAX5\"},{\"Name\":\"FLIP_PARTNER_TAX_LEVEL\"},{\"Name\":\"FLIP_PARTNER_TAX_QUALIFICATION\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE2\"},{\"Name\":\"FLIP_PERSON_IN_CHARGE\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS1\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS2\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS3\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS4\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS5\"},{\"Name\":\"FLIP_PROPERTY_CODE\",\"Value\":\"Recepcion1\"},{\"Name\":\"FLIP_PROPERTY_COUNTRY\"},{\"Name\":\"FLIP_PROPERTY_ECONOMIC_ID\",\"Value\":\"HOTELERIA\"},{\"Name\":\"FLIP_PROPERTY_ID\",\"Value\":\"2\"},{\"Name\":\"FLIP_RECEIVER_AUTHORITY\"},{\"Name\":\"FLIP_SENDER_AUTHORITY\"},{\"Name\":\"FLIP_SERVER_ADDRESS\",\"Value\":\"GOTERO-X380YOGA.AR.ORACLE.COM\"},{\"Name\":\"FLIP_TAX_FREE_PROPERTY\"},{\"Name\":\"FLIP_TEMPLATEFILE\",\"Value\":\"FLIPTemplateGenericJSON.xslt\"},{\"Name\":\"FLIP_UNIT_CODE\"}]}]},\"FiscalTerminalInfo\":{\"TerminalAddressAndPort\":\"Front1\",\"TerminalID\":\"0001\"},\"FolioInfo\":{\"FolioHeaderInfo\":{\"BillGenerationDate\":\"2020-08-19T14:13:06\",\"FolioType\":\"102\",\"CreditBill\":true,\"FolioNo\":\"917452\",\"BillNo\":\"1\",\"InvoiceCurrencyCode\":\"USD\",\"Window\":\"1\",\"FiscalFolioStatus\":\"OK\",\"LocalBillGenerationDate\":\"2020-08-19T14:13:06\",\"OriginalBillNo\":\"1013\",\"AssociatedBillNo\":\"3\",\"AssociatedBillGenerationDate\":\"2020-08-19\",\"AssociatedBillFiscalBillNo\":\"12345\",\"AssociatedBillFolioType\":\"101\",\"AssociatedFiscalBillDate\":\"2020-08-19\",\"AssociatedFiscalBillTime\":\"14:09:14\",\"AssociatedBillFiscalBillGenerationDate\":\"2021-06-28\",\"AssociatedBillFiscalBillGenerationTime\":\"09:09:18\",\"AssociatedTerminal\":\"OPERA9TERMINAL\",\"AssociatedFiscalTerminalInfo\":{\"TerminalID\":\"0001\"},\"CollectingAgentTaxes\":{},\"LocalAssociatedBillFiscalBillGenerationTime\":\"09:09:18\",\"LocalAssociatedFiscalBillTime\":\"14:09:14\"},\"PayeeInfo\":{\"NameId\":1275658,\"FirstName\":\"Emiliano\",\"LastName\":\"Brancciari\",\"Passport\":\"\",\"Tax1No\":\"XXXXXXXXXX\",\"Tax2No\":\"XXXXXXXXXXX\",\"NameTaxType\":\"NOR\",\"PaymentDueDate\":\"2020-08-29\",\"UserDefinedFields\":{},\"IdentificationInfos\":{\"IdentificationInfo\":[{\"IdType\":\"6\",\"IdNumber\":\"XXXXXXX\",\"Primary\":true}]},\"KeywordInfos\":{},\"Email\":\"notevaagustar@gmail.com\",\"Address\":{\"Address1\":\"scalabrini Ortiz\",\"Address2\":\"134\",\"AddresseeCountryDesc\":\"Argentina\",\"Country\":\"AR\",\"IsoCode\":\"AR\",\"Primary\":true,\"Type\":\"HOME\"},\"Phone\":{\"Number\":\"scalabrini ortiz\",\"Type\":\"HOME\"},\"Language\":\"E\",\"NameType\":\"INDIVIDUAL\"},\"Postings\":[{\"TrxNo\":1001369148,\"TrxCode\":\"1000\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":-1000.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T14:13:06\",\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"NetAmount\":-1000.0,\"GrossAmount\":-1000.0,\"GuestAccountDebit\":-1000.0,\"TranActionId\":1374481,\"FinDmlSeqNo\":1100736,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"Quantity\":1.0,\"TaxInclusive\":false,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T14:13:06\",\"TrxNo\":1001369149,\"TrxType\":\"C\",\"UnitPrice\":0.0,\"FinDmlSeqNo\":1100737,\"GrossAmount\":0.0,\"GuestAccountDebit\":0.0,\"NetAmount\":0.0,\"Reference\":\"[Add: 0%Prices.(B)]\",\"TranActionId\":1374481,\"TrxNoAddedBy\":1001369148}]}},{\"TrxNo\":1001369149,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":0.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T14:13:06\",\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"NetAmount\":0.0,\"GrossAmount\":0.0,\"TrxNoAddedBy\":1001369148,\"Reference\":\"[Add: 0%Prices.(B)]\",\"GuestAccountDebit\":0.0,\"TranActionId\":1374481,\"FinDmlSeqNo\":1100737},{\"TrxNo\":1001369150,\"TrxCode\":\"2005\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":-100.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T14:13:06\",\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"NetAmount\":-81.967213114754,\"GrossAmount\":-100.0,\"GuestAccountDebit\":-100.0,\"TranActionId\":1374483,\"FinDmlSeqNo\":1100738,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T14:13:06\",\"TrxNo\":1001369151,\"TrxType\":\"C\",\"UnitPrice\":0.0,\"FinDmlSeqNo\":1100739,\"NetAmount\":0.0,\"Reference\":\"[Add: 0%Prices.(B)]\",\"TranActionId\":1374483,\"TrxNoAddedBy\":1001369150},{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TrxCode\":\"8505\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T14:13:06\",\"TrxNo\":1001369152,\"TrxType\":\"C\",\"UnitPrice\":-18.03,\"FinDmlSeqNo\":1100740,\"NetAmount\":-18.032786885246,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":1374483,\"TrxNoAddedBy\":1001369150}]}},{\"TrxNo\":1001369151,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":0.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T14:13:06\",\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"NetAmount\":0.0,\"TrxNoAddedBy\":1001369150,\"Reference\":\"[Add: 0%Prices.(B)]\",\"TranActionId\":1374483,\"FinDmlSeqNo\":1100739},{\"TrxNo\":1001369152,\"TrxCode\":\"8505\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":-18.03,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T14:13:06\",\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"NetAmount\":-18.032786885246,\"TrxNoAddedBy\":1001369150,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":1374483,\"FinDmlSeqNo\":1100740},{\"TrxNo\":1001369153,\"TrxCode\":\"2015\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":-400.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T14:13:06\",\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"NetAmount\":-400.0,\"GrossAmount\":-400.0,\"GuestAccountDebit\":-400.0,\"TranActionId\":1374485,\"FinDmlSeqNo\":1100741,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TrxCode\":\"8525\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T14:13:06\",\"TrxNo\":1001369154,\"TrxType\":\"C\",\"UnitPrice\":0.0,\"FinDmlSeqNo\":1100742,\"NetAmount\":0.0,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":1374485,\"TrxNoAddedBy\":1001369153}]}},{\"TrxNo\":1001369154,\"TrxCode\":\"8525\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":0.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T14:13:06\",\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"NetAmount\":0.0,\"TrxNoAddedBy\":1001369153,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":1374485,\"FinDmlSeqNo\":1100742},{\"TrxNo\":1001369155,\"TrxCode\":\"9000\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"FC\",\"UnitPrice\":-1500.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T14:13:06\",\"LocalTrxDateTime\":\"2020-08-19T14:13:06\",\"GuestAccountCredit\":-1500.0,\"TranActionId\":1375768,\"FinDmlSeqNo\":1100743,\"ReceiptNumber\":3,\"ReceiptType\":\"PAR\"}],\"RevenueBucketInfo\":[{\"BucketCode\":\"CASH\",\"BucketType\":\"FLIP_PAY_SUBTYPE\",\"BucketValue\":\"1\",\"Description\":\"Cash Payments\",\"BucketCodeTotalGross\":-1500.0,\"TrxCode\":[\"9000\"]},{\"BucketCode\":\"GROUP_1\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Habitaciones\",\"BucketCodeTotalGross\":-1000.0,\"TrxCode\":[\"1000\"]},{\"BucketCode\":\"GROUP_10\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Food Lunch\",\"BucketCodeTotalGross\":-100.0,\"TrxCode\":[\"2005\"]},{\"BucketCode\":\"GROUP_4\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Tips\",\"BucketCodeTotalGross\":-400.0,\"TrxCode\":[\"2015\"]},{\"BucketCode\":\"TAX_2\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"2\",\"Description\":\"Iva Tasa Minima\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8500\"]},{\"BucketCode\":\"TAX_3\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"3\",\"Description\":\"IVA Tasa Basica\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8505\"]},{\"BucketCode\":\"TAX_5\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"6\",\"Description\":\"Producto o Servicio no facturable\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8525\"]}],\"TotalInfo\":{\"NetAmount\":0.0,\"GrossAmount\":0.0,\"NonTaxableAmount\":0.0,\"PaidOut\":0.0,\"Taxes\":{\"Tax\":[{\"Name\":\"1\",\"Value\":0.0,\"NetAmount\":-1081.967213114754,\"Percent\":\"\",\"Amount\":\"\"},{\"Name\":\"2\",\"Value\":-18.032786885246,\"NetAmount\":-81.967213114754,\"Percent\":\"\",\"Amount\":\"\"},{\"Name\":\"6\",\"Value\":0.0,\"NetAmount\":-400.0,\"Percent\":\"\",\"Amount\":\"\"}]}},\"TrxInfo\":[{\"HotelCode\":\"URYY\",\"Group\":\"ROOM\",\"SubGroup\":\"ROOM\",\"Code\":\"1000\",\"TrxType\":\"C\",\"Description\":\"Accommodation - Tax Inclusive\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"L\"},{\"HotelCode\":\"URYY\",\"Group\":\"FB\",\"SubGroup\":\"FOOD\",\"Code\":\"2005\",\"TrxType\":\"C\",\"Description\":\"Food Inclusive  - Lunch\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"F\"},{\"HotelCode\":\"URYY\",\"Group\":\"TIPS\",\"SubGroup\":\"TIPS\",\"Code\":\"2015\",\"TrxType\":\"C\",\"Description\":\"Food Inclusive  - Gratuities\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"N\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8500\",\"TrxType\":\"C\",\"TaxCodeNo\":1,\"Description\":\"IVA 10%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8505\",\"TrxType\":\"C\",\"TaxCodeNo\":2,\"Description\":\"IVA 22%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8525\",\"TrxType\":\"C\",\"TaxCodeNo\":6,\"Description\":\"No billable\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"PAYMENT\",\"SubGroup\":\"CASH\",\"Code\":\"9000\",\"TrxType\":\"FC\",\"Description\":\"Cash\",\"Articles\":{},\"TranslatedDescriptions\":{}}]},\"HotelInfo\":{\"HotelCode\":\"URYY\",\"HotelName\":\"OPERA Cloud Uruguay Property\",\"Address\":{\"Country\":\"UY\"},\"LocalCurrency\":\"UYU\",\"CurrencySymbol\":\"$U\",\"Decimals\":\"2\",\"ExchangeRates\":{\"ExchangeRateInfo\":[{\"CurrencyCode\":\"USD\"}]},\"PropertyDateTime\":\"2021-06-28T14:13:06\"},\"FiscalFolioUserInfo\":{\"AppUser\":\"GASTONVAF@VAFISCAL\",\"AppUserId\":\"51457\",\"EmployeeNumber\":\"\",\"CashierId\":\"\"}}";
		String jsonInvoiceExample1 = "{\"DocumentInfo\":{\"HotelCode\":\"LP_URY\",\"BillNo\":\"91\",\"FolioType\":\"101\",\"TerminalId\":\"0001\",\"ProgramName\":\"\",\"FiscalFolioId\":\"567725\",\"OperaFiscalBillNo\":\"\",\"Application\":\"OPERA_CLOUD\",\"PropertyTaxNumber\":\"100007800017\",\"BankName\":\"\",\"BankCode\":\"\",\"BankIdType\":\"\",\"BankIdCode\":\"\",\"BusinessDate\":\"2021-01-25\",\"BusinessDateTime\":\"2021-01-25T13:19:05\",\"CountryCode\":\"UY\",\"CountryName\":\"Uruguay\",\"Command\":\"INVOICE\",\"FiscalTimeoutPeriod\":\"\"},\"AdditionalInfo\":{\"BeforeSettlement\":{},\"ProfileOptions\":{},\"MappedValues\":{\"MappingType\":[{\"Type\":\"FISCAL_CNTRY_CITIES\"},{\"Type\":\"FISCAL_ID_COUNTRY\"},{\"Type\":\"FISCAL_TRANSPORT\"}]},\"ReservationOptions\":{}},\"UserDefinedFields\":{\"CharacterUDFs\":[{\"UDF\":[{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_DATE\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_NO\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_TIME\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_TERMINAL_ID\"},{\"Name\":\"FLIP_CERTIFICATE_NUMBER\"},{\"Name\":\"FLIP_CONFIGMODE\",\"Value\":\"DEFAULT\"},{\"Name\":\"FLIP_DELIVERYLAYOUT\",\"Value\":\"FLIPPAYLOAD\"},{\"Name\":\"FLIP_DEPOSIT_TRANSFER\"},{\"Name\":\"FLIP_FISCAL_DEVICE_PASSWORD\"},{\"Name\":\"FLIP_NO_PAYMENT_DESCRIPTION\"},{\"Name\":\"FLIP_NO_PAYMENT_SUBTYPE\"},{\"Name\":\"FLIP_NO_PAYMENT_TYPE\"},{\"Name\":\"FLIP_PARTNER_BRANCH_CODE\"},{\"Name\":\"FLIP_PARTNER_CHARGE_ID\"},{\"Name\":\"FLIP_PARTNER_CONTRIBUTOR\"},{\"Name\":\"FLIP_PARTNER_FISCAL_BILL_NO\",\"Value\":\"Y\"},{\"Name\":\"FLIP_PARTNER_FISCAL_INCENTIVE\"},{\"Name\":\"FLIP_PARTNER_FOLIO_TEXT\"},{\"Name\":\"FLIP_PARTNER_GOODS_FOLIO\"},{\"Name\":\"FLIP_PARTNER_MODE\"},{\"Name\":\"FLIP_PARTNER_NETGROSS\"},{\"Name\":\"FLIP_PARTNER_PATTERN\"},{\"Name\":\"FLIP_PARTNER_RULE\"},{\"Name\":\"FLIP_PARTNER_SERIAL\"},{\"Name\":\"FLIP_PARTNER_SERVICES_FOLIO\"},{\"Name\":\"FLIP_PARTNER_SPECIAL_TAX\"},{\"Name\":\"FLIP_PARTNER_TAX1\"},{\"Name\":\"FLIP_PARTNER_TAX2\"},{\"Name\":\"FLIP_PARTNER_TAX3\"},{\"Name\":\"FLIP_PARTNER_TAX4\"},{\"Name\":\"FLIP_PARTNER_TAX5\"},{\"Name\":\"FLIP_PARTNER_TAX_LEVEL\"},{\"Name\":\"FLIP_PARTNER_TAX_QUALIFICATION\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE2\"},{\"Name\":\"FLIP_PERSON_IN_CHARGE\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS1\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS2\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS3\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS4\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS5\"},{\"Name\":\"FLIP_PROPERTY_CATALOGUE_ID\"},{\"Name\":\"FLIP_PROPERTY_CODE\",\"Value\":\"Recepcion1\"},{\"Name\":\"FLIP_PROPERTY_COUNTRY\"},{\"Name\":\"FLIP_PROPERTY_ECONOMIC_ID\",\"Value\":\"HOTELERIA\"},{\"Name\":\"FLIP_PROPERTY_ID\",\"Value\":\"2\"},{\"Name\":\"FLIP_RECEIVER_AUTHORITY\"},{\"Name\":\"FLIP_SENDER_AUTHORITY\"},{\"Name\":\"FLIP_SERVER_ADDRESS\",\"Value\":\"GOTERO-X380YOGA.AR.ORACLE.COM\"},{\"Name\":\"FLIP_SOFTWARE_NAME\"},{\"Name\":\"FLIP_TAX_FREE_PROPERTY\"},{\"Name\":\"FLIP_TEMPLATEFILE\",\"Value\":\"FLIPTemplateGenericJSON.xslt\"},{\"Name\":\"FLIP_UNIT_CODE\"}]}]},\"FiscalTerminalInfo\":{\"TerminalAddressAndPort\":\"0001\",\"TerminalID\":\"0001\"},\"FolioInfo\":{\"FolioHeaderInfo\":{\"BillGenerationDate\":\"2021-01-25T13:19:05\",\"FolioType\":\"101\",\"CreditBill\":false,\"FolioNo\":\"2207249349\",\"BillNo\":\"91\",\"InvoiceCurrencyCode\":\"USD\",\"InvoiceCurrencyRate\":\"42.99\",\"Window\":\"7\",\"CashierNumber\":\"6\",\"FiscalFolioStatus\":\"OK\",\"LocalBillGenerationDate\":\"2021-01-25T13:19:05\",\"CollectingAgentTaxes\":{\"AmountUDFsType\":{}},\"FolioTypeUniqueCode\":\"0\",\"CreditBillReason\":\"\"},\"PayeeInfo\":{\"NameId\":2208342722,\"FirstName\":\"Sebastian\",\"LastName\":\"Teysera\",\"DateOfBirth\":\"\",\"Passport\":\"\",\"Tax1No\":\"XXXXXXXXXXX\",\"Tax2No\":\"\",\"NameTaxType\":\"RTA\",\"PaymentDueDate\":\"2021-02-04\",\"UserDefinedFields\":{},\"IdentificationInfos\":{\"IdentificationInfo\":[{\"IdType\":\"3\",\"IdNumber\":\"XXXXXXX\",\"Primary\":true}]},\"KeywordInfos\":{},\"Email\":\"lavela@gmail.com\",\"Address\":{\"Address1\":\"Calle Peres Castellano 1495\",\"AddresseeCountryDesc\":\"Uruguay\",\"AddresseeStateDesc\":\"Montevideo\",\"Country\":\"UY\",\"IsoCode\":\"UY\",\"Primary\":true,\"State\":\"MVD\",\"Type\":\"HOME\"},\"Phone\":{\"Number\":\"23103921\",\"Type\":\"HOME\"},\"Language\":\"E\",\"NameType\":\"INDIVIDUAL\"},\"Postings\":[{\"TrxNo\":2215802765,\"TrxCode\":\"9000\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"FC\",\"UnitPrice\":1630.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:16:13\",\"LocalTrxDateTime\":\"2021-01-25T13:16:13\",\"Reference\":\"\",\"GuestAccountCredit\":1630.0,\"TranActionId\":2212407387,\"FinDmlSeqNo\":2210312383,\"ReceiptNumber\":68,\"ReceiptType\":\"PAR\"},{\"TrxNo\":2215804347,\"TrxCode\":\"2000\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":200.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:16:02\",\"LocalTrxDateTime\":\"2021-01-25T13:16:02\",\"NetAmount\":163.934426229508,\"GrossAmount\":200.0,\"TrxNoAgainstPackage\":2215804342,\"Reference\":\"[NA Pkgs.BKF]  [Against Pkg.: BKF][Manual] \",\"Product\":\"BKF\",\"PackageDebit\":200.0,\"PackageTrxType\":\"INCLTAX_PKG_WITHOUT_ALLOWANCE\",\"TranActionId\":2212408061,\"FinDmlSeqNo\":2210313178,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2021-01-25T13:16:02\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":22.0,\"TrxCode\":\"8505\",\"TrxDate\":\"2021-01-25\",\"TrxDateTime\":\"2021-01-25T13:16:02\",\"TrxNo\":2215804349,\"TrxType\":\"C\",\"UnitPrice\":36.07,\"FinDmlSeqNo\":2210313179,\"NetAmount\":36.065573770492,\"PackageTrxType\":\"INCLTAX_PKG_WITHOUT_ALLOWANCE\",\"Product\":\"BKF\",\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":2212408061,\"TrxNoAddedBy\":2215804347,\"TrxNoAgainstPackage\":2215804342}]}},{\"TrxNo\":2215804349,\"TrxCode\":\"8505\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":36.07,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:16:02\",\"LocalTrxDateTime\":\"2021-01-25T13:16:02\",\"NetAmount\":36.065573770492,\"TrxNoAddedBy\":2215804347,\"TrxNoAgainstPackage\":2215804342,\"Reference\":\"[Add: 22%.(B)]\",\"Product\":\"BKF\",\"PackageTrxType\":\"INCLTAX_PKG_WITHOUT_ALLOWANCE\",\"TranActionId\":2212408061,\"FinDmlSeqNo\":2210313179},{\"TrxNo\":2215804342,\"TrxCode\":\"8000\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"PK\",\"UnitPrice\":1500.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:16:02\",\"LocalTrxDateTime\":\"2021-01-25T13:16:02\",\"TrxNoAgainstPackage\":2215804342,\"Reference\":\"[NA Pkg. Trx] [Manual] \",\"PackageCredit\":1500.0,\"PackageTrxType\":\"PKG_WRAPPER\",\"TranActionId\":2212408061,\"FinDmlSeqNo\":2210313177},{\"TrxNo\":2215804343,\"TrxCode\":\"8000\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"PK\",\"UnitPrice\":1500.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:16:02\",\"LocalTrxDateTime\":\"2021-01-25T13:16:02\",\"TrxNoAgainstPackage\":2215804342,\"Remark\":\" 1 @ 1,500.00 Rate Code RTBKF \",\"Reference\":\"[NA Pkg. Trx] [Manual] \",\"GuestAccountDebit\":1500.0,\"PackageTrxType\":\"PKG_WRAPPER\",\"TranActionId\":2212408065,\"FinDmlSeqNo\":2210313177},{\"TrxNo\":2215804344,\"TrxCode\":\"1010\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":1300.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:16:02\",\"LocalTrxDateTime\":\"2021-01-25T13:16:02\",\"NetAmount\":1300.0,\"GrossAmount\":1300.0,\"TrxNoAgainstPackage\":2215804342,\"Reference\":\"[NA P.Room] [Manual] \",\"PackageDebit\":1300.0,\"PackageTrxType\":\"EXCLTAX_PKG_ROOM\",\"TranActionId\":2212408061,\"FinDmlSeqNo\":2210313177,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2021-01-25T13:16:02\",\"Quantity\":1.0,\"TaxInclusive\":false,\"TaxRate\":10.0,\"TrxCode\":\"8500\",\"TrxDate\":\"2021-01-25\",\"TrxDateTime\":\"2021-01-25T13:16:02\",\"TrxNo\":2215804346,\"TrxType\":\"C\",\"UnitPrice\":130.0,\"FinDmlSeqNo\":2210313178,\"GrossAmount\":130.0,\"GuestAccountDebit\":130.0,\"NetAmount\":130.0,\"PackageTrxType\":\"EXCLTAX_PKG_ROOM\",\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":2212408061,\"TrxNoAddedBy\":2215804344,\"TrxNoAgainstPackage\":2215804342}]}},{\"TrxNo\":2215804346,\"TrxCode\":\"8500\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":130.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:16:02\",\"LocalTrxDateTime\":\"2021-01-25T13:16:02\",\"NetAmount\":130.0,\"GrossAmount\":130.0,\"TrxNoAddedBy\":2215804344,\"TrxNoAgainstPackage\":2215804342,\"Reference\":\"[Add: 10%Prices.(B)]\",\"GuestAccountDebit\":130.0,\"PackageTrxType\":\"EXCLTAX_PKG_ROOM\",\"TranActionId\":2212408061,\"FinDmlSeqNo\":2210313178}],\"RevenueBucketInfo\":[{\"BucketCode\":\"CASH\",\"BucketType\":\"FLIP_PAY_SUBTYPE\",\"BucketValue\":\"1\",\"Description\":\"Cash Payments\",\"BucketCodeTotalGross\":1630.0,\"TrxCode\":[\"9000\"]},{\"BucketCode\":\"GROUP_1\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Habitaciones\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"1010\"]},{\"BucketCode\":\"GROUP_2\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Food\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"2000\"]},{\"BucketCode\":\"GROUP_5\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Wrapper\",\"BucketCodeTotalGross\":1500.0,\"TrxCode\":[\"8000\"]},{\"BucketCode\":\"TAX_2\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"2\",\"Description\":\"Gravado tasa Minima 10%\",\"BucketCodeTotalGross\":130.0,\"TrxCode\":[\"8500\"]},{\"BucketCode\":\"TAX_3\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"3\",\"Description\":\"Tasa Basica\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8505\"]}],\"TotalInfo\":{\"NetAmount\":1463.93442622951,\"GrossAmount\":1630.0,\"NonTaxableAmount\":0.0,\"PaidOut\":0.0,\"Taxes\":{\"Tax\":[{\"Name\":\"1\",\"Value\":130.0,\"NetAmount\":1300.0,\"Percent\":\"10.00\",\"Amount\":\"\"},{\"Name\":\"2\",\"Value\":36.065573770492,\"NetAmount\":163.934426229508,\"Percent\":\"22.00\",\"Amount\":\"\"}]}},\"TrxInfo\":[{\"HotelCode\":\"LP_URY\",\"Group\":\"ROOM\",\"SubGroup\":\"ROOM\",\"Code\":\"1010\",\"TrxType\":\"C\",\"Description\":\"Accommodation\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"L\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"FB\",\"SubGroup\":\"FOOD\",\"Code\":\"2000\",\"TrxType\":\"C\",\"Description\":\"Food Inclusive  - Breakfast\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"F\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"PACKAGE\",\"SubGroup\":\"PACKAGE\",\"Code\":\"8000\",\"TrxType\":\"PK\",\"Description\":\"Package Wrapper\",\"Articles\":{},\"TranslatedDescriptions\":{}},{\"HotelCode\":\"LP_URY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8500\",\"TrxType\":\"C\",\"TaxCodeNo\":1,\"Description\":\"IVA 10%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8505\",\"TrxType\":\"C\",\"TaxCodeNo\":2,\"Description\":\"IVA 22%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"PAYMENT\",\"SubGroup\":\"CASH\",\"Code\":\"9000\",\"TrxType\":\"FC\",\"Description\":\"Cash\",\"Articles\":{},\"TranslatedDescriptions\":{}}]},\"HotelInfo\":{\"HotelCode\":\"LP_URY\",\"HotelName\":\"OPERA Cloud Uruguay Property - LP\",\"LegalOwner\":\"Hoteles Uruguay S.A\",\"Address\":{\"Address\":\"Pocitos 1234\",\"AddresseeStateDesc\":\"Montevideo\",\"City\":\"Montevideo\",\"Country\":\"UY\",\"PostalCode\":\"11300\",\"State\":\"MVD\"},\"LocalCurrency\":\"UYU\",\"CurrencySymbol\":\"$U\",\"Decimals\":\"2\",\"PhoneNo\":\"245444\",\"ExchangeRates\":{\"ExchangeRateInfo\":[{\"CurrencyCode\":\"USD\",\"ExchangeRate\":42.99}]},\"PropertyDateTime\":\"2023-05-18T13:19:05\"},\"ReservationInfo\":{\"ConfirmationNo\":\"2208957784\",\"ResvNameID\":\"2216532547\",\"ArrivalDate\":\"2021-01-25\",\"ArrivalTime\":\"12:13:35\",\"NumberOfNights\":\"3\",\"DepartureDate\":\"2021-01-28\",\"NumAdults\":\"1\",\"NumChilds\":\"0\",\"GuestInfo\":{\"NameId\":2208342722,\"FirstName\":\"Sebastian\",\"LastName\":\"Teysera\",\"DateOfBirth\":\"\",\"Passport\":\"\",\"Tax1No\":\"XXXXXXXXXXX\",\"Tax2No\":\"\",\"NameTaxType\":\"RTA\",\"PaymentDueDate\":\"2021-02-04\",\"UserDefinedFields\":{},\"IdentificationInfos\":{\"IdentificationInfo\":[{\"IdType\":\"3\",\"IdNumber\":\"XXXXXXX\",\"Primary\":true}]},\"KeywordInfos\":{},\"Email\":\"lavela@gmail.com\",\"Address\":{\"Address1\":\"Calle Peres Castellano 1495\",\"AddresseeCountryDesc\":\"Uruguay\",\"AddresseeStateDesc\":\"Montevideo\",\"Country\":\"UY\",\"IsoCode\":\"UY\",\"Primary\":true,\"State\":\"MVD\",\"Type\":\"HOME\"},\"Phone\":{\"Number\":\"23103921\",\"Type\":\"HOME\"}},\"NameTaxType\":\"RTA\",\"RoomRate\":1500.0,\"RatePlanCode\":\"RTBKF\",\"RoomNumber\":\"105\",\"RoomClass\":\"ALL\",\"RoomType\":\"SINGLE\",\"NumberOfRooms\":\"1\",\"Guarantee\":\"CHECKED IN\",\"MarketCode\":\"DAY\",\"ResStatus\":\"CHECKED IN\",\"UserDefinedFields\":{},\"SourceCode\":\"IND\",\"SourceGroup\":\"ALL\"},\"FiscalFolioUserInfo\":{\"AppUser\":\"GOTLPFISCAL@OLPFISCA\",\"AppUserId\":\"141729\",\"EmployeeNumber\":\"\",\"CashierId\":\"6\"},\"VersionInfo\":{\"PayloadVersion\":\"00105\",\"ApplicationPath\":\"\",\"OPERA5\":{\"OPERA5Version\":\"23.2.1.0.0\",\"Major\":23,\"Minor\":2,\"Patch\":0,\"Patchset\":1},\"OPERACloud\":{\"OPERACloudVersion\":\"23.2.1.0\",\"Major\":23,\"Minor\":2,\"Patch\":0,\"Patchset\":1}},\"FiscalPartnerResponse\":{\"FiscalResponse\":{}}}"; 
		String jsonInvoiceExample2 = "{\"DocumentInfo\":{\"HotelCode\":\"LP_URY\",\"BillNo\":\"92\",\"FolioType\":\"111\",\"TerminalId\":\"0001\",\"ProgramName\":\"\",\"FiscalFolioId\":\"567726\",\"OperaFiscalBillNo\":\"\",\"Application\":\"OPERA_CLOUD\",\"PropertyTaxNumber\":\"100007800017\",\"BankName\":\"\",\"BankCode\":\"\",\"BankIdType\":\"\",\"BankIdCode\":\"\",\"BusinessDate\":\"2021-01-25\",\"BusinessDateTime\":\"2021-01-25T13:26:31\",\"CountryCode\":\"UY\",\"CountryName\":\"Uruguay\",\"Command\":\"INVOICE\",\"FiscalTimeoutPeriod\":\"\"},\"AdditionalInfo\":{\"BeforeSettlement\":{},\"ProfileOptions\":{\"NV\":[{\"Name\":\"eInvoice_Address\",\"Value\":\"araca@gmail.com\"}]},\"MappedValues\":{\"MappingType\":[{\"Type\":\"FISCAL_CNTRY_CITIES\"},{\"Type\":\"FISCAL_TRANSPORT\"}]},\"ReservationOptions\":{}},\"UserDefinedFields\":{\"CharacterUDFs\":[{\"UDF\":[{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_DATE\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_NO\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_TIME\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_TERMINAL_ID\"},{\"Name\":\"FLIP_CERTIFICATE_NUMBER\"},{\"Name\":\"FLIP_CONFIGMODE\",\"Value\":\"DEFAULT\"},{\"Name\":\"FLIP_DELIVERYLAYOUT\",\"Value\":\"FLIPPAYLOAD\"},{\"Name\":\"FLIP_DEPOSIT_TRANSFER\"},{\"Name\":\"FLIP_FISCAL_DEVICE_PASSWORD\"},{\"Name\":\"FLIP_NO_PAYMENT_DESCRIPTION\"},{\"Name\":\"FLIP_NO_PAYMENT_SUBTYPE\"},{\"Name\":\"FLIP_NO_PAYMENT_TYPE\"},{\"Name\":\"FLIP_PARTNER_BRANCH_CODE\"},{\"Name\":\"FLIP_PARTNER_CHARGE_ID\"},{\"Name\":\"FLIP_PARTNER_CONTRIBUTOR\"},{\"Name\":\"FLIP_PARTNER_FISCAL_BILL_NO\",\"Value\":\"Y\"},{\"Name\":\"FLIP_PARTNER_FISCAL_INCENTIVE\"},{\"Name\":\"FLIP_PARTNER_FOLIO_TEXT\"},{\"Name\":\"FLIP_PARTNER_GOODS_FOLIO\"},{\"Name\":\"FLIP_PARTNER_MODE\"},{\"Name\":\"FLIP_PARTNER_NETGROSS\"},{\"Name\":\"FLIP_PARTNER_PATTERN\"},{\"Name\":\"FLIP_PARTNER_RULE\"},{\"Name\":\"FLIP_PARTNER_SERIAL\"},{\"Name\":\"FLIP_PARTNER_SERVICES_FOLIO\"},{\"Name\":\"FLIP_PARTNER_SPECIAL_TAX\"},{\"Name\":\"FLIP_PARTNER_TAX1\"},{\"Name\":\"FLIP_PARTNER_TAX2\"},{\"Name\":\"FLIP_PARTNER_TAX3\"},{\"Name\":\"FLIP_PARTNER_TAX4\"},{\"Name\":\"FLIP_PARTNER_TAX5\"},{\"Name\":\"FLIP_PARTNER_TAX_LEVEL\"},{\"Name\":\"FLIP_PARTNER_TAX_QUALIFICATION\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE2\"},{\"Name\":\"FLIP_PERSON_IN_CHARGE\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS1\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS2\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS3\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS4\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS5\"},{\"Name\":\"FLIP_PROPERTY_CATALOGUE_ID\"},{\"Name\":\"FLIP_PROPERTY_CODE\",\"Value\":\"Recepcion1\"},{\"Name\":\"FLIP_PROPERTY_COUNTRY\"},{\"Name\":\"FLIP_PROPERTY_ECONOMIC_ID\",\"Value\":\"HOTELERIA\"},{\"Name\":\"FLIP_PROPERTY_ID\",\"Value\":\"2\"},{\"Name\":\"FLIP_RECEIVER_AUTHORITY\"},{\"Name\":\"FLIP_SENDER_AUTHORITY\"},{\"Name\":\"FLIP_SERVER_ADDRESS\",\"Value\":\"GOTERO-X380YOGA.AR.ORACLE.COM\"},{\"Name\":\"FLIP_SOFTWARE_NAME\"},{\"Name\":\"FLIP_TAX_FREE_PROPERTY\"},{\"Name\":\"FLIP_TEMPLATEFILE\",\"Value\":\"FLIPTemplateGenericJSON.xslt\"},{\"Name\":\"FLIP_UNIT_CODE\"}]}]},\"FiscalTerminalInfo\":{\"TerminalAddressAndPort\":\"0001\",\"TerminalID\":\"0001\"},\"FolioInfo\":{\"FolioHeaderInfo\":{\"BillGenerationDate\":\"2021-01-25T13:26:30\",\"FolioType\":\"111\",\"CreditBill\":false,\"FolioNo\":\"2207399814\",\"BillNo\":\"92\",\"InvoiceCurrencyCode\":\"USD\",\"InvoiceCurrencyRate\":\"42.99\",\"Window\":\"8\",\"CashierNumber\":\"6\",\"FiscalFolioStatus\":\"OK\",\"LocalBillGenerationDate\":\"2021-01-25T13:26:30\",\"CollectingAgentTaxes\":{\"AmountUDFsType\":{}},\"FolioTypeUniqueCode\":\"0\",\"CreditBillReason\":\"\"},\"PayeeInfo\":{\"NameId\":2208343071,\"LastName\":\"Araca la cana\",\"Name2\":\"Compañia Murguera S.A\",\"DateOfBirth\":\"\",\"Passport\":\"\",\"Tax1No\":\"XXXXXXXXXX\",\"Tax2No\":\"\",\"NameTaxType\":\"RES\",\"PaymentDueDate\":\"2021-02-04\",\"UserDefinedFields\":{},\"IdentificationInfos\":{},\"KeywordInfos\":{\"KeywordInfo\":[{\"KeywordType\":\"UY_IDTP\",\"KeyWord\":\"2\"}]},\"ArNumber\":\"ARAC-001\",\"Address\":{\"Address1\":\"Positos 1234\",\"AddresseeCountryDesc\":\"Uruguay\",\"AddresseeStateDesc\":\"Montevideo\",\"City\":\"Montevideo\",\"Country\":\"UY\",\"IsoCode\":\"UY\",\"Primary\":true,\"State\":\"MVD\",\"Type\":\"AR ADDRESS\"},\"Phone\":{\"Number\":\"positos 1234\",\"Type\":\"BUSINESS\"},\"NameType\":\"COMPANY\"},\"Postings\":[{\"TrxNo\":2215802766,\"TrxCode\":\"2000\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":10.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:21:50\",\"LocalTrxDateTime\":\"2021-01-25T13:21:50\",\"NetAmount\":8.196721311475,\"GrossAmount\":10.0,\"Reference\":\"\",\"GuestAccountDebit\":10.0,\"TranActionId\":2212407389,\"FinDmlSeqNo\":2210312384,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2021-01-25T13:21:50\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":22.0,\"TrxCode\":\"8505\",\"TrxDate\":\"2021-01-25\",\"TrxDateTime\":\"2021-01-25T13:21:50\",\"TrxNo\":2215802768,\"TrxType\":\"C\",\"UnitPrice\":1.8,\"FinDmlSeqNo\":2210312385,\"NetAmount\":1.803278688525,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":2212407389,\"TrxNoAddedBy\":2215802766}]}},{\"TrxNo\":2215802768,\"TrxCode\":\"8505\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":1.8,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:21:50\",\"LocalTrxDateTime\":\"2021-01-25T13:21:50\",\"NetAmount\":1.803278688525,\"TrxNoAddedBy\":2215802766,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":2212407389,\"FinDmlSeqNo\":2210312385},{\"TrxNo\":2215804350,\"TrxCode\":\"1010\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":900.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:21:41\",\"LocalTrxDateTime\":\"2021-01-25T13:21:41\",\"NetAmount\":900.0,\"GrossAmount\":900.0,\"Reference\":\"\",\"GuestAccountDebit\":900.0,\"TranActionId\":2212408070,\"FinDmlSeqNo\":2210313180,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2021-01-25T13:21:41\",\"Quantity\":1.0,\"TaxInclusive\":false,\"TaxRate\":10.0,\"TrxCode\":\"8500\",\"TrxDate\":\"2021-01-25\",\"TrxDateTime\":\"2021-01-25T13:21:41\",\"TrxNo\":2215804352,\"TrxType\":\"C\",\"UnitPrice\":90.0,\"FinDmlSeqNo\":2210313181,\"GrossAmount\":90.0,\"GuestAccountDebit\":90.0,\"NetAmount\":90.0,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":2212408070,\"TrxNoAddedBy\":2215804350}]}},{\"TrxNo\":2215804352,\"TrxCode\":\"8500\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":90.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:21:41\",\"LocalTrxDateTime\":\"2021-01-25T13:21:41\",\"NetAmount\":90.0,\"GrossAmount\":90.0,\"TrxNoAddedBy\":2215804350,\"Reference\":\"[Add: 10%Prices.(B)]\",\"GuestAccountDebit\":90.0,\"TranActionId\":2212408070,\"FinDmlSeqNo\":2210313181},{\"TrxNo\":2215804355,\"TrxCode\":\"8525\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":0.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:22:01\",\"LocalTrxDateTime\":\"2021-01-25T13:22:01\",\"NetAmount\":0.0,\"TrxNoAddedBy\":2215804353,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":2212408072,\"FinDmlSeqNo\":2210313183},{\"TrxNo\":2215804353,\"TrxCode\":\"3015\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":10.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:22:01\",\"LocalTrxDateTime\":\"2021-01-25T13:22:01\",\"NetAmount\":10.0,\"GrossAmount\":10.0,\"Reference\":\"\",\"GuestAccountDebit\":10.0,\"TranActionId\":2212408072,\"FinDmlSeqNo\":2210313182,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2021-01-25T13:22:01\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":0.0,\"TrxCode\":\"8525\",\"TrxDate\":\"2021-01-25\",\"TrxDateTime\":\"2021-01-25T13:22:01\",\"TrxNo\":2215804355,\"TrxType\":\"C\",\"UnitPrice\":0.0,\"FinDmlSeqNo\":2210313183,\"NetAmount\":0.0,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":2212408072,\"TrxNoAddedBy\":2215804353}]}},{\"TrxNo\":2215804356,\"TrxCode\":\"9000\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"FC\",\"UnitPrice\":1010.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:22:50\",\"LocalTrxDateTime\":\"2021-01-25T13:22:50\",\"Reference\":\"\",\"GuestAccountCredit\":1010.0,\"TranActionId\":2212408074,\"FinDmlSeqNo\":2210313184,\"ReceiptNumber\":69,\"ReceiptType\":\"PAR\"}],\"RevenueBucketInfo\":[{\"BucketCode\":\"CASH\",\"BucketType\":\"FLIP_PAY_SUBTYPE\",\"BucketValue\":\"1\",\"Description\":\"Cash Payments\",\"BucketCodeTotalGross\":1010.0,\"TrxCode\":[\"9000\"]},{\"BucketCode\":\"GROUP_1\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Habitaciones\",\"BucketCodeTotalGross\":900.0,\"TrxCode\":[\"1010\"]},{\"BucketCode\":\"GROUP_2\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Food\",\"BucketCodeTotalGross\":10.0,\"TrxCode\":[\"2000\"]},{\"BucketCode\":\"GROUP_4\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"TIPS\",\"BucketCodeTotalGross\":10.0,\"TrxCode\":[\"3015\"]},{\"BucketCode\":\"TAX_2\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"2\",\"Description\":\"Gravado tasa Minima 10%\",\"BucketCodeTotalGross\":90.0,\"TrxCode\":[\"8500\"]},{\"BucketCode\":\"TAX_3\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"3\",\"Description\":\"Tasa Basica\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8505\"]},{\"BucketCode\":\"TAX_7\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"6\",\"Description\":\"No Facturable\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8525\"]}],\"TotalInfo\":{\"NetAmount\":918.19672131148,\"GrossAmount\":1010.0,\"NonTaxableAmount\":0.0,\"PaidOut\":0.0,\"Taxes\":{\"Tax\":[{\"Name\":\"1\",\"Value\":90.0,\"NetAmount\":900.0,\"Percent\":\"10.00\",\"Amount\":\"\"},{\"Name\":\"2\",\"Value\":1.803278688525,\"NetAmount\":8.196721311475,\"Percent\":\"22.00\",\"Amount\":\"\"},{\"Name\":\"6\",\"Value\":0.0,\"NetAmount\":10.0,\"Percent\":\"0.00\",\"Amount\":\"\"}]}},\"TrxInfo\":[{\"HotelCode\":\"LP_URY\",\"Group\":\"ROOM\",\"SubGroup\":\"ROOM\",\"Code\":\"1010\",\"TrxType\":\"C\",\"Description\":\"Accommodation\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"L\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"FB\",\"SubGroup\":\"FOOD\",\"Code\":\"2000\",\"TrxType\":\"C\",\"Description\":\"Food Inclusive  - Breakfast\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"F\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"TIPS\",\"SubGroup\":\"TIPS\",\"Code\":\"3015\",\"TrxType\":\"C\",\"Description\":\"Beverage Inclusive - Gratuities\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"N\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8500\",\"TrxType\":\"C\",\"TaxCodeNo\":1,\"Description\":\"IVA 10%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8505\",\"TrxType\":\"C\",\"TaxCodeNo\":2,\"Description\":\"IVA 22%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8525\",\"TrxType\":\"C\",\"TaxCodeNo\":6,\"Description\":\"No facturable\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"PAYMENT\",\"SubGroup\":\"CASH\",\"Code\":\"9000\",\"TrxType\":\"FC\",\"Description\":\"Cash\",\"Articles\":{},\"TranslatedDescriptions\":{}}]},\"HotelInfo\":{\"HotelCode\":\"LP_URY\",\"HotelName\":\"OPERA Cloud Uruguay Property - LP\",\"LegalOwner\":\"Hoteles Uruguay S.A\",\"Address\":{\"Address\":\"Pocitos 1234\",\"AddresseeStateDesc\":\"Montevideo\",\"City\":\"Montevideo\",\"Country\":\"UY\",\"PostalCode\":\"11300\",\"State\":\"MVD\"},\"LocalCurrency\":\"UYU\",\"CurrencySymbol\":\"$U\",\"Decimals\":\"2\",\"PhoneNo\":\"245444\",\"ExchangeRates\":{\"ExchangeRateInfo\":[{\"CurrencyCode\":\"USD\",\"ExchangeRate\":42.99}]},\"PropertyDateTime\":\"2023-05-18T13:26:30\"},\"ReservationInfo\":{\"ConfirmationNo\":\"2208665561\",\"ResvNameID\":\"2216423310\",\"ArrivalDate\":\"2021-01-25\",\"ArrivalTime\":\"16:58:27\",\"NumberOfNights\":\"1\",\"DepartureDate\":\"2021-01-26\",\"NumAdults\":\"1\",\"NumChilds\":\"0\",\"GuestInfo\":{\"NameId\":2208343071,\"LastName\":\"Araca la cana\",\"Name2\":\"Compañia Murguera S.A\",\"DateOfBirth\":\"\",\"Passport\":\"\",\"Tax1No\":\"XXXXXXXXXX\",\"Tax2No\":\"\",\"NameTaxType\":\"RES\",\"PaymentDueDate\":\"2021-02-04\",\"UserDefinedFields\":{},\"IdentificationInfos\":{},\"KeywordInfos\":{\"KeywordInfo\":[{\"KeywordType\":\"UY_IDTP\",\"KeyWord\":\"2\"}]},\"ArNumber\":\"ARAC-001\",\"Address\":{\"Address1\":\"Positos 1234\",\"AddresseeCountryDesc\":\"Uruguay\",\"AddresseeStateDesc\":\"Montevideo\",\"City\":\"Montevideo\",\"Country\":\"UY\",\"IsoCode\":\"UY\",\"Primary\":true,\"State\":\"MVD\",\"Type\":\"AR ADDRESS\"},\"Phone\":{\"Number\":\"positos 1234\",\"Type\":\"BUSINESS\"}},\"NameTaxType\":\"RES\",\"RoomRate\":1500.0,\"RatePlanCode\":\"RTBKF\",\"RoomNumber\":\"201\",\"RoomClass\":\"ALL\",\"RoomType\":\"DOUBLE\",\"NumberOfRooms\":\"1\",\"Guarantee\":\"CHECKED IN\",\"MarketCode\":\"DAY\",\"ResStatus\":\"CHECKED IN\",\"UserDefinedFields\":{\"CharacterUDFs\":[{\"UDF\":[{\"Name\":\"21\",\"Value\":\"TA - prueba gaston\"}]}]},\"SourceCode\":\"CMP\",\"SourceGroup\":\"ALL\"},\"FiscalFolioUserInfo\":{\"AppUser\":\"GOTLPFISCAL@OLPFISCA\",\"AppUserId\":\"141729\",\"EmployeeNumber\":\"\",\"CashierId\":\"6\"},\"VersionInfo\":{\"PayloadVersion\":\"00105\",\"ApplicationPath\":\"\",\"OPERA5\":{\"OPERA5Version\":\"23.2.1.0.0\",\"Major\":23,\"Minor\":2,\"Patch\":0,\"Patchset\":1},\"OPERACloud\":{\"OPERACloudVersion\":\"23.2.1.0\",\"Major\":23,\"Minor\":2,\"Patch\":0,\"Patchset\":1}},\"FiscalPartnerResponse\":{\"FiscalResponse\":{}}}";
		//String jsonInvoice = "{\"DocumentInfo\":{\"HotelCode\":\"LP_URY\",\"BillNo\":\"93\",\"FolioType\":\"101\",\"TerminalId\":\"0001\",\"ProgramName\":\"\",\"FiscalFolioId\":\"567473\",\"OperaFiscalBillNo\":\"\",\"Application\":\"OPERA_CLOUD\",\"PropertyTaxNumber\":\"100007800017\",\"BankName\":\"\",\"BankCode\":\"\",\"BankIdType\":\"\",\"BankIdCode\":\"\",\"BusinessDate\":\"2021-01-25\",\"BusinessDateTime\":\"2021-01-25T13:28:33\",\"CountryCode\":\"UY\",\"CountryName\":\"Uruguay\",\"Command\":\"INVOICE\",\"FiscalTimeoutPeriod\":\"\"},\"AdditionalInfo\":{\"BeforeSettlement\":{},\"ProfileOptions\":{},\"MappedValues\":{\"MappingType\":[{\"Type\":\"FISCAL_CNTRY_CITIES\"},{\"Type\":\"FISCAL_ID_COUNTRY\"},{\"Type\":\"FISCAL_TRANSPORT\"}]},\"ReservationOptions\":{}},\"UserDefinedFields\":{\"CharacterUDFs\":[{\"UDF\":[{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_DATE\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_NO\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_TIME\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_TERMINAL_ID\"},{\"Name\":\"FLIP_CERTIFICATE_NUMBER\"},{\"Name\":\"FLIP_CONFIGMODE\",\"Value\":\"DEFAULT\"},{\"Name\":\"FLIP_DELIVERYLAYOUT\",\"Value\":\"FLIPPAYLOAD\"},{\"Name\":\"FLIP_DEPOSIT_TRANSFER\"},{\"Name\":\"FLIP_FISCAL_DEVICE_PASSWORD\"},{\"Name\":\"FLIP_NO_PAYMENT_DESCRIPTION\"},{\"Name\":\"FLIP_NO_PAYMENT_SUBTYPE\"},{\"Name\":\"FLIP_NO_PAYMENT_TYPE\"},{\"Name\":\"FLIP_PARTNER_BRANCH_CODE\"},{\"Name\":\"FLIP_PARTNER_CHARGE_ID\"},{\"Name\":\"FLIP_PARTNER_CONTRIBUTOR\"},{\"Name\":\"FLIP_PARTNER_FISCAL_BILL_NO\",\"Value\":\"Y\"},{\"Name\":\"FLIP_PARTNER_FISCAL_INCENTIVE\"},{\"Name\":\"FLIP_PARTNER_FOLIO_TEXT\"},{\"Name\":\"FLIP_PARTNER_GOODS_FOLIO\"},{\"Name\":\"FLIP_PARTNER_MODE\"},{\"Name\":\"FLIP_PARTNER_NETGROSS\"},{\"Name\":\"FLIP_PARTNER_PATTERN\"},{\"Name\":\"FLIP_PARTNER_RULE\"},{\"Name\":\"FLIP_PARTNER_SERIAL\"},{\"Name\":\"FLIP_PARTNER_SERVICES_FOLIO\"},{\"Name\":\"FLIP_PARTNER_SPECIAL_TAX\"},{\"Name\":\"FLIP_PARTNER_TAX1\"},{\"Name\":\"FLIP_PARTNER_TAX2\"},{\"Name\":\"FLIP_PARTNER_TAX3\"},{\"Name\":\"FLIP_PARTNER_TAX4\"},{\"Name\":\"FLIP_PARTNER_TAX5\"},{\"Name\":\"FLIP_PARTNER_TAX_LEVEL\"},{\"Name\":\"FLIP_PARTNER_TAX_QUALIFICATION\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE2\"},{\"Name\":\"FLIP_PERSON_IN_CHARGE\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS1\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS2\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS3\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS4\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS5\"},{\"Name\":\"FLIP_PROPERTY_CATALOGUE_ID\"},{\"Name\":\"FLIP_PROPERTY_CODE\",\"Value\":\"Recepcion1\"},{\"Name\":\"FLIP_PROPERTY_COUNTRY\"},{\"Name\":\"FLIP_PROPERTY_ECONOMIC_ID\",\"Value\":\"HOTELERIA\"},{\"Name\":\"FLIP_PROPERTY_ID\",\"Value\":\"2\"},{\"Name\":\"FLIP_RECEIVER_AUTHORITY\"},{\"Name\":\"FLIP_SENDER_AUTHORITY\"},{\"Name\":\"FLIP_SERVER_ADDRESS\",\"Value\":\"GOTERO-X380YOGA.AR.ORACLE.COM\"},{\"Name\":\"FLIP_SOFTWARE_NAME\"},{\"Name\":\"FLIP_TAX_FREE_PROPERTY\"},{\"Name\":\"FLIP_TEMPLATEFILE\",\"Value\":\"FLIPTemplateGenericJSON.xslt\"},{\"Name\":\"FLIP_UNIT_CODE\"}]}]},\"FiscalTerminalInfo\":{\"TerminalAddressAndPort\":\"0001\",\"TerminalID\":\"0001\"},\"FolioInfo\":{\"FolioHeaderInfo\":{\"BillGenerationDate\":\"2021-01-25T13:28:32\",\"FolioType\":\"101\",\"CreditBill\":false,\"FolioNo\":\"2207254925\",\"BillNo\":\"93\",\"InvoiceCurrencyCode\":\"USD\",\"InvoiceCurrencyRate\":\"42.99\",\"Window\":\"7\",\"CashierNumber\":\"6\",\"FiscalFolioStatus\":\"OK\",\"LocalBillGenerationDate\":\"2021-01-25T13:28:32\",\"CollectingAgentTaxes\":{\"AmountUDFsType\":{}},\"FolioTypeUniqueCode\":\"0\",\"CreditBillReason\":\"\"},\"PayeeInfo\":{\"NameId\":2208343068,\"FirstName\":\"Emiliano\",\"LastName\":\"Brancciari\",\"DateOfBirth\":\"\",\"Passport\":\"\",\"Tax1No\":\"XXXXXXXX\",\"Tax2No\":\"\",\"NameTaxType\":\"NOR\",\"PaymentDueDate\":\"2021-02-04\",\"UserDefinedFields\":{},\"IdentificationInfos\":{\"IdentificationInfo\":[{\"IdType\":\"6\",\"IdNumber\":\"XXXXXXXX\",\"Primary\":true}]},\"KeywordInfos\":{},\"Email\":\"notevagustar@gmail.com\",\"Address\":{\"Address1\":\"Scalabrini Ortiz 112\",\"AddresseeCountryDesc\":\"Argentina\",\"Country\":\"AR\",\"IsoCode\":\"AR\",\"Primary\":true,\"Type\":\"HOME\"},\"Phone\":{\"Number\":\"Scalabrini ortiz\",\"Type\":\"HOME\"},\"Language\":\"E\",\"NameType\":\"INDIVIDUAL\"},\"Postings\":[{\"TrxNo\":2215802770,\"TrxCode\":\"4000\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":33.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:28:11\",\"LocalTrxDateTime\":\"2021-01-25T13:28:11\",\"NetAmount\":33.0,\"GrossAmount\":33.0,\"Reference\":\"\",\"GuestAccountDebit\":33.0,\"TranActionId\":2212407392,\"FinDmlSeqNo\":2210312387,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2021-01-25T13:28:11\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":0.0,\"TrxCode\":\"8515\",\"TrxDate\":\"2021-01-25\",\"TrxDateTime\":\"2021-01-25T13:28:11\",\"TrxNo\":2215802772,\"TrxType\":\"C\",\"UnitPrice\":0.0,\"FinDmlSeqNo\":2210312388,\"NetAmount\":0.0,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":2212407392,\"TrxNoAddedBy\":2215802770}]}},{\"TrxNo\":2215802772,\"TrxCode\":\"8515\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":0.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:28:11\",\"LocalTrxDateTime\":\"2021-01-25T13:28:11\",\"NetAmount\":0.0,\"TrxNoAddedBy\":2215802770,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":2212407392,\"FinDmlSeqNo\":2210312388},{\"TrxNo\":2215802773,\"TrxCode\":\"9000\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"FC\",\"UnitPrice\":1499.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:28:26\",\"LocalTrxDateTime\":\"2021-01-25T13:28:26\",\"Reference\":\"\",\"GuestAccountCredit\":1499.0,\"TranActionId\":2212407394,\"FinDmlSeqNo\":2210312389,\"ReceiptNumber\":70,\"ReceiptType\":\"PAR\"},{\"TrxNo\":2215804357,\"TrxCode\":\"2000\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":900.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:27:59\",\"LocalTrxDateTime\":\"2021-01-25T13:27:59\",\"NetAmount\":737.704918032787,\"GrossAmount\":900.0,\"Reference\":\"\",\"GuestAccountDebit\":900.0,\"TranActionId\":2212408080,\"FinDmlSeqNo\":2210313185,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2021-01-25T13:27:59\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":22.0,\"TrxCode\":\"8505\",\"TrxDate\":\"2021-01-25\",\"TrxDateTime\":\"2021-01-25T13:27:59\",\"TrxNo\":2215804359,\"TrxType\":\"C\",\"UnitPrice\":162.3,\"FinDmlSeqNo\":2210313186,\"NetAmount\":162.295081967213,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":2212408080,\"TrxNoAddedBy\":2215804357}]}},{\"TrxNo\":2215804359,\"TrxCode\":\"8505\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":162.3,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:27:59\",\"LocalTrxDateTime\":\"2021-01-25T13:27:59\",\"NetAmount\":162.295081967213,\"TrxNoAddedBy\":2215804357,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":2212408080,\"FinDmlSeqNo\":2210313186},{\"TrxNo\":2215804360,\"TrxCode\":\"1010\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":566.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:28:19\",\"LocalTrxDateTime\":\"2021-01-25T13:28:19\",\"NetAmount\":566.0,\"GrossAmount\":566.0,\"Reference\":\"\",\"GuestAccountDebit\":566.0,\"TranActionId\":2212408082,\"FinDmlSeqNo\":2210313187,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2021-01-25T13:28:19\",\"Quantity\":1.0,\"TaxInclusive\":false,\"TaxRate\":0.0,\"TrxCode\":\"8510\",\"TrxDate\":\"2021-01-25\",\"TrxDateTime\":\"2021-01-25T13:28:19\",\"TrxNo\":2215804362,\"TrxType\":\"C\",\"UnitPrice\":0.0,\"FinDmlSeqNo\":2210313188,\"GrossAmount\":0.0,\"GuestAccountDebit\":0.0,\"NetAmount\":0.0,\"Reference\":\"[Add: 0%Prices.(B)]\",\"TranActionId\":2212408082,\"TrxNoAddedBy\":2215804360}]}},{\"TrxNo\":2215804362,\"TrxCode\":\"8510\",\"TrxDate\":\"2021-01-25\",\"TrxType\":\"C\",\"UnitPrice\":0.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2021-01-25T13:28:19\",\"LocalTrxDateTime\":\"2021-01-25T13:28:19\",\"NetAmount\":0.0,\"GrossAmount\":0.0,\"TrxNoAddedBy\":2215804360,\"Reference\":\"[Add: 0%Prices.(B)]\",\"GuestAccountDebit\":0.0,\"TranActionId\":2212408082,\"FinDmlSeqNo\":2210313188}],\"RevenueBucketInfo\":[{\"BucketCode\":\"CASH\",\"BucketType\":\"FLIP_PAY_SUBTYPE\",\"BucketValue\":\"1\",\"Description\":\"Cash Payments\",\"BucketCodeTotalGross\":1499.0,\"TrxCode\":[\"9000\"]},{\"BucketCode\":\"GROUP_1\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Habitaciones\",\"BucketCodeTotalGross\":566.0,\"TrxCode\":[\"1010\"]},{\"BucketCode\":\"GROUP_2\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Food\",\"BucketCodeTotalGross\":900.0,\"TrxCode\":[\"2000\"]},{\"BucketCode\":\"GROUP_6\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Trasnportation\",\"BucketCodeTotalGross\":33.0,\"TrxCode\":[\"4000\"]},{\"BucketCode\":\"TAX_1\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"1\",\"Description\":\"Exento de IVA\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8515\"]},{\"BucketCode\":\"TAX_3\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"3\",\"Description\":\"Tasa Basica\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8505\"]},{\"BucketCode\":\"TAX_6\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"10\",\"Description\":\"Exportacion o asimilados\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8510\"]}],\"TotalInfo\":{\"NetAmount\":1336.70491803279,\"GrossAmount\":1499.0,\"NonTaxableAmount\":0.0,\"PaidOut\":0.0,\"Taxes\":{\"Tax\":[{\"Name\":\"2\",\"Value\":162.295081967213,\"NetAmount\":737.704918032787,\"Percent\":\"22.00\",\"Amount\":\"\"},{\"Name\":\"3\",\"Value\":0.0,\"NetAmount\":566.0,\"Percent\":\"0.00\",\"Amount\":\"\"},{\"Name\":\"4\",\"Value\":0.0,\"NetAmount\":33.0,\"Percent\":\"0.00\",\"Amount\":\"\"}]}},\"TrxInfo\":[{\"HotelCode\":\"LP_URY\",\"Group\":\"ROOM\",\"SubGroup\":\"ROOM\",\"Code\":\"1010\",\"TrxType\":\"C\",\"Description\":\"Accommodation\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"L\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"FB\",\"SubGroup\":\"FOOD\",\"Code\":\"2000\",\"TrxType\":\"C\",\"Description\":\"Food Inclusive  - Breakfast\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"F\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"MISC\",\"SubGroup\":\"TRANS\",\"Code\":\"4000\",\"TrxType\":\"C\",\"Description\":\"Airport Transportation\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"O\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8505\",\"TrxType\":\"C\",\"TaxCodeNo\":2,\"Description\":\"IVA 22%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8510\",\"TrxType\":\"C\",\"TaxCodeNo\":3,\"Description\":\"0% Exportacion\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8515\",\"TrxType\":\"C\",\"TaxCodeNo\":4,\"Description\":\"0% Exento\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"LP_URY\",\"Group\":\"PAYMENT\",\"SubGroup\":\"CASH\",\"Code\":\"9000\",\"TrxType\":\"FC\",\"Description\":\"Cash\",\"Articles\":{},\"TranslatedDescriptions\":{}}]},\"HotelInfo\":{\"HotelCode\":\"LP_URY\",\"HotelName\":\"OPERA Cloud Uruguay Property - LP\",\"LegalOwner\":\"Hoteles Uruguay S.A\",\"Address\":{\"Address\":\"Pocitos 1234\",\"AddresseeStateDesc\":\"Montevideo\",\"City\":\"Montevideo\",\"Country\":\"UY\",\"PostalCode\":\"11300\",\"State\":\"MVD\"},\"LocalCurrency\":\"UYU\",\"CurrencySymbol\":\"$U\",\"Decimals\":\"2\",\"PhoneNo\":\"245444\",\"ExchangeRates\":{\"ExchangeRateInfo\":[{\"CurrencyCode\":\"USD\",\"ExchangeRate\":42.99}]},\"PropertyDateTime\":\"2023-05-18T13:28:32\"},\"ReservationInfo\":{\"ConfirmationNo\":\"2208659247\",\"ResvNameID\":\"2216421760\",\"ArrivalDate\":\"2021-01-25\",\"ArrivalTime\":\"11:23:50\",\"NumberOfNights\":\"1\",\"DepartureDate\":\"2021-01-26\",\"NumAdults\":\"1\",\"NumChilds\":\"0\",\"GuestInfo\":{\"NameId\":2208343068,\"FirstName\":\"Emiliano\",\"LastName\":\"Brancciari\",\"DateOfBirth\":\"\",\"Passport\":\"\",\"Tax1No\":\"XXXXXXXX\",\"Tax2No\":\"\",\"NameTaxType\":\"NOR\",\"PaymentDueDate\":\"2021-02-04\",\"UserDefinedFields\":{},\"IdentificationInfos\":{\"IdentificationInfo\":[{\"IdType\":\"6\",\"IdNumber\":\"XXXXXXXX\",\"Primary\":true}]},\"KeywordInfos\":{},\"Email\":\"notevagustar@gmail.com\",\"Address\":{\"Address1\":\"Scalabrini Ortiz 112\",\"AddresseeCountryDesc\":\"Argentina\",\"Country\":\"AR\",\"IsoCode\":\"AR\",\"Primary\":true,\"Type\":\"HOME\"},\"Phone\":{\"Number\":\"Scalabrini ortiz\",\"Type\":\"HOME\"}},\"NameTaxType\":\"NOR\",\"RoomRate\":1500.0,\"RatePlanCode\":\"RTBKF\",\"RoomNumber\":\"101\",\"RoomClass\":\"ALL\",\"RoomType\":\"SINGLE\",\"NumberOfRooms\":\"1\",\"Guarantee\":\"CHECKED IN\",\"MarketCode\":\"DAY\",\"ResStatus\":\"CHECKED IN\",\"UserDefinedFields\":{},\"SourceCode\":\"IND\",\"SourceGroup\":\"ALL\"},\"FiscalFolioUserInfo\":{\"AppUser\":\"GOTLPFISCAL@OLPFISCA\",\"AppUserId\":\"141729\",\"EmployeeNumber\":\"\",\"CashierId\":\"6\"},\"VersionInfo\":{\"PayloadVersion\":\"00105\",\"ApplicationPath\":\"\",\"OPERA5\":{\"OPERA5Version\":\"23.2.1.0.0\",\"Major\":23,\"Minor\":2,\"Patch\":0,\"Patchset\":1},\"OPERACloud\":{\"OPERACloudVersion\":\"23.2.1.0\",\"Major\":23,\"Minor\":2,\"Patch\":0,\"Patchset\":1}},\"FiscalPartnerResponse\":{\"FiscalResponse\":{}}}";
		System.out.println(jsonInvoice);
		
		try {

		JsonObject jsonObjectInvoice = new JsonParser().parse(jsonInvoice).getAsJsonObject();

		// ACA TRAEMOS EL HUESPED
		JsonObject guestInfo=null;
		JsonObject guestAddressInfo=null;
		JsonObject reservationInfo=null;
		JsonObject docInfo = jsonObjectInvoice.getAsJsonObject("DocumentInfo");
		JsonObject folioInfo = jsonObjectInvoice.getAsJsonObject("FolioInfo");
		if (jsonObjectInvoice.getAsJsonObject("ReservationInfo") != null) {
			reservationInfo = jsonObjectInvoice.getAsJsonObject("ReservationInfo");
			guestInfo = reservationInfo.getAsJsonObject("GuestInfo");
			guestAddressInfo = guestInfo.getAsJsonObject("Address");
		}
		else {
			guestInfo = folioInfo.getAsJsonObject("PayeeInfo");
			guestAddressInfo = guestInfo.getAsJsonObject("Address");
		}
		
		
		JsonObject totalesInfo = folioInfo.getAsJsonObject("TotalInfo");
		JsonObject infoPagador = folioInfo.getAsJsonObject("PayeeInfo");
		JsonArray trxInfo = folioInfo.getAsJsonArray("TrxInfo");
		 revenueBucketInfo = folioInfo.getAsJsonArray("RevenueBucketInfo");

		String nombrePagador = infoPagador.get("LastName").getAsString();
		reserva.setNombrePagador(nombrePagador);
		String documentoPagador = infoPagador.get("Tax1No").getAsString();
		reserva.setDocumentoPagador(documentoPagador);

		JsonObject pagadorAddress = infoPagador.getAsJsonObject("Address");
		String direccionPagador = "";
		if (pagadorAddress.get("Address1").isJsonNull()) {
			direccionPagador = "Direccion";
		} else {
			direccionPagador = pagadorAddress.get("Address1").getAsString();
		}

		String paisPagador = "";
		if (pagadorAddress.get("Country").isJsonNull()) {
			paisPagador = "AR";
		} else {
			paisPagador = pagadorAddress.get("Country").getAsString();
		}
		reserva.setPaisPagador(paisPagador);

		String ciudadPagador = "";
		if (pagadorAddress.get("Country").isJsonNull()) {
			ciudadPagador = "Ciudad";
		} else {
			ciudadPagador = pagadorAddress.get("Country").getAsString();
		}
		reserva.setCiudadPagador(ciudadPagador);

		String nombreHuesped = guestInfo.get("LastName").getAsString();
		reserva.setNombreHuesped(nombreHuesped);

		String paisHuesped = "";
		if (guestAddressInfo.get("Country").isJsonNull()) {
			paisHuesped = "AR";
		} else {
			paisHuesped = guestAddressInfo.get("Country").getAsString();
		}
		reserva.setPaisHuesped(paisHuesped);

		String direccionHuesped = "";
		if (guestAddressInfo.get("Address1").isJsonNull()) {
			direccionHuesped = "Direccion";
		} else {
			direccionHuesped = guestAddressInfo.get("Address1").getAsString();
		}
		reserva.setDireccionHuesped(direccionHuesped);

		String ciudadHuesped = "";
		if (guestAddressInfo.get("Country").isJsonNull()) {
			ciudadHuesped = "Ciudad";
		} else {
			ciudadHuesped = guestAddressInfo.get("Country").getAsString();
		}
		reserva.setCiudadHuesped(ciudadHuesped);

		String cedulaHuesped = "";
		if (guestInfo.get("Passport").isJsonNull()) {
			cedulaHuesped = "22222222";
		} else {
			cedulaHuesped = guestInfo.get("Passport").getAsString();
		}
		reserva.setDocumentoHuesped(cedulaHuesped);

		String tipoDocumentoHuesped = "";
		if (cedulaHuesped.length() == 8 && paisHuesped.equalsIgnoreCase("UY")) {
			tipoDocumentoHuesped = "CI";
		} else if (cedulaHuesped.length() == 8 && !paisHuesped.equalsIgnoreCase("UY")) {
			paisHuesped = "AR";
			tipoDocumentoHuesped = "OTROS";
		} else if (cedulaHuesped.length() == 12 && paisHuesped.equalsIgnoreCase("UY")) {
			tipoDocumentoHuesped = "RUT";
		}
		reserva.setTipoDocumentoHuesped(tipoDocumentoHuesped);
		
		if(documentoPagador.length()!=12 && tipoDocumentoHuesped.equalsIgnoreCase("RUT")){
			throw new Exception("El RUT debe contener 12 digitos");
		}

		JsonObject totalImpuestosObject = totalesInfo.get("Taxes").getAsJsonObject();
		JsonArray totalImpuestos = totalImpuestosObject.get("Tax").getAsJsonArray();
		JsonObject iva10 = new JsonObject();
		JsonObject iva22 = new JsonObject();
		JsonObject iva0 = new JsonObject();
		iva0.addProperty("Value", "0.0");
		iva0.addProperty("NetAmount", "0.0");
		iva10.addProperty("Value", "0.0");
		iva10.addProperty("NetAmount", "0.0");
		iva22.addProperty("Value", "0.0");
		iva22.addProperty("NetAmount", "0.0");
		for (int r = 0; r < totalImpuestos.size(); r++) {
			if(totalImpuestos.get(r).getAsJsonObject().get("Percent").getAsString().equalsIgnoreCase("10.00")) {
				iva10 = totalImpuestos.get(r).getAsJsonObject();
			}
			else if(totalImpuestos.get(r).getAsJsonObject().get("Percent").getAsString().equalsIgnoreCase("22.00")) {
				 iva22 = totalImpuestos.get(r).getAsJsonObject();
			}
			else if(totalImpuestos.get(r).getAsJsonObject().get("Percent").getAsString().equalsIgnoreCase("0.00")) {
				if(iva0.get("NetAmount").getAsString().equalsIgnoreCase("0.0")) { 
					iva0 = totalImpuestos.get(r).getAsJsonObject();
				}
				else {
					String montoIvaCeroTotal=iva0.get("NetAmount").getAsString();
					iva0 = totalImpuestos.get(r).getAsJsonObject();
					Double montoIvaCeroTotalDouble = Double.valueOf(iva0.get("NetAmount").getAsString()) + Double.valueOf(montoIvaCeroTotal);
					iva0.addProperty("NetAmount", montoIvaCeroTotalDouble.toString());
				}
			}
		}

		Double ivaBasica = Double.valueOf(iva22.get("Value").getAsString());
		Double MontoTotalSinIvaBasica = Double.valueOf(iva22.get("NetAmount").getAsString());
		Double ivaMinima = Double.valueOf(iva10.get("Value").getAsString());
		Double MontoTotalSinIvaMinima = Double.valueOf(iva10.get("NetAmount").getAsString());
		Double ivaCero = Double.valueOf(iva0.get("Value").getAsString());
		Double MontoTotalSinIvaCero = Double.valueOf(iva0.get("NetAmount").getAsString());

		if (jsonObjectInvoice.getAsJsonObject("ReservationInfo") != null) {
		/*int cantidadNoches = reservationInfo.get("NumberOfNights").getAsInt();
		Double tarifaHabitacion = reservationInfo.get("RoomRate").getAsDouble();
		Double montoTotalHabitaciones = tarifaHabitacion * cantidadNoches;
		reserva.setMontoTotalHabitaciones(montoTotalHabitaciones);*/
		}


		Double montoTotalFactura = totalesInfo.get("GrossAmount").getAsDouble();
		reserva.setMontoTotalFactura(montoTotalFactura);

		JsonArray trxs = trxInfo;
		int nlinea3 = 1;
		ArrayList<SRProducto_oracle> srproductos3 = new ArrayList<>();
		nlinea3 = 1;
		int nivel3 = 0;

		if (trxs != null) {

			for (int r = 0; r < trxs.size(); r++) {

				JsonObject rowfields = (JsonObject) trxs.get(r);
				// JsonElement customCedula = row.get("customFieldValue");
				String code = rowfields.get("Code").getAsString();
				if (rowfields.get("TaxCodeNo") != null) {
					String tipoIva = rowfields.get("TaxCodeNo").getAsString();
					if (tipoIva.equalsIgnoreCase("1")) {
						codigoIvaMinimo = code;
					} else if (tipoIva.equalsIgnoreCase("2")) {
						codigoIvaBasico = code;
					} else if (tipoIva.equalsIgnoreCase("6")) {
						codigoIvaExcento = code;
					}
				}

			}
		}

		int nlinea = 1;
		ArrayList<SRProducto_oracle> srproductos = new ArrayList<>();
		nlinea = 1;

		JsonArray productosPostings = folioInfo.getAsJsonArray("Postings");
		ArrayList<SRProducto_oracle> srproductos2 = new ArrayList<>();

		if (productosPostings != null) {

			Double ivaNoGravado = 0.0;
			Double ivaMinimo = 0.0;
			Double ivaMaximo = 0.0;
			Double totalMontoFactura = 0.0;
			Double totalSubTotal = 0.0;

			for (int r = 0; r < productosPostings.size(); r++) {

				String descripcion = "";

				JsonObject rowfields = (JsonObject) productosPostings.get(r);
				// JsonElement customCedula = row.get("customFieldValue");
				String codeGroup = rowfields.get("TrxCode").getAsString();
				Double precioUnitario = rowfields.get("UnitPrice").getAsDouble();
				Double cantidadOrigen = rowfields.get("Quantity").getAsDouble();
				int cantidad = (int) Math.round(cantidadOrigen);
				Double netAmountPadre=0.0;
				Boolean impuestoIncluido = rowfields.get("TaxInclusive").getAsBoolean();
				reserva.setImpuestoIncluido(impuestoIncluido);
				if (rowfields.get("NetAmount") != null) {
				netAmountPadre = rowfields.get("NetAmount").getAsDouble();
				}
				
					
					for (int z = 0; z < revenueBucketInfo.size(); z++) {

						JsonObject rowfields2 = (JsonObject) revenueBucketInfo.get(z);
						// JsonElement customCedula = row.get("customFieldValue");
						JsonArray codearray = rowfields2.get("TrxCode").getAsJsonArray();
						String code="";
						String code2="";
						if(codearray.size()>1) {
							code=codearray.get(0).getAsString();
							code2=codearray.get(1).getAsString();
						}
						code = codearray.get(0).getAsString();

						if (codeGroup.equalsIgnoreCase(code) || codeGroup.equalsIgnoreCase(code2)) {
							descripcion = rowfields2.get("Description").getAsString();
						}
					}

					Boolean taxIncluido = rowfields.get("TaxInclusive").getAsBoolean();

					if (rowfields.get("NetAmount") != null) {
						Double precioTotal = rowfields.get("NetAmount").getAsDouble();

						if (rowfields.get("Generates") != null) {


							JsonObject generates = rowfields.get("Generates").getAsJsonObject();
							JsonArray subgenerates = generates.get("Generate").getAsJsonArray();
							for (int e = 0; e < subgenerates.size(); e++) {

								JsonObject subRowfields = (JsonObject) subgenerates.get(e);
								String subCodeGroup = subRowfields.get("TrxCode").getAsString();
								Double subPrecioUintario = subRowfields.get("UnitPrice").getAsDouble();
								Double subCantidad = subRowfields.get("Quantity").getAsDouble();
								Double subPrecioTotal = precioUnitario * cantidad;
								Boolean subTaxIncluido = subRowfields.get("TaxInclusive").getAsBoolean();
								Double subNetAmount = subRowfields.get("NetAmount").getAsDouble();

								if (subCodeGroup.equalsIgnoreCase(codigoIvaExcento)) {
									ivaNoGravado = subNetAmount;

									// ivaExcento = precioTotal + ivaExcento;
								} else if (subCodeGroup.equalsIgnoreCase(codigoIvaMinimo)) {

									ivaMinimo = subNetAmount;

								} else if (subCodeGroup.equalsIgnoreCase(codigoIvaBasico)) {

									ivaMaximo = subNetAmount;
								}

							}

							SRProducto_oracle srp = new SRProducto_oracle(descripcion, cantidad, netAmountPadre,
									ivaMinimo, ivaMaximo, ivaNoGravado, String.valueOf(nlinea),impuestoIncluido,precioUnitario,
									codeGroup,descripcion);
							srproductos.add(srp);

							nlinea++;
							ivaNoGravado = 0.0;
							ivaMinimo = 0.0;
							ivaMaximo = 0.0;

						}

						

						//System.out.println("total iva excento: " + ivaExcento);
						Double totalivaminima = ivaMinimo / 0.10;
						//System.out.println("total iva minimo: " + totalivaminima);
						Double totalivabasica = ivaMaximo / 0.22;
						//System.out.println("total iva maximo: " + totalivabasica);

					}
				}

			for (int e = 0; e < srproductos.size(); e++) {
				System.out.println(srproductos.get(e).getDescripcion()+"-"+
						srproductos.get(e).getPrecio()+"-"+srproductos.get(e).getIva_10()
						+"-"+srproductos.get(e).getIva_22());
			}
			
				if (docInfo.get("FolioType").getAsString().equalsIgnoreCase("112")) {
					reserva.setTipoDocumentoCfe("112");
					montoTotalFactura = montoTotalFactura * -1;
					reserva.setMontoTotalFactura(montoTotalFactura);
					//montoTotalHabitaciones = montoTotalHabitaciones * -1;
					//reserva.setMontoTotalHabitaciones(montoTotalHabitaciones);
				} else if (docInfo.get("FolioType").getAsString().equalsIgnoreCase("102")) {
					reserva.setTipoDocumentoCfe("102");
					montoTotalFactura = montoTotalFactura * -1;
					reserva.setMontoTotalFactura(montoTotalFactura);
					//montoTotalHabitaciones = montoTotalHabitaciones * -1;
					//reserva.setMontoTotalHabitaciones(montoTotalHabitaciones);
				} else if (docInfo.get("FolioType").getAsString().equalsIgnoreCase("111")) {
					reserva.setTipoDocumentoCfe("111");
					reserva.setTipoDocumentoHuesped("RUT");
				} else if (docInfo.get("FolioType").getAsString().equalsIgnoreCase("101")) {
					reserva.setTipoDocumentoCfe("101");
				}

				if (reservationInfo != null) {

				String fecha = reservationInfo.get("DepartureDate").getAsString();
				reserva.setFechaComprobante(fecha);

				reserva.setFinEstadia(reservationInfo.get("DepartureDate").getAsString());
				reserva.setInicioEstadia(reservationInfo.get("ArrivalDate").getAsString());
				}
				else {
					Calendar c = Calendar.getInstance();
					Date now = c.getTime();
					DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");  
					String hoy = dateFormat.format(now);
					reserva.setFechaComprobante(hoy);
				}

				reserva.setProductos(srproductos);

				reserva.setDocumentoHuesped(cedulaHuesped);
				reserva.setProductos(srproductos);
				
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			String mensaje = e.getMessage();
			
			insertarNoEmitida(idReserva, mensaje, idhotel);
			throw new Exception(e.getMessage());
		}
		
		return reserva;

	}

	private static String capturarJson(String idReserva) {
		// TODO Auto-generated method stub
		String jsonString="";

		try {
			int contador = 0;
			DBDriverPostgreSQLOpera bd = null;
			bd = abrirConexionOpera(bd, contador);
			String consultaR1 = "select * from operawss where procesado=false and idreserva=?";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			psR1.setString(1, idReserva);
			ResultSet rsR1 = null;
			rsR1 = psR1.executeQuery();
			while (rsR1.next()) { 
				 jsonString = rsR1.getString(4);
			}
			// String ultimo="0";
			cerrarConexionOpera(bd, contador);

			rsR1.close();
			psR1.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}	
		
		return jsonString;
	}

	public static ArrayList<String> getReservations(String idReserva) throws IOException {
		/*ultimoToken = obtenerTokenEnBd();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		String token = ultimoToken;
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				.url("https://hotels.cloudbeds.com/api/v1.1/getReservationsWithRateDetails?pageSize=50")
				.method("GET", null).addHeader("Authorization", "Bearer " + token)
				// .addHeader("Cookie",
				// "acessa_session=eyJraWQiOiJ2djdzTjJnRTB0TnhQWVI4OURRZkdoNGx6Qk9lak43VUpmVTgxWWllRFN3IiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULk9rcHhJX3NweTA1anEyNkVyY1d5ZXBXZzdKT3ZMWkVobVNROFpCU3Y0NDQub2FycTg5ZjR3UlBFYUxTR081ZDYiLCJpc3MiOiJodHRwczovL2lkcC5jbG91ZGJlZHMuY29tL29hdXRoMi9hdXNkNWcydTY5QmxKNFdBYzVkNiIsImF1ZCI6Imh0dHBzOi8vaG90ZWxzLmNsb3VkYmVkcy5jb20vYXBpIiwiaWF0IjoxNjY3NDg5NzA2LCJleHAiOjE2Njc0OTMzMDYsImNpZCI6ImxpdmUxXzIxOTQ5Ml9weFBRWW15Z3M5Q0p1U1VUSU5Eb2FBRjEiLCJ1aWQiOiIwMHU2cXQ5OG84RkhCVTZUdjVkNyIsInNjcCI6WyJvZmZsaW5lX2FjY2VzcyJdLCJhdXRoX3RpbWUiOjE2Njc0ODk2NjEsInN1YiI6ImRpZWdvQGZhY3R1cmFsaXN0YS5jb20udXkiLCJ0eXBlIjoicHJvcGVydHkifQ.h5lz_O3ZmuRzVgFaVfcZ9BG1SgGOpv17NG1ZTn5R3onRbI-1_S7e-Q50dYZntUGlh3t5MrE-a-K-WVoQSnJVQD57Iq1E7npDOWnkAIfL96GT_HWoBqi8uq-FMbKa0-lTNcR96Li7aODkBS6mZMT52f1H40SPkEWQar2Kel2RnGnHU3kpl2zFMxnbSCvzKEBqWxL-AG_AK04syPTT1I8zzOgguWlcbBz5undjxOqEix-58uC0Qa-Rp_dWtl-4rczlbrVKZ-c6HvXsDQM3z4HBfkhjfu-d2lzTRLRGfVCbFvwi_XsUsq7699GTgxekOJkLGFQJItidJ3zaoTIPLkd5DA;
				// csrf_accessa_cookie=2bfc4740f4790cf51109830d1fdb4335")
				.build();
		Response response = client.newCall(request).execute();
		/*
		 * Gson gson = new Gson(); ResponseBody responseBody =
		 * client.newCall(request).execute().body(); SimpleEntity entity =
		 * gson.fromJson(responseBody.string(), SimpleEntity.class);
		 * System.out.println(entity);
		 *
		System.out.println("----- RESERVAS ------");
		System.out.println(response.body().string());

		String json = response.body().string();
		return json;*/
		
		boolean existe = false;
		

		ArrayList<String> arrayFacturas = new ArrayList<String>();

		try {
			int contador = 0;
			DBDriverPostgreSQLOpera bd = null;
			bd = abrirConexionOpera(bd, contador);
			String consultaR1 = "select * from operawss where procesado=false and idreserva=?";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			psR1.setString(1, idReserva);
			ResultSet rsR1 = null;
			rsR1 = psR1.executeQuery();
			while (rsR1.next()) { 
				String jsonString = rsR1.getString(4);
				JSONObject json = new JSONObject(jsonString);
			    arrayFacturas.add(jsonString);

			}
			// String ultimo="0";
			cerrarConexionOpera(bd, contador);

			rsR1.close();
			psR1.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return arrayFacturas;
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
	
	private static void insertarNoEmitida(String reserva, String message,String idhotel) throws Exception {
		// TODO Auto-generated method stub
		Calendar c = Calendar.getInstance();
		Date now = c.getTime();
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		String hoy = dateFormat.format(now);

		int contador = 0;
		DBDriverPostgreSQL bd = null;
		bd = abrirConexionPos(bd, contador);
		String consultaR1 = "insert into veniceNoEmitidas (idFactura, fecha, mensaje)" + "values ('" + reserva + "','"
				+ hoy + "','" + message + "')";
		PreparedStatement psR1 = bd.prepareStatement(consultaR1);
		psR1.executeUpdate();
		psR1.close();
		cerrarConexionPos(bd, 1);
		

		contador = 0;
		DBDriverPostgreSQLFacturaLista bdFl = null;
		bdFl = abrirConexionPosFacturaLista(bdFl, contador);
		String consultaR2 = "insert into erroresintegraciones (idFactura, fecha, idhotel, software, coderror, mensaje, solucionado)"
				+ "" + "values ('" + reserva + "','"+ hoy + "','"+idhotel+"','FLVENICE','052023','" + message + "',false)";
		PreparedStatement psR2 = bdFl.prepareStatement(consultaR2);
		psR2.executeUpdate();
		psR2.close();
		cerrarConexionPosFacturaLista(bdFl, 1);

	}
	
	private static DBDriverPostgreSQLFacturaLista abrirConexionPosFacturaLista(DBDriverPostgreSQLFacturaLista bd, int contador) throws Exception {
		if (bd == null) {
			bd = new DBDriverPostgreSQLFacturaLista();
			bd.conectar();
			// System.out.println("Abro conexion " + contador);
		}
		return bd;
	}

	private static DBDriverPostgreSQLFacturaLista cerrarConexionPosFacturaLista(DBDriverPostgreSQLFacturaLista bd, int contador) throws Exception {
		if (bd != null) {
			bd.desconectar();
			// System.out.println("Cerrar conexion " + contador);
		}
		return bd;
	}

	private static void updateIdReserva(int idllamado, String idReserva, String idHotel) {
		// TODO Auto-generated method stub
		try {
			int contador = 0;
			DBDriverPostgreSQLWS bd = null;
			bd = abrirConexionPosWS(bd, contador);
			String consultaR1 = "update operawss set idreserva='"+idReserva+"', idhotel='"+idHotel+"' where idllamado="+idllamado;
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			int result = psR1.executeUpdate();
			// String ultimo="0";
			cerrarConexionPosWS(bd, contador);

			psR1.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		
	}

	public static ArrayList<JsonObject> getFacturas() throws Exception {
		// obtenerCodigo();
		/*
		 * Calendar c = Calendar.getInstance(); Date now = c.getTime();
		 * c.add(Calendar.DATE, -4); Date ayerdate = c.getTime(); DateFormat dateFormat
		 * = new SimpleDateFormat("YYYY-MM-dd"); String ayer =
		 * dateFormat.format(ayerdate); //ayer="2023-04-09";
		 * 
		 * 
		 * Calendar choy = Calendar.getInstance(); Date nowhoy = c.getTime(); Date
		 * hoydate = choy.getTime(); DateFormat dateFormathoy = new
		 * SimpleDateFormat("YYYY-MM-dd"); String hoy = dateFormathoy.format(hoydate);
		 * //hoy="2023-04-09";
		 */
		ArrayList<JsonObject> arrayFacturasCheckedOut = new ArrayList<JsonObject>();

		/*
		 * Boolean check = checkWebService(); if(check==true) {
		 * 
		 * //ultimoToken = obtenerTokenEnBd(); OkHttpClient client = new
		 * OkHttpClient().newBuilder() .build(); MediaType mediaType =
		 * MediaType.parse("application/json"); RequestBody body =
		 * RequestBody.create(mediaType, "{\r\n  \"vendor\": 1,\r\n  \"date_from\": \""
		 * +ayer+"\",\r\n  \"date_to\": \""+hoy+"\"\r\n}"); Request request = new
		 * Request.Builder()
		 * .url("https://s1.venicepms.com/pca025/api/billing/invoices")
		 * //.url("https://s1.venicepms.com/dev000/api/billing/invoices")
		 * .method("POST", body) .addHeader("X-API-KEY",
		 * "8568ff01a7d29277e42d0fefc8a504c772897f3cb2fd25b16e900d88c886")
		 * .addHeader("X-RESORT-CODE", "1") .addHeader("Content-Type",
		 * "application/json") .build(); Response response =
		 * client.newCall(request).execute();
		 * 
		 * //System.out.println("----- RESERVAS ------");s
		 */
		// String json = response.body().string();
		
		//FOR PARA RECORRER LOS ARRAYS
		JsonArray facturas = new JsonArray();
		
		for (int i = 0; i < facturas.size(); i++) {
			
		}

		String json = "[{\"DocumentInfo\":{\"HotelCode\":\"URYY\",\"BillNo\":\"1\",\"FolioType\":\"111\",\"TerminalId\":\"FLIP\",\"ProgramName\":\"\",\"FiscalFolioId\":\"144044\",\"OperaFiscalBillNo\":\"\",\"Application\":\"OPERA_CLOUD\",\"PropertyTaxNumber\":\"432123456\",\"BankName\":\"\",\"BankCode\":\"\",\"BankIdType\":\"\",\"BankIdCode\":\"\",\"BusinessDate\":\"2020-08-19\",\"BusinessDateTime\":\"2020-08-19T13:28:32\",\"CountryCode\":\"UY\",\"CountryName\":\"Uruguay\",\"Command\":\"INVOICE\",\"FiscalTimeoutPeriod\":\"\"},\"AdditionalInfo\":{\"BeforeSettlement\":{},\"ProfileOptions\":{\"NV\":[{\"Name\":\"eInvoice_Address\",\"Value\":\"araca@gmail.com\"}]},\"MappedValues\":{\"MappingType\":[{\"Type\":\"FISCAL_CNTRY_CITIES\"},{\"Type\":\"FISCAL_TRANSPORT\"}]}},\"UserDefinedFields\":{\"CharacterUDFs\":[{\"UDF\":[{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_DATE\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_NO\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_BILL_TIME\"},{\"Name\":\"FLIP_ASSOCIATED_FISCAL_TERMINAL_ID\"},{\"Name\":\"FLIP_CONFIGMODE\",\"Value\":\"DEFAULT\"},{\"Name\":\"FLIP_DELIVERYLAYOUT\",\"Value\":\"FLIPPAYLOAD\"},{\"Name\":\"FLIP_DEPOSIT_TRANSFER\"},{\"Name\":\"FLIP_LOGLEVEL\",\"Value\":\"DEBUG\"},{\"Name\":\"FLIP_NO_PAYMENT_DESCRIPTION\"},{\"Name\":\"FLIP_NO_PAYMENT_SUBTYPE\"},{\"Name\":\"FLIP_NO_PAYMENT_TYPE\"},{\"Name\":\"FLIP_PARTNER_BRANCH_CODE\"},{\"Name\":\"FLIP_PARTNER_CHARGE_ID\"},{\"Name\":\"FLIP_PARTNER_CONTRIBUTOR\"},{\"Name\":\"FLIP_PARTNER_FISCAL_BILL_NO\",\"Value\":\"Y\"},{\"Name\":\"FLIP_PARTNER_FISCAL_INCENTIVE\"},{\"Name\":\"FLIP_PARTNER_FOLIO_TEXT\"},{\"Name\":\"FLIP_PARTNER_MODE\"},{\"Name\":\"FLIP_PARTNER_NETGROSS\"},{\"Name\":\"FLIP_PARTNER_PATTERN\"},{\"Name\":\"FLIP_PARTNER_SERIAL\"},{\"Name\":\"FLIP_PARTNER_SPECIAL_TAX\"},{\"Name\":\"FLIP_PARTNER_TAX1\"},{\"Name\":\"FLIP_PARTNER_TAX2\"},{\"Name\":\"FLIP_PARTNER_TAX3\"},{\"Name\":\"FLIP_PARTNER_TAX4\"},{\"Name\":\"FLIP_PARTNER_TAX5\"},{\"Name\":\"FLIP_PARTNER_TAX_LEVEL\"},{\"Name\":\"FLIP_PARTNER_TAX_QUALIFICATION\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE\"},{\"Name\":\"FLIP_PARTNER_VAT_RATE2\"},{\"Name\":\"FLIP_PERSON_IN_CHARGE\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS1\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS2\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS3\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS4\"},{\"Name\":\"FLIP_PROPERTY_ADDRESS5\"},{\"Name\":\"FLIP_PROPERTY_CODE\",\"Value\":\"Recepcion1\"},{\"Name\":\"FLIP_PROPERTY_COUNTRY\"},{\"Name\":\"FLIP_PROPERTY_ECONOMIC_ID\",\"Value\":\"HOTELERIA\"},{\"Name\":\"FLIP_PROPERTY_ID\",\"Value\":\"2\"},{\"Name\":\"FLIP_RECEIVER_AUTHORITY\"},{\"Name\":\"FLIP_SENDER_AUTHORITY\"},{\"Name\":\"FLIP_SERVER_ADDRESS\",\"Value\":\"GOTERO-X380YOGA.AR.ORACLE.COM\"},{\"Name\":\"FLIP_TAX_FREE_PROPERTY\"},{\"Name\":\"FLIP_TEMPLATEFILE\",\"Value\":\"FLIPTemplateGenericJSON.xslt\"},{\"Name\":\"FLIP_UNIT_CODE\"}]}]},\"FolioInfo\":{\"FolioHeaderInfo\":{\"BillGenerationDate\":\"2020-08-19T13:28:31\",\"FolioType\":\"EFACTURA\",\"CreditBill\":false,\"FolioNo\":\"916199\",\"BillNo\":\"1\",\"InvoiceCurrencyCode\":\"USD\",\"Window\":\"1\",\"CashierNumber\":\"24\",\"FiscalFolioStatus\":\"OK\",\"LocalBillGenerationDate\":\"2020-08-19T13:28:31\",\"CollectingAgentTaxes\":{}},\"PayeeInfo\":{\"NameId\":1275660,\"LastName\":\"Araca la cana\",\"Passport\":\"\",\"Tax1No\":\"\",\"Tax2No\":\"\",\"BusinessId\":\"2\",\"NameTaxType\":\"RES\",\"PaymentDueDate\":\"2020-08-29\",\"UserDefinedFields\":{},\"IdentificationInfos\":{},\"KeywordInfos\":{},\"ArNumber\":\"ARAC-001\",\"Address\":{\"Address1\":\"Av caseros 3073\",\"AddresseeCountryDesc\":\"Uruguay\",\"Country\":\"UY\",\"IsoCode\":\"UY\",\"Primary\":true,\"Type\":\"AR ADDRESS\"},\"Phone\":{\"Number\":\"Av Caseros 3073\",\"Type\":\"BUSINESS\"},\"NameType\":\"COMPANY\"},\"Postings\":[{\"TrxNo\":1001367635,\"TrxCode\":\"2005\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":500.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:17:10\",\"LocalTrxDateTime\":\"2020-08-19T18:17:10\",\"NetAmount\":378.787878787879,\"GrossAmount\":500.0,\"GuestAccountDebit\":500.0,\"TranActionId\":1374496,\"FinDmlSeqNo\":1099729,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:17:10\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":10.0,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:17:10\",\"TrxNo\":1001367638,\"TrxType\":\"C\",\"UnitPrice\":37.88,\"FinDmlSeqNo\":1099730,\"NetAmount\":37.878787878788,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374496,\"TrxNoAddedBy\":1001367635},{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:17:10\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":22.0,\"TrxCode\":\"8505\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:17:10\",\"TrxNo\":1001367639,\"TrxType\":\"C\",\"UnitPrice\":83.33,\"FinDmlSeqNo\":1099731,\"NetAmount\":83.333333333333,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":1374496,\"TrxNoAddedBy\":1001367635}]}},{\"TrxNo\":1001367645,\"TrxCode\":\"8525\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":0.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:18:01\",\"LocalTrxDateTime\":\"2020-08-19T18:18:01\",\"NetAmount\":0.0,\"TrxNoAddedBy\":1001367643,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":1374500,\"FinDmlSeqNo\":1099735},{\"TrxNo\":1001367638,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":37.88,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:17:10\",\"LocalTrxDateTime\":\"2020-08-19T18:17:10\",\"NetAmount\":37.878787878788,\"TrxNoAddedBy\":1001367635,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374496,\"FinDmlSeqNo\":1099730},{\"TrxNo\":1001367639,\"TrxCode\":\"8505\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":83.33,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:17:10\",\"LocalTrxDateTime\":\"2020-08-19T18:17:10\",\"NetAmount\":83.333333333333,\"TrxNoAddedBy\":1001367635,\"Reference\":\"[Add: 22%.(B)]\",\"TranActionId\":1374496,\"FinDmlSeqNo\":1099731},{\"TrxNo\":1001367643,\"TrxCode\":\"2015\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":300.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:18:01\",\"LocalTrxDateTime\":\"2020-08-19T18:18:01\",\"NetAmount\":300.0,\"GrossAmount\":300.0,\"GuestAccountDebit\":300.0,\"TranActionId\":1374500,\"FinDmlSeqNo\":1099734,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:18:01\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":0.0,\"TrxCode\":\"8525\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:18:01\",\"TrxNo\":1001367645,\"TrxType\":\"C\",\"UnitPrice\":0.0,\"FinDmlSeqNo\":1099735,\"NetAmount\":0.0,\"Reference\":\"[Add: 0%.(B)]\",\"TranActionId\":1374500,\"TrxNoAddedBy\":1001367643}]}},{\"TrxNo\":1001367632,\"TrxCode\":\"1000\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":1000.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:16:50\",\"LocalTrxDateTime\":\"2020-08-19T18:16:50\",\"NetAmount\":1000.0,\"GrossAmount\":1000.0,\"GuestAccountDebit\":1000.0,\"TranActionId\":1374494,\"FinDmlSeqNo\":1099727,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:16:50\",\"Quantity\":1.0,\"TaxInclusive\":false,\"TaxRate\":10.0,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:16:50\",\"TrxNo\":1001367634,\"TrxType\":\"C\",\"UnitPrice\":100.0,\"FinDmlSeqNo\":1099728,\"GrossAmount\":100.0,\"GuestAccountDebit\":100.0,\"NetAmount\":100.0,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374494,\"TrxNoAddedBy\":1001367632}]}},{\"TrxNo\":1001367642,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":36.36,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:17:51\",\"LocalTrxDateTime\":\"2020-08-19T18:17:51\",\"NetAmount\":36.363636363636,\"TrxNoAddedBy\":1001367640,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374498,\"FinDmlSeqNo\":1099733},{\"TrxNo\":1001367634,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":100.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:16:50\",\"LocalTrxDateTime\":\"2020-08-19T18:16:50\",\"NetAmount\":100.0,\"GrossAmount\":100.0,\"TrxNoAddedBy\":1001367632,\"Reference\":\"[Add: 10%Prices.(B)]\",\"GuestAccountDebit\":100.0,\"TranActionId\":1374494,\"FinDmlSeqNo\":1099728},{\"TrxNo\":1001367640,\"TrxCode\":\"3000\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"C\",\"UnitPrice\":400.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":true,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T18:17:51\",\"LocalTrxDateTime\":\"2020-08-19T18:17:51\",\"NetAmount\":363.636363636364,\"GrossAmount\":400.0,\"GuestAccountDebit\":400.0,\"TranActionId\":1374498,\"FinDmlSeqNo\":1099732,\"Generates\":{\"Generate\":[{\"Currency\":\"UYU\",\"ExchangeRate\":1.0,\"LocalTrxDateTime\":\"2020-08-19T18:17:51\",\"Quantity\":1.0,\"TaxInclusive\":true,\"TaxRate\":10.0,\"TrxCode\":\"8500\",\"TrxDate\":\"2020-08-19\",\"TrxDateTime\":\"2020-08-19T18:17:51\",\"TrxNo\":1001367642,\"TrxType\":\"C\",\"UnitPrice\":36.36,\"FinDmlSeqNo\":1099733,\"NetAmount\":36.363636363636,\"Reference\":\"[Add: 10%Prices.(B)]\",\"TranActionId\":1374498,\"TrxNoAddedBy\":1001367640}]}},{\"TrxNo\":1001369131,\"TrxCode\":\"9000\",\"TrxDate\":\"2020-08-19\",\"TrxType\":\"FC\",\"UnitPrice\":2300.0,\"Quantity\":1.0,\"Currency\":\"UYU\",\"TaxInclusive\":false,\"ExchangeRate\":1.0,\"TrxDateTime\":\"2020-08-19T13:28:14\",\"LocalTrxDateTime\":\"2020-08-19T13:28:14\",\"GuestAccountCredit\":2300.0,\"TranActionId\":1375750,\"FinDmlSeqNo\":1100719,\"ReceiptNumber\":1,\"ReceiptType\":\"PAR\"}],\"RevenueBucketInfo\":[{\"BucketCode\":\"CASH\",\"BucketType\":\"FLIP_PAY_SUBTYPE\",\"BucketValue\":\"1\",\"Description\":\"Cash Payments\",\"BucketCodeTotalGross\":2300.0,\"TrxCode\":[\"9000\"]},{\"BucketCode\":\"GROUP_1\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Habitaciones\",\"BucketCodeTotalGross\":1000.0,\"TrxCode\":[\"1000\"]},{\"BucketCode\":\"GROUP_10\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Food Lunch\",\"BucketCodeTotalGross\":500.0,\"TrxCode\":[\"2005\"]},{\"BucketCode\":\"GROUP_4\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Tips\",\"BucketCodeTotalGross\":300.0,\"TrxCode\":[\"2015\"]},{\"BucketCode\":\"GROUP_5\",\"BucketType\":\"FLIP_TRX_BY_GRP\",\"Description\":\"Beverage Breakfast\",\"BucketCodeTotalGross\":400.0,\"TrxCode\":[\"3000\"]},{\"BucketCode\":\"TAX_2\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"2\",\"Description\":\"Iva Tasa Minima\",\"BucketCodeTotalGross\":100.0,\"TrxCode\":[\"8500\"]},{\"BucketCode\":\"TAX_3\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"3\",\"Description\":\"IVA Tasa Basica\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8505\"]},{\"BucketCode\":\"TAX_5\",\"BucketType\":\"FLIP_TAX_CODES\",\"BucketValue\":\"6\",\"Description\":\"Producto o Servicio no facturable\",\"BucketCodeTotalGross\":0.0,\"TrxCode\":[\"8525\"]}],\"TotalInfo\":{\"NetAmount\":2042.42424242424,\"GrossAmount\":2300.0,\"NonTaxableAmount\":0.0,\"PaidOut\":0.0,\"Taxes\":{\"Tax\":[{\"Name\":\"1\",\"Value\":174.242424242424,\"NetAmount\":1742.424242424243,\"Percent\":\"10.00\",\"Amount\":\"\"},{\"Name\":\"2\",\"Value\":83.333333333333,\"NetAmount\":378.787878787879,\"Percent\":\"22.00\",\"Amount\":\"\"},{\"Name\":\"6\",\"Value\":0.0,\"NetAmount\":300.0,\"Percent\":\"0.00\",\"Amount\":\"\"}]}},\"TrxInfo\":[{\"HotelCode\":\"URYY\",\"Group\":\"ROOM\",\"SubGroup\":\"ROOM\",\"Code\":\"1000\",\"TrxType\":\"C\",\"Description\":\"Accommodation - Tax Inclusive\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"L\"},{\"HotelCode\":\"URYY\",\"Group\":\"FB\",\"SubGroup\":\"FOOD\",\"Code\":\"2005\",\"TrxType\":\"C\",\"Description\":\"Food Inclusive  - Lunch\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"F\"},{\"HotelCode\":\"URYY\",\"Group\":\"TIPS\",\"SubGroup\":\"TIPS\",\"Code\":\"2015\",\"TrxType\":\"C\",\"Description\":\"Food Inclusive  - Gratuities\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"N\"},{\"HotelCode\":\"URYY\",\"Group\":\"FB\",\"SubGroup\":\"BEVERAGE\",\"Code\":\"3000\",\"TrxType\":\"C\",\"Description\":\"Beverage Inclusive - Breakfast\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"F\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8500\",\"TrxType\":\"C\",\"TaxCodeNo\":1,\"Description\":\"IVA 10%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8505\",\"TrxType\":\"C\",\"TaxCodeNo\":2,\"Description\":\"IVA 22%\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"TAX\",\"SubGroup\":\"TAX\",\"Code\":\"8525\",\"TrxType\":\"C\",\"TaxCodeNo\":6,\"Description\":\"No billable\",\"Articles\":{},\"TranslatedDescriptions\":{},\"TrxCodeType\":\"X\"},{\"HotelCode\":\"URYY\",\"Group\":\"PAYMENT\",\"SubGroup\":\"CASH\",\"Code\":\"9000\",\"TrxType\":\"FC\",\"Description\":\"Cash\",\"Articles\":{},\"TranslatedDescriptions\":{}}]},\"HotelInfo\":{\"HotelCode\":\"URYY\",\"HotelName\":\"OPERA Cloud Uruguay Property\",\"Address\":{\"Country\":\"UY\"},\"LocalCurrency\":\"UYU\",\"CurrencySymbol\":\"$U\",\"Decimals\":\"2\",\"ExchangeRates\":{\"ExchangeRateInfo\":[{\"CurrencyCode\":\"USD\"}]},\"PropertyDateTime\":\"2021-06-28T13:28:32\"},\"ReservationInfo\":{\"ConfirmationNo\":\"1522159\",\"ResvNameID\":\"936813\",\"ArrivalDate\":\"2020-08-19\",\"NumberOfNights\":\"1\",\"DepartureDate\":\"2020-08-20\",\"NumAdults\":\"1\",\"NumChilds\":\"0\",\"GuestInfo\":{\"NameId\":1275660,\"LastName\":\"Araca la cana\",\"Passport\":\"\",\"Tax1No\":\"\",\"Tax2No\":\"\",\"BusinessId\":\"2\",\"NameTaxType\":\"RES\",\"PaymentDueDate\":\"2020-08-29\",\"UserDefinedFields\":{},\"IdentificationInfos\":{},\"KeywordInfos\":{},\"ArNumber\":\"ARAC-001\",\"Address\":{\"Address1\":\"Av caseros 3073\",\"AddresseeCountryDesc\":\"Uruguay\",\"Country\":\"UY\",\"IsoCode\":\"UY\",\"Primary\":true,\"Type\":\"AR ADDRESS\"},\"Phone\":{\"Number\":\"Av Caseros 3073\",\"Type\":\"BUSINESS\"}},\"NameTaxType\":\"RES\",\"RoomRate\":1100.0,\"RatePlanCode\":\"RACK\",\"RoomNumber\":\"103\",\"RoomClass\":\"ALL\",\"RoomType\":\"SINGLE\",\"NumberOfRooms\":\"1\",\"Guarantee\":\"CHECKED IN\",\"MarketCode\":\"COR\",\"ResStatus\":\"CHECKED IN\",\"UserDefinedFields\":{}},\"FiscalFolioUserInfo\":{\"AppUser\":\"GASTONVAF@VAFISCAL\",\"AppUserId\":\"51457\",\"EmployeeNumber\":\"\",\"CashierId\":\"\"}}]";

		System.out.println(json);

		// JsonObject jsonObjectReservations = new
		// JsonParser().parse(json).getAsJsonObject();
		//JsonArray facturas = new JsonParser().parse(json).getAsJsonArray();

		for (int i = 0; i < facturas.size(); i++) {
			JsonObject row = (JsonObject) facturas.get(i);
			// String nroFactura = row.get("number").getAsString();

			arrayFacturasCheckedOut.add(row);
		}

		// actualizarWebService();

		// }
		// System.out.println(arrayFacturasCheckedOut);
		return arrayFacturasCheckedOut;

	}

	private static void actualizarWebService() throws Exception {
		// TODO Auto-generated method stub
		int contador = 0;
		DBDriverPostgreSQL bd = null;
		bd = abrirConexionPos(bd, contador);
		String consultaR1 = "update venicewebservice set bandera =?";
		PreparedStatement psR1 = bd.prepareStatement(consultaR1);
		psR1.setBoolean(1, false);
		psR1.executeUpdate();
		psR1.close();
		cerrarConexionPos(bd, 1);

	}

	private static Boolean checkWebService() {

		boolean existe = false;

		try {
			int contador = 0;
			DBDriverPostgreSQLWS bd = null;
			bd = abrirConexionPosWS(bd, contador);
			String consultaR1 = "select nrointernovenice from venicewebservice where bandera=false";
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

	public static void facturar() throws Exception {
		ArrayList<JsonObject> facturas = getFacturas();
		InterfazFL_Opera i = new InterfazFL_Opera();
		// i.
	}

	public static String getReservationsPorFecha(String fecha) throws IOException {
		ultimoToken = obtenerTokenEnBd();

		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM).addFormDataPart("date", fecha)
				.build();
		String token = ultimoToken;

		Request request = new Request.Builder()
				.url("https://hotels.cloudbeds.com/api/v1.1/getReservationAssignments?date=" + fecha)
				.method("GET", null).addHeader("Authorization", "Bearer " + token)
				// .addHeader("Cookie",
				// "acessa_session=2a9c998c2df5a5400cd833f9aaa1627e57c6610d;
				// csrf_accessa_cookie=2bfc4740f4790cf51109830d1fdb4335")
				.build();
		Response response = client.newCall(request).execute();
		String json = response.body().string();

		return json;

		/*
		 * JsonObject jsonReservas = new JsonParser().parse(json).getAsJsonObject();
		 * 
		 * JsonArray reservas = jsonReservas.getAsJsonArray("data"); String idReserva
		 * ="0"; ArrayList<String> arrayIdReservas = new ArrayList<>();; for (int i = 0;
		 * i< reservas.size(); i++){ System.out.println(reservas.get(i).toString());
		 * JsonObject row = (JsonObject) reservas.get(i); idReserva =
		 * row.get("reservationID").getAsString(); arrayIdReservas.add(idReserva); } //n
		 */

	}

	public static String getReservationInvoiceById(String idReserva) throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				// .url("https://s1.venicepms.com/oxf999/api/billing/invoices/"+idReserva)
				.url("https://s1.venicepms.com/pca025/api/billing/invoices/" + idReserva).method("GET", null)
				// .addHeader("X-API-KEY",
				// "c250f4a5efecc56549c03b25a7edf14c0c795cf9a2f8d201807bae5e4794")
				.addHeader("X-API-KEY", "8568ff01a7d29277e42d0fefc8a504c772897f3cb2fd25b16e900d88c886")
				.addHeader("X-RESORT-CODE", "1").build();
		Response response = client.newCall(request).execute();
		String json = response.body().string();
		// System.out.println(json);

		return json;
	}

	public static String obtenerToken() throws IOException {
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
				.addFormDataPart("grant_type", "authorization_code")
				.addFormDataPart("client_id", "live1_219492_jbtkWpeXZLmQrsAD8aohYxU4")
				.addFormDataPart("client_secret", "o5RlCbG8rmHeKpiczEgyOBD0QfVLUYx9")
				.addFormDataPart("redirect_uri", "https://www.test.com")
				.addFormDataPart("code", "FYjo_sVjpPDA1nAIUnQdG7KSUm9c5jMR3y8Ix2jLnM8").build();
		Request request = new Request.Builder().url("https://hotels.cloudbeds.com/api/v1.1/access_token")
				.method("POST", body)
				.addHeader("Cookie",
						"acessa_session=31ce0b4b7247a71f4c25387dba4984e8e489b64b; csrf_accessa_cookie=bbe080c03777f4f157cfc0fba97e7498; HotelLng=en")
				.build();
		Response response = client.newCall(request).execute();
		String json = response.body().string();
		System.out.println(json);

		return json;

	}

	public static String obtenerCodigo() throws IOException {
		ultimoRefreshToken = obtenerRefreshTokenEnBd();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		RequestBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
				.addFormDataPart("grant_type", "refresh_token")
				.addFormDataPart("client_id", "live1_219492_jbtkWpeXZLmQrsAD8aohYxU4")
				.addFormDataPart("client_secret", "o5RlCbG8rmHeKpiczEgyOBD0QfVLUYx9")
				.addFormDataPart("refresh_token", ultimoRefreshToken).build();
		Request request = new Request.Builder().url("https://hotels.cloudbeds.com/api/v1.1/access_token")
				.method("POST", body)
				.addHeader("Cookie",
						"acessa_session=e0fbcf6741d1fc18baf79441a26b493e4c901569; csrf_accessa_cookie=915333cda9d7ea075477785762084426; HotelLng=en")
				.build();
		Response response = client.newCall(request).execute();
		String json = response.body().string();
		// System.out.println(json);
		readJsonCodigo(json);
		return "";

	}

	public static void readJsonCodigo(String string) {
		JsonObject jsonObject = new JsonParser().parse(string).getAsJsonObject();
		String refreshToken = jsonObject.get("refresh_token").getAsString();
		String ultimoTokenb = jsonObject.get("access_token").getAsString();

		// System.out.println(refreshToken);
		// System.out.println(ultimoTokenb);
		ultimoRefreshToken = refreshToken;
		ultimoToken = ultimoTokenb;
		guardarTokekEnBd(ultimoRefreshToken, ultimoToken);

	}

	public static void guardarTokekEnBd(String ultimoRefreshToken, String ultimoToken) {

		try {
			int contador = 0;
			DBDriverPostgreSQL bd = null;
			bd = abrirConexionPos(bd, contador);
			String consultaR1 = "update cloudbeds set ultimorefreshtoken ='" + ultimoRefreshToken + "'";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			psR1.executeUpdate();

			String consultaR2 = "update cloudbeds set ultimotoken ='" + ultimoToken + "'";
			PreparedStatement psR2 = bd.prepareStatement(consultaR2);
			psR2.executeUpdate();

			cerrarConexionPos(bd, contador);
			psR1.close();
			psR2.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}

	public static String obtenerRefreshTokenEnBd() {
		String ultimo = "0";
		try {
			int contador = 0;
			DBDriverPostgreSQL bd = null;
			bd = abrirConexionPos(bd, contador);
			String consultaR1 = "select ultimoRefreshToken from cloudbeds";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			ResultSet rsR1 = null;
			rsR1 = psR1.executeQuery();

			while (rsR1.next()) {
				ultimo = rsR1.getString(1);
			}
			cerrarConexionPos(bd, contador);

			rsR1.close();
			psR1.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return ultimo;

	}

	public static String obtenerTokenEnBd() {
		String ultimo = "0";
		try {
			int contador = 0;
			DBDriverPostgreSQL bd = null;
			bd = abrirConexionPos(bd, contador);
			String consultaR1 = "select ultimoToken from cloudbeds";
			PreparedStatement psR1 = bd.prepareStatement(consultaR1);
			ResultSet rsR1 = null;
			rsR1 = psR1.executeQuery();

			while (rsR1.next()) {
				ultimo = rsR1.getString(1);
			}
			cerrarConexionPos(bd, contador);

			rsR1.close();
			psR1.close();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		return ultimo;

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

	private static String base64EncodedBasicAuthentication() {
		// TODO Auto-generated method stub
		return null;
	}

	private static DBDriverPostgreSQL abrirConexionPos(DBDriverPostgreSQL bd, int contador) throws Exception {
		if (bd == null) {
			bd = new DBDriverPostgreSQL();
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

	public static String getReservationById(String idReserva) throws IOException {
		ultimoToken = obtenerTokenEnBd();
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		MediaType mediaType = MediaType.parse("text/plain");
		String token = ultimoToken;
		RequestBody body = RequestBody.create(mediaType, "");
		Request request = new Request.Builder()
				.url("https://hotels.cloudbeds.com/api/v1.1/getReservation?reservationID=" + idReserva + "")
				.method("GET", null).addHeader("Authorization", "Bearer " + token)
				// .addHeader("Cookie",
				// "acessa_session=eyJraWQiOiJ2djdzTjJnRTB0TnhQWVI4OURRZkdoNGx6Qk9lak43VUpmVTgxWWllRFN3IiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULk9rcHhJX3NweTA1anEyNkVyY1d5ZXBXZzdKT3ZMWkVobVNROFpCU3Y0NDQub2FycTg5ZjR3UlBFYUxTR081ZDYiLCJpc3MiOiJodHRwczovL2lkcC5jbG91ZGJlZHMuY29tL29hdXRoMi9hdXNkNWcydTY5QmxKNFdBYzVkNiIsImF1ZCI6Imh0dHBzOi8vaG90ZWxzLmNsb3VkYmVkcy5jb20vYXBpIiwiaWF0IjoxNjY3NDg5NzA2LCJleHAiOjE2Njc0OTMzMDYsImNpZCI6ImxpdmUxXzIxOTQ5Ml9weFBRWW15Z3M5Q0p1U1VUSU5Eb2FBRjEiLCJ1aWQiOiIwMHU2cXQ5OG84RkhCVTZUdjVkNyIsInNjcCI6WyJvZmZsaW5lX2FjY2VzcyJdLCJhdXRoX3RpbWUiOjE2Njc0ODk2NjEsInN1YiI6ImRpZWdvQGZhY3R1cmFsaXN0YS5jb20udXkiLCJ0eXBlIjoicHJvcGVydHkifQ.h5lz_O3ZmuRzVgFaVfcZ9BG1SgGOpv17NG1ZTn5R3onRbI-1_S7e-Q50dYZntUGlh3t5MrE-a-K-WVoQSnJVQD57Iq1E7npDOWnkAIfL96GT_HWoBqi8uq-FMbKa0-lTNcR96Li7aODkBS6mZMT52f1H40SPkEWQar2Kel2RnGnHU3kpl2zFMxnbSCvzKEBqWxL-AG_AK04syPTT1I8zzOgguWlcbBz5undjxOqEix-58uC0Qa-Rp_dWtl-4rczlbrVKZ-c6HvXsDQM3z4HBfkhjfu-d2lzTRLRGfVCbFvwi_XsUsq7699GTgxekOJkLGFQJItidJ3zaoTIPLkd5DA;
				// csrf_accessa_cookie=2bfc4740f4790cf51109830d1fdb4335")
				.build();
		Response response = client.newCall(request).execute();
		/*
		 * Gson gson = new Gson(); ResponseBody responseBody =
		 * client.newCall(request).execute().body(); SimpleEntity entity =
		 * gson.fromJson(responseBody.string(), SimpleEntity.class);
		 * System.out.println(entity);
		 */
		System.out.println("----- RESERVA ------");
		// System.out.println(response.body().string());
		String json = response.body().string();
		// System.out.println(json);

		return json;
	}

	public JsonArray getArrayCodigos() {
		// TODO Auto-generated method stub
		
		JsonArray arrayCodes = revenueBucketInfo;
		//rrayCodes=revenueBucketInfo;
		/*for (int z = 0; z < revenueBucketInfo.size(); z++) {
			JsonObject rowfields2 = (JsonObject) revenueBucketInfo.get(z);
			// JsonElement customCedula = row.get("customFieldValue");
			JsonArray codearray = rowfields2.get("TrxCode").getAsJsonArray();
			String group = rowfields2.get("BucketType").getAsString();
			//String groupdescripcion = group.getAsString();
			String code = "";
			for(int a=0;a<codearray.size();a++) {
				code=codearray.get(a).getAsString();
				if(group.equalsIgnoreCase("FLIP_TRX_BY_GRP")) {
					arrayCodes.add(code);
				}
			}
		}*/
		
		return arrayCodes;

	}

}
