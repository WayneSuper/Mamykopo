package cn.wayne.mamypoko.mode.circle;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseScrollViewActivity;
import cn.wayne.mamypoko.base.entity.EntityList;
import cn.wayne.mamypoko.mode.bbs.BbsAdapter;
import cn.wayne.mamypoko.mode.circle.entity.PostsModel;
import cn.wayne.mamypoko.mode.home.activity.FindContentActivity;
import cn.wayne.mamypoko.mode.home.entity.Beauty;
import cn.wayne.mamypoko.ui.NoScrollListView;
import cn.wayne.mamypoko.utils.AppUtil;
import cn.wayne.mamypoko.utils.StringUtil;

/**
 * Created by Pollux on 2015/4/16.
 * //
 */
public class CircleListActivity extends BaseScrollViewActivity {
    private String qId = "";
    private List<Beauty.BeautyEntity> mTopData;
    private ArrayAdapter<Beauty.BeautyEntity> mTopAdapter;
    private NoScrollListView topListView;
    private BbsAdapter mAdapter;
    private List<Beauty.BeautyEntity> mData;
    private String replyNum;
    private String postNum;
    private String mainName;
    private SimpleDraweeView mAvader;
    private TextView mMainName,mTieNum,mReplyNum;
    private String mAvaderURL;

    @Override
    protected RequestParams setRequestParams() {
        RequestParams params = new RequestParams();
        params.put("user_id", "5");
        params.put("cid", "6");
        params.put("order", "1");
        params.put("qid", qId);
        params.put("btime", AppUtil.getDataTime("yyyy-MM-dd"));
        params.put("dated", dated);
        return params;
    }

    @Override
    protected String setRequestURL() {

        return "ios/api/bbs_list_v3_test.php?";
    }

    @Override
    protected EntityList parseList(String str) {
        Gson gson = new Gson();
        Type type = new TypeToken<PostsModel>() {
        }.getType();
        PostsModel model = gson.fromJson(str, type);
        return model;
    }

    @Override
    protected void executeOnLoadDataSuccess(List<?> list, boolean isRefresh) {
        if (isRefresh) {
            mTopData.clear();
            mData.clear();
            for (int i=0;i<list.size();i++) {
                Beauty.BeautyEntity item = ((Beauty.BeautyEntity)list.get(i));
                int type = item.getPost_type();
                if(type == 1) {
                    mTopData.add(item);
                }else {
                   mData.add(item);
                }
            }
            mTopAdapter.notifyDataSetChanged();
           mAdapter.notifyDataSetChanged();

        } else {
            mData.addAll((Collection<? extends Beauty.BeautyEntity>) list);
            mAdapter.notifyDataSetChanged();
        }
        dated = ((Beauty.BeautyEntity)list.get(list.size()-1)).getDated()+"";
    }

    @Override
    protected int setContentViewID() {
        return R.layout.act_scroll_view;
    }

    @Override
    protected void initView() {
        super.initView();
        View content = LayoutInflater.from(this).inflate(R.layout.activity_circle_list, null);
        if (null != content) {
            mListView = (NoScrollListView) content.findViewById(android.R.id.list);
            mListView.setFocusable(false);
            mListView.setFocusableInTouchMode(false);
            mData = new ArrayList<>();
            mAdapter = new BbsAdapter(this, mData);
            mListView.setAdapter(mAdapter);
            topListView = (NoScrollListView) content.findViewById(R.id.list_top);
            mTopData = new ArrayList<>();
            mTopAdapter = new ArrayAdapter<Beauty.BeautyEntity>(this, R.layout.item_circle_top, R.id.text, mTopData);
            topListView.setAdapter(mTopAdapter);

            mAvader = (SimpleDraweeView) content.findViewById(R.id.drawee_icon);
            mMainName = (TextView) content.findViewById(R.id.text_name);
            mTieNum = (TextView) content.findViewById(R.id.text_tie_num);
            mReplyNum = (TextView) content.findViewById(R.id.text_replys_num);


        }
        mScrollView.setView(content);
    }

    @Override
    protected void initData() {
        qId = getIntent().getStringExtra("Qid");
        replyNum = getIntent().getStringExtra("replyNum");
        postNum = getIntent().getStringExtra("postNum");
        mainName = getIntent().getStringExtra("name");
        mAvaderURL = getIntent().getStringExtra("avater");
        mReplyNum.setText(replyNum);
        mMainName.setText(mainName);
        mTieNum.setText(postNum);
        mAvader.setImageURI(Uri.parse(mAvaderURL));
        refreshData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Beauty.BeautyEntity item = mData.get(position);
                Intent intent = new Intent(CircleListActivity.this, FindContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FindContentActivity.ARGS_ID, item.getUrl());
                bundle.putString(FindContentActivity.ARGS_TIME, StringUtil.friendlyTime(item.getShowdated()));
                intent.putExtra("bundle", bundle);
                CircleListActivity.this.startActivity(intent);
            }
        });


        topListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Beauty.BeautyEntity item = mTopData.get(position);
                Intent intent = new Intent(CircleListActivity.this, FindContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FindContentActivity.ARGS_ID, item.getUrl());
                bundle.putString(FindContentActivity.ARGS_TIME, StringUtil.friendlyTime(item.getShowdated()));
                intent.putExtra("bundle", bundle);
                CircleListActivity.this.startActivity(intent);
            }
        });



    }

    @Override
    protected int getToolbarTitle() {
        return R.string.circle;
    }
}
