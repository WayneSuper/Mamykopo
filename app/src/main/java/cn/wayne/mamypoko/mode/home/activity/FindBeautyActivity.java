package cn.wayne.mamypoko.mode.home.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.loopj.android.http.RequestParams;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseXListVIewActivity;
import cn.wayne.mamypoko.base.entity.EntityList;
import cn.wayne.mamypoko.mode.home.adapter.FindBtyAdapter;
import cn.wayne.mamypoko.mode.home.entity.Beauty;
import cn.wayne.mamypoko.mode.home.entity.FindBtyEntity;

public class FindBeautyActivity extends BaseXListVIewActivity {
     private FindBtyAdapter mAdapter;
    private List<FindBtyEntity.DataEntity> mDataset;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_find_beauty;
    }



    @Override
    protected void initData() {
        mDataset = new ArrayList<>();
        mAdapter = new FindBtyAdapter(this,mDataset);
        mListView.setAdapter(mAdapter);
        refreshData();

    }


    @Override
    protected void initEvent() {
        super.initEvent();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FindBtyEntity.DataEntity item = mAdapter.getItem(position - 1);
                Intent intent = new Intent(FindBeautyActivity.this, FindContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FindContentActivity.ARGS_ID,item.getId());
                bundle.putString(FindContentActivity.ARGS_TIME, item.getShowdated());
                intent.putExtra("bundle",bundle);
                FindBeautyActivity.this.startActivity(intent);
            }
        });
    }


    @Override
    protected int getToolbarTitle() {
        return R.string.find_bty;
    }

    @Override
    protected int getRightIconRes() {
        return R.drawable.ic_actionbar_edit;
    }

    @Override
    protected int getRightTitle() {
        return R.string.edit;
    }

    @Override
    protected boolean isRightShow() {
        return true;
    }

    @Override
    protected RequestParams setRequestParams() {
        //http://www.qubaobei.com/ios/api/find_beauty.php?qid=&user_id=4646798
        RequestParams params = new RequestParams();
        params.put("qid","");
        params.put("user_id","4646798");
        params.put("dated",dated);
        return params;
    }

    @Override
    protected void initView() {
        super.initView();
        setPullOrRefreshEnable(true, true);
        mToolbar.setTitle(getString(R.string.find_bty));
    }

    @Override
    protected String setRequestURL() {
        return "ios/api/find_beauty.php?";
    }

    @Override
    protected EntityList parseList(String str) {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(str.getBytes())));
        Type type = new TypeToken<FindBtyEntity>(){}.getType();
        return gson.fromJson(reader,type);
    }



    @Override
    protected void executeOnLoadDataSuccess(List<?> list, boolean isRefresh) {
        if(isRefresh) {
            mDataset.clear();
            mDataset.addAll((java.util.Collection<? extends FindBtyEntity.DataEntity>) list);
        }else {
            mDataset.addAll((java.util.Collection<? extends FindBtyEntity.DataEntity>) list);
        }
        mAdapter.notifyDataSetChanged();
        dated = ((FindBtyEntity.DataEntity)list.get(list.size()-1)).getDated()+"";
    }
}
