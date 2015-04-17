package cn.wayne.mamypoko.mode.bbs;

import java.io.Serializable;
import java.util.List;

import cn.wayne.mamypoko.base.entity.Entity;
import cn.wayne.mamypoko.base.entity.EntityList;
import cn.wayne.mamypoko.mode.home.entity.Beauty;

/**
 * Created by Pollux on 2015/4/16.
 * //
 */
public class BbsModel extends Entity implements EntityList {


    private int ret;
    private int top_num;
    private List<Beauty.BeautyEntity> data;

    @Override
    public List<?> getList() {
        return getData();
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setTop_num(int top_num) {
        this.top_num = top_num;
    }

    public void setData(List<Beauty.BeautyEntity> data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public int getTop_num() {
        return top_num;
    }

    public List<Beauty.BeautyEntity> getData() {
        return data;
    }

//    public class BbsEntity  extends Beauty.BeautyEntity{
//        /**
//         * age_str : 孕27周
//         * id : 7225608
//         * title : 亲爱的，进来看看，发表一下你们的意见。
//         * re_num : 349
//         * nickname : 巨蟹baby
//         * dated : 1429098635
//         * post_type : 3
//         * showdated : 14小时前
//         * pic : 1
//         * avatar : http://www.qubaobei.com/files/head/150/20150203/14229732324192.jpg
//         * gold_added : 0
//         */
//        private String age_str;
//        private String id;
//        private String title;
//        private String re_num;
//        private String nickname;
//        private int dated;
//        private int post_type;
//        private String showdated;
//        private String pic;
//        private String avatar;
//        private int gold_added;
//
//        public void setAge_str(String age_str) {
//            this.age_str = age_str;
//        }
//
//        public void setId(String id) {
//            this.id = id;
//        }
//
//        public void setTitle(String title) {
//            this.title = title;
//        }
//
//        public void setRe_num(String re_num) {
//            this.re_num = re_num;
//        }
//
//        public void setNickname(String nickname) {
//            this.nickname = nickname;
//        }
//
//        public void setDated(int dated) {
//            this.dated = dated;
//        }
//
//        public void setPost_type(int post_type) {
//            this.post_type = post_type;
//        }
//
//        public void setShowdated(String showdated) {
//            this.showdated = showdated;
//        }
//
//        public void setPic(String pic) {
//            this.pic = pic;
//        }
//
//        public void setAvatar(String avatar) {
//            this.avatar = avatar;
//        }
//
//        public void setGold_added(int gold_added) {
//            this.gold_added = gold_added;
//        }
//
//        public String getAge_str() {
//            return age_str;
//        }
//
//        public String getId() {
//            return id;
//        }
//
//        public String getTitle() {
//            return title;
//        }
//
//        public String getRe_num() {
//            return re_num;
//        }
//
//        public String getNickname() {
//            return nickname;
//        }
//
//        public int getDated() {
//            return dated;
//        }
//
//        public int getPost_type() {
//            return post_type;
//        }
//
//        public String getShowdated() {
//            return showdated;
//        }
//
//        public String getPic() {
//            return pic;
//        }
//
//        public String getAvatar() {
//            return avatar;
//        }
//
//        public int getGold_added() {
//            return gold_added;
//        }
//    }
}
