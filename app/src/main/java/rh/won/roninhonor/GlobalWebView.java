package rh.won.roninhonor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class GlobalWebView extends WebView {

    public GlobalWebView(Context context) {
        super(context);
        initWebViewSettings();
    }

    public GlobalWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initWebViewSettings();
    }

    public GlobalWebView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWebViewSettings();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebViewSettings() {
        WebSettings webSettings = getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);

        setWebViewClient(new CustomWebClient());

    }

    private class CustomWebClient extends WebViewClient {
        @Override
        public void doUpdateVisitedHistory(WebView view, String url, boolean isReload) {
            new Handler().postDelayed(() -> view.evaluateJavascript(

                    "(function() { " +
                            "   if(document.getElementById('pngPreloaderWrapper')) {" +
                            "       document.getElementById('pngPreloaderWrapper').removeChild(document.getElementById('pngLogoWrapper')); " +
                            "   }" +
                            "})();",
                    html -> {
                    }), 600);



            GlobalConfig.navStatus = view.getUrl().contains("asccw");



            new Handler().postDelayed(() -> view.evaluateJavascript(
                    "(function() { " +
                            "   var myHome = document.getElementById('lobbyButtonWrapper'); " +
                            "   if(document.getElementById('lobbyButtonWrapper')) {" +
                            "       document.getElementById('lobbyButtonWrapper').style = 'display:none;';" +
                            "   }" +
                            "});",
                    html -> {
                    }), 5000);

            new Handler().postDelayed(() -> view.evaluateJavascript(
                    "(function() { " +
                            "   const collection = document.getElementsByClassName('MkSy5eDdoOSfXU3d7o2M'); " +
                            "   if(collection[0]) {" +
                            "       collection[0].style = 'display:none;';" +
                            "   }" +
                            "});",
                    html -> {
                    }), 1800);

            // Define the JavaScript code to remove the element
            String removeElementCode = "(function() { " +
                    "   var elementToRemove = document.querySelector('.MkSy5eDdoOSfXU3d7o2M');" +
                    "   if (elementToRemove) {" +
                    "       elementToRemove.parentNode.removeChild(elementToRemove);" +
                    "   }" +
                    "})();";

            // Execute the JavaScript code to remove the specified element
            view.evaluateJavascript(removeElementCode, null);
        }
    }

}