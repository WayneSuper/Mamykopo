package cn.wayne.mamypoko.base;

import android.view.View;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.widget.EmptyView;
import cn.wayne.mamypoko.widget.xlistview.XListView;

/**
 * Created by Lumia on 2015/4/4.
 */
public abstract class BaseXListViewFragment extends CommBaseFragment {
    private XListView listView;
    private EmptyView mEmptyView;
    @Override
    protected int setRootLayoutID() {
        return R.layout.layout_comm_xlistview;
    }

    @Override
    protected void initView(View view) {
        listView = (XListView) view.findViewById(android.R.id.list);
        mEmptyView = (EmptyView) view.findViewById(R.id.empty_view);
        mEmptyView.setErrorType(setEmptyType());
    }

    /**
     * 设置EmptyView的类型
     *  HIDE_LAYOUT
     *  NETWORK_ERROR
     *  NETWORK_LOADING
     *  NETWORK_LOADING_ANIM
     *  NODATA
     *  NODATA_NO_CLICK
     * @return
     */
    protected abstract int setEmptyType();

    @Override
    protected void initEvent(View view) {
        listView.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {

            }

            @Override
            public void onLoadMore() {

            }
        });
    }
}
