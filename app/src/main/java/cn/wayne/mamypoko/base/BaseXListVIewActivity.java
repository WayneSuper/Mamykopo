package cn.wayne.mamypoko.base;

import android.os.AsyncTask;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.entity.EntityList;
import cn.wayne.mamypoko.net.MamyClient;
import cn.wayne.mamypoko.ui.xlistview.XListView;
import cn.wayne.mamypoko.utils.AppUtil;

/**
 * Created by Pollux on 2015/4/14.
 * //
 */
public abstract class BaseXListVIewActivity extends CommBaseActivity {
    protected String dated="";
    protected XListView mListView;
    protected int mPageIndex = 1;
    private ParserTask mParserTask;
    boolean isRefresh = true;
    boolean refreshing;
    protected Toolbar mToolbar;

    @Override
    protected void initEvent() {
        mListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                if (!refreshing)
                    dated = "";
                    refreshData();
            }

            @Override
            public void onLoadMore() {
                if (!refreshing) requestMoreData();
            }
        });
    }

    @Override
    protected void initView() {
        mListView = getView(android.R.id.list);
        setPullOrRefreshEnable(true, false);
        mToolbar = getView(R.id.toolbar);
        mToolbar.setTitle(getToolbarTitle());
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mListView.autoRefresh();
        invalidateOptionsMenu();
    }

    protected int getToolbarTitle() {
        return 0;
    }

    protected void setPullOrRefreshEnable(boolean refreshable,boolean pullable) {
        mListView.setPullRefreshEnable(refreshable);
        mListView.setPullLoadEnable(pullable);
    }

    protected void refreshData() {
        refreshing = true;
        isRefresh = true;
        mPageIndex = 1;
        sendRequest();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_right) {
            onRightClick();
        }
        return true;
    }

    protected void onRightClick() {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listview_child,menu);
        if(isRightShow()){
            menu.findItem(R.id.action_right).setVisible(true);
            menu.findItem(R.id.action_right).setIcon(getRightIconRes());
            menu.findItem(R.id.action_right).setTitle(getRightTitle());
        }else {
            menu.findItem(R.id.action_right).setVisible(false);
        }
        return true;
    }

    protected int getRightTitle() {
        return 0;
    }

    protected int getRightIconRes() {
        return 0;
    }

    protected boolean isRightShow() {
        return false;
    }

    private void sendRequest() {
        if(AppUtil.hasInternet(this)){
            MamyClient.get(setRequestURL(), setRequestParams(), mHandler);
        }else {
            Toast.makeText(this,getString(R.string.have_no_network),Toast.LENGTH_LONG).show();
            finish();
        }
    }

    protected abstract RequestParams setRequestParams();


    public void string(){}



    protected abstract String setRequestURL();

    protected void requestMoreData() {
        refreshing = true;
        isRefresh = false;
        mPageIndex++;
        sendRequest();
    }

    private AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            executeParserTask(responseBody);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            Toast.makeText(BaseXListVIewActivity.this,getString(R.string.load_error),Toast.LENGTH_SHORT).show();
            onLoaded();
            refreshing = false;
            isRefresh = true;
        }
    };

    private void executeParserTask(byte[] data) {
        cancelParserTask();
        mParserTask = new ParserTask(data);
        mParserTask.execute();
    }

    private void cancelParserTask() {
        if (mParserTask != null) {
            mParserTask.cancel(true);
            mParserTask = null;
        }
    }

    protected abstract EntityList parseList(String str);


    private class ParserTask extends AsyncTask<Void, Void, String> {
        private byte[] data;
        private List<?> list;
        public ParserTask(byte[] data) {
            this.data = data;
        }
        @Override
        protected String doInBackground(Void... params) {
            String str = new String(data);
            EntityList parseList = parseList(str);
            list = parseList.getList();
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            onLoaded();
            executeOnLoadDataSuccess(list, isRefresh);
            refreshing = false;
        }
    }

    protected void onLoaded() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime(getString(R.string.last_time));
    }

    protected abstract void executeOnLoadDataSuccess(List<?> list,boolean isRefresh);
}
