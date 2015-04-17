package cn.wayne.mamypoko.mode.noeat;

import java.util.List;

/**
 * Created by Pollux on 2015/4/15.
 * //
 */
public class NoeatDetailModel {
    /**
     * ret : 1
     * data : {"data":[{"content":"坐月子期间活动少，加上内脏受压迫的情况刚刚解除，胃肠消化功能都比较弱，所以常会发生便秘。而麦片属于粗粮类，能促进排便有效预防便秘。但是，麦片也有分品种，坐月子一定要选用纯燕麦麦片，普通的麦片会有很多添加剂，吃了会导致奶水减少，不利于下奶。","title":"产妇能吃麦片吗？","can_eat":"2"},{"content":"麦片含有丰富的B族维生素和膳食纤维，能够补充维生素和预防便秘，是孕妇的理想进补食物。不过提醒各位孕妈，怀孕后期就要少吃燕麦，会减少奶水。","title":"孕妇能吃麦片吗？","can_eat":"2"},{"content":"哺乳期的妈妈吃麦片要特别注意，一定要选纯正的燕麦片，不可食用普通麦片代替燕麦片，普通麦片含有小麦、大麦、麦芽糊精、砂糖、奶精等成分，哺乳期妈妈吃了普通麦片可能会发生回奶现象，导致奶水减少，不利于下奶，影响宝宝。","title":"坐月子能吃麦片吗？","can_eat":"2"},{"content":"燕麦营养丰富，但很难咀嚼。婴幼儿可以打成糊状之后食用。不过不宜给宝宝食用过多的哦！要适量才不会影响到宝宝的消化和吸收的哦！","title":"婴儿能吃麦片吗？","can_eat":"1"}],"img":"http://filebaby.qubaobei.com/uploads/nbnc/600/2.jpg"}
     */
    private int ret;
    private DetailImage data;

    public void setRet(int ret) {
        this.ret = ret;
    }

    public void setData(DetailImage data) {
        this.data = data;
    }

    public int getRet() {
        return ret;
    }

    public DetailImage getData() {
        return data;
    }

    public class DetailImage {
        /**
         * data : [{"content":"坐月子期间活动少，加上内脏受压迫的情况刚刚解除，胃肠消化功能都比较弱，所以常会发生便秘。而麦片属于粗粮类，能促进排便有效预防便秘。但是，麦片也有分品种，坐月子一定要选用纯燕麦麦片，普通的麦片会有很多添加剂，吃了会导致奶水减少，不利于下奶。","title":"产妇能吃麦片吗？","can_eat":"2"},{"content":"麦片含有丰富的B族维生素和膳食纤维，能够补充维生素和预防便秘，是孕妇的理想进补食物。不过提醒各位孕妈，怀孕后期就要少吃燕麦，会减少奶水。","title":"孕妇能吃麦片吗？","can_eat":"2"},{"content":"哺乳期的妈妈吃麦片要特别注意，一定要选纯正的燕麦片，不可食用普通麦片代替燕麦片，普通麦片含有小麦、大麦、麦芽糊精、砂糖、奶精等成分，哺乳期妈妈吃了普通麦片可能会发生回奶现象，导致奶水减少，不利于下奶，影响宝宝。","title":"坐月子能吃麦片吗？","can_eat":"2"},{"content":"燕麦营养丰富，但很难咀嚼。婴幼儿可以打成糊状之后食用。不过不宜给宝宝食用过多的哦！要适量才不会影响到宝宝的消化和吸收的哦！","title":"婴儿能吃麦片吗？","can_eat":"1"}]
         * img : http://filebaby.qubaobei.com/uploads/nbnc/600/2.jpg
         */
        private List<DataEntity> data;
        private String img;

        public void setData(List<DataEntity> data) {
            this.data = data;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public List<DataEntity> getData() {
            return data;
        }

        public String getImg() {
            return img;
        }

        public class DataEntity {
            /**
             * content : 坐月子期间活动少，加上内脏受压迫的情况刚刚解除，胃肠消化功能都比较弱，所以常会发生便秘。而麦片属于粗粮类，能促进排便有效预防便秘。但是，麦片也有分品种，坐月子一定要选用纯燕麦麦片，普通的麦片会有很多添加剂，吃了会导致奶水减少，不利于下奶。
             * title : 产妇能吃麦片吗？
             * can_eat : 2
             */
            private String content;
            private String title;
            private String can_eat;

            public void setContent(String content) {
                this.content = content;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setCan_eat(String can_eat) {
                this.can_eat = can_eat;
            }

            public String getContent() {
                return content;
            }

            public String getTitle() {
                return title;
            }

            public String getCan_eat() {
                return can_eat;
            }
        }
    }
}
