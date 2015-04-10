package cn.wayne.mamypoko.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.facebook.drawee.view.SimpleDraweeView;

import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cn.wayne.mamypoko.R;

/**
 * Created by Pollux on 2015/4/1.
 * //
 */
public class CarouselView extends FrameLayout {

    private static final String DEFAULT_IMAGE = "defalut_image";
    private ImageHandler mhandler = new ImageHandler(new WeakReference<CarouselView>(this));
    private List<String> imageUris;
    private int mDefaultID = 0;
    private Context context;
    private List<ImageView> imageViewsList;
    private List<ImageView> dotViewsList;
    private LinearLayout mLinearLayout;
    private ViewPager mViewPager;
    private CarouselViewListener mFlashViewListener;//向外提供接口

    private int effect;//图片切换的动画效果

    public CarouselView(Context context) {
        this(context, null);

    }

    public CarouselView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CarouselView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub
        //读取该自定义控件自定义的属性
        this.context = context;
        TypedArray mTypedArray = context.obtainStyledAttributes(attrs, R.styleable.CarouselView);
        mDefaultID = mTypedArray.getResourceId(R.styleable.CarouselView_defalut_image, mDefaultID);
        mTypedArray.recycle();
        initUI(context);
        if (!(imageUris.size() <= 0)) {
            setImageUris(imageUris);//
        }

    }

    /**
     * 设置监听
     *
     * @param mFlashViewListener
     */
    public void setOnPageClickListener(CarouselViewListener mFlashViewListener) {

        this.mFlashViewListener = mFlashViewListener;
    }

    private void initUI(Context context) {
        imageViewsList = new ArrayList<ImageView>();
        dotViewsList = new ArrayList<ImageView>();
        imageUris = new ArrayList<String>();
        LayoutInflater.from(context).inflate(R.layout.layout_banner, this, true);
        mLinearLayout = (LinearLayout) findViewById(R.id.linearlayout);
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        //mFlashViewListener必须实例化

    }

    public void setImageUris(List<String> imageuris) {
        if (imageuris.size() <= 0)// 如果得到的图片张数为0，则增加一张默认的图片
        {   //Uri uri = Uri.parse("");
            imageUris.add(DEFAULT_IMAGE);
        } else {
            for (int i = 0; i < imageuris.size(); i++) {
                imageUris.add(imageuris.get(i));
            }
        }

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(5, 0, 0, 0);
        for (int i = 0; i < imageUris.size(); i++) {
            SimpleDraweeView imageView = new SimpleDraweeView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);// X和Y方向都填满
            if (imageUris.get(i).equals(DEFAULT_IMAGE)) {
                imageView.setBackgroundResource(mDefaultID);
            }else {
                imageView.setImageURI(Uri.parse(imageUris.get(i)));
            }
            imageViewsList.add(imageView);
            ImageView viewDot = new ImageView(getContext());
            if (i == 0) {
                viewDot.setBackgroundResource(R.drawable.main_dot_white);
            } else {
                viewDot.setBackgroundResource(R.drawable.main_dot_light);
            }
            viewDot.setLayoutParams(lp);
            dotViewsList.add(viewDot);
            mLinearLayout.addView(viewDot);
        }
        mViewPager.setFocusable(true);
        mViewPager.setAdapter(new MyPagerAdapter());
        mViewPager.setOnPageChangeListener(new MyPageChangeListener());
//        setEffect(effect);
//        if (imageUris.size() <= 1) {
//
//        } else {
//            // 利用反射修改自动轮播的动画持续时间
//            try {
//                Field field = ViewPager.class.getDeclaredField("mScroller");
//                field.setAccessible(true);
//                FixedSpeedScroller scroller = new FixedSpeedScroller(
//                        mViewPager.getContext(), new AccelerateInterpolator());
//                field.set(mViewPager, scroller);
//                scroller.setmDuration(1000);
//                mViewPager.setCurrentItem(100 * imageViewsList.size());
//                mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE,
//                        ImageHandler.MSG_DELAY);
//            } catch (Exception e) {
//
//            }
//        }

    }

    /**
     * 切换轮播小点的显示
     *
     * @param selectItems
     */
    private void setImageBackground(int selectItems) {
        for (int i = 0; i < dotViewsList.size(); i++) {
            if (i == selectItems % dotViewsList.size()) {
                dotViewsList.get(i).setBackgroundResource(R.drawable.main_dot_white);
            } else {
                dotViewsList.get(i).setBackgroundResource(R.drawable.main_dot_light);
            }
        }
    }

    /**
     * 数据适配器
     */
    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public void destroyItem(View container, int position, Object object) {

        }

        @Override
        public Object instantiateItem(View container, int position) {
            position = position % imageViewsList.size();

            if (position < 0) {
                position = position + imageViewsList.size();

            }
            final int pos = position;
            View view = imageViewsList.get(position);
            view.setTag(position);
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    if (mFlashViewListener != null) {
                        mFlashViewListener.onClick(pos);
                    } else {

                    }

                }
            });
            ViewParent vp = view.getParent();
            if (vp != null) {
                ViewPager pager = (ViewPager) vp;
                pager.removeView(view);
            }
            ((ViewPager) container).addView(view);
            return view;
        }

        @Override
        public int getCount() {
            if (imageUris.size() <= 1) {
                return 1;
            } else {
                return Integer.MAX_VALUE;
            }

        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
    }

    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrollStateChanged(int arg0) {
            // TODO Auto-generated method stub

            switch (arg0) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    mhandler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    mhandler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE, ImageHandler.MSG_DELAY);
                    break;
                default:
                    break;
            }

        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onPageSelected(int pos) {
            // TODO Auto-generated method stub
            mhandler.sendMessage(Message.obtain(mhandler, ImageHandler.MSG_PAGE_CHANGED, pos, 0));
            setImageBackground(pos);

        }

    }

    @SuppressWarnings("unused")
    private void destoryBitmaps() {
        for (int i = 0; i < imageViewsList.size(); i++) {
            ImageView imageView = imageViewsList.get(i);
            Drawable drawable = imageView.getDrawable();
            if (drawable != null) {
                drawable.setCallback(null);
            }
        }
    }

