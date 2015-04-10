package cn.wayne.mamypoko.base;

import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.Window;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cn.wayne.mamypoko.R;

/**
 * Created by Pollux on 2015/4/8.
 * //
 */
public abstract class BaseChildActivity extends CommBaseActivity {
    private Toolbar mToolBar;

    @Override
    protected void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        // toolbar.setLogo(R.drawable.ic_launcher);
        mToolBar.setTitle(setPageTitle());
        setSupportActionBar(mToolBar);
        // Navigation Icon 要.設定在 setSupoortActionBar 才有作用
        // 否則會出現 back bottom
        mToolBar.setNavigationIcon(R.drawable.ic_back);
        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseChildActivity.this.finish();
            }
        });
        setOverflowButtonAlwaysShow();
        supportInvalidateOptionsMenu();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_child_content, menu);

        if(isMoreShow()) {
            menu.findItem(R.id.menu_more_share).setVisible(true);
            menu.findItem(R.id.menu_more_collect).setVisible(true);
            menu.findItem(R.id.menu_more_report).setVisible(true);
        }else {
            menu.findItem(R.id.menu_more_share).setVisible(false);
            menu.findItem(R.id.menu_more_collect).setVisible(false);
            menu.findItem(R.id.menu_more_report).setVisible(false);
        }

        if(isRightShow()) {
            menu.findItem(R.id.menu_right_btn).setVisible(true);
            menu.findItem(R.id.menu_right_btn).setIcon(setRightBtnIcon());
        }else {
            menu.findItem(R.id.menu_right_btn).setVisible(false);
        }

        return true;
    }

    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if(featureId == Window.FEATURE_ACTION_BAR && menu != null) {
           if(menu.getClass().getSimpleName().equals("MenuBuilder")) {
               try {
                   Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible",Boolean.TYPE);
                    m.setAccessible(true);
                    m.invoke(menu,true);
               } catch (NoSuchMethodException e) {
                   e.printStackTrace();
               } catch (InvocationTargetException e) {
                   e.printStackTrace();
               } catch (IllegalAccessException e) {
                   e.printStackTrace();
               }
           }
        }
        return super.onMenuOpened(featureId, menu);
    }

    private void setOverflowButtonAlwaysShow () {

        try {
            ViewConfiguration cfg = ViewConfiguration.get(this);
            Field mMenuKey = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            mMenuKey.setAccessible(true);
            mMenuKey.setBoolean(cfg,false);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    protected boolean isRightShow() {
        return true;
    }

    protected boolean isMoreShow() {
        return true;
    }

    protected abstract int setRightBtnIcon();

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_right_btn:
              onRightButtonClick();
                break;
            case R.id.menu_more_share:
                onShareButtonClick();
                break;
            case R.id.menu_more_collect:
                onCollectButtonClick();
                break;
            case R.id.menu_more_report:
              onReportButtonClick();
                break;
        }
        return true;
    }

    protected void onReportButtonClick() {}

    protected void onCollectButtonClick() {}

    protected void onShareButtonClick() {}

    protected void onRightButtonClick(){}

    protected abstract String setPageTitle();


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_MENU) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
