package cn.wayne.mamypoko.mode.noeat;

import java.util.List;

/**
 * Created by Pollux on 2015/4/14.
 * //
 */
public class NoEatModel {


    /**
     * ret : 1
     * season : ["²¤²Ë","Î÷ºìÊÁ","ÜÈ²Ë","¿ÕÐÄ²Ë","Â«Ëñ","Üù²Ë"]
     * data : [{"color":"#F98466","name":"±§±¦±¦"},{"color":"#DF9798","name":"Æê´ø»¤Àí"},{"color":"#F98466","name":"Ôç²ú¶ù"},{"color":"#BB7CD3","name":"³öÉúÖ¤Ã÷"},{"color":"#DF9798","name":"Ì¥¼Ç"},{"color":"#BB7CD3","name":"ÒçÄÌ"},{"color":"#F98466","name":"»Æðã"},{"color":"#BB7CD3","name":"ÊªÕî"},{"color":"#DF9798","name":"ÄÌ·Û"},{"color":"#69A2CF","name":"Óã¸ÎÓÍ"},{"color":"#DF9798","name":"Ö½Äò¿ã"},{"color":"#82C28E","name":"ÄÌË®²»×ã"}]
     * season2 : [{"color":"#F98466","name":"²¤²Ë"},{"color":"#F98466","name":"Î÷ºìÊÁ"},{"color":"#F98466","name":"ÜÈ²Ë"},{"color":"#F98466","name":"¿ÕÐÄ²Ë"},{"color":"#F98466","name":"Â«Ëñ"},{"color":"#F98466","name":"Üù²Ë"}]
     */
    private int ret;
    private List<ProbleamEntity> data;
    private List<FoodEntity> season2;

    public void setRet(int ret) {
        this.ret = ret;
    }


    public void setData(List<ProbleamEntity> data) {
        this.data = data;
    }

    public void setSeason2(List<FoodEntity> season2) {
        this.season2 = season2;
    }

    public int getRet() {
        return ret;
    }

    public List<ProbleamEntity> getData() {
        return data;
    }

    public List<FoodEntity> getSeason2() {
        return season2;
    }

    public class ProbleamEntity {
        /**
         * color : #F98466
         * name : ±§±¦±¦
         */
        private String color;
        private String name;

        public void setColor(String color) {
            this.color = color;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public String getName() {
            return name;
        }
    }

    public class FoodEntity {
        /**
         * color : #F98466
         * name : ²¤²Ë
         */
        private String color;
        private String name;

        public void setColor(String color) {
            this.color = color;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getColor() {
            return color;
        }

        public String getName() {
            return name;
        }
    }
}
