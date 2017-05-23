package hu.barbar.http_helper;

public class App {
	
    public static void main( String[] args ){
    	try {
			System.out.println(RequestHelper.sendGet("https://api.coinbase.com/v2/exchange-rates?currency=BTC").getResponseBody());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
}
