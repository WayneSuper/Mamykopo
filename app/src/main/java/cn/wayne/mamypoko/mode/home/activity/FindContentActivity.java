package cn.wayne.mamypoko.mode.home.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.text.Html;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseChildActivity;
import cn.wayne.mamypoko.base.ExpressionFragment;
import cn.wayne.mamypoko.base.parser.FindParser;
import cn.wayne.mamypoko.mode.home.adapter.FindReplyAdapter;
import cn.wayne.mamypoko.mode.home.entity.Beauty;
import cn.wayne.mamypoko.mode.home.entity.FindContent;
import cn.wayne.mamypoko.mode.home.entity.FindContentEntity;
import cn.wayne.mamypoko.mode.home.entity.FindMoreReplyEntity;
import cn.wayne.mamypoko.mode.home.entity.FindReplyEntity;
import cn.wayne.mamypoko.net.MamyClient;
import cn.wayne.mamypoko.ui.NoScrollListView;

public class FindContentActivity extends BaseChildActivity implements ExpressionFragment.OnReplyBtnClick {

    public static final String DISPLAY_OBJECT = "DISPLAY_OBJECT";
    private View rootView;
    TextView text_artical_title;
    private TextView text_artical_replys, text_artical_from,
            text_artical_author_name, text_artical_author_pubtime,
            text_artical_author_state, text_artical_author_location;
    private ImageView image_artical_author_level;
    private Beauty.BeautyEntity mBeautyEntity;
    private SimpleDraweeView drawee_icon;
    private LinearLayout mContent;
    private FindContent mFindContent;
    private FindReplyAdapter mReplyAdapter;
    private List<FindReplyEntity> mReplyEntities;
    private NoScrollListView mReplyListView;
    private Button loadMoreBtn;
    private View loadMoreView;
    private boolean flag;
    private int number = 20;
    private boolean loadfinish = true;
    private ScrollView mScrollView;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_find_content;
    }

    @Override
    protected void initView() {
        super.initView();
        rootView = findViewById(R.id.root_view);
        text_artical_title = getView(R.id.text_artical_title);
        text_artical_from = getView(R.id.text_artical_from);
        text_artical_replys = getView(R.id.text_artical_replys);
        text_artical_author_name = getView(R.id.text_artical_author_name);
        image_artical_author_level = getView(R.id.text_artical_author_level);
        text_artical_author_pubtime = getView(R.id.text_artical_author_pubtime);
        text_artical_author_state = getView(R.id.text_artical_author_state);
        text_artical_author_location = getView(R.id.text_artical_author_location);
        mReplyListView = getView(android.R.id.list);
        loadMoreView = getLayoutInflater().inflate(R.layout.layout_load_more, null);
        loadMoreBtn = (Button) loadMoreView.findViewById(R.id.btn_load_more);
        mReplyListView.addFooterView(loadMoreView);// 添加页脚（放在ListView最后）
        mScrollView = getView(R.id.scrollView);
        mContent = getView(R.id.ll_real_container);
        drawee_icon = getView(R.id.drawee_icon);

    }

    @Override
    protected int setRightBtnIcon() {
        return 0;
    }

    @Override
    protected boolean isRightShow() {
        return false;
    }

    @Override
    protected boolean isMoreShow() {
        return true;
    }

    @Override
    protected void initEvent() {
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ExpressionFragment mExpressionFragment = (ExpressionFragment) getSupportFragmentManager()
                        .findFragmentByTag(getString(R.string.tag_expression));
                mExpressionFragment.hideReplyContainer();
            }
        });
        ExpressionFragment mExpressionFragment = (ExpressionFragment) getSupportFragmentManager()
                .findFragmentByTag(getString(R.string.tag_expression));
        mExpressionFragment.setOnReplyBtnClick(this);
        mScrollView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_MOVE:
                        int scrollY = v.getScrollY();
                        int height = v.getHeight();
                        int scrollViewMeasuredHeight = mScrollView.getChildAt(0).getMeasuredHeight();
                        if ((scrollY + height) >= scrollViewMeasuredHeight) {
                            if(hasMore && loadfinish){
                                loadfinish = false;
                                loadMoreReply();
                            }

                        }
                        break;
                }
                return false;
            }
        });

        loadMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMoreReply();
            }
        });
    }

    @Override
    protected void initData() {
        mBeautyEntity = (Beauty.BeautyEntity) getIntent().getSerializableExtra(DISPLAY_OBJECT);
        if (mBeautyEntity == null) {
            finish();
            return;
        }
        text_artical_title.setText(mBeautyEntity.getTitle());
        text_artical_from.setText(mBeautyEntity.getFrom());
        text_artical_replys.setText(mBeautyEntity.getRe_num());
        text_artical_author_name.setText(mBeautyEntity.getNickname());
        text_artical_author_pubtime.setText(mBeautyEntity.getShowdated());
        text_artical_author_state.setText(mBeautyEntity.getAge_str());
        image_artical_author_level.setImageBitmap(getDrawableByName(mBeautyEntity.getPic()));
        drawee_icon.setImageURI(Uri.parse(mBeautyEntity.getAvatar()));
        getFindContentFromServer();
        mReplyEntities = new ArrayList<>();
        mReplyAdapter = new FindReplyAdapter(this, 0, mReplyEntities);

        mReplyListView.setAdapter(mReplyAdapter);

    }

    private void getFindContentFromServer() {
        RequestParams params = new RequestParams();
        params.put("mobile", 61);
        params.put("id", mBeautyEntity.getId());
        params.put("limit", 20);
        params.put("mobile", 1);
        //http://www.qubaobei.com/ios/api/adr_view.php?mobile=61&id=7097801&limit=20&order=1
        MamyClient.get("ios/api/adr_view.php?", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                mFindContent = FindParser.parser(responseBody);
                if (mFindContent == null) {
                    Toast.makeText(FindContentActivity.this, "数据解析错误", Toast.LENGTH_SHORT).show();
                    FindContentActivity.this.finish();
                    return;
                }
                mReplyEntities.addAll(mFindContent.getReplys());
                mReplyAdapter.notifyDataSetChanged();
                text_artical_author_location.setText(mFindContent.getCity());
                createContent();
                if (mFindContent.getHas_more() == 1) {
                    hasMore = true;
                } else {
                    hasMore = false;
                }
                changeFooterViewState();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(FindContentActivity.this, "数据解析错误", Toast.LENGTH_SHORT).show();
                FindContentActivity.this.finish();
            }
        });
    }

    private void changeFooterViewState() {
        if (hasMore) {
            loadMoreBtn.setText(getString(R.string.xlistview_footer_hint_normal));
            loadMoreBtn.setEnabled(true);
        } else {
            loadMoreBtn.setText("没有更多数据了");
            loadMoreBtn.setEnabled(false);
        }
    }

    private void createContent() {
        List<FindContentEntity> list = mFindContent.getContents();
        for (int i = 0; i < list.size(); i++) {
            FindContentEntity item = list.get(i);
            if (item.getType() == 1) {
                TextView textView = new TextView(this);
                textView.setText(Html.fromHtml(item.getContent()));
                textView.setTextColor(Color.parseColor("#003E3E"));
                // textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f);
                mContent.addView(textView);
            } else {
                SimpleDraweeView draweeView = new SimpleDraweeView(this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        (int) ((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, item.getHeight(), getResources().getDisplayMetrics()) / 1.5f));
                draweeView.setLayoutParams(lp);
                draweeView.getHierarchy().setPlaceholderImage(R.drawable.default_home_list_bg);
                draweeView.setImageURI(Uri.parse(item.getContent()));
                mContent.addView(draweeView);
            }
        }
    }


    protected Bitmap getDrawableByName(String num) {
        int id = getResources().getIdentifier("myinfo_level_lv" + num, "drawable", getPackageName());
        if (id > 0) {
            return BitmapFactory.decodeResource(getResources(), id);
        }
        return null;
    }

    @Override
    public void onClick(String text) {
//http://www.qubaobei.com/ios/api/adr_reply_add_v2.php
        //to_reply_id=0
        // &crc=MzE5NjgwOSw0MzI1NjkwLDQ1NTk1OTA7MWU2NjUxOWI5ZDFlNWI1ZDVhN2RmNTYzYTdiMzc0YWY%3D
        // &o_user_id=4325690
        // &site=2
        // &content=%E5%95%A6%E5%95%A6%E5%95%A6%E5%95%A6
        // &crc_qq=NDU2NzAzMSw0NjQ2Nzk4LDE2MTAxNzA7Mzk5OTM5NGEwMjRmZjg2NjFlZmY4MDY3NWQzMGVhYjg%3D
        // &post_id=7097760
        // &to_reply_step=0
        RequestParams params = new RequestParams();
        params.put("crc","MzE5NjgwOSw0MzI1NjkwLDQ1NTk1OTA7MWU2NjUxOWI5ZDFlNWI1ZDVhN2RmNTYzYTdiMzc0YWY%3D");
        params.put("o_user_id","4325690");
        params.put("site","2");
        params.put("content", text);
        params.put("crc_qq","NDU2NzAzMSw0NjQ2Nzk4LDE2MTAxNzA7Mzk5OTM5NGEwMjRmZjg2NjFlZmY4MDY3NWQzMGVhYjg%3D");
        params.put("post_id",mBeautyEntity.getId());
        params.put("to_reply_step","0");

        MamyClient.post("", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //{"ret":1,"data":{"reply_id":147511515},"error":0,"msg_id":0}
                String str = new String(responseBody);
                try {
                    JSONObject obj = new JSONObject(str);
                    int ret = obj.getInt("ret");
                    int error = obj.getInt("error");
                    if(ret == 1 && error == 0) {
                        Toast.makeText(FindContentActivity.this,"回复成功",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(FindContentActivity.this,"回复失败",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Toast.makeText(FindContentActivity.this,"回复失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected String setPageTitle() {
        return getString(R.string.find_bty);
    }

    private int pageIndex = 2;

    private boolean hasMore = false;

    private void loadMoreReply() {
        if (hasMore) {
            loadMoreBtn.setText(getString(R.string.xlistview_header_hint_loading));
            //http://www.qubaobei.com/ios/api/adr_reply_list.php?limit=20&order=1&id=7097671&page=6
            RequestParams params = new RequestParams();
            params.put("limit", "20");
            params.put("order", "1");
            params.put("id", mBeautyEntity.getId());
            params.put("page", pageIndex++);
            MamyClient.get("ios/api/adr_reply_list.php?", params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                    FindMoreReplyEntity entity = FindParser.parserMoreReply(responseBody);
                    if (entity.getHas_more() == 1) {
                        hasMore = true;
                    } else {
                        hasMore = false;
                    }
                    mReplyEntities.addAll(entity.getData());
                    mReplyAdapter.notifyDataSetChanged();
                    loadfinish = true; // 加载完成
                    changeFooterViewState();
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                    loadMoreBtn.setText("数据加载异常,点击重试");
                    if (pageIndex > 2) {
                        pageIndex--;
                    }
                }
            });
        }
    }
}
