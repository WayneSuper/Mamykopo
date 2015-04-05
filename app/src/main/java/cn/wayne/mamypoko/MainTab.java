package cn.wayne.mamypoko;

import cn.wayne.mamypoko.modules.circle.fragment.CircleFragment;
import cn.wayne.mamypoko.modules.found.fragment.FoundFragment;
import cn.wayne.mamypoko.modules.home.fragment.HomeFragment;
import cn.wayne.mamypoko.modules.movement.fragment.MovementFragment;
import cn.wayne.mamypoko.modules.personal.fragment.PersonalFragment;

/**
 * Created by Lumia on 2015/3/29.
 */
public enum MainTab {
    HOME(0,R.string.home,R.drawable.tab1, HomeFragment.class),
    CIRCLE(1,R.string.circle,R.drawable.tab2, CircleFragment.class),
    FOUND(2,R.string.found,R.drawable.tab3, FoundFragment.class),
    MOVEMENT(3,R.string.movement,R.drawable.tab4, MovementFragment.class),
    MY(4,R.string.personal,R.drawable.tab5, PersonalFragment.class);


    private int index;
    private int tabName;
    private int resIcon;
    private Class clzz;

    MainTab(int index, int tabName, int resIcon,Class<?> clzz) {
        this.index = index;
        this.tabName = tabName;
        this.resIcon = resIcon;
        this.clzz = clzz;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getTabName() {
        return tabName;
    }

    public void setTabName(int tabName) {
        this.tabName = tabName;
    }

    public int getResIcon() {
        return resIcon;
    }

    public void setResIcon(int resIcon) {
        this.resIcon = resIcon;
    }

    public Class getClzz() {
        return clzz;
    }

    public void setClzz(Class clzz) {
        this.clzz = clzz;
    }
}
