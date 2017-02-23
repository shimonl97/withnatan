package com.brahalla.Cerberus.push.gcm.pushraven;
import java.io.DataOutputStream;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * This method allows for simple and modular Notification creation. Notifications can then be pushed to clients
 * over FCM using the push() method.
 * @author Raudius
 *
 */
public class Pushraven extends Notification {
	private final String API_URL = "https://fcm.googleapis.com/fcm/send";
	private String FIREBASE_SERVER_KEY;
	
	public Pushraven(String key){
		super();
		
		FIREBASE_SERVER_KEY = key;
	}
	
	/**
	 * Messages sent to targets.
	 * This class interfaces with the FCM server by sending the Notification over HTTP-POST JSON.
	 * @return
	 */
	public FcmResponse push() {
		
		System.out.println(toJSON());
		
		HttpsURLConnection con = null;
		try{
			String url = API_URL;
			
			URL obj = new URL(url);
			con = (HttpsURLConnection) obj.openConnection();
	
			con.setRequestMethod("POST");
	
			// Set POST headers
			con.setRequestProperty("Authorization", "key="+FIREBASE_SERVER_KEY);
			con.setRequestProperty("Content-Type", "application/json");
	
			
			// Send POST body
			con.setDoOutput(true);
			DataOutputStream wr = new DataOutputStream(con.getOutputStream());
			
			wr.writeBytes( this.toJSON() );
			
			wr.flush();
			wr.close();
			
			con.getResponseCode();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return new FcmResponse(con);
	}
	
}
