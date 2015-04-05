package cn.wayne.mamypoko.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.wayne.mamypoko.R;

/**
 * Created by Lumia on 2015/4/1.
 */
public class FindItem extends LinearLayout {
    private int mViewBg;
    private String mTypeText;
    private Drawable mTpyeImage;
    private TextView mTextCount;
    private int number=-1;
    private int mCount = 0;
    public FindItem(Context context) {
        this(context, null);
    }

    public FindItem(Context context, AttributeSet attrs) {
        super(context, attrs);
       TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.FindItem);
        mTpyeImage = a.getDrawable(R.styleable.FindItem_typeImage);
        mTypeText = a.getString(R.styleable.FindItem_typeText);
        mViewBg = a.getInt(R.styleable.FindItem_showLine, 0);
        number = a.getInt(R.styleable.FindItem_number, -1);
        a.recycle();
        LayoutInflater.from(context).inflate(R.layout.layout_find_item,this,true);
    }


    @Override
    protected void onFinishInflate() {

        super.onFinishInflate();
        ImageView imageView = (ImageView) findViewById(R.id.image_found_item_type);
        TextView textView = (TextView) findViewById(R.id.text_found_item_title);
        View view =  findViewById(R.id.view_found_item_bg);
        mTextCount = (TextView) findViewById(R.id.text_found_item_count);
        if(mTpyeImage!=null) {
            imageView.setImageDrawable(mTpyeImage);
        }

        if(mTypeText!=null) {
            textView.setText(mTypeText);
        }

        if(number < 0) {
            mTextCount.setVisibility(View.GONE);
        }else {
            mTextCount.setVisibility(View.VISIBLE);
        }

        mTextCount.setText(Integer.toString(number));

       switch (mViewBg) {
           case 0:
               view.setVisibility(View.VISIBLE);
               break;
           case 1:
               view.setVisibility(View.GONE);
               break;
       }
    }

    /**
     * 设置右边的数目
     * @param count
     */
    public void setCount(int count) {
        if(count < 0) {
            mTextCount.setVisibility(View.GONE);
        }else {
            mTextCount.setVisibility(View.VISIBLE);
        }
        mTextCount.setText(count+"");
        mCount = count;
        invalidate();
    }

    /**
     * 返回count
     * @return
     */
    public int getCount() {
        return mCount;

    }

}
