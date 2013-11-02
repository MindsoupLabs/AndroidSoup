/**
 * 
 */
package net.mindsoup.androidsoup;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * @author Valentijn
 *
 */
public class ConnectivityCheck implements Runnable {

	private final String myUrl;
	private final int timeout;
	private final Context context;
	private final ConnectivityListener listener;
	
	public ConnectivityCheck(ConnectivityListener listener, Context context, String url) {
		this(listener, context, url, 3000);
	}
	
	public ConnectivityCheck(ConnectivityListener listener, Context context, String url, int timeout) {
		this.myUrl = url;
		this.timeout = timeout;
		this.context = context;
		this.listener = listener;
	}
	
	@Override
	public void run()  {
		// check if we can connect to the internet
		boolean internet = false;
		
		ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo currentNetwork = connectivityManager.getActiveNetworkInfo();
		
		if(currentNetwork != null) {
			if(currentNetwork.isConnected()) {
				try {
					// try to actually connect to mindsoup to verify end-to-end communication
					URL url = new URL(myUrl);
		            HttpURLConnection urlc = (HttpURLConnection)url.openConnection();
		            urlc.setConnectTimeout(timeout);
		            urlc.connect();
		            
		            if (urlc.getResponseCode() == HttpURLConnection.HTTP_OK) {
		                internet = true;
		            }
				} catch (IOException e) {
					// do nothing, we don't have internet :(
				}
			}
		}
		
		listener.onConnectionCheckComplete(internet);
	}

}
