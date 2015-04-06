package cn.wayne.mamypoko.mode.movement.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;

import java.util.ArrayList;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseXListViewFragment;
import cn.wayne.mamypoko.base.entity.EntityList;
import cn.wayne.mamypoko.mode.movement.adapter.ActivityAdapter;
import cn.wayne.mamypoko.mode.movement.entity.ActMode;
import cn.wayne.mamypoko.mode.movement.entity.DataEntity;
import cn.wayne.mamypoko.widget.EmptyView;

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
