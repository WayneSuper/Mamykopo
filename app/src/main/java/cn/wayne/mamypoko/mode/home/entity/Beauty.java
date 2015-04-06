package cn.wayne.mamypoko.mode.home.entity;

import java.util.List;

/**
 * Created by Lumia on 2015/4/6.
 */
public class Beauty {


    /**
     * ret : 1
     * data : [{"gold_added":0,"age_str":"孕33周","pic":"7","avatar":"http://www.qubaobei.com/files/head/150/20140922/3/2095652233022.jpg","title":"一个唇印，两种结果","url":"http://filebaby.qubaobei.com/uploads/qq/640p/20150403/1/180139640217.jpg","showdated":"4小时前","user_id":"3464621","nickname":"故事情感","dated":1428306302,"post_type":3,"from":"故事情感","id":"7044304","re_num":"253"},{"gold_added":0,"age_str":"备孕中","pic":"15","avatar":"http://www.qubaobei.com/files/head/150/20141023/36/1545842233022.jpg","title":"13种方法，让旧T恤起死回生","url":"http://filebaby.qubaobei.com/uploads/qq/640p/20150403/21/160927612683.jpg","showdated":"5小时前","user_id":"3464604","nickname":"创意手工","dated":1428304801,"post_type":3,"from":"创意手工","id":"7044066","re_num":"312"},{"gold_added":0,"age_str":"4岁2个月","pic":"25","avatar":"http://www.qubaobei.com/files/head/150/20140922/9/3199412233022.jpg","title":"给照片赋予电影般的魔力","url":"http://filebaby.qubaobei.com/uploads/qq/640p/20150403/43/150413993481.jpg","showdated":"8小时前","user_id":"3464628","nickname":"旅游摄影","dated":1428291001,"post_type":3,"from":"旅游摄影","id":"7027774","re_num":"131"},{"gold_added":0,"age_str":"1岁3个月","pic":"2","avatar":"http://www.qubaobei.com/files/head/150/20141021/22/6561802233022.jpg","title":"你的回答将影响孩子的一生","url":"http://filebaby.qubaobei.com/uploads/qq/640p/20150403/31/151333867495.jpg","showdated":"13小时前","user_id":"3688052","nickname":"宝宝养育","dated":1428274801,"post_type":3,"from":"宝宝养育","id":"7042941","re_num":"489"},{"gold_added":0,"age_str":"5个月16天","pic":"15","avatar":"http://www.qubaobei.com/files/head/150/20140922/23/4163462233022.jpg","title":"土豪家的狗，都必须是金牙","url":"http://filebaby.qubaobei.com/uploads/qq/640p/20150402/7/110752638641.jpg","showdated":"13小时前","user_id":"3464598","nickname":"娱乐笑话","dated":1428274501,"post_type":3,"from":"娱乐笑话","id":"7027607","re_num":"774"},{"gold_added":0,"age_str":"1岁3个月","pic":"4","avatar":"http://www.qubaobei.com/files/head/150/20141021/22/6561802233022.jpg","title":"【转】想让孩子开朗、独立、懂爱吗？","url":"http://filebaby.qubaobei.com/uploads/qq/640p/20150403/28/151216370989.jpg","showdated":"13小时前","user_id":"3688052","nickname":"宝宝养育","dated":1428273301,"post_type":3,"from":"宝宝养育","id":"7042963","re_num":"416"}]
     */
    private int ret;
    private List<DataEntity> data;

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public class DataEntity {
        /**
         * gold_added : 0
         * age_str : 孕33周
         * pic : 7
         * avatar : http://www.qubaobei.com/files/head/150/20140922/3/2095652233022.jpg
         * title : 一个唇印，两种结果
         * url : http://filebaby.qubaobei.com/uploads/qq/640p/20150403/1/180139640217.jpg
         * showdated : 4小时前
         * user_id : 3464621
         * nickname : 故事情感
         * dated : 1428306302
         * post_type : 3
         * from : 故事情感
         * id : 7044304
         * re_num : 253
         */
        private int gold_added;
        private String age_str;
        private String pic;
        private String avatar;
        private String title;
        private String url;
        private String showdated;
        private String user_id;
        private String nickname;
        private int dated;
        private int post_type;
        private String from;
        private String id;
        private String re_num;

        public void setGold_added(int gold_added) {
            this.gold_added = gold_added;
        }

        public void setAge_str(String age_str) {
            this.age_str = age_str;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setShowdated(String showdated) {
            this.showdated = showdated;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setDated(int dated) {
            this.dated = dated;
        }

        public void setPost_type(int post_type) {
            this.post_type = post_type;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setRe_num(String re_num) {
            this.re_num = re_num;
        }

        public int getGold_added() {
            return gold_added;
        }

        public String getAge_str() {
            return age_str;
        }

        public String getPic() {
            return pic;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getTitle() {
            return title;
        }

        public String getUrl() {
            return url;
        }

        public String getShowdated() {
            return showdated;
        }

        public String getUser_id() {
            return user_id;
        }

        public String getNickname() {
            return nickname;
        }

        public int getDated() {
            return dated;
        }

        public int getPost_type() {
            return post_type;
        }

        public String getFrom() {
            return from;
        }

        public String getId() {
            return id;
        }

        public String getRe_num() {
            return re_num;
        }
    }
}
