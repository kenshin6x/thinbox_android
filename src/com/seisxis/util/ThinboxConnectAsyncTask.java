package com.seisxis.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.SystemClock;
import android.util.Log;
import android.widget.Toast;

import com.seisxis.dto.AuthDTO;
import com.seisxis.thinbox.R;
import com.seisxis.thinbox.WebViewActivity;

public class ThinboxConnectAsyncTask extends AsyncTask<Void, Void, String> {
	
	private ProgressDialog dialog;
    private final Context context;
    AuthDTO authDTO;

    public ThinboxConnectAsyncTask(Context context, AuthDTO authDTO) {
        this.context = context;
        this.authDTO = authDTO;
    }
    
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage(context.getString(R.string.dialog_connect_message));
        dialog.setCancelable(true);
        dialog.show();
    }
    
    protected String doInBackground(Void... ignored) {
        String returnMessage = null;
        try {
        	
        	boolean response = validateService(authDTO);
        	
			if(!response) {
				throw new Exception("Invalid URL");
			} else {
				Intent i = new Intent(context, WebViewActivity.class);
				//i.putExtra("authDTO", authDTO);
				context.startActivity(i);
			}
			
        } catch (Exception e) {
            returnMessage = e.getMessage();
        }
        return returnMessage;
    }
    
    protected void onPostExecute(String message) {
        dialog.dismiss();
        if (message != null) {
            Log.e("ThinboxConnectAsyncTask", String.format("I received an error: %s", message));
            Toast toast = Toast.makeText(context, "Invalid URL.", Toast.LENGTH_LONG);
			toast.show();
        } else {
            Log.i("ThinboxConnectAsyncTask", "No problems");
        }
    }

    
	private boolean validateService(AuthDTO authDTO) throws Exception {
				
		try {
			SystemClock.sleep(2000);
			StrictMode.ThreadPolicy policy = new StrictMode.
			ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
			
			String serviceUrl = authDTO.getUrl() + "/rest/validate/?username="+authDTO.getUsername()+"&password="+authDTO.getPassword();
			System.out.println(serviceUrl);
			
			URL url = new URL(serviceUrl);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			CharSequence response = readStream(con.getInputStream());
						
			JSONObject jsonObject = (JSONObject) new JSONTokener((String) response).nextValue();
			Boolean jsonResponse = jsonObject.getBoolean("response");
			
			if(!jsonResponse) {
				throw new Exception("Invalid Response");
			}
			
			return true;
			
						
		} catch(Exception e) {
			e.getStackTrace();
		}
		
		return false;

	}
	
	private CharSequence readStream(InputStream in) {
		BufferedReader reader = null;
		CharSequence response = "";
		try {
			reader = new BufferedReader(new InputStreamReader(in));
			String line = "";
			while ((line = reader.readLine()) != null) {
				response = response + line;
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
			  try {
			    reader.close();
				return response;
				
			  } catch (IOException e) {
			    e.printStackTrace();
		      }
			}
		}
		
		return response;
		
	}


}
