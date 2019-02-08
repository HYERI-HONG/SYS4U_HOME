package kr.sys4u.plainhttpserver.exception;

public class NoSuchMIMETypeException extends RuntimeException {

	private static final long serialVersionUID = 2530676414268356953L;

	public NoSuchMIMETypeException() {
		super();
	}

	public NoSuchMIMETypeException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoSuchMIMETypeException(String message) {
		super(message);
	}

	public NoSuchMIMETypeException(Throwable cause) {
		super(cause);
	}
	
	

}
