package kr.sys4u.plainhttpserver;

public class StatusLineParser {

	private final String url;
	private boolean hasExtension;
	private String extension;
	private String fileName;

	public StatusLineParser(String url) {
		
		this.url = url.split(" ")[1];
		this.fileName = this.url.substring(this.url.lastIndexOf("/"));
		
		String[] parsed = fileName.split("\\.");
		this.hasExtension = (parsed.length >= 2);
		this.extension = (hasExtension)? 
				parsed[parsed.length-1] : null;
		
	}

	public String getFileName() {
		return fileName;
	}

	public String getExtension() {
		return extension;
	}

	public boolean getHasExtension() {
		return hasExtension;
	}

	public String getUrl() {
		return url;
	}
}
