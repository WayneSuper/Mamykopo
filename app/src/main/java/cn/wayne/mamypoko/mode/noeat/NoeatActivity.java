package cn.wayne.mamypoko.mode.noeat;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.SearchView;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseChildActivity;
import cn.wayne.mamypoko.net.MamyClient;
import cn.wayne.mamypoko.ui.FlowLayout;
import cn.wayne.mamypoko.ui.KeyBoardLayout;

public class NoeatActivity extends BaseChildActivity {
    public final static String SEARCH_CATE_ID = "cateid";
    public static final String SEARCH_RESULT_TITLE = "title";
    public static final String SEARCH_KEY_WORD = "keyword";
    private SearchView mSearchView;
    private GridView mGridView;
    private KeyBoardLayout rootView;
    private View introduceView;
    private View foodtypeView;
    private FlowLayout mHotContainer;
    private  FoodTypeAdapter mAdapter;

    @Override
    protected int setContentViewID() {
        return R.layout.activity_noeat;
    }

    @Override
    protected void initEvent() {
        rootView.setOnkbdStateListener(new KeyBoardLayout.onKybdsChangeListener() {
            @Override
            public void onKeyBoardStateChange(int state) {
                switch (state) {
                    case KeyBoardLayout.KEYBOARD_STATE_SHOW:
                        introduceView.setVisibility(View.VISIBLE);
                        foodtypeView.setVisibility(View.GONE);
                        break;
                    case KeyBoardLayout.KEYBOARD_STATE_HIDE:
                        introduceView.setVisibility(View.GONE);
                        foodtypeView.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });


        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NoeatActivity.this, NoeatSearchResultActivity.class);
                intent.putExtra(SEARCH_CATE_ID, position + 1 + "");
                intent.putExtra(SEARCH_RESULT_TITLE, mAdapter.getItem(position));
                NoeatActivity.this.startActivity(intent);
            }
        });

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(s==null || s.length()<1) {
                    Toast.makeText(NoeatActivity.this,getString(R.string.key_word),Toast.LENGTH_LONG).show();
                    return true;
                }
                Intent intent = new Intent(NoeatActivity.this, NoeatSearchResultActivity.class);
                intent.putExtra(SEARCH_RESULT_TITLE, getString(R.string.search_result_title));
                intent.putExtra(SEARCH_KEY_WORD, s);
                mSearchView.setQuery("",false);
                NoeatActivity.this.startActivity(intent);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    protected void initData() {
        mAdapter = new FoodTypeAdapter(this);
        mGridView.setAdapter(mAdapter);

        //http://baby.ci123.com/yunqi/api/ios_search.php?uid=4646798
        RequestParams params = new RequestParams();
        params.put("uid", "4646798");
        MamyClient.getWithFull("http://baby.ci123.com/yunqi/api/ios_search.php?", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Gson gson = new Gson();
                JsonReader reader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(responseBody)));
                Type type = new TypeToken<NoEatModel>() {
                }.getType();
                NoEatModel mode = gson.fromJson(reader, type);
                createHotSearch(mode);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });


    }

    private void createHotSearch(NoEatModel mode) {
        if (mode != null) {

            for (final NoEatModel.FoodEntity item : mode.getSeason2()) {
                Button btn = new Button(this);
                btn.setText(item.getName());
                btn.setTextSize(12);
                btn.setTextColor(Color.parseColor(item.getColor()));
                btn.setBackgroundResource(R.drawable.btn_oval_bg);
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(NoeatActivity.this,NoeatSearchResultActivity.class);
                        intent.putExtra(SEARCH_KEY_WORD,item.getName());
                        intent.putExtra(SEARCH_RESULT_TITLE,item.getName());
                        NoeatActivity.this.startActivity(intent);
                    }
                });
                FlowLayout.LayoutParams lp = new FlowLayout.LayoutParams(FlowLayout.LayoutParams.WRAP_CONTENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 30, getResources().getDisplayMetrics()));
                lp.gravity = Gravity.CENTER;
                lp.setMargins(15, 20, 0, 0);
                btn.setLayoutParams(lp);
                mHotContainer.addView(btn);
            }
        }
    }

    @Override
    protected void initView() {
        super.initView();
        mSearchView = (SearchView) findViewById(R.id.searchView);
        mGridView = (GridView) findViewById(R.id.gridview);
        rootView = getView(R.id.root_view);
        introduceView = getView(R.id.introduce_container);
        foodtypeView = getView(R.id.foodtype_container);
        mHotContainer = getView(R.id.hot_search_food);
        setUpSearchView();
    }

    private void setUpSearchView() {
        try {
            mSearchView.onActionViewExpanded();
            Class<?> argClass = mSearchView.getClass();
            Field ownField = argClass.getDeclaredField("mSearchPlate");
            ownField.setAccessible(true);
            View mView = (View) ownField.get(mSearchView);
            mView.setBackgroundDrawable(getResources().getDrawable(R.drawable.input_reply_bg));
            TextView textView = (TextView) mSearchView.findViewById(R.id.search_src_text);
            textView.setTextColor(Color.WHITE);
            textView.setHintTextColor(Color.WHITE);
            textView.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics()));
        } catch (Exception e) {
        }
    }

    @Override
    protected boolean isMoreShow() {
        return false;
    }

    @Override
    protected boolean isRightShow() {
        return false;
    }

    @Override
    protected int setRightBtnIcon() {
        return 0;
    }

    @Override
    protected String setPageTitle() {
        return getString(R.string.not_eat);
    }
}
