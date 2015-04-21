package cn.wayne.mamypoko;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import cn.bmob.v3.Bmob;
import cn.wayne.mamypoko.ui.FragmentTabHost;


public class MainActivity extends ActionBarActivity implements TabHost.OnTabChangeListener {
    private Toolbar mToolbar;
    private FragmentTabHost mTabhost;
    private Menu mMenu;
    private TextView mToolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bmob.initialize(this, "2db46aef650e34ddf6a276e5425ffe83");
        setToolbarUp();
        setTabHostUp();

    }

    private void setTabHostUp() {
        mTabhost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabhost.setup(this, getSupportFragmentManager(), R.id.real_content);
        if (Build.VERSION.SDK_INT > 10) {
            mTabhost.getTabWidget().setShowDividers(0);
        }
        initTabs();
        mTabhost.setCurrentTab(0);
        mTabhost.setOnTabChangedListener(this);
    }

    /**
     * 初始化toolbar
     */
    private void setToolbarUp() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void initTabs() {
        MainTab[] mainTabs = MainTab.values();
        for (MainTab mTab : mainTabs) {
            TabHost.TabSpec mSpec = mTabhost.newTabSpec(getString(mTab.getTabName()));
            View indicator = LayoutInflater.from(this).inflate(R.layout.main_tab, null);
            ImageView imageView = (ImageView) indicator.findViewById(R.id.main_tab_image);
            TextView textView = (TextView) indicator.findViewById(R.id.main_tab_text);
            imageView.setImageResource(mTab.getResIcon());
            textView.setText(mTab.getTabName());
            mSpec.setIndicator(indicator);
            mSpec.setContent(new TabHost.TabContentFactory() {
                public View createTabContent(String tag) {
                    return new View(MainActivity.this);
                }
            });

            mTabhost.addTab(mSpec, mTab.getClzz(), null);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        switch (mTabhost.getCurrentTab()) {
            case 0:
                showMenuItem(menu, 0, R.string.app_name, R.id.action_main_mail);
                break;
            case 1:
                showMenuItem(menu, 1, R.string.circle,0);
                break;
            case 2:
                showMenuItem(menu, 2, R.string.found, 0);
                break;
            case 3:
                showMenuItem(menu, 3, R.string.movement, R.id.action_main_mygift);
                break;
            case 4:
                showMenuItem(menu, 4, R.string.personal, R.id.action_main_settings);
                break;
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_main_mail:  //消息通知
                break;
            case R.id.action_main_mygift:  //礼物
                break;
            case R.id.action_main_settings:  //设置
                break;
        }
        return true;
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabhost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View view = mTabhost.getTabWidget().getChildAt(i);
            if (i == mTabhost.getCurrentTab()) {
                view.findViewById(R.id.main_tab_image).setSelected(true);
                view.findViewById(R.id.main_tab_text).setSelected(true);
            } else {
                view.findViewById(R.id.main_tab_image).setSelected(false);
                view.findViewById(R.id.main_tab_text).setSelected(false);
            }
        }
        supportInvalidateOptionsMenu();
    }

    private void showMenuItem(Menu menu, int index, int titleNameId, int menuId) {
        mToolbarTitle.setText(titleNameId);
        if (menuId == 0) {
            return;
        }
        menu.findItem(menuId).setVisible(true);
        menu.findItem(menuId).setEnabled(true);
    }

}
