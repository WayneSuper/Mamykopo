package cn.wayne.mamypoko.mode.found.fragment;


import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import cn.wayne.mamypoko.MainActivity;
import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.found.LocalMusicActivity;
import cn.wayne.mamypoko.mode.found.adapter.SingleAdapter;
import cn.wayne.mamypoko.mode.found.model.Audio;
import cn.wayne.mamypoko.ui.view.SlideBar;
import cn.wayne.mamypoko.utils.CharacterParser;
import cn.wayne.mamypoko.utils.PinyinComparator;

/**
 * A simple {@link Fragment} subclass.
 */
public class SingleMusicFragment extends Fragment implements LocalMusicActivity.OnSlideBarStateChangeListener{

    private SlideBar mSlidebar;
    private List<Audio> mAudioList;
    private SingleAdapter mAdapter;
    private ListView mListView;
    private PinyinComparator pinyinComparator;
    private List<Audio> mSortedList;
    private TextView mTextDialog;

    @Override
    public void onAttach(Activity activity) {
        ((LocalMusicActivity) activity).setSlideBarStateChangeListener(this);
        super.onAttach(activity);
    }

    public SingleMusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_single_music, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mSlidebar = (SlideBar) view.findViewById(R.id.slidebar);
        mListView = (ListView) view.findViewById(android.R.id.list);
        mTextDialog = (TextView) view.findViewById(R.id.text_tip);
        mSlidebar.setTextView(mTextDialog);
        initData();
        initEvent();
    }

    private void initEvent() {
        mSlidebar.setOnTouchLetterChangeListenner(new SlideBar.OnTouchLetterChangeListenner() {
            @Override
            public void onTouchLetterChange(MotionEvent event, String s) {
                //该字母首次出现的位置
                int position = mAdapter.getPositionForSection(s.charAt(0));
                if(position != -1){
                    mListView.setSelection(position);
                }
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ((LocalMusicActivity)getActivity()).getService().setAudio(mSortedList.get(position));
            }
        });
    }

    private void initData() {
        mSortedList = ((LocalMusicActivity)getActivity()).getSortedList();
        //实例化汉字转拼音类
        pinyinComparator = new PinyinComparator();
        // 根据a-z进行排序源数据
        Collections.sort(mSortedList, pinyinComparator);
        mAdapter = new SingleAdapter(getActivity(),mSortedList);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onChange(int position, float offset) {
        if(position == 1) {
            mSlidebar.setAlpha(offset);
        }else if(position == 0) {
            mSlidebar.setAlpha(1 - offset);
        }
    }
}
