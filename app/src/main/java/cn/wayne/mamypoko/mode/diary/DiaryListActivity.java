package cn.wayne.mamypoko.mode.diary;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.facebook.cache.disk.DiskStorageCache;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseChildActivity;
import cn.wayne.mamypoko.net.MamyClient;
import cn.wayne.mamypoko.ui.EmptyView;
import cn.wayne.mamypoko.ui.actionbutton.FloatingActionButton;
import cn.wayne.mamypoko.ui.xlistview.XListView;
import cn.wayne.mamypoko.utils.AppUtil;

public class DiaryListActivity extends BaseChildActivity {
    private XListView mListView;
    private List<Diary> mDataSource;
    private DiaryAdapter mDiaryAdapter;
    private EmptyView mEmptyView;
    private FloatingActionButton mAddNewbtn;

    @Override
    protected void initView() {
        super.initView();
        mListView = getView(android.R.id.list);
        mListView.setPullRefreshEnable(true);
        mListView.setSelector(R.color.translate);
        mListView.setPullLoadEnable(false);
        mEmptyView = getView(R.id.empty_view);
        mListView.autoRefresh();
        mAddNewbtn = getView(R.id.action_btn_addnew);
    }

    protected void onLoaded() {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime("刚刚");
        findViewById(R.id.action_btn_addnew).setVisibility(View.VISIBLE);
    }

    protected void refreshData() {
        sendRequest();
    }

    private void sendRequest() {
        //http://baby.ci123.com/yunqi/ios2/diary_v4.php?o_user_id=4325690
        if (AppUtil.hasInternet(this)) {
            RequestParams params = new RequestParams();
            params.put("o_user_id", "4325690");
            MamyClient.getWithFull("http://baby.ci123.com/yunqi/ios2/diary_v4.php?", params, mHandler);
        } else {
            Toast.makeText(this, "未发现网络连接", Toast.LENGTH_SHORT).show();
            mEmptyView.setErrorType(EmptyView.NETWORK_ERROR);
        }
    }

    private AsyncHttpResponseHandler mHandler = new AsyncHttpResponseHandler() {
        @Override
        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            String str = new String(responseBody);
            try {
                JSONObject o = new JSONObject(str);
                int ret = o.getInt("ret");
                if (ret == 1) {
                    JSONArray a = o.getJSONArray("data");
                    JSONObject oo = a.getJSONObject(0);
                    String dd = oo.getString("data");
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Diary>>() {
                    }.getType();
                    JsonReader reader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(dd.getBytes())));
                    reader.setLenient(true);
                    List<Diary> entitys = gson.fromJson(reader, type);
                    mDataSource.addAll(entitys);
                    mDiaryAdapter.notifyDataSetChanged();
                    mEmptyView.dismiss();
                    onLoaded();
                } else {
                    mEmptyView.setErrorType(EmptyView.NODATA_NO_CLICK);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

//            Gson gson = new Gson();
//            Type type = new TypeToken<DiaryEntity>(){}.getType();
//            JsonReader reader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(responseBody)));
//            reader.setLenient(true);
//            DiaryEntity entity = gson.fromJson(reader,type);
//            mDataSource.add(entity.getData().get(0).getData());
//            mDiaryAdapter.notifyDataSetChanged();
//            mEmptyView.dismiss();
//            onLoaded();
        }

        @Override
        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
            mEmptyView.setErrorType(EmptyView.NETWORK_ERROR);
        }
    };

    @Override
    protected void initEvent() {
        mListView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                refreshData();
            }

            @Override
            public void onLoadMore() {

            }
        });


        mAddNewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DiaryListActivity.this,DiaryActivity.class));
            }
        });

    }

    @Override
    protected void initData() {
        mDataSource = new ArrayList();
        mDiaryAdapter = new DiaryAdapter(this, mDataSource);
        mListView.setAdapter(mDiaryAdapter);
        refreshData();
    }

    @Override
    protected void onRightButtonClick() {

    }

    @Override
    protected int setRightBtnIcon() {
        return R.drawable.ic_actionbar_edit;
    }

    @Override
    protected String setPageTitle() {
        return getString(R.string.mydiary);
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_diary_list;
    }

    @Override
    protected boolean isRightShow() {
        return true;
    }

    @Override
    protected boolean isMoreShow() {
        return false;
    }
}
