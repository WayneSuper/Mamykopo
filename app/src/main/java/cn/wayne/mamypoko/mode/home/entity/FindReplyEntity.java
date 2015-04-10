package cn.wayne.mamypoko.mode.home.entity;

import java.util.List;

import cn.wayne.mamypoko.base.entity.EntityList;

/**
 * Created by Pollux on 2015/4/9.
 * //
 */
public class FindReplyEntity implements EntityList {
    /**
     {
     "id": "147107558",
     "post_id": "7097801",
     "user_id": "4655590",
     "quote_user_id": 0,
     "content": "",
     "view_date": "2个月6天",
     "dated": 1428537785,
     "ip": "171.120.197.243",
     "state": "1",
     "o_id": "18",
     "site": "61",
     "to_site": "27",
     "step": "0",
     "nickname": "你是我的小宝贝",
     "city": "运城",
     "avatar": "http://www.qubaobei.com/files/head/150/20150331/22/14277728035118.jpg",
     "age_str": "2个月6天",
     "sex": "1",
     "show_date": "8小时前",
     "daren": [],
     "pic_big": "http://www.qubaobei.com/style/images/face/e18new.png",
     "width": 100,
     "height": 57,
     "pic": "http://www.qubaobei.com/style/images/face/e18new.png",
     "quote_sex": 1,
     "quote_step": "0",
     "host": 0
     }
     */
    private String id;
    private String post_id;
    private String user_id;
    private String quote_user_id;
    private String content;
    private String view_date;
    private String dated;
    private String ip;
    private String state;
    private String to_site;
    private String site;
    private String step;
    private String nickname;
    private String city;
    private String avatar;
    private String age_str;
    private String sex;
    private String show_date;
    private String pic_big;
    private int width;
    private int height;
    private String pic;
    private int quote_sex;
    private String quote_step;
    private int host;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getQuote_user_id() {
        return quote_user_id;
    }

    public void setQuote_user_id(String quote_user_id) {
        this.quote_user_id = quote_user_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getView_date() {
        return view_date;
    }

    public void setView_date(String view_date) {
        this.view_date = view_date;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTo_site() {
        return to_site;
    }

    public void setTo_site(String to_site) {
        this.to_site = to_site;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAge_str() {
        return age_str;
    }

    public void setAge_str(String age_str) {
        this.age_str = age_str;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getShow_date() {
        return show_date;
    }

    public void setShow_date(String show_date) {
        this.show_date = show_date;
    }

    public String getPic_big() {
        return pic_big;
    }

    public void setPic_big(String pic_big) {
        this.pic_big = pic_big;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public int getQuote_sex() {
        return quote_sex;
    }

    public void setQuote_sex(int quote_sex) {
        this.quote_sex = quote_sex;
    }

    public String getQuote_step() {
        return quote_step;
    }

    public void setQuote_step(String quote_step) {
        this.quote_step = quote_step;
    }

    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    @Override
    public List<?> getList() {
        return null;
    }


    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }
}
