package cn.wayne.mamypoko.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.wayne.mamypoko.R;

/**
 * Created by Lumia on 2015/4/4.
 */
public class EmptyView extends RelativeLayout implements View.OnClickListener {
    public static final int HIDE_LAYOUT = 4;
    public static final int NETWORK_ERROR = 1;
    public static final int NETWORK_LOADING = 2;
    public static final int NETWORK_LOADING_ANIM = 6;
    public static final int NODATA = 3;
    public static final int NODATA_NO_CLICK = 5;
    private int mErrorState = HIDE_LAYOUT;
    private ImageView loadingAnim;
    private ImageView loadNone;
    private ProgressBar progressBar;
    private TextView textView;
    private AnimationDrawable mAnim;
    private OnClickListener listener;
    private boolean clickable = false;
    private String mMessage;

    public EmptyView(Context context) {
        this(context, null);
    }

    public EmptyView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EmptyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_empty_view, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        init();
    }

    private void init() {
        loadingAnim = (ImageView) findViewById(R.id.image_loading);
        loadNone = (ImageView) findViewById(R.id.image_none_data);
        progressBar = (ProgressBar) findViewById(R.id.pb_loading);
        textView = (TextView) findViewById(R.id.tv_error_layout);
        mAnim = (AnimationDrawable) loadingAnim.getDrawable();
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (clickable && listener != null) {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void dismiss() {
        setVisibility(View.GONE);
    }

    public int getErrorState() {
        return mErrorState;
    }

    public void setErrorMessage(String msg) {
        textView.setText(msg);
        mMessage = msg;
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (visibility == View.GONE) {
            mErrorState = HIDE_LAYOUT;
            clickable = false;
        }
    }

    /**
     * HIDE_LAYOUT
     * NETWORK_ERROR
     * NETWORK_LOADING
     * NODATA
     * NODATA_NO_CLICK
     */
    public void setErrorType(int type) {
        setVisibility(View.VISIBLE);
        switch (type) {
            case HIDE_LAYOUT:
                setVisibility(View.GONE);
                if (mAnim.isRunning())
                    mAnim.stop();
                loadingAnim.clearAnimation();
                break;
            case NETWORK_ERROR:
                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                loadingAnim.setVisibility(View.GONE);
                loadNone.setVisibility(View.GONE);
                textView.setText("无网络链接，点击重试");
                if (mAnim.isRunning())
                    mAnim.stop();
                clickable = true;
                mErrorState = NETWORK_ERROR;
                break;
            case NETWORK_LOADING:
                textView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                loadingAnim.setVisibility(View.GONE);
                loadNone.setVisibility(View.GONE);
                if (mAnim.isRunning())
                    mAnim.stop();
                clickable = false;
                mErrorState = NETWORK_LOADING;
                break;
            case NETWORK_LOADING_ANIM:
                textView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                loadingAnim.setVisibility(View.VISIBLE);
                loadNone.setVisibility(View.GONE);
                if (!mAnim.isRunning())
                    mAnim.start();
                clickable = false;
                mErrorState = NETWORK_LOADING_ANIM;
                break;
            case NODATA:
                textView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                loadingAnim.setVisibility(View.GONE);
                loadNone.setVisibility(View.VISIBLE);
                if (mAnim.isRunning())
                    mAnim.stop();
                clickable = true;
                mErrorState = NODATA;
                break;
            case NODATA_NO_CLICK:
                textView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                loadingAnim.setVisibility(View.GONE);
                loadNone.setVisibility(View.VISIBLE);
                if (mAnim.isRunning())
                    mAnim.stop();
                clickable = false;
                mErrorState = NODATA_NO_CLICK;
                break;
            default:
                setVisibility(View.GONE);
        }
    }
}
