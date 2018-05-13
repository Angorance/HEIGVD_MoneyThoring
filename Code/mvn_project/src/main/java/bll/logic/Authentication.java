package bll.logic;

import bll.mappers.DAL.DALClientMapper;
import bll.model.ClientModel;
import dal.ientites.IDALClientEntity;
import dal.irepositories.IClientRepository;
import dal.orm.IORM;
import dal.orm.MasterORM;
import dal.orm.PgORM;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.codec.binary.Base64;

/**
 * TODO
 * @author Daniel Gonzalez Lopez
 * @version 2.0
 */
public class Authentication {
	
	private static final Random RANDOM = new SecureRandom();
	
    // Regex to check email format. Found on the internet
    // stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]"
            + "+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|"
            + "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	
	/**
	 * Check if the registration of the client is correct.
	 *
	 * @param username Username entered by the client.
	 * @param email Email entered by the client.
	 * @param password1 Password asked.
	 * @param password2 Confirmation password.
	 *
	 * @return result[0] : true if the username is correct, false otherwise.
	 *         result[1] : true if the email is correct, false otherwise.
	 *         result[2] : true if the password is correct, false otherwise.
	 */
	public static boolean[] checkRegistration(String username, String email, String password1, String password2) {
		
		IORM orm = MasterORM.getInstance().getPgORM(); // TODO - Manage if connected to internet or not!
		
		boolean usernameIsCorrect = false;
		boolean emailIsCorrect = false;
		boolean passwordIsCorrect = false;
		
		try {
			orm.beginTransaction();
			
			IClientRepository client = orm.getClientRepository();
			
			// Check if the username is available
			if(!username.isEmpty()) {
				usernameIsCorrect = !client.pseudoExist(username);
			}
			
			// Check if the email is available and correct
			if(!email.isEmpty()) {
				emailIsCorrect = !client.mailExist(email) && checkEmailFormat(email);
			}
			
			// Check the password
			if(!password1.isEmpty() & !password2.isEmpty()) {
				passwordIsCorrect = password1.equals(password2);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Create the result
		boolean[] result = {usernameIsCorrect, emailIsCorrect, passwordIsCorrect};
		
		return result;
	}
	
	/**
	 * Check the format of the email entered by the client.
	 *
	 * @param email Email entered by the client.
	 *
	 * @return True if it's an email with valid format, false otherwise.
	 */
	public static boolean checkEmailFormat(String email) {
		
		// For now, regex. Maybe better with Apache commons validator or
		// Java InternetAddress.
		Pattern p = java.util.regex.Pattern.compile(EMAIL_REGEX);
		Matcher m = p.matcher(email);
		
		return m.matches();
	}
	
	/**
	 * Try to connect the client to MoneyThoring.
	 *
	 * @param username  Username or email entered by the client.
	 * @param password  Password entered by the client.
	 * @return  true if the client is connected, false otherwise.
	 */
	public static boolean connect(String username, String password) {
    	
    	boolean success = false;
    	
    	if (!password.isEmpty()) {
		
		    IORM orm = MasterORM.getInstance().getPgORM();
		    String salt;
		
		    try {
			    orm.beginTransaction();
			
			    IClientRepository client = orm.getClientRepository();
			    
			    // Get the salt of the client
			    salt = client.retriveSaltByUserLogin(username);
			
			    // Hash the password with the salt
			    String hash = hash(password, salt);
			
			    // Check if the client exists
			    IDALClientEntity ce = client
					    .checkUserAndPassword(username, hash);
			    
			    if (ce != null) {
				    DALClientMapper.toBo(ce);
				    ClientLogic.getInstance().setDataFromDB();
				    success = true;
			    }
			
		    } catch (Exception e) {
			    e.printStackTrace();
		    }
	    }
	    
	    return success;
	}
	
	/**
	 * Check if the activation code entered by the client is the good one.
	 *
	 * @param activationCode    Code entered by the client.
	 *
	 * @return True if the code is the good one, false otherwise.
	 */
	public static boolean checkActivationCode(String activationCode) {
		
		boolean isCorrect = false;
		
		// Check if the code is correct
		if(ClientLogic.getInstance().getKey().equals(activationCode)) {
			
			isCorrect = true;
			
			// Activate the client
			ClientLogic.getInstance().setActivated(true);
			
			// Update the database
			ClientLogic.getInstance().updateClientToDB();
		}
		
		return isCorrect;
	}
	
	private static String saltGenerator() {
		byte[] salt = new byte[16];
		
		RANDOM.nextBytes(salt);
		
		String salty = salt.toString();
		ClientLogic.getInstance().setSalt(salty);
		
		return salty;
	}
	
	public static String hash(String password, String salt)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.update((password + salt).getBytes("UTF-8"));
		
		String hashed = Base64.encodeBase64URLSafeString(md.digest());
		
		return hashed;
	}
	
	public static String hash(String password)
			throws UnsupportedEncodingException, NoSuchAlgorithmException {
		
		String salt = saltGenerator();
		
		return hash(password, salt);
	}
	
	public static void disconnect() {
		
		ClientLogic.getInstance().wipe();
	}
}
