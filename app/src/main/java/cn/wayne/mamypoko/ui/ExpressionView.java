package cn.wayne.mamypoko.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Pollux on 2015/4/7.
 * //
 */
public class ExpressionView extends LinearLayout {

    public ExpressionView(Context context) {
        this(context,null);
    }

    public ExpressionView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public ExpressionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
