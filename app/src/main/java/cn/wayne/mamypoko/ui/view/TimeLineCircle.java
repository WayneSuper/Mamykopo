package cn.wayne.mamypoko.ui.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.utils.Logger;

/**
 * Created by Lumia on 2015/4/11.
 */
public class TimeLineCircle extends View {

    private String dayStr;
    private String monthStr;
    private int borderColor = Color.parseColor("#FF0033");
    private int bootomColor = Color.parseColor("#FF0033");
    private Paint paint;
    private TextPaint textPaint;
    private int topTextColor = bootomColor;
    private int bottomTextColor = Color.WHITE;
    private int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,10,getResources().getDisplayMetrics());
    private  Rect textBound;
    private RectF oval ;
    private int textHight;
    private float tw;
    private float textWidth;

    public TimeLineCircle(Context context) {
        this(context, null);
    }

    public TimeLineCircle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TimeLineCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TimeLineCircle, defStyleAttr, 0);
        monthStr = a.getString(R.styleable.TimeLineCircle_month);
        dayStr = a.getString(R.styleable.TimeLineCircle_day);
        borderColor = a.getColor(R.styleable.TimeLineCircle_borderColor, borderColor);
        bootomColor = a.getColor(R.styleable.TimeLineCircle_bootomColor, bootomColor);
        a.recycle();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        oval = new RectF();
        textBound = new Rect();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.WHITE);
        int w = Math.max(getMeasuredWidth(), getMeasuredHeight());
        oval.set(0,0,w,w);
        int ww = w - 4;
        tw = (float)(Math.sqrt(ww*ww/2.0f));
        drawCircle(canvas, w);
        drawLine(canvas, w);
        drawTopText(canvas, w);
        canvas.drawArc(oval,0,180,true,paint);
        drawBottomText(canvas, w);
    }

    private void drawLine(Canvas canvas, int w) {
        paint.reset();
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawLine(0,w/2.0f,w,w/2.0f,paint);
    }

    private void drawCircle(Canvas canvas, int w) {
        paint.setAntiAlias(true);
        paint.setColor(borderColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        canvas.drawCircle(w / 2.0f, w / 2.0f, (w - 4f) / 2, paint);
    }

    private void drawTopText(Canvas canvas, int w) {
        textPaint.setColor(topTextColor);
        textPaint.setTextSize(textSize);
        textPaint.getTextBounds(monthStr,0,monthStr.length(),textBound);
        canvas.drawText(monthStr,w/2-tw+(2*tw-textBound.width())/2,w/4+textBound.height()/2,textPaint);
    }

    private void drawBottomText(Canvas canvas, int w) {
        textPaint.reset();
        textPaint.setColor(bottomTextColor);
        textPaint.setTextSize(textSize);
        canvas.drawText(dayStr,w/2-tw+(2*tw-textBound.width())/2,3*w/4+textBound.height()/2,textPaint);
    }

    public void setMoth(String month) {
        this.monthStr = month;
        invalidate();
    }

    public void setDay(String day) {
        this.dayStr = day;
        invalidate();
    }

    public String getMonth() {
        return monthStr;
    }

    public String getDay() {
        return dayStr;
    }
}
