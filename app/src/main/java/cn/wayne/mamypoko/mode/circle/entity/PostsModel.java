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
     * data : [{"age_str":"������","id":"7012617","title":"�������������������ǣ��������ָ������֮�ã�","re_num":"904","nickname":"СĻ","dated":1427853713,"post_type":1,"showdated":"4��1��","pic":"2","avatar":"http://www.qubaobei.com/files/head/150/20140506/26/649843.jpg","gold_added":0},{"age_str":"3����11��","id":"7224662","title":"�ֻ��Ļ����ˣ���������޵Ĺ���","re_num":"420","nickname":"��ϫ","dated":1429086317,"post_type":1,"showdated":"���� 16:25","pic":"10","avatar":"http://www.qubaobei.com/files/head/150/20141012/3/8842663272018.jpg","gold_added":0},{"age_str":"6����8��","id":"7236081","title":"�ж��ٱ����Լ���2��С�������Ƕ��","re_num":"115","nickname":"����֮��","dated":1429164637,"post_type":2,"showdated":"3Сʱǰ","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20141210/7/1418220760995.jpg","gold_added":0},{"age_str":"1����25��","id":"7235824","title":"�����±�������","re_num":"32","nickname":"��������","dated":1429163639,"post_type":2,"showdated":"3Сʱǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150411/29/14287404165732.jpg","gold_added":0},{"age_str":"4����20��","id":"7238069","title":"�ĸ����µı������������ֳ���û","re_num":"30","nickname":"����һĨ������","dated":1429172832,"post_type":2,"showdated":"1Сʱǰ","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20141218/17/14188390401548.jpg","gold_added":0},{"age_str":"6����16��","id":"7237130","title":"���Ǳ�����\u201c�˲�\u201d������","re_num":"27","nickname":"��������","dated":1429168751,"post_type":2,"showdated":"2Сʱǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20140131/13911257365106.jpg","gold_added":0},{"age_str":"6����18��","id":"7239107","title":"���ǣ�����ɹͼ����diy����*^_^*���ǹ��Ŷ��","re_num":"0","nickname":"�䰮","dated":1429177500,"post_type":3,"showdated":"�ո�","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20150106/25/7419554061770.jpg","gold_added":0},{"age_str":"8����21��","id":"7239106","title":"׼������ͷ��","re_num":"0","nickname":"���������","dated":1429177498,"post_type":3,"showdated":"�ո�","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20140624/16/1890002444139.jpg","gold_added":0},{"age_str":"1��","id":"7239098","title":"����ָ����ʳ","re_num":"0","nickname":"��ֹ���","dated":1429177475,"post_type":3,"showdated":"1����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150414/18/6159584485460.jpg","gold_added":0},{"age_str":"6����29��","id":"7239097","title":"����������Ƣ����ô","re_num":"0","nickname":"ƤС椡�","dated":1429177473,"post_type":3,"showdated":"1����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150219/17/6475294054280.jpg","gold_added":0},{"age_str":"1����28��","id":"7239079","title":"����һ�첻ι��զ�죿","re_num":"0","nickname":"������/tp","dated":1429177378,"post_type":3,"showdated":"2����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150318/39/8167274223754.jpg","gold_added":0},{"age_str":"3����30��","id":"7239074","title":"������ʪ��ģ�����ڳ�ʲô�̷�","re_num":"3","nickname":"����618","dated":1429177351,"post_type":3,"showdated":"3����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150314/36/14263266125499.jpg","gold_added":0},{"age_str":"3����4��","id":"7239072","title":"�ùԵı���","re_num":"1","nickname":"��ӣǱ��","dated":1429177333,"post_type":3,"showdated":"3����ǰ","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20150408/37/919933.jpg","gold_added":0},{"age_str":"��38��","id":"7239070","title":"��û�б����������������䣿��","re_num":"0","nickname":"�ҵ������������˧","dated":1429177331,"post_type":3,"showdated":"3����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150325/2/121656.jpg","gold_added":0},{"age_str":"2��3����","id":"7239066","title":"�����ǰ��æ","re_num":"1","nickname":"��baby","dated":1429177321,"post_type":3,"showdated":"3����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20140824/37/9580662802090.jpg","gold_added":0},{"age_str":"9����5��","id":"7239059","title":"���������ʴ��һ������","re_num":"0","nickname":"��������","dated":1429177301,"post_type":3,"showdated":"4����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150403/40/14280406107143.jpg","gold_added":0},{"age_str":"������","id":"7239054","title":"�������룬�����������£������������������","re_num":"4","nickname":"����919422","dated":1429177267,"post_type":3,"showdated":"4����ǰ","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20140703/14043716899130.jpg","gold_added":0},{"age_str":"1��9����","id":"7239050","title":"�������������˸�����","re_num":"0","nickname":"����","dated":1429177259,"post_type":3,"showdated":"4����ǰ","pic":"2","avatar":"http://www.qubaobei.com/files/head/150/20140705/13/207086224798.jpg","gold_added":0},{"age_str":"5����7��","id":"7239019","title":"����±���������������ô�찡��ϣ���о���ı���֧��","re_num":"0","nickname":"���౦��","dated":1429177252,"post_type":3,"showdated":"4����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20131126/38/4769411257502.jpg","gold_added":0},{"age_str":"9��","id":"7239046","title":"������ι�̵�����","re_num":"0","nickname":"�����ᡢ�ӻ�С�ഺ","dated":1429177249,"post_type":3,"showdated":"4����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20141222/14192100906344.jpg","gold_added":0},{"age_str":"7����25��","id":"7239045","title":"������˸����� ��û����","re_num":"1","nickname":"�ۻ�","dated":1429177245,"post_type":3,"showdated":"5����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20140729/30/14066347987745.jpg","gold_added":0},{"age_str":"9����6��","id":"7239038","title":"֪������ʲô����","re_num":"1","nickname":"���ǵ��","dated":1429177198,"post_type":3,"showdated":"5����ǰ","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20141007/14126731245077.jpg","gold_added":0},{"age_str":"6����8��","id":"7239025","title":"��⡣��������","re_num":"0","nickname":"Сƻ��","dated":1429177138,"post_type":3,"showdated":"6����ǰ","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20150401/6/14278568084293.jpg","gold_added":0},{"age_str":"2����12��","id":"7239021","title":"���ڱ�����Ĥ����\u2026\u2026�б���֪������","re_num":"0","nickname":"С����","dated":1429177129,"post_type":3,"showdated":"6����ǰ","pic":"3","avatar":"http://www.qubaobei.com/files/head/150/20150131/12/14226952493523.jpg","gold_added":0},{"age_str":"3����23��","id":"7239009","title":"�����ж��ٱ���������Ե������̷�","re_num":"0","nickname":"�������","dated":1429177094,"post_type":3,"showdated":"7����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150416/39/14291771423889.jpg","gold_added":0},{"age_str":"��8��6��","id":"7239003","title":"����ʪ����������","re_num":"0","nickname":"���ﳿ","dated":1429177057,"post_type":3,"showdated":"8����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150407/22/142841335432.jpg","gold_added":0},{"age_str":"4����28��","id":"7238989","title":"��������һֱ����","re_num":"0","nickname":"���Ѥ��","dated":1429176992,"post_type":3,"showdated":"9����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20141013/14131673903304.jpg","gold_added":0},{"age_str":"3����","id":"7238966","title":"С������...","re_num":"3","nickname":"���ֵ�DT","dated":1429176904,"post_type":3,"showdated":"10����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20140624/23/5910752398240.jpg","gold_added":0},{"age_str":"2��7����","id":"7238965","title":"Ϊ���ܹ�˳�������׶�԰��û���а��������","re_num":"1","nickname":"����С����","dated":1429176898,"post_type":3,"showdated":"10����ǰ","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20140624/33/5979891863681.jpg","gold_added":0},{"age_str":"16��","id":"7238945","title":"���ڶ�¶���ܶ�","re_num":"5","nickname":"����453","dated":1429176762,"post_type":3,"showdated":"13����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20141104/14150947463951.jpg","gold_added":0},{"age_str":"3����12��","id":"7238917","title":"ɹ���󱦺Ͷ���","re_num":"3","nickname":"���Ĺ���","dated":1429176598,"post_type":3,"showdated":"15����ǰ","pic":"1","avatar":"http://www.qubaobei.com/files/head/150/20150326/12/8209384612488.jpg","gold_added":0},{"age_str":"5����16��","id":"7238914","title":"����һ�£�л��","re_num":"7","nickname":"����С��","dated":1429176578,"post_type":3,"showdated":"16����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150407/2/838146.jpg","gold_added":0},{"age_str":"2����25��","id":"7238911","title":"���˹������ã��ں���ҩ�������ǣ����Ƕ�����ô���Ƶ�","re_num":"1","nickname":"��������","dated":1429176565,"post_type":3,"showdated":"16����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150303/34/14253652036835.jpg","gold_added":0},{"age_str":"4����21��","id":"7238910","title":"��̱����ǣ��Ҽұ����ĸ��¶�ʮ���ˣ�ǰ����Ż���֮��\u2026\u2026","re_num":"2","nickname":"�׳�����","dated":1429176549,"post_type":3,"showdated":"16����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150401/10/14278480627496.jpg","gold_added":0},{"age_str":"11����23��","id":"7238909","title":"���̵ڶ���","re_num":"2","nickname":"������","dated":1429176548,"post_type":3,"showdated":"16����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20140720/29/1966642938663.jpg","gold_added":0},{"age_str":"2����4��","id":"7238906","title":"���������²Ż�Ц","re_num":"15","nickname":"����","dated":1429176541,"post_type":3,"showdated":"16����ǰ","pic":"0","avatar":"http://www.qubaobei.com/files/head/150/20150309/29/14258784563228.jpg","gold_added":0}]
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
         * age_str : ������
         * id : 7012617
         * title : �������������������ǣ��������ָ������֮�ã�
         * re_num : 904
         * nickname : СĻ
         * dated : 1427853713
         * post_type : 1
         * showdated : 4��1��
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
