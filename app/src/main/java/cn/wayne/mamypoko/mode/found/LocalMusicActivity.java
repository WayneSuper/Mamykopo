package cn.wayne.mamypoko.mode.found;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.CommBaseActivity;
import cn.wayne.mamypoko.mode.found.fragment.AlbumFragment;
import cn.wayne.mamypoko.mode.found.fragment.FolderFragment;
import cn.wayne.mamypoko.mode.found.fragment.SingerFragment;
import cn.wayne.mamypoko.mode.found.fragment.SingleMusicFragment;
import cn.wayne.mamypoko.mode.found.model.Audio;
import cn.wayne.mamypoko.mode.found.model.MediaUtils;
import cn.wayne.mamypoko.service.CoreService;
import cn.wayne.mamypoko.service.ICoreService;
import cn.wayne.mamypoko.utils.CharacterParser;
import cn.wayne.mamypoko.utils.DensityUtil;
import cn.wayne.mamypoko.utils.Logger;
import cn.wayne.mamypoko.utils.PinyinComparator;

public class LocalMusicActivity extends CommBaseActivity implements ViewPager.OnPageChangeListener,View.OnClickListener , CoreService.OnSeekChangeListener {
    private Toolbar mToolbar;
    private List<Fragment> mFragmengtContent = new ArrayList<>();
    private List<TextView> mTextsContent = new ArrayList<>();
    private TextView mToolbarTitle;
    private TextView mTextTip1;
    private TextView mTextTip2;
    private TextView mTextTip3;
    private TextView mTextTip4;
    private View mViewLine;
    private ViewPager mViewPager;
    private int mScreenW;
    private int mSingleTipW;
    private FragmentPagerAdapter mAdapter;
    private List<Audio> mAudioList;
    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;
    /**
     * 根据拼音来排列ListView里面的数据类
     */
    private PinyinComparator pinyinComparator;
    private ArrayList<Audio> mSortAudioList;
    private ImageButton btnPlay;
    private ImageButton btnPre;
    private ImageButton btnNext;
    private ProgressBar mProgressbar;


    @Override
    protected int setContentViewID() {
        return R.layout.activity_local_music;
    }

    @Override
    protected void initEvent() {
        mViewPager.setOnPageChangeListener(this);
        mTextTip1.setOnClickListener(this);
        mTextTip2.setOnClickListener(this);
        mTextTip3.setOnClickListener(this);
        mTextTip4.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPre.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        characterParser = CharacterParser.getInstance();
        mAudioList = MediaUtils.getAudioList(this);
        sortAudioList();
        this.bindService(new Intent(this, CoreService.class), mServiceConnection, Service.BIND_AUTO_CREATE);
        this.startService(new Intent(this,CoreService.class));

    }

    private CoreService mCoreService;
    private ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mCoreService = ((CoreService.MBind) service).getService();
            mCoreService.setAudioList(mSortAudioList);
            mCoreService.setSeekChangeListener(LocalMusicActivity.this);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mCoreService = null;
        }
    };

    private void sortAudioList() {
        mSortAudioList = new ArrayList<>();
        for (Audio audio : mAudioList) {
            //汉字转换成拼音
            String pinyin = characterParser.getSelling(audio.getTitle());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if(sortString.matches("[A-Z]")){
                audio.setSortLetters(sortString.toUpperCase());
            }else{
                audio.setSortLetters("#");
            }

            mSortAudioList.add(audio);
        }
    }

    public List<Audio> getSortedList() {
        return mSortAudioList;
    }

    @Override
    protected void initView() {
        mToolbar = getView(R.id.toolbar);
        mToolbarTitle = getView(R.id.toolbar_title);
        mTextTip1 = getView(R.id.text_music_type1);
        mTextTip2 = getView(R.id.text_music_type2);
        mTextTip3 = getView(R.id.text_music_type3);
        mTextTip4 = getView(R.id.text_music_type4);

        btnPlay = getView(R.id.btn_play);
        btnPre = getView(R.id.btn_previous);
        btnNext = getView(R.id.btn_next);
        mProgressbar = getView(R.id.progressBar);

        mTextsContent.add(mTextTip1);
        mTextsContent.add(mTextTip2);
        mTextsContent.add(mTextTip3);
        mTextsContent.add(mTextTip4);
        mViewLine = getView(R.id.view_muisc_line);
        mViewPager = getView(R.id.viewPager);
        mToolbarTitle.setText(getString(R.string.local_music));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mToolbar.setNavigationIcon(R.drawable.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        setUpTypeLine();
        setUpViewPager();

    }

    private void setUpViewPager() {
        SingleMusicFragment mSingleFragment = new SingleMusicFragment();
        SingerFragment mSingerFragment = new SingerFragment();
        AlbumFragment mAlbumFragment = new AlbumFragment();
        FolderFragment mFolderFragment = new FolderFragment();
        mFragmengtContent.add(mSingleFragment);
        mFragmengtContent.add(mSingerFragment);
        mFragmengtContent.add(mAlbumFragment);
        mFragmengtContent.add(mFolderFragment);
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mFragmengtContent.size();
            }

            @Override
            public Fragment getItem(int position) {
                return mFragmengtContent.get(position);
            }
        };
        mViewPager.setAdapter(mAdapter);
    }

    private void setUpTypeLine() {
        mScreenW = DensityUtil.getScreenW(this);
        mSingleTipW = mScreenW / 4;
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mViewLine.getLayoutParams();
        lp.height = DensityUtil.dip2px(this, 3);
        lp.width = mSingleTipW;
        mViewLine.setLayoutParams(lp);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        int translateX = (int) (position * mSingleTipW + mSingleTipW * positionOffset);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mViewLine.getLayoutParams();
        lp.leftMargin = translateX;
        mViewLine.setLayoutParams(lp);
        if(mSlideBarStateChangeListener!=null) {
            mSlideBarStateChangeListener.onChange(position,positionOffset);
        }
    }

    @Override
    public void onPageSelected(int position) {
        setSelectedText(position);
    }

    private void setSelectedText(int position) {
        for (int i = 0; i < mTextsContent.size(); i++) {
            TextView tv = mTextsContent.get(i);
            if (position == i) {
                tv.setTextColor(Color.parseColor("#ffff3b71"));
            } else {
                tv.setTextColor(Color.parseColor("#3c3c3c"));
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_music_type1:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.text_music_type2:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.text_music_type3:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.text_music_type4:
                mViewPager.setCurrentItem(3);
                break;
            case R.id.btn_play:
                if(mCoreService.isPlaying()) {
                    btnPlay.setSelected(true);
                    mCoreService.pause();
                }else {
                    btnPlay.setSelected(false);
                    mCoreService.play();
                }
                break;
            case R.id.btn_next:
                mCoreService.next();
                break;
            case R.id.btn_previous:
                mCoreService.previous();
                break;
        }
    }
    private OnSlideBarStateChangeListener mSlideBarStateChangeListener;

    public void setSlideBarStateChangeListener(OnSlideBarStateChangeListener mSlideBarStateChangeListener) {
        this.mSlideBarStateChangeListener = mSlideBarStateChangeListener;
    }

    public List<Audio> getAudioList() {
        return mAudioList;
    }

    @Override
    public void onChanged(int total,int seek) {
        mProgressbar.setMax(total);
        mProgressbar.setProgress(seek);
    }

    public ICoreService getService() {
        return mCoreService;
    }

    public interface OnSlideBarStateChangeListener {
        void onChange(int position ,float offset);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mServiceConnection);
    }
}
