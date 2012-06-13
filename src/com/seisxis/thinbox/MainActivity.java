package com.seisxis.thinbox;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Button btnSave = (Button) findViewById(R.id.btnSave);
		btnSave.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				CharSequence toast_text = "Please, put a valid url.";
				int toast_duration = Toast.LENGTH_SHORT;

				TextView txfServiceUrl = (TextView) findViewById(R.id.txfServiceUrl);

				final String service_url = txfServiceUrl.getText().toString();
				
				if(!service_url.equals(null)) {
					
				}

				SharedPreferences settings = getSharedPreferences("THINBOX", 0);
				SharedPreferences.Editor prefsEditor = settings.edit();

				prefsEditor.putString("service_url", service_url);
				prefsEditor.commit();

				Toast toast = Toast.makeText(getBaseContext(),
				toast_text + "1", toast_duration);
				toast.show();

			}
		});

	}
}
