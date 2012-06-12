package com.seisxis.thinbox;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ThinboxActivity extends Activity {
	
	WebView mWebView;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.webview);

	    mWebView = (WebView) findViewById(R.id.webview);
	    
	    mWebView.setWebViewClient(new WebViewClient());
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    
	    mWebView.loadUrl("http://186.236.198.209:9090/m/");
	}
	
}