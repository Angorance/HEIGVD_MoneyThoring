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
		String objet = "Activation code for your MoneyThoring account";
		
		String texte = "Welcome to MoneyThoring " + username + ", \n\n"
						+ "To use this app, you need to activate your account with \n"
						+ "this code on your next connection : " + activationCode + " \n\n"
						+ "Thanks for joining us \n\n"
						+ "MoneyThoring";
		
		Properties props = System.getProperties();
		props.put("mail.smtp.host", smtpServer);
		Session session = Session.getDefaultInstance(props, null);
		
		// Create the message
		Message msg = new MimeMessage(session);
		
		try {
			
			// Fill the message
			msg.setFrom(new InternetAddress(from));
			msg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to, false));
			msg.setSubject(objet);
			msg.setText(texte);
			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
			msg.addHeader("format", "flowed");
			msg.addHeader("Content-Transfer-Encoding", "8bit");
			
			// Send the message
			Transport.send(msg);
			
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
