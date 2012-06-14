package com.seisxis.thinbox;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.seisxis.dto.AuthDTO;

public class WebViewActivity extends Activity {
	
	WebView mWebView;
	SharedPreferences prefs;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.webview);
	    
	    String serviceUrl = (String)getIntent().getExtras().get(AuthDTO.AUTH_URL_INFO);
	    
	    this.savePreferences(serviceUrl);
	    
	    mWebView = (WebView) findViewById(R.id.webview);
	    
	    mWebView.setWebViewClient(new WebViewClient());
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    
	    StringBuffer buffer=new StringBuffer(serviceUrl + "/m/");
	    buffer.append("?"+AuthDTO.MOBILE_AUTH_TOKEN_NAME+"="+AuthDTO.MOBILE_AUTH_TOKEN_VALUE);
	    
	    System.out.println(buffer.toString());
	    
	    mWebView.loadUrl(buffer.toString());
	}
	
	private void savePreferences(String serviceUrl) {
		Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.putString(AuthDTO.AUTH_URL_INFO, serviceUrl);              
        editor.commit();
	}
	
}