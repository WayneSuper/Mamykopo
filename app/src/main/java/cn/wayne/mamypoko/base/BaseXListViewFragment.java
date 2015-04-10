package cn.wayne.mamypoko.base;

import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.entity.EntityList;
import cn.wayne.mamypoko.net.MamyClient;
import cn.wayne.mamypoko.utils.AppUtil;
import cn.wayne.mamypoko.ui.EmptyView;
import cn.wayne.mamypoko.ui.xlistview.XListView;

/**
 * Created by Lumia on 2015/4/4.
 */
public abstract class BaseXListViewFragment extends CommBaseFragment {
    protected XListView listView;
    protected EmptyView mEmptyView;
    protected int mPageIndex = 0;
    private ParserTask mParserTask;
    protected static final int STATE_REFRESHING = 0;
    protected static final int STATE_LOADMORE = 0;
    protected static final int STATE_NORMAL = 0;
    protected int STATE = STATE_NORMAL;

    @Override
    protected void initView(View view) {
        listView = (XListView) view.findViewById(android.R.id.list);
        mEmptyView = (EmptyView) view.findViewById(R.id.empty_view);
        mEmptyView.setErrorType(setEmptyType());
        setPullOrRefreshEnable(true,false);
        listView.autoRefresh();
    }

    protected void setPullOrRefreshEnable(boolean refreshable,boolean pullable) {
        listView.setPullRefreshEnable(refreshable);
        listView.setPullLoadEnable(pullable);
    }

    /**
     * 设置EmptyView的类型
     *  HIDE_LAYOUT
     *  NETWORK_ERROR
     *  NETWORK_LOADING
     *  NETWORK_LOADING_ANIM
     *  NODATA
     *  NODATA_NO_CLICK
     * @return
     */
    protected abstract int setEmptyType();

    @Override
    protected void initEvent(View view) {

        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }

            @Override
            public void onLoadMore() {
                requestMoreData();
            }
        });

        mEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });
    }

    protected void refreshData() {
        mPageIndex = 0;
        STATE = STATE_REFRESHING;
        sendRequest();
    }

    private void sendRequest() {
        if(AppUtil.hasInternet(mContext)){
            MamyClient.get(setRequestURL(),setRequestParams(),mHandler);
        }else {
            mEmptyView.setErrorType(EmptyView.NETWORK_ERROR);
        }
    }

    protected void requestMoreData() {
        mPageIndex++;
        STATE = STATE_LOADMORE;
        sendRequest();

    }

    private AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
            executeParserTask(responseBody);
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            mEmptyView.dismiss();
            Toast.makeText(mContext,mContext.getString(R.string.data_load_error),Toast.LENGTH_SHORT).show();
            STATE = STATE_NORMAL;
            onLoaded();
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

    protected abstract String setRequestURL();

    protected abstract RequestParams setRequestParams();

    protected abstract EntityList parseList(String str);


    private class ParserTask extends AsyncTask<Void, Void, String>{
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

            if(list ==null || list.size()<=0) {
                mEmptyView.setErrorType(EmptyView.NODATA_NO_CLICK);
            }
            mEmptyView.dismiss();
            onLoaded();
            executeOnLoadDataSuccess(list);
        }
    }

    protected void onLoaded() {
        listView.stopRefresh();
        listView.stopLoadMore();
        listView.setRefreshTime("刚刚");
    }

    protected abstract void executeOnLoadDataSuccess(List<?> list);

}
