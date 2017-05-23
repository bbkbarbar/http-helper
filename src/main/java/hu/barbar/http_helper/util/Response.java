package hu.barbar.http_helper.util;

/**
 * Response object to store the following response data: 
 * <br> - url (optional)
 * <br> - response code
 * <br> - response body
 * 
 * @author 
 *
 */
public class Response {

	public static final int RESPONSE_CODE_UNDEFINED = -1;
	
	
	private String url = null;
	
	private int code = RESPONSE_CODE_UNDEFINED;
	
	private static final String DEFAULT_SEPARATOR = "\n";
	
	private StringBuffer responseBody = null;

	
	
	public Response(String url, int code, StringBuffer responseBody) {
		super();
		this.url = url;
		this.code = code;
		this.responseBody = responseBody;
	}
	
	public Response(int code, StringBuffer responseBody) {
		super();
		this.url = null;
		this.code = code;
		this.responseBody = responseBody;
	}

	public int getCode() {
		return code;
	}

	public StringBuffer getResponseBody() {
		return responseBody;
	}
	
	public String getUrl(){
		return url;
	}
	
	
	public String toString(String separator){
		String s = "";
		
		if(url != null){
			s += "Url: " + url + separator;
		}
		
		s += "ResponseCode: " + this.getCode() + separator;
		
		s += this.getResponseBody();
		
		return s;
	}
	
	public String toString(){
		return this.toString(DEFAULT_SEPARATOR);
	}
	
	
}
