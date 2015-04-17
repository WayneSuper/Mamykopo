package cn.wayne.mamypoko.base;

import android.os.AsyncTask;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.app.AppCore;
import cn.wayne.mamypoko.base.entity.EntityList;
import cn.wayne.mamypoko.net.MamyClient;
import cn.wayne.mamypoko.ui.NoScrollListView;
import cn.wayne.mamypoko.ui.xlistview.XScrollView;
import cn.wayne.mamypoko.utils.AppUtil;

/**
 * Created by Pollux on 2015/4/16.
 * //
 */
public abstract class BaseScrollViewActivity extends CommBaseActivity{

    protected String dated="";
    protected XScrollView mScrollView;
    protected NoScrollListView mListView;
    protected int mPageIndex = 1;
    private ParserTask mParserTask;
    boolean isRefresh = true;
    boolean refreshing;
    protected Toolbar mToolbar;

    @Override
    protected void initEvent() {
        mScrollView.setIXScrollViewListener(new XScrollView.IXScrollViewListener() {
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
        mScrollView = (XScrollView) findViewById(R.id.scroll_view);
        mScrollView.setPullRefreshEnable(true);
        mScrollView.setPullLoadEnable(true);
        mScrollView.setAutoLoadEnable(true);
        mScrollView.setRefreshTime(AppUtil.getDataTime());
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
        invalidateOptionsMenu();
    }

    protected int getToolbarTitle() {
        return 0;
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
            Toast.makeText(this, getString(R.string.have_no_network), Toast.LENGTH_LONG).show();
            finish();
        }
    }

    protected abstract RequestParams setRequestParams();

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
            Toast.makeText(BaseScrollViewActivity.this,getString(R.string.load_error),Toast.LENGTH_SHORT).show();
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
       // measureHeight();
        mScrollView.stopRefresh();
        mScrollView.stopLoadMore();
        mScrollView.setRefreshTime(getString(R.string.last_time));
    }

    protected abstract void executeOnLoadDataSuccess(List<?> list,boolean isRefresh);


    protected int measureHeight() {
        // get ListView adapter
        ListAdapter adapter = mListView.getAdapter();
        if (null == adapter) {
            return 0;
        }

        int totalHeight = 0;

        for (int i = 0, len = adapter.getCount(); i < len; i++) {
            View item = adapter.getView(i, null, mListView);
            if (null == item) continue;
            // measure each item width and height
            item.measure(0, 0);
            // calculate all height
            totalHeight += item.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = mListView.getLayoutParams();

        if (null == params) {
            params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        // calculate ListView height
        params.height = totalHeight + (mListView.getDividerHeight() * (adapter.getCount() - 1));

        mListView.setLayoutParams(params);

        return params.height;
    }
}
