package com.anjac.sakthi.anjacalumni.libs;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class ConnectionDetector {
	
	private Context _context;
	
	public ConnectionDetector(Context context)
	{
		_context=context;
	}
	

	public boolean isConnectingToInternet() {
		ConnectivityManager
				cm = (ConnectivityManager) _context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		return activeNetwork != null
				&& activeNetwork.isConnectedOrConnecting();
	}

}
