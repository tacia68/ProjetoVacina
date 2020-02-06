package br.fabrica.projeto.carteiravacinadigital;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class MyWebView extends AppCompatActivity {

    private WebView webView;
    //objeto para atualizar a pagina
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    ProgressBar progresso;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_web_view);
        mSwipeRefreshLayout = findViewById(R.id.swipelayout);
        progresso = findViewById(R.id.progresso);

        webView = findViewById(R.id.webView);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progresso.setVisibility(View.VISIBLE);
                setTitle("Arguarde...");
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progresso.setVisibility(View.GONE);
                setTitle(getTitle());

            }
        });

        webView.loadUrl("https://saude.gov.br/saude-de-a-z/vacinacao/");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(true);
                (new Handler()).postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                        webView = findViewById(R.id.webView);
                        webView.setWebChromeClient(new WebChromeClient());
                        webView.setWebViewClient(new WebViewClient());
                        //hotel las vegas 192.168.0.126
                        //nao lembro 192.168.43.15
                        webView.loadUrl("https://saude.gov.br/saude-de-a-z/vacinacao/");

                        WebSettings webSettings = webView.getSettings();
                        webSettings.setJavaScriptEnabled(true);
                    }
                },3000);
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
        }
    }
}
