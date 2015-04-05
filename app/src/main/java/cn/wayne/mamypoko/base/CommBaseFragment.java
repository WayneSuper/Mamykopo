package cn.wayne.mamypoko.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class CommBaseFragment extends Fragment {

    private Context mContext;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();
        return inflater.inflate(setRootLayoutID(), container, false);
    }

    /**
     * 设置Fragment的rootViewID
     *
     * @return
     */
    protected abstract int setRootLayoutID();

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData(view);
        initEvent(view);
    }

    /**
     * 获取视图
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 设置数据
     *
     * @param view
     */
    protected abstract void initData(View view);

    /**
     * 设置事件
     *
     * @param view
     */
    protected abstract void initEvent(View view);
}
