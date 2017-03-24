package org.armanious;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class TestClient {
	
	public static void main(String...args) throws Exception {
		String urlParameters  = "update=false&data=ABO%2CNOD2%2CIL6%2CTLR4%2CFGA%2CFGB%2CFGG%2CIL10%2CPGF%2CFLT1%2CENG";
		byte[] postData       = urlParameters.getBytes( StandardCharsets.UTF_8 );
		int    postDataLength = postData.length;
		String request        = "https://ptbdb.cs.brown.edu:8080/";
		URL    url            = new URL( request );
		HttpURLConnection conn= (HttpURLConnection) url.openConnection();      
		System.out.println("Opened connection");
		conn.setDoOutput( true );
		conn.setInstanceFollowRedirects( false );
		conn.setRequestMethod( "POST" );
		conn.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded"); 
		conn.setRequestProperty( "charset", "utf-8");
		conn.setRequestProperty( "Content-Length", Integer.toString( postDataLength ));
		conn.setUseCaches( false );
		System.out.println("HERE");
		try( DataOutputStream wr = new DataOutputStream( conn.getOutputStream())) {
		   wr.write( postData );
		}
		System.out.println("Connecting");
		conn.connect();
		System.out.println("Connected");
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String s;
		while((s = br.readLine()) != null){
			System.out.println(s);
		}
	}

}
