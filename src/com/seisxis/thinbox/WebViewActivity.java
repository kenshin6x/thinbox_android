package com.seisxis.thinbox;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {
	
	WebView mWebView;
	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.webview);

	    mWebView = (WebView) findViewById(R.id.webview);
	    
	    mWebView.setWebViewClient(new WebViewClient());
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    
	    mWebView.loadUrl(prefs.getString("SERVICE_URL",""));
	}
	
}