package kr.sys4u.chatting;

public class ChattingServer {
	
	private final int port = 9000;
	private boolean initialized;
	
	public ChattingServer() {
		this.initialized = false;
	}
	
	private void initialize() {
		if(initialized) {
			
		}
		
	}
	private void execute() {
		
	}
	private void close() {
		
	}
	
	public static void main(String[] args) {
		
		ChattingServer server = new ChattingServer();
		server.initialize();
		server.execute();
		server.close();
	}
}
