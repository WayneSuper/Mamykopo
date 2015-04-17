package cn.wayne.mamypoko.base;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.ui.view.ProgressWebView;

public class BaseWebActivity extends CommBaseActivity {

    private ProgressWebView mWebView;
    private Toolbar mToolbar;
    private TextView mTextTitle;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_base_web;
    }

    @Override
    protected void initEvent() {
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                return false;

            }
        });
        mWebView.setTitleReceivedListener(new ProgressWebView.OnTitleReceivedListener() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                mTextTitle.setText(title);
            }
        });
    }

    protected void initData() {
        Intent intent = getIntent();
        String url =  intent.getStringExtra("url");
        // if(!TextUtils.isEmpty(url)&&TextUtils.isEmpty(title)){
        mWebView.loadUrl(url);

        // }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listview_child,menu);
        menu.findItem(R.id.action_right).setTitle(getString(R.string.refresh));
        menu.findItem(R.id.action_right).setIcon(R.drawable.ic_refresh);
        menu.findItem(R.id.action_right).setTitle(getString(R.string.refresh));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mWebView.reload();  //刷新
        return true;
    }

    @Override
    protected void initView() {
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        mTextTitle = (TextView)findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Navigation Icon 要.設定在 setSupoortActionBar 才有作用
        // 否則會出現 back bottom
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseWebActivity.this.finish();
            }
        });


        mWebView = (ProgressWebView) findViewById(R.id.baseweb_webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        mWebView = null;

    }
}