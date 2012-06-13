package com.seisxis.thinbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.seisxis.util.ThinboxConnectAsyncTask;

public class MainActivity extends Activity implements OnClickListener {
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		String serviceUrl = getPreferences(MODE_PRIVATE).getString("service_url","");
		
		if(!serviceUrl.equals(null)) {
			TextView txfServiceUrl = (TextView) findViewById(R.id.txfServiceUrl);
			txfServiceUrl.setText(serviceUrl);
		}
		
		Button btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		
		if(!isNetworkAvailable()) {
			Toast toast = Toast.makeText(getBaseContext(), R.string.error_network_fail, Toast.LENGTH_LONG);
			toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
			toast.show();
		}
		
		TextView txfServiceUrl = (TextView) findViewById(R.id.txfServiceUrl);
		final String serviceUrl = txfServiceUrl.getText().toString();

		new ThinboxConnectAsyncTask(MainActivity.this,serviceUrl).execute((Void []) null);

	}
	
	
	public boolean isNetworkAvailable() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = cm.getActiveNetworkInfo();

		if (networkInfo != null && networkInfo.isConnected()) {
			return true;
		} else {
			return false;
		}

	}

}
