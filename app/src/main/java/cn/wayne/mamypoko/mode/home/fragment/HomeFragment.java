package cn.wayne.mamypoko.mode.home.fragment;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.diary.DiaryListActivity;
import cn.wayne.mamypoko.mode.home.activity.FindContentActivity;
import cn.wayne.mamypoko.mode.home.adapter.FindAdapter;
import cn.wayne.mamypoko.mode.home.entity.Advert;
import cn.wayne.mamypoko.mode.home.entity.Beauty;
import cn.wayne.mamypoko.net.MamyClient;
import cn.wayne.mamypoko.ui.CarouselView;
import cn.wayne.mamypoko.ui.NoScrollListView;
import cn.wayne.mamypoko.ui.ObservableScrollView;
import cn.wayne.mamypoko.ui.actionbutton.FloatingActionButton;
import cn.wayne.mamypoko.ui.actionbutton.FloatingActionsMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ObservableScrollView.OnScrollListener,View.OnClickListener {
    private LinearLayout trick;
    private ObservableScrollView scrollView;
    private FrameLayout root;
    private LinearLayout normal;
    private NoScrollListView mListView;
    private Context mContext;
    private CarouselView mCarouselView;
    private ArrayList<String> imageUrls;
    private FloatingActionButton actionButtonNoeat;
    private FloatingActionButton actionButtonDiary;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext = getActivity();
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        root = (FrameLayout) view.findViewById(R.id.root);
        scrollView = (ObservableScrollView) view.findViewById(R.id.scrollView);
        trick = (LinearLayout) view.findViewById(R.id.trickView1);
        normal = (LinearLayout) view.findViewById(R.id.trickView2);
        mListView = (NoScrollListView)view.findViewById(R.id.listView);
        actionButtonNoeat = (FloatingActionButton)view.findViewById(R.id.action_btn_noeat);
        actionButtonDiary = (FloatingActionButton)view.findViewById(R.id.action_btn_diary);
        scrollView.setOnScrollListener(this);
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScroll(scrollView.getScrollY());
            }
        });
        FloatingActionsMenu actionsMenu = (FloatingActionsMenu) root.findViewById(R.id.fab_home_actions_menu);
        actionsMenu.attachToScrollView(scrollView, null);


        initFoundData();
        mCarouselView =(CarouselView)view.findViewById(R.id.carouseView);
        getAdverts();
        initEvent();
    }

    private void initEvent() {
        actionButtonDiary.setOnClickListener(this);
        actionButtonNoeat.setOnClickListener(this);
    }


    private void getAdverts() {
        imageUrls=new ArrayList<String>();
        //http://www.qubaobei.com/ios/api/adr_ad.php?is_new=1&site=61&uid=4325690&user_type=2&baby_time=2015-04-01
        RequestParams params = new RequestParams();
        params.put("is_new","1");
        params.put("site","61");
        params.put("uid","4325690");
        params.put("user_type","2");
        params.put("baby_time", "2015-04-01");
        MamyClient.get("ios/api/adr_ad.php?", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                List<Advert> adverts = Advert.parser(responseBody);
                if(adverts!=null) {
                    for (int i = 0; i < adverts.size(); i++) {
                        Advert item = adverts.get(i);
                        imageUrls.add(item.getPic200());
                    }
                }
                mCarouselView.setImageUris(imageUrls);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                mCarouselView.setImageUris(imageUrls);
            }

        });
    }

    FindAdapter mAdapter;
    List<Beauty.BeautyEntity> mData;
    private void initFoundData(){
        mData = new ArrayList<>();
        mAdapter = new FindAdapter(mContext,mData);
        mListView.setAdapter(mAdapter);
       /// http://www.qubaobei.com/ios/api/home_beauty.php?id=4646798
        RequestParams params = new RequestParams();
        params.put("id","4646798");
        MamyClient.get("ios/api/home_beauty.php?", params, new AsyncHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Beauty beauty = Beauty.parser(responseBody);
                List<Beauty.BeautyEntity> items = beauty.getData();
                mData.addAll(items);
                mAdapter.notifyDataSetChanged();
            }

            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(mContext, FindContentActivity.class);
                intent.putExtra(FindContentActivity.DISPLAY_OBJECT,mData.get(position));
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public void onScroll(int scrollY) {
// 获取正常布局的位置来重新设置粘至布局的位置
        int layoutTop = Math.max(scrollY, trick.getTop());
        normal.layout(0, layoutTop, normal.getWidth(),
                layoutTop + normal.getHeight());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_btn_noeat:
                break;
            case R.id.action_btn_diary:
                startActivity(new Intent(mContext, DiaryListActivity.class));
                break;
        }
    }
}
