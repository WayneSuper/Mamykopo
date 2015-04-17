package cn.wayne.mamypoko.mode.bbs;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import org.apache.http.protocol.RequestDate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseChildActivity;
import cn.wayne.mamypoko.base.BaseXListViewActivity;
import cn.wayne.mamypoko.base.entity.EntityList;
import cn.wayne.mamypoko.mode.home.activity.FindContentActivity;
import cn.wayne.mamypoko.mode.home.entity.Beauty;
import cn.wayne.mamypoko.utils.AppUtil;
import cn.wayne.mamypoko.utils.StringUtil;

public class BbsActivity extends BaseXListViewActivity {

    private BbsAdapter mAdapter;
    private List<Beauty.BeautyEntity> mDataSet;
    @Override
    protected RequestParams setRequestParams() {
        //http://www.qubaobei.com/ios/api/bbs_list_top_v3.php?cid=1&btime=2015-04-01

        RequestParams params = new RequestParams();
        params.put("cid" ,"1");
        params.put("btime" , AppUtil.getDataTime("yyyy-MM-dd"));
        if(dated!=null && dated.length()>0) {
            params.put("dated",dated);
        }

        return params;
    }

    @Override
    protected String setRequestURL() {
        return "ios/api/bbs_list_top_v3.php?";
    }

    @Override
    protected EntityList parseList(String str) {
        Gson gson = new Gson();
        Type type = new TypeToken<BbsModel>(){}.getType();
        BbsModel mode = gson.fromJson(str, type);
        return mode;
    }

    @Override
    protected void executeOnLoadDataSuccess(List<?> list, boolean isRefresh) {
        if(isRefresh) {
            mDataSet.clear();
            mDataSet.addAll((Collection<? extends Beauty.BeautyEntity>) list);
        }else {
            mDataSet.addAll((Collection<? extends Beauty.BeautyEntity>) list);
        }
        this.dated = ((Beauty.BeautyEntity)list.get(list.size()-1)).getDated()+"";
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_bbs;
    }

    @Override
    protected void initData() {

        mDataSet = new ArrayList<>();
        mAdapter= new BbsAdapter(this,mDataSet);
        mListView.setAdapter(mAdapter);
        refreshData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Beauty.BeautyEntity item = mAdapter.getItem(position - 1);
                Intent intent = new Intent(BbsActivity.this, FindContentActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString(FindContentActivity.ARGS_ID,item.getId());
                bundle.putString(FindContentActivity.ARGS_TIME, item.getShowdated());
                intent.putExtra("bundle",bundle);
                BbsActivity.this.startActivity(intent);
            }
        });
    }

    @Override
    protected void initView() {
        super.initView();
        setPullOrRefreshEnable(true, true);
        mToolbar.setTitle(getString(R.string.hot_bbs_name));
    }

    @Override
    protected int getToolbarTitle() {
        return R.string.hot_bbs;
    }

    @Override
    protected boolean isRightShow() {
        return true;
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
    protected void onRightClick() {
        Toast.makeText(this,"hello",Toast.LENGTH_LONG).show();
    }
}
