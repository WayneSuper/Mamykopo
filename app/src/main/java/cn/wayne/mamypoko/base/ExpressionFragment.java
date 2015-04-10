package cn.wayne.mamypoko.base;


import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.home.adapter.ExpressionAdapter;
import cn.wayne.mamypoko.mode.home.adapter.ExpressionPagerAdapter;
import cn.wayne.mamypoko.ui.NoScrollGridView;
import cn.wayne.mamypoko.ui.PasteEditText;
import cn.wayne.mamypoko.utils.AppUtil;
import cn.wayne.mamypoko.utils.SmileUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExpressionFragment extends CommBaseFragment implements View.OnClickListener {
    private Context mContext;
    private ViewPager expressionViewpager;
    private PasteEditText mEditTextContent;
    private List<String> reslist;
    private Button btnReplay;
    private LinearLayout faceContainer;
    private ImageButton btnSmile;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mOnReplyBtnClick = (OnReplyBtnClick) activity;
        } catch (ClassCastException e) {
            new Throwable(activity.getClass().getName() + "must implement OnReplyBtnClickListener");
        }
    }

    @Override
    protected int setRootLayoutID() {
        return R.layout.layout_replay_input;
    }

    protected void initView(View view) {
        mContext = getActivity();
        expressionViewpager = (ViewPager) view.findViewById(R.id.viewPager);
        mEditTextContent = (PasteEditText) view.findViewById(R.id.edit_replay_content);
        btnReplay = (Button) view.findViewById(R.id.btn_send_replay);
        faceContainer = (LinearLayout) view.findViewById(R.id.ll_face_container);
        btnSmile = (ImageButton) view.findViewById(R.id.ib_smail_click);
        btnReplay.setEnabled(false);
    }

    @Override
    protected void initData(View view) {
        // 表情list
        initSmileContainer();
    }

    @Override
    protected void initEvent(View view) {
        btnSmile.setOnClickListener(this);
        btnReplay.setOnClickListener(this);
        mEditTextContent.setOnClickListener(this);
        mEditTextContent.addTextChangedListener(mTextWatcher);
    }

    private void initSmileContainer() {
        reslist = getExpressionRes(104);
        // 初始化表情viewpager
        List<View> views = new ArrayList<View>();
        View gv1 = getGridChildView(1);
        View gv2 = getGridChildView(2);
        View gv3 = getGridChildView(3);
        View gv4 = getGridChildView(4);

        views.add(gv1);
        views.add(gv2);
        views.add(gv3);
        views.add(gv4);
        expressionViewpager.setAdapter(new ExpressionPagerAdapter(views));
    }

    public List<String> getExpressionRes(int getSum) {
        List<String> reslist = new ArrayList<String>();
        for (int x = 0; x <= getSum; x++) {
            String filename = "smiley_" + x;
            reslist.add(filename);
        }
        return reslist;

    }


    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (count > 0) {
                btnReplay.setEnabled(true);
            } else {
                btnReplay.setEnabled(false);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };


    /**
     * 获取表情的gridview的子view
     *
     * @param i
     * @return
     */
    private View getGridChildView(int i) {
        View view = View.inflate(mContext, R.layout.expression_gridview, null);
        NoScrollGridView gv = (NoScrollGridView) view.findViewById(R.id.gridview);
        List<String> list = new ArrayList<String>();
        if (i == 1) {
            List<String> list1 = reslist.subList(0, 30);
            list.addAll(list1);
        } else if (i == 2) {
            list.addAll(reslist.subList(30, 60));
        } else if (i == 3) {
            list.addAll(reslist.subList(60, 90));
        } else if (i == 4){
            list.addAll(reslist.subList(90,reslist.size()));
        }
        list.add("delete_expression");
        final ExpressionAdapter expressionAdapter = new ExpressionAdapter(mContext, 1, list);
        gv.setAdapter(expressionAdapter);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String filename = expressionAdapter.getItem(position);
                try {
                    if (!"delete_expression".equals(filename)) { // 不是删除键，显示表情
                        // 这里用的反射，所以混淆的时候不要混淆SmileUtils这个类
                        Class clz = Class.forName("cn.wayne.mamypoko.utils.SmileUtils");
                        Field field = clz.getField(filename);
                        mEditTextContent.append(SmileUtils.getSmiledText(mContext, (String) field.get(null)));
                    } else { // 删除文字或者表情
                        if (!TextUtils.isEmpty(mEditTextContent.getText())) {

                            int selectionStart = mEditTextContent.getSelectionStart();// 获取光标的位置
                            if (selectionStart > 0) {
                                String body = mEditTextContent.getText().toString();
                                String tempStr = body.substring(0, selectionStart);
                                int i = tempStr.lastIndexOf("[");// 获取最后一个表情的位置
                                if (i != -1) {
                                    CharSequence cs = tempStr.substring(i, selectionStart);
                                    if (SmileUtils.containsKey(cs.toString()))
                                        mEditTextContent.getEditableText().delete(i, selectionStart);
                                    else
                                        mEditTextContent.getEditableText().delete(selectionStart - 1, selectionStart);
                                } else {
                                    mEditTextContent.getEditableText().delete(selectionStart - 1, selectionStart);
                                }
                            }
                        }

                    }
                } catch (Exception e) {
                }
            }
        });
        return view;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_send_replay:
                AppUtil.hideKeyBoard((Activity) mContext);
                faceContainer.setVisibility(View.GONE);
                if (mOnReplyBtnClick != null) {
                    // Spannable span = SmileUtils.getSmiledText(mContext, mEditTextContent.getText().toString());
                    //   holder.tv.setText(span, TextView.BufferType.SPANNABLE);
                    if (mEditTextContent.getText().toString().length() > 0) {
                        mOnReplyBtnClick.onClick(mEditTextContent.getText().toString());
                        mEditTextContent.setText("");

                    } else {
                        Toast.makeText(mContext, mContext.getString(R.string.reply_empty_tip)
                                , Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.ib_smail_click:
                if (faceContainer.getVisibility() == View.GONE) {
                    faceContainer.setVisibility(View.VISIBLE);
                    AppUtil.hideKeyBoard((Activity) mContext);
                } else {
                    faceContainer.setVisibility(View.GONE);
                }
                break;
            case R.id.edit_replay_content:
                faceContainer.setVisibility(View.GONE);
                break;
        }
    }

    private OnReplyBtnClick mOnReplyBtnClick;

    public void setOnReplyBtnClick(OnReplyBtnClick mOnReplyBtnClick) {
        this.mOnReplyBtnClick = mOnReplyBtnClick;
    }

    /**
     * 供使用这个控件的活动调用隐藏掉表情容器和软键盘
     */
    public void hideReplyContainer() {
        AppUtil.hideKeyBoard((Activity) mContext);
        faceContainer.setVisibility(View.GONE);
    }

    public interface OnReplyBtnClick {
        void onClick(String text);
    }
}
