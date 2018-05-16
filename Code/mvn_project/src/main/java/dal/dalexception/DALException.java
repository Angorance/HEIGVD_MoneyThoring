package dal.dalexception;

/**
 * Thrown to indicate that the dal problem.<p>
 * @author Guillaume zaretti
 * @version 1.2
 * DALException handling dal exception
 */
public class DALException extends Exception {
	/**
	 * Constructs an DALException with no detail message.
	 */
	public DALException() {
		
		super();
	}

	/**
	 * Constructs an DALException with the specified
	 * detail message.
	 *
	 * @param message the detail message
	 */
	public DALException(String message) {
		
		super(message);
	}

	/**
	 * Constructs a new DALException with the specified detail message and
	 * cause.
	 *
	 * <p>Note that the detail message associated with <code>cause</code> is
	 * <i>not</i> automatically incorporated in this exception's detail
	 * message.
	 *
	 * @param  message the detail message (which is saved for later retrieval
	 *         by the {@link Throwable#getMessage()} method).
	 * @param  cause the cause (which is saved for later retrieval by the
	 *         {@link Throwable#getCause()} method).  (A <tt>null</tt> value
	 *         is permitted, and indicates that the cause is nonexistent or
	 *         unknown.)
	 */
	public DALException(String message, Throwable cause) {
		
		super(message, cause);
	}

	/**
	 * Constructs a new DALException with the specified cause and a detail
	 * message of <tt>(cause==null ? null : cause.toString())</tt> (which
	 * typically contains the class and detail message of <tt>cause</tt>).
	 * This constructor is useful for exceptions that are little more than
	 * wrappers for other throwables (for example, {@link
	 * java.security.PrivilegedActionException}).
	 *
	 * @param  cause the cause (which is saved for later retrieval by the
	 *         {@link Throwable#getCause()} method).  (A <tt>null</tt> value is
	 *         permitted, and indicates that the cause is nonexistent or
	 *         unknown.)
	 * @since  1.5
	 */
	public DALException(Throwable cause) {
		
		super(cause);
	}
}