//    public void setEffect(int selectEffect)
//    {
//        switch (selectEffect) {
//            case 0:
//                setPageTransformer(true,new AccordionTransformer());
//                break;
//            case 1:
//                setPageTransformer(true,new CubeTransformer());
//                break;
//            case 2:
//                setPageTransformer(true,new DefaultTransformer());
//                break;
//            case 3:
//                setPageTransformer(true,new DepthPageTransformer());
//                break;
//            case 4:
//                setPageTransformer(true,new InRightDownTransformer());
//                break;
//            case 5:
//                setPageTransformer(true,new InRightUpTransformer());
//                break;
//            case 6:
//                setPageTransformer(true,new RotateTransformer());
//                break;
//            case 7:setPageTransformer(true,new ZoomOutPageTransformer());
//
//                break;
//            default:
//                break;
//        }
//    }
//    /**
//     * 设置切换效果
//     * @param b
//     * @param rotateTransformer
//     */
//    public void setPageTransformer(boolean b, ViewPager.PageTransformer rotateTransformer)
//    {
//        // TODO Auto-generated method stub
//        mViewPager.setPageTransformer(b, rotateTransformer);
//    }

    /**
     * FixedSpeedScroller类的源码来源于网络，在此谢过贡献此代码的道友
     */
    public class FixedSpeedScroller extends Scroller {
        private int mDuration = 1500;

        public FixedSpeedScroller(Context context) {
            super(context);
        }

        public FixedSpeedScroller(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy, int duration) {

            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        @Override
        public void startScroll(int startX, int startY, int dx, int dy) {

            super.startScroll(startX, startY, dx, dy, mDuration);
        }

        public void setmDuration(int time) {
            mDuration = time;
        }

        public int getmDuration() {
            return mDuration;
        }
    }

    private static class ImageHandler extends Handler {

        protected static final int MSG_UPDATE_IMAGE = 1;

        protected static final int MSG_KEEP_SILENT = 2;

        protected static final int MSG_BREAK_SILENT = 3;

        protected static final int MSG_PAGE_CHANGED = 4;

        protected static final long MSG_DELAY = 2000;

        private WeakReference<CarouselView> weakReference;
        private int currentItem = 0;

        protected ImageHandler(WeakReference<CarouselView> wk) {
            weakReference = wk;
            System.out.println("dsfdsfdsf:::" + currentItem);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            CarouselView activity = weakReference.get();
            if (activity == null) {
                return;
            }
            if (activity.mhandler.hasMessages(MSG_UPDATE_IMAGE)) {
                if (currentItem > 0)// 这里必须加入currentItem>0的判断，否则不能完美的自动轮播
                {
                    activity.mhandler.removeMessages(MSG_UPDATE_IMAGE);
                }
            }
            switch (msg.what) {
                case MSG_UPDATE_IMAGE:
                    System.out.println("cccccc:::" + currentItem);
                    currentItem++;
                    activity.mViewPager.setCurrentItem(currentItem);
                    activity.mhandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_KEEP_SILENT:
                    break;
                case MSG_BREAK_SILENT:
                    activity.mhandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                    break;
                case MSG_PAGE_CHANGED:
                    currentItem = msg.arg1;
                    break;
                default:
                    break;
            }
        }
    }


    /**
     * 功能：向外提供点击事件的接口，其中position代表的是图片的索引，即第几张图片
     *
     * @author Android将军
     */
    public interface CarouselViewListener {
        public void onClick(int position);
    }


}