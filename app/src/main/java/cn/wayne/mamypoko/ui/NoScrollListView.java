package cn.wayne.mamypoko.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Lumia on 2015/3/29.
 */
public class NoScrollListView extends ListView {
    public NoScrollListView(Context context) {
        this(context,null);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public NoScrollListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //根据模式计算每个child的高度和宽度
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
