package cn.wayne.mamypoko.mode.home.entity;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Lumia on 2015/3/30.
 */
public class FindModel extends BmobObject {

    private String title;
    private String articleType;
    private BmobFile displayImage;
    private int pinglunNum;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArticleType() {
        return articleType;
    }

    public void setArticleType(String articleType) {
        this.articleType = articleType;
    }

    public BmobFile getDisplayImage() {
        return displayImage;
    }

    public void setDisplayImage(BmobFile displayImage) {
        this.displayImage = displayImage;
    }

    public int getPinglunNum() {
        return pinglunNum;
    }

    public void setPinglunNum(int pinglunNum) {
        this.pinglunNum = pinglunNum;
    }
}
