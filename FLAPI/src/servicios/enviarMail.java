package servicios;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import bd.Direccionador;

public class enviarMail {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();
		//url = propiedades.getProperty("servidorbd");
		String to = "soporte@facturalista.com.uy";// change accordingly
		final String user = "santi.buzik@gmail.com";// change accordingly
		final String password = "Camel3pill";// change accordingly
		

		// 1) get the session object
		Properties properties = System.getProperties();
		
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", propiedades.getProperty("port"));
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable","true");
		properties.setProperty("mail.smtp.ssl.enable", "true");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 2) compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("TEST FACTURA");

			// 3) create MimeBodyPart object and set your message text
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("This is message body");

			// 4) create new MimeBodyPart object and set DataHandler object to this object
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filename = "C:\\FacturaLista\\Instaladores\\configuracion.txt";// change accordingly
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);

			// 5) create Multipart object and add MimeBodyPart objects to this object
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			// 6) set the multiplart object to the message object
			message.setContent(multipart);

			// 7) send message
			Transport.send(message);

			//return "exito";
		} catch (MessagingException ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
			//return "error";
		} 
	}

	public String enviar(String receptor) {
		Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();
		String to = receptor;// change accordingly
		final String user = propiedades.getProperty("remitente");// change accordingly
		final String password = propiedades.getProperty("claveEmail");// change accordingly
		

		// 1) get the session object
		Properties properties = System.getProperties();
		
		properties.setProperty(propiedades.getProperty("smtp1"), propiedades.getProperty("smtp2"));
		properties.put("mail.smtp.port", propiedades.getProperty("port"));
		properties.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		// 2) compose message
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("TEST FACTURA");

			// 3) create MimeBodyPart object and set your message text
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("This is message body");

			// 4) create new MimeBodyPart object and set DataHandler object to this object
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filename = "C:\\FacturaLista\\Instaladores\\configuracion.txt";// change accordingly
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);

			// 5) create Multipart object and add MimeBodyPart objects to this object
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart2);

			// 6) set the multiplart object to the message object
			message.setContent(multipart);

			// 7) send message
			Transport.send(message);

			return "exito";
		} catch (MessagingException ex) {
			ex.printStackTrace();
			return "error";
		}
	}
}
