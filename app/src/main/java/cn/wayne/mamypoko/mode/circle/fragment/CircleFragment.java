package cn.wayne.mamypoko.mode.circle.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.mode.circle.adapter.CircleAdapter;
import cn.wayne.mamypoko.mode.circle.entity.CircleModel;
import cn.wayne.mamypoko.ui.dragsortlistview.DragSortController;
import cn.wayne.mamypoko.ui.dragsortlistview.DragSortListView;


/**
 * A simple {@link Fragment} subclass.
 */
public class CircleFragment extends ListFragment{
    private DragSortListView mDragListView;
    private Context mContext;
    private CircleAdapter mAdapter;
    private DragSortListView.DropListener onDrop =
            new DragSortListView.DropListener() {
                @Override
                public void drop(int from, int to) {
                    if (from != to) {
                        //上下拖动时,手指释放瞬间调用
                        CircleModel item = mAdapter.getItem(from);
                        mAdapter.remove(item);
                        mAdapter.insert(item, to);
                    }
                }
            };
    private DragSortController mController;

    public CircleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mContext = getActivity();

        return inflater.inflate(R.layout.fragment_circle, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDragListView = (DragSortListView) getListView();
        setupDragListView();
        List<CircleModel> objects = new ArrayList();
        List<String> list = new ArrayList<String>();
        list.add("http://www.qipaox.com/tupian/200810/20081051924582.jpg");
        list.add("http://www.bz55.com/uploads1/allimg/120312/1_120312100435_8.jpg");
        list.add("http://img3.iqilu.com/data/attachment/forum/201308/21/192654ai88zf6zaa60zddo.jpg");
        list.add("http://img2.pconline.com.cn/pconline/0706/19/1038447_34.jpg");
        list.add("http://www.kole8.com/desktop/desk_file-11/2/2/2012/11/2012113013552959.jpg");
        list.add("http://www.237.cc/uploads/pcline/712_0_1680x1050.jpg");

        CircleModel model1 = new CircleModel(R.drawable.info_bg5,"亲子教育","我们一起来耍啊",list);
        CircleModel model2 = new CircleModel(R.drawable.info_bg5,"亲子教育","我们一起来耍啊",list);
        CircleModel model3 = new CircleModel(R.drawable.info_bg5,"亲子教育","我们一起来耍啊",list);
        CircleModel model4 = new CircleModel(R.drawable.info_bg5,"亲子教育","我们一起来耍啊",list);
        CircleModel model5 = new CircleModel(R.drawable.info_bg5,"亲子教育","我们一起来耍啊",list);
        CircleModel model6 = new CircleModel(R.drawable.info_bg5,"亲子教育","我们一起来耍啊",list);
        objects.add(model1);
        objects.add(model2);
        objects.add(model3);
        objects.add(model4);
        objects.add(model5);
        objects.add(model6);
        mAdapter = new CircleAdapter(mContext,0,objects);
       setListAdapter(mAdapter);

    }

    private void setupDragListView() {
        //打开拖动开关,如果不打开 右滑操作没反应
        mDragListView.setDragEnabled(true);
        //设置监听器
        mDragListView.setDropListener(onDrop);
        mController = buildController(mDragListView);
        //设置悬浮框管理器,点击监听器
        mDragListView.setFloatViewManager(mController);
        mDragListView.setOnTouchListener(mController);
    }

    public DragSortController buildController(DragSortListView dslv) {
        DragSortController controller = new DragSortController(dslv);
        //设置拖动对象 id映射View
        controller.setDragHandleId(R.id.drag_handle);
        //设置移除开关
        //controller.setRemoveEnabled(true);
        //设置拖动/移除 模式
        controller.setDragInitMode(DragSortController.ON_LONG_PRESS);
       // controller.setRemoveMode(DragSortController.FLING_REMOVE);
        return controller;
    }
}
