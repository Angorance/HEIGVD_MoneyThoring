package smtp;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * Class used to send mail with activation code to new clients.
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class Mail {
	
	/**
	 * Send the activation code to the new client.
	 *
	 * @param username          Username of the new client.
	 * @param email             Email of the new client.
	 * @param activationCode    Activation code of the new client.
	 */
	public static void sendMail(String username, String email, String activationCode) {
		
		// Parameters of the email
		String smtpServer = "mail.infomaniak.com";
		String from = "noreply-moneythoring@angorance.ch";
		String to = email;
		String objet = "Code d'activation pour votre compte MoneyThoring";
		
		String texte = "Bienvenue chez MoneyThoring " + username + ", <br><br>"
						+ "Pour utiliser cette application vous devez activer votre compte avec <br>"
						+ "ce code lors de votre prochaine connexion : " + activationCode + "<br><br>"
						+ "Merci d'utiliser nos services <br><br>"
						+ "MoneyThoring";
		
		// Connection to the smtp server
		Authenticator auth = new SMTPAuthenticator();
		
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		
		Session session = Session.getDefaultInstance(props, auth);
		
		// Create the message
		Message msg = new MimeMessage(session);
		
		try {
			
			// Fill the message
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
			msg.setSubject(objet);
			msg.setText(texte);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			
			// Send the message
			Transport.send(msg);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
