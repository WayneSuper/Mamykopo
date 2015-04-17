package cn.wayne.mamypoko.mode.noeat;

import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseChildActivity;
import cn.wayne.mamypoko.net.MamyClient;
import cn.wayne.mamypoko.ui.EmptyView;
import cn.wayne.mamypoko.ui.NoScrollListView;

public class NoeatDetailActivity extends BaseChildActivity {
    public static final String FOOD_ID = "id";
    public static final String FOOD_NAME = "name";
    private EmptyView mEmptyView;
    private NoScrollListView mListView;
    private NoeatDetailAdapter mAdapter;
    private SimpleDraweeView mDraweeView;
    private String mFoodId;
    private String mFoodName;
    private List<NoeatDetailModel.DetailImage.DataEntity> mData;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_noeat_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        mEmptyView = getView(R.id.empty_view);
        mListView = getView(android.R.id.list);
        mDraweeView = getView(R.id.draweee_image);
        mEmptyView.setErrorType(EmptyView.NETWORK_LOADING_ANIM);
    }

    @Override
    protected void initEvent() {
        mEmptyView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEmptyView.setErrorType(EmptyView.NETWORK_LOADING_ANIM);
                reqeustData();
            }
        });
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        mAdapter = new NoeatDetailAdapter(this,mData);
        mListView.setAdapter(mAdapter);
        mFoodId = getIntent().getStringExtra(FOOD_ID);
        mFoodName = getIntent().getStringExtra(FOOD_NAME);
        setPageTitle(mFoodName);
        reqeustData();
    }

    private void reqeustData() {
        //http://www.qubaobei.com/ios/cf/eat_view2.php?id=2&o_user_id=4325690
        RequestParams params = new RequestParams();
        params.put("id",mFoodId);
        params.put("o_user_id","4325690");
        MamyClient.get("ios/cf/eat_view2.php?", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(responseBody)));
                Type type = new TypeToken<NoeatDetailModel>() {
                }.getType();
                NoeatDetailModel model = gson.fromJson(reader, type);
                mDraweeView.setImageURI(Uri.parse(model.getData().getImg()));
                List<NoeatDetailModel.DetailImage.DataEntity> mList = model.getData().getData();
                mData.addAll(mList);
                mAdapter.notifyDataSetChanged();
                mEmptyView.setErrorType(EmptyView.HIDE_LAYOUT);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                mEmptyView.setErrorType(EmptyView.NETWORK_ERROR);
            }
        });
    }

    @Override
    protected boolean isRightShow() {
        return false;
    }

    @Override
    protected boolean isMoreShow() {
        return false;
    }

    @Override
    protected int setRightBtnIcon() {
        return 0;
    }

    @Override
    protected String setPageTitle() {
        return "";
    }
}
