package fi.ptm.webviewexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

/**
 *
 * @author PTM
 */
public class MainActivity extends Activity {
    private WebView webView;
    private String url="http://www.jamk.fi";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        editText = (EditText) findViewById(R.id.editText);

        // uncomment below to test wanted behavior

        // 1. just show one url, link pressing opens a real browser
        //webView.loadUrl(url);
        //editText.setText(url);

        // 2. override WebViewClient to be able to handle page loading
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl(url);

        // 3. display dynamic data
        //webView.loadData("<html><body>Hello Android!</body></html>", "text/html", "UTF-8");

        // enable zooming
        //webView.getSettings().setBuiltInZoomControls(true);
        // enable JavaScript, disabled by default
        //webView.getSettings().setJavaScriptEnabled(true);
    }

    // called from Button
    public void loadPage(View view) {
        url = editText.getText().toString();
        webView.loadUrl(url);
    }

    // handle back key button pressed in device
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    // link pressing opens a new page inside WebView
    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            webView.loadUrl(url);
            return true;
        }
    }

}
