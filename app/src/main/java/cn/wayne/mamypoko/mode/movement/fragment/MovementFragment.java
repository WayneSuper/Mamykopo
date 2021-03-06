package cn.wayne.mamypoko.mode.movement.fragment;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseWebActivity;
import cn.wayne.mamypoko.base.BaseXListViewFragment;
import cn.wayne.mamypoko.base.entity.EntityList;
import cn.wayne.mamypoko.mode.movement.adapter.ActivityAdapter;
import cn.wayne.mamypoko.mode.movement.entity.ActMode;
import cn.wayne.mamypoko.mode.movement.entity.DataEntity;
import cn.wayne.mamypoko.ui.EmptyView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovementFragment extends BaseXListViewFragment {
    private List<DataEntity> data;
    private ActivityAdapter mAdapter;

    @Override
    protected int setRootLayoutID() {
        return R.layout.fragment_movement;
    }

    @Override
    protected void initData(View view) {
        data = new ArrayList<>();
        mAdapter = new ActivityAdapter(mContext,data);
        listView.setAdapter(mAdapter);
        listView.setDividerHeight(20);
        refreshData();
    }


    @Override
    protected int setEmptyType() {
        return EmptyView.NETWORK_LOADING_ANIM;
    }

    @Override
    protected String setRequestURL() {
        return "ios/api/city_act_list.php?";
    }

    @Override
    protected RequestParams setRequestParams() {
        RequestParams params = new RequestParams();
        params.put("user_id","4646798");
        params.put("mobile_id","61");
        params.put("m_plat","61");
        params.put("m_site","2");
        return params;
    }

    @Override
    protected EntityList parseList(String str) {
        Gson gson = new Gson();
        return gson.fromJson(str, ActMode.class);
    }

    @Override
    protected void initEvent(View view) {
        super.initEvent(view);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataEntity item = data.get(position - 1) ;
                Intent intent = new Intent(mContext, BaseWebActivity.class);
                //http://baby.ci123.com/box/mobile/detail.php?gid=429&action=goto_innerweb
                //http://baby.ci123.com/box/mobile/detail.php?gid=456&action=goto_innerweb&o_user_id=4325690&m_plat=61&m_site=2
                intent.putExtra("url",item.getUrl()+"&o_user_id=4325690&m_plat=61&m_site=2");
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    protected void executeOnLoadDataSuccess(List<?> list) {
       if(list!=null) {
           if(STATE == STATE_REFRESHING) {
               data.clear();
               data.addAll((java.util.Collection<? extends DataEntity>) list);
               mAdapter.notifyDataSetChanged();
           }else if(STATE == STATE_LOADMORE){
               data.addAll((java.util.Collection<? extends DataEntity>) list);
               mAdapter.notifyDataSetChanged();
           }else{

           }
           STATE = STATE_NORMAL;
       }
    }
}
