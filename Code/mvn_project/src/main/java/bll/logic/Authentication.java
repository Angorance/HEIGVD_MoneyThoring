package bll.logic;

import dal.orm.IORM;
import dal.orm.PgORM;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @authors Daniel Gonzalez Lopez, Héléna Line Reymond
 */
public class Authentication {

    // Regex to check email format. Found on the internet
    // stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]"
            + "+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|"
            + "(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";

    /**
     * Check if the passwords inserted by the client are equals.
     *
     * @param pass1 Password asked.
     * @param pass2 Confirmation password.
     *
     * @return True if the client typed same passwords, false otherwise.
     */
    public static boolean checkPasswords(String pass1, String pass2) {
        return pass1.equals(pass2);
    }

    /**
     * Check email validity.
     * An email is valid when it is available and has a good format.
     *
     * @param email Email entered by the client.
     *
     * @return True if email is valid, false otherwise.
     */
    public static boolean checkEmail(String email) {
        return checkEmailFormat(email) && checkEmailAvailable(email);
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
     * Check if the email is not already contained in the database.
     *
     * @param email Email entered by the client.
     *
     * @return True if the email is not in the database, false otherwise.
     */
    public static boolean checkEmailAvailable(String email) {
    	// TODO - Manage if connected to internet or not!
	    
        IORM orm = new PgORM();
        boolean result = false;
        
        try {
	        orm.beginTransaction();
	        result = orm.getClientRepository().mailExist(email);
	    } catch (Exception e) {
	        System.out.println(e);
        }
        
        return result;
    }

    /**
     * Check if the username is not already used by another client.
     *
     * @param username Username entered by the client.
     *
     * @return True is username is not used, false otherwise.
     */
    public static boolean checkUsernameAvailable(String username) {
        // TODO - Manage if connected to internet or not!
	    
	    IORM orm = new PgORM();
	    boolean result = false;
	    
	    try {
	    	orm.beginTransaction();
	    	result = orm.getClientRepository().pseudoExist(username);
	    } catch (Exception e) {
		    System.out.println(e);
	    }

        return result;
    }
}
