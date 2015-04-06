package cn.wayne.mamypoko.mode.home.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.home.adapter.FindAdapter;
import cn.wayne.mamypoko.mode.home.entity.Advert;
import cn.wayne.mamypoko.mode.home.entity.FindModel;
import cn.wayne.mamypoko.net.MamyClient;
import cn.wayne.mamypoko.widget.CarouselView;
import cn.wayne.mamypoko.widget.NoScrollListView;
import cn.wayne.mamypoko.widget.ObservableScrollView;
import cn.wayne.mamypoko.widget.actionbutton.FloatingActionsMenu;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements ObservableScrollView.OnScrollListener {
    private LinearLayout trick;
    private ObservableScrollView scrollView;
    private FrameLayout root;
    private LinearLayout normal;
    private NoScrollListView mListView;
    private Context mContext;
    private CarouselView mCarouselView;
    private ArrayList<String> imageUrls;

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
        scrollView.setOnScrollListener(this);
        root.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                onScroll(scrollView.getScrollY());
            }
        });
        FloatingActionsMenu actionsMenu = (FloatingActionsMenu) root.findViewById(R.id.fab_home_actions_menu);
        initFoundData();
        actionsMenu.attachToScrollView(scrollView,null);

        mCarouselView =(CarouselView)view.findViewById(R.id.carouseView);
        getAdverts();
    }

    private void getAdverts() {
        imageUrls=new ArrayList<String>();
        imageUrls.add("http://www.qipaox.com/tupian/200810/20081051924582.jpg");
        imageUrls.add("http://www.bz55.com/uploads1/allimg/120312/1_120312100435_8.jpg");
        imageUrls.add("http://img3.iqilu.com/data/attachment/forum/201308/21/192654ai88zf6zaa60zddo.jpg");
        imageUrls.add("http://img2.pconline.com.cn/pconline/0706/19/1038447_34.jpg");
        imageUrls.add("http://www.kole8.com/desktop/desk_file-11/2/2/2012/11/2012113013552959.jpg");
        imageUrls.add("http://www.237.cc/uploads/pcline/712_0_1680x1050.jpg");
        //http://www.qubaobei.com/ios/api/adr_ad.php?is_new=1&site=61&uid=4325690&user_type=2&baby_time=2015-04-01
        RequestParams params = new RequestParams();
        params.put("is_new","1");
        params.put("site","61");
        params.put("uid","4325690");
        params.put("user_type","2");
        params.put("baby_time","2015-04-01");
        MamyClient.get("ios/api/adr_ad.php?",params,new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(responseBody)));
                reader.setLenient(true);
//                Type type = new  <Advert>.getType();
//               List<Advert> advert =  gson.fromJson(reader,type);
//                List<Advert.DataEntity> adverts = (List<Advert.DataEntity>) advert.getList();
//                imageUrls=new ArrayList<String>();
//                for (int i = 0; i < adverts.size(); i++) {
//                    Advert.DataEntity item = adverts.get(i);
//                    imageUrls.add(item.getPic());
//                }
//                mCarouselView.setImageUris(imageUrls);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });





        mCarouselView.setImageUris(imageUrls);
    }

    FindAdapter mAdapter;
    List<FindModel> mData;
    private void initFoundData(){
        mData = new ArrayList<>();
        mAdapter = new FindAdapter(mContext,mData);
        mListView.setAdapter(mAdapter);
        BmobQuery<FindModel> query = new BmobQuery<>();
        query.setLimit(5);
        query.findObjects(mContext,new FindListener<FindModel>() {
            @Override
            public void onSuccess(List<FindModel> foundModels) {
                mData.addAll(foundModels);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(int i, String s) {
                Toast.makeText(mContext,s,Toast.LENGTH_LONG).show();
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
}
