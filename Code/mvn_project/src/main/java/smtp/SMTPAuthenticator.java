package smtp;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * Class used to create a SMTP authentication.
 *
 * @author Héléna Line Reymond
 * @version 1.0
 */
public class SMTPAuthenticator extends Authenticator {
	
	protected PasswordAuthentication getPasswordAuthentication() {
		
		return new PasswordAuthentication("noreply-moneythoring@angorance.ch",
				"jaimelechocolat@");
	}
}
