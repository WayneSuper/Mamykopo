package cn.wayne.mamypoko.mode.home.entity;

import java.util.List;

/**
 * Created by Pollux on 2015/4/9.
 * //
 */
public class FindContent {
    /**
     * age_str : 备孕中
     * o_user_id : 3469252
     * b_id : 9992
     * title : 16种排骨的做法，不停的换味！
     * re_num : 471
     * level : 1
     * nickname : 美食天下
     * user_id : 3469252
     * avatar : http://www.qubaobei.com/files/head/150/20140924/26/1891992233022.jpg
     * is_lock : 1
     * "has_more": 1,
     "msg_reply": "0"
     */
    private String age_str;
    private String o_user_id;
    private String b_id;
    private String title;
    private String re_num;
    private int level;
    private String nickname;
    private String user_id;
    private String avatar;
    private int is_lock;
    private List<FindContentEntity> contents;
    private List<FindReplyEntity> replys;
    private int has_more;
    private String msg_reply;
    private String city;



    public void setAge_str(String age_str) {
        this.age_str = age_str;
    }

    public void setO_user_id(String o_user_id) {
        this.o_user_id = o_user_id;
    }

    public void setB_id(String b_id) {
        this.b_id = b_id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRe_num(String re_num) {
        this.re_num = re_num;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setIs_lock(int is_lock) {
        this.is_lock = is_lock;
    }

    public String getAge_str() {
        return age_str;
    }

    public String getO_user_id() {
        return o_user_id;
    }

    public String getB_id() {
        return b_id;
    }

    public String getTitle() {
        return title;
    }

    public String getRe_num() {
        return re_num;
    }

    public int getLevel() {
        return level;
    }

    public String getNickname() {
        return nickname;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getIs_lock() {
        return is_lock;
    }

    public int getHas_more() {
        return has_more;
    }

    public void setHas_more(int has_more) {
        this.has_more = has_more;
    }

    public String getMsg_reply() {
        return msg_reply;
    }

    public void setMsg_reply(String msg_reply) {
        this.msg_reply = msg_reply;
    }

    public List<FindContentEntity> getContents() {
        return contents;
    }

    public void setContents(List<FindContentEntity> contents) {
        this.contents = contents;
    }

    public List<FindReplyEntity> getReplys() {
        return replys;
    }

    public void setReplys(List<FindReplyEntity> replys) {
        this.replys = replys;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
