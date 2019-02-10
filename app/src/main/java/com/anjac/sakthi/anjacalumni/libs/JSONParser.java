package com.anjac.sakthi.anjacalumni.libs;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JSONParser {

	static InputStream is = null;
	static String json = "";
	//JSON means Java Script Object Nation used to data exchange

	// function get json from url
	// by making HTTP POST or GET mehtod
	public String makeHttpRequest(String url, String method, JSONObject jsondata) throws MalformedURLException {

		// Making HTTP request

		// check for request method

		URL url1=new URL(url);
		HttpURLConnection connection=null;
		Log.d(">>>>>>>>>Request", jsondata.toString());
		Log.d(">>>>>>>>>URL",url);
		if(method.equalsIgnoreCase("POST"))
        {
            // request method is POST
            // defaultHttpClient
            try {
                connection=(HttpURLConnection)url1.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoInput(true);
				connection.setDoOutput(true);
             //   connection.setConnectTimeout(10000);
              //  connection.setReadTimeout(25000);
                OutputStreamWriter wr=new OutputStreamWriter(connection.getOutputStream(),"UTF-8");
                wr.write(jsondata.toString());
                wr.flush();
                wr.close();
                is=connection.getInputStream();
            }catch (Exception e){
                e.printStackTrace();
            }
        }else if(method.equals("GET")){
			// request method is GET

			try {
				connection= (HttpURLConnection) url1.openConnection();
				connection.setDoOutput(false);
				connection.setRequestMethod("GET");
				connection.setRequestProperty("Accept-Charset", "UTF-8");
				//connection.setConnectTimeout(15000);
				connection.connect();
				is = new BufferedInputStream(connection.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			is.close();
			json = sb.toString();
			Log.d(">>>>>>>>Response",json);
		} catch (Exception e) {
			Log.e("Buffer Error", "Error converting result " + e.toString());
		}
		// return JSON String
		return json;
	}	
}
