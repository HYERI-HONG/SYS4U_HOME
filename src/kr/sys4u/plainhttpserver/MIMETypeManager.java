package kr.sys4u.plainhttpserver;

import java.util.HashMap;
import java.util.Map;

public class MIMETypeManager {
	Map<String, String> MimeTypeList = new HashMap<>();

	public MIMETypeManager() {

		MimeTypeList.put("htm", "text/html");
		MimeTypeList.put("html", "text/html");
		MimeTypeList.put("jpeg", "image/jpeg");
		MimeTypeList.put("jpg", "image/jpeg");
		MimeTypeList.put("gif", "image/gif");

	}

	public void addMIMEType(String extension, String type) {

		if (!MimeTypeList.containsKey(extension)) {
			MimeTypeList.put(extension, type);
		}
	}

	public void deleteMIMEType(String extension) {

		if (MimeTypeList.containsKey(extension)) {
			MimeTypeList.remove(extension);
		}
	}

	public String getMIMEType(String extension) {
		return (MimeTypeList.containsKey(extension)) ? 
				MimeTypeList.get(extension) : null;
	}

	public Map<String, String> getMimeTypeList() {
		return MimeTypeList;
	}

}
