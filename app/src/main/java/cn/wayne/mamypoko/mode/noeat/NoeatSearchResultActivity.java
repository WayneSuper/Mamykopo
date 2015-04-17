package cn.wayne.mamypoko.mode.noeat;

import android.content.Intent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseXListViewActivity;
import cn.wayne.mamypoko.base.entity.EntityList;

public class NoeatSearchResultActivity extends BaseXListViewActivity {
    private String keyword;
    private ResultAdapter mAdapter;
    private List<ResultModel.DataEntity> mData;
    private String cateId;
    private String title;

    //http://www.qubaobei.com/ios/cf/eat_list.php?page=1&keyword=%E8%8F%A0%E8%8F%9C
    //http://www.qubaobei.com/ios/cf/eat_list.php?page=1&cate_id=1 
    @Override
    protected RequestParams setRequestParams() {
        RequestParams params = new RequestParams();
        params.put("page",mPageIndex);
        params.put("keyword",keyword);
        params.put("cate_id",cateId);
        return params;
    }

    @Override
    protected void initView() {
        super.initView();
        setPullOrRefreshEnable(true,true);
    }

    @Override
    protected String setRequestURL() {
        return "ios/cf/eat_list.php?";
    }

    @Override
    protected EntityList parseList(String str) {
        Gson gson = new Gson();
        ResultModel mode = gson.fromJson(str,new TypeToken<ResultModel>(){}.getType());
        return mode;
    }

    @Override
    protected void executeOnLoadDataSuccess(List<?> list, boolean isRefresh) {
        if(isRefresh) {
            mData.clear();
            mData.addAll((Collection<? extends ResultModel.DataEntity>) list);
            mAdapter.notifyDataSetChanged();
        }else {
            mData.addAll((Collection<? extends ResultModel.DataEntity>) list);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_noeat_search_result;
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        keyword = getIntent().getStringExtra(NoeatActivity.SEARCH_KEY_WORD);
        cateId = getIntent().getStringExtra(NoeatActivity.SEARCH_CATE_ID);
        title = getIntent().getStringExtra(NoeatActivity.SEARCH_RESULT_TITLE);
        mAdapter = new ResultAdapter(this,mData);
        mListView.setAdapter(mAdapter);
        mToolbar.setTitle(title);
        refreshData();
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ResultModel.DataEntity data = mAdapter.getItem(position-1);
                Intent intent = new Intent(NoeatSearchResultActivity.this,NoeatDetailActivity.class);
                intent.putExtra(NoeatDetailActivity.FOOD_ID, data.getId());
                intent.putExtra(NoeatDetailActivity.FOOD_NAME,data.getName());
                NoeatSearchResultActivity.this.startActivity(intent);
            }
        });
    }
}
