package cn.wayne.mamypoko.mode.diary;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import cn.wayne.mamypoko.R;
import cn.wayne.mamypoko.base.BaseChildActivity;

public class DiaryListActivity extends BaseChildActivity {


    @Override
    protected void initEvent() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void onRightButtonClick() {

    }

    @Override
    protected int setRightBtnIcon() {
        return R.drawable.ic_actionbar_edit;
    }

    @Override
    protected String setPageTitle() {
        return getString(R.string.mydiary);
    }

    @Override
    protected int setContentViewID() {
        return R.layout.activity_diary_list;
    }

    @Override
    protected boolean isRightShow() {
        return true;
    }

    @Override
    protected boolean isMoreShow() {
        return false;
    }
}
