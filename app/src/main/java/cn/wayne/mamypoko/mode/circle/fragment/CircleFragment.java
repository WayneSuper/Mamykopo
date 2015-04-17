package cn.wayne.mamypoko.mode.circle.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

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
import cn.wayne.mamypoko.mode.circle.CircleListActivity;
import cn.wayne.mamypoko.mode.circle.adapter.CircleAdapter;
import cn.wayne.mamypoko.mode.circle.entity.CircleModel;
import cn.wayne.mamypoko.net.MamyClient;
import cn.wayne.mamypoko.ui.dragsortlistview.DragSortController;
import cn.wayne.mamypoko.ui.dragsortlistview.DragSortListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment extends ListFragment{
    private DragSortListView mDragListView;
    private Context mContext;
    private CircleAdapter mAdapter;
    private DragSortListView.DropListener onDrop =
            new DragSortListView.DropListener() {
                @Override
                public void drop(int from, int to) {
                    if (from != to) {
                        //上下拖动时,手指释放瞬间调用
                        CircleModel.DataEntity item = mAdapter.getItem(from);
                        mAdapter.remove(item);
                        mAdapter.insert(item, to);
                    }
                }
            };
    private DragSortController mController;
    private List<CircleModel.DataEntity> mData;

    public CircleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();

        return inflater.inflate(R.layout.fragment_circle, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDragListView = (DragSortListView) getListView();
        mData = new ArrayList<>();
        mAdapter = new CircleAdapter(mContext,0,mData);
       setListAdapter(mAdapter);
        getDatsetFromServer();
        mDragListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CircleModel.DataEntity item = mAdapter.getItem(position);
                Intent intent = new Intent(mContext, CircleListActivity.class);
                intent.putExtra("Qid",item.getBid());
                intent.putExtra("replyNum",item.getReply_num_string());
                intent.putExtra("postNum",item.getPost_num_string());
                intent.putExtra("name",item.getName());
                intent.putExtra("avater",item.getPic());
                mContext.startActivity(intent);
            }
        });
    }

    private  void getDatsetFromServer() {
        //http://www.qubaobei.com/ios/api/quan_list_v8.php?site=2&o_user_id=4325690
        RequestParams parmas = new RequestParams();
        parmas.put("o_user_id","4325690");
        parmas.put("site","2");

        MamyClient.get("ios/api/quan_list_v8.php?", parmas, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                JsonReader reader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(responseBody)));
                Type type = new TypeToken<CircleModel>(){}.getType();
                Gson gson = new Gson();
                CircleModel mode = gson.fromJson(reader, type);
                mData.addAll(mode.getData());
                mAdapter.notifyDataSetChanged();
                setupDragListView();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }



    private void setupDragListView() {
        //打开拖动开关,如果不打开 右滑操作没反应
        mDragListView.setDragEnabled(true);
        //设置监听器
        mDragListView.setDropListener(onDrop);
        mController = buildController(mDragListView);
        //设置悬浮框管理器,点击监听器
        mDragListView.setFloatViewManager(mController);
        mDragListView.setOnTouchListener(mController);
    }

    public DragSortController buildController(DragSortListView dslv) {
        DragSortController controller = new DragSortController(dslv);
        //设置拖动对象 id映射View
        controller.setDragHandleId(R.id.drag_handle);
        //设置移除开关
        //controller.setRemoveEnabled(true);
        //设置拖动/移除 模式
        controller.setDragInitMode(DragSortController.ON_LONG_PRESS);
       // controller.setRemoveMode(DragSortController.FLING_REMOVE);
        return controller;
    }
}
