package com.seisxis.thinbox;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends Activity {
	
	WebView mWebView;
	SharedPreferences prefs;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.webview);
	    
	    String serviceUrl = (String)getIntent().getExtras().get("service_url");
	    serviceUrl += "/m/";
	    
	    mWebView = (WebView) findViewById(R.id.webview);
	    
	    mWebView.setWebViewClient(new WebViewClient());
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    
	    mWebView.loadUrl(serviceUrl);
	}
	
	public boolean savePreferences(String serviceUrl) {
		Editor editor = prefs.edit();
        editor.putString("service_url", serviceUrl);
        editor.commit();
        
        return true;
	}
	
}