package servicios;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import bd.Direccionador;

public class SendMail {

    public static void main(String[] args) {

    	//enviarFactura("12");

    }
    
    public String enviarFactura(String numero, String destinatario, String asunto, String cuerpo) {
    	
    	Properties propiedades = Direccionador.getInstance().getArchivoConfiguracion();
        // Recipient's email ID needs to be mentioned.
        String to = destinatario;

        // Sender's email ID needs to be mentioned
        String username = propiedades.getProperty("remitente");

        // Assuming you are sending email from through gmails smtp
        String host = propiedades.getProperty("host");
        
        String password = propiedades.getProperty("claveEmail");

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", propiedades.getProperty("port"));
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new GMailAuthenticator(username, password));

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));

            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            message.setSubject(asunto);

            message.setText(cuerpo);

			MimeBodyPart messageBodyPart2 = new MimeBodyPart();

			String filename = "C:\\FLOfiHotel\\Imprimibles\\Factura"+numero+".pdf";// change accordingly
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart2);

			message.setContent(multipart);
			
            System.out.println("sending...");
            message.setText(cuerpo);
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
            
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
        
        return "exito";
    }

}