package kr.sys4u.plainhttpserver.exception;

public class CanNotConnectToClientException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CanNotConnectToClientException() {
		
		super();
	}

	public CanNotConnectToClientException(String message, Throwable cause) {
		
		super(message, cause);
	}

	public CanNotConnectToClientException(String message) {
		
		super(message);
	}

	public CanNotConnectToClientException(Throwable cause) {
		
		super(cause);
	}

}
