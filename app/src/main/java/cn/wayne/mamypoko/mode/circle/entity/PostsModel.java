package cn.wayne.mamypoko.mode.circle.entity;

import java.util.List;

import cn.wayne.mamypoko.base.entity.Entity;
import cn.wayne.mamypoko.base.entity.EntityList;
import cn.wayne.mamypoko.mode.home.entity.Beauty;

/**
 * Created by Pollux on 2015/4/16.
 * //
 */
public class PostsModel extends Entity implements EntityList {
    /**
     * ret : 1
     * top_num : 2
     * data : [{"age_str":"备孕中","id":"7012617","title":"【名单】争当社区新星，开启你的指尖育儿之旅！","re_num":"904","nickname":"小幕","dated":1427853713,"post_type":1,"showdated":"4月1日","pic":"2","avatar":"http://www.qubaobei.com/files/head/150/20140506/26/649843.jpg","gold_added":0},{"age_str":"3个月11天","id":"7224662","title":"手机拍花与人，做花间最惊艳的姑娘","re_num":"420","nickname":"美汐","dated":1429086317,"post_type":1,"showdated":"昨天 16:25","pic":"10","avatar":"http://www.qubaobei.com/files/head/150/20141012/3/8842663272018.jpg","gold_added":0},{"age_str":"6个月8天","id":"7236081","title":"有多少宝妈自己带2个小孩？都是多大？","re_num":"115","nickname":"快乐之旅","dated":1429164637,"post_type":2,"showdated":"3小时前","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20141210/7/1418220760995.jpg","gold_added":0},{"age_str":"1个月25天","id":"7235824","title":"两个月宝宝吃手","re_num":"32","nickname":"阳阳妈咪","dated":1429163639,"post_type":2,"showdated":"3小时前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150411/29/14287404165732.jpg","gold_added":0},{"age_str":"4个月20天","id":"7238069","title":"四个多月的宝宝可以坐这种车了没","re_num":"30","nickname":"ァ那一抹傷痕灬","dated":1429172832,"post_type":2,"showdated":"1小时前","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20141218/17/14188390401548.jpg","gold_added":0},{"age_str":"6个月16天","id":"7237130","title":"你们宝宝的\u201c人参\u201d长在哪","re_num":"27","nickname":"泽少骐少","dated":1429168751,"post_type":2,"showdated":"2小时前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20140131/13911257365106.jpg","gold_added":0},{"age_str":"6个月18天","id":"7239107","title":"亲们！我来晒图啦！diy发夹*^_^*不是广告哦～","re_num":"0","nickname":"朵爱","dated":1429177500,"post_type":3,"showdated":"刚刚","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20150106/25/7419554061770.jpg","gold_added":0},{"age_str":"8个月21天","id":"7239106","title":"准备留长头发","re_num":"0","nickname":"庆庆的麻麻","dated":1429177498,"post_type":3,"showdated":"刚刚","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20140624/16/1890002444139.jpg","gold_added":0},{"age_str":"1岁","id":"7239098","title":"宝妈指点美食","re_num":"0","nickname":"睿乐哈哈","dated":1429177475,"post_type":3,"showdated":"1分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150414/18/6159584485460.jpg","gold_added":0},{"age_str":"6个月29天","id":"7239097","title":"宝宝总拉是脾不好么","re_num":"0","nickname":"皮小妞。","dated":1429177473,"post_type":3,"showdated":"1分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150219/17/6475294054280.jpg","gold_added":0},{"age_str":"1个月28天","id":"7239079","title":"有事一天不喂奶咋办？","re_num":"0","nickname":"蓝雨音/tp","dated":1429177378,"post_type":3,"showdated":"2分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150318/39/8167274223754.jpg","gold_added":0},{"age_str":"3个月30天","id":"7239074","title":"宝宝有湿疹的，大家在吃什么奶粉","re_num":"3","nickname":"辣妈618","dated":1429177351,"post_type":3,"showdated":"3分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150314/36/14263266125499.jpg","gold_added":0},{"age_str":"3个月4天","id":"7239072","title":"好乖的宝贝","re_num":"1","nickname":"玉樱潜梦","dated":1429177333,"post_type":3,"showdated":"3分钟前","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20150408/37/919933.jpg","gold_added":0},{"age_str":"孕38周","id":"7239070","title":"有没有宝妈下载了我是妈咪？？","re_num":"0","nickname":"我的美、衬托你的帅","dated":1429177331,"post_type":3,"showdated":"3分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150325/2/121656.jpg","gold_added":0},{"age_str":"2岁3个月","id":"7239066","title":"宝妈们帮帮忙","re_num":"1","nickname":"婷baby","dated":1429177321,"post_type":3,"showdated":"3分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20140824/37/9580662802090.jpg","gold_added":0},{"age_str":"9个月5天","id":"7239059","title":"急急急，问大家一个问题","re_num":"0","nickname":"航航麻麻","dated":1429177301,"post_type":3,"showdated":"4分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150403/40/14280406107143.jpg","gold_added":0},{"age_str":"备孕中","id":"7239054","title":"，，有想，，做，，，衣，，，服，，的嘛，，，","re_num":"4","nickname":"辣妈919422","dated":1429177267,"post_type":3,"showdated":"4分钟前","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20140703/14043716899130.jpg","gold_added":0},{"age_str":"1岁9个月","id":"7239050","title":"宝宝喉咙里起了个疱疹","re_num":"0","nickname":"大脸","dated":1429177259,"post_type":3,"showdated":"4分钟前","pic":"2","avatar":"http://www.qubaobei.com/files/head/150/20140705/13/207086224798.jpg","gold_added":0},{"age_str":"5个月7天","id":"7239019","title":"五个月宝宝反复拉肚子怎么办啊，希望有经验的宝妈支招","re_num":"0","nickname":"海绵宝宝","dated":1429177252,"post_type":3,"showdated":"4分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20131126/38/4769411257502.jpg","gold_added":0},{"age_str":"9天","id":"7239046","title":"请问下喂奶的问题","re_num":"0","nickname":"趁年轻、挥霍小青春","dated":1429177249,"post_type":3,"showdated":"4分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20141222/14192100906344.jpg","gold_added":0},{"age_str":"7个月25天","id":"7239045","title":"宝贝快八个月了 还没出牙","re_num":"1","nickname":"慧慧","dated":1429177245,"post_type":3,"showdated":"5分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20140729/30/14066347987745.jpg","gold_added":0},{"age_str":"9个月6天","id":"7239038","title":"知道这是什么花吗","re_num":"1","nickname":"星星点点","dated":1429177198,"post_type":3,"showdated":"5分钟前","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20141007/14126731245077.jpg","gold_added":0},{"age_str":"6个月8天","id":"7239025","title":"求解。。。。。","re_num":"0","nickname":"小苹果","dated":1429177138,"post_type":3,"showdated":"6分钟前","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20150401/6/14278568084293.jpg","gold_added":0},{"age_str":"2个月12天","id":"7239021","title":"关于宝宝耳膜穿孔\u2026\u2026有宝妈知道的吗？","re_num":"0","nickname":"小啊萌","dated":1429177129,"post_type":3,"showdated":"6分钟前","pic":"3","avatar":"http://www.qubaobei.com/files/head/150/20150131/12/14226952493523.jpg","gold_added":0},{"age_str":"3个月23天","id":"7239009","title":"请问有多少宝妈给宝宝吃的雅培奶粉","re_num":"0","nickname":"宇皓妈咪","dated":1429177094,"post_type":3,"showdated":"7分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150416/39/14291771423889.jpg","gold_added":0},{"age_str":"孕8周6天","id":"7239003","title":"宝宝湿疹怎样避免","re_num":"0","nickname":"谷蕊晨","dated":1429177057,"post_type":3,"showdated":"8分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150407/22/142841335432.jpg","gold_added":0},{"age_str":"4个月28天","id":"7238989","title":"宝宝咳嗽一直不好","re_num":"0","nickname":"◇已つ深爱","dated":1429176992,"post_type":3,"showdated":"9分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20141013/14131673903304.jpg","gold_added":0},{"age_str":"3个月","id":"7238966","title":"小丑来了...","re_num":"3","nickname":"快乐的DT","dated":1429176904,"post_type":3,"showdated":"10分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20140624/23/5910752398240.jpg","gold_added":0},{"age_str":"2岁7个月","id":"7238965","title":"为了能够顺利的上幼儿园我没上托班上早教了","re_num":"1","nickname":"龙宝小开心","dated":1429176898,"post_type":3,"showdated":"10分钟前","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20140624/33/5979891863681.jpg","gold_added":0},{"age_str":"16天","id":"7238945","title":"关于恶露，攒肚","re_num":"5","nickname":"辣妈453","dated":1429176762,"post_type":3,"showdated":"13分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20141104/14150947463951.jpg","gold_added":0},{"age_str":"3个月12天","id":"7238917","title":"晒哈大宝和二宝","re_num":"3","nickname":"甜心公主","dated":1429176598,"post_type":3,"showdated":"15分钟前","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20150326/12/8209384612488.jpg","gold_added":0},{"age_str":"5个月16天","id":"7238914","title":"建议一下！谢拉","re_num":"7","nickname":"晴天小猪","dated":1429176578,"post_type":3,"showdated":"16分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150407/2/838146.jpg","gold_added":0},{"age_str":"2个月25天","id":"7238911","title":"得了宫颈糜烂，在喝中药，宝妈们，你们都是怎么治疗的","re_num":"1","nickname":"怡宝麻麻","dated":1429176565,"post_type":3,"showdated":"16分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150303/34/14253652036835.jpg","gold_added":0},{"age_str":"4个月21天","id":"7238910","title":"请教宝妈们，我家宝宝四个月二十天了，前天出门回来之后\u2026\u2026","re_num":"2","nickname":"易辰妈妈","dated":1429176549,"post_type":3,"showdated":"16分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150401/10/14278480627496.jpg","gold_added":0},{"age_str":"11个月23天","id":"7238909","title":"断奶第二天","re_num":"2","nickname":"布丁妈","dated":1429176548,"post_type":3,"showdated":"16分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20140720/29/1966642938663.jpg","gold_added":0},{"age_str":"2个月4天","id":"7238906","title":"宝宝两个月才会笑","re_num":"15","nickname":"贝壳","dated":1429176541,"post_type":3,"showdated":"16分钟前","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150309/29/14258784563228.jpg","gold_added":0}]
     */
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


    public class DataEntity {
        /**
         * age_str : 备孕中
         * id : 7012617
         * title : 【名单】争当社区新星，开启你的指尖育儿之旅！
         * re_num : 904
         * nickname : 小幕
         * dated : 1427853713
         * post_type : 1
         * showdated : 4月1日
         * pic : 2
         * avatar : http://www.qubaobei.com/files/head/150/20140506/26/649843.jpg
         * gold_added : 0
         */


        private String age_str;
        private String id;
        private String title;
        private String re_num;
        private String nickname;
        private int dated;
        private int post_type;
        private String showdated;
        private String pic;
        private String avatar;
        private int gold_added;

        public void setAge_str(String age_str) {
            this.age_str = age_str;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setRe_num(String re_num) {
            this.re_num = re_num;
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

        public void setShowdated(String showdated) {
            this.showdated = showdated;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setGold_added(int gold_added) {
            this.gold_added = gold_added;
        }

        public String getAge_str() {
            return age_str;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getRe_num() {
            return re_num;
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

        public String getShowdated() {
            return showdated;
        }

        public String getPic() {
            return pic;
        }

        public String getAvatar() {
            return avatar;
        }

        public int getGold_added() {
            return gold_added;
        }
    }
}
