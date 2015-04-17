package cn.wayne.mamypoko.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.utils.Logger;

/**
 * Created by Pollux on 2015/4/15.
 * //
 */
public class StateImageText extends RelativeLayout {

    public static final int STATE_OK = 1;
    public static final int STATE_WARM = 2;
    public static final int STATE_ERROR = 3;
    private String mText;
    private int mImageState = STATE_OK;
    private ImageView mImageView;
    private TextView mTextView;


    public StateImageText(Context context) {
        this(context, null);
    }

    public StateImageText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StateImageText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.StateImageText, defStyleAttr, 0);
        mText = a.getString(R.styleable.StateImageText_android_text);
        mImageState = a.getInt(R.styleable.StateImageText_state, STATE_OK);
        a.recycle();
        LayoutInflater.from(context).inflate(R.layout.item_image_text,this,true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mImageView = (ImageView) findViewById(R.id.image_type);
        mTextView = (TextView) findViewById(R.id.text_type);
        if(mText!=null) mTextView.setText(mText);
        setImageState();
    }

    private void setImageState() {
        switch (mImageState) {
            case STATE_OK:
                mImageView.setImageResource(R.drawable.eat_yes);
                break;
            case STATE_WARM:
                mImageView.setImageResource(R.drawable.eat_danger);
                break;
            case STATE_ERROR:
                mImageView.setImageResource(R.drawable.eat_no);
                break;

        }
    }

    public void setState(int state) {
        this.mImageState = state;
       setImageState();
    }
}
