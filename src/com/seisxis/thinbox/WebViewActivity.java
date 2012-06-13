package com.seisxis.thinbox;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.seisxis.dto.AuthDTO;

public class WebViewActivity extends Activity {
	
	WebView mWebView;
	SharedPreferences prefs;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.webview);
	    
	    AuthDTO authDTO = (AuthDTO) getIntent().getExtras().get("authDTO");
	    
	    String serviceUrl = authDTO.getUrl() + "/m/";
	    
	    mWebView = (WebView) findViewById(R.id.webview);
	    
	    mWebView.setWebViewClient(new WebViewClient());
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    
	    mWebView.loadUrl(serviceUrl);
	}
	
	public boolean savePreferences(AuthDTO authDTO) {
		Editor editor = prefs.edit();
        editor.putString("auth_url", authDTO.getUsername());
        editor.putString("auth_username", authDTO.getUsername());
        editor.putString("auth_password", authDTO.getPassword());
        editor.commit();
        
        return true;
	}
	
}