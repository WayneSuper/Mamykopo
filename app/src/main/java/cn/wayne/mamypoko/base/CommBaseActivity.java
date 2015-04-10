package cn.wayne.mamypoko.base;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;

import cn.wayne.mamypoko.utils.Logger;

/**
 * Created by Pollux on 2015/4/8.
 * //
 */
public abstract class CommBaseActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewID());
        initView();
        initData();
        initEvent();
    }

    protected abstract int setContentViewID();

    protected abstract void initEvent();

    protected abstract void initData();

    protected abstract void initView();


    public final <E extends View> E getView (int id) {
        try {
            return (E) findViewById(id);
        } catch (ClassCastException ex) {
            Logger.debug("Could not cast View to concrete class."+ex);
            throw ex;
        }
    }
}
