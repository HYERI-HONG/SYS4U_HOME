package kr.sys4u.plainhttpserver.exception;


public class CanNotReadFileException extends RuntimeException  {

	private static final long serialVersionUID = 7943916902931114033L;

	public CanNotReadFileException() {
		super();
	}

	public CanNotReadFileException(String message, Throwable cause) {
		super(message, cause);
	}

	public CanNotReadFileException(String message) {
		super(message);
	}

	public CanNotReadFileException(Throwable cause) {
		super(cause);
	}
	
	

}
