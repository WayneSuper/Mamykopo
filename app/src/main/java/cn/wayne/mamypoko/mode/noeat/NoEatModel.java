package cn.wayne.mamypoko.mode.noeat;

import java.util.List;

/**
 * Created by Pollux on 2015/4/14.
 * //
 */
public class NoEatModel {


    /**
     * ret : 1
     * season : ["����","������","�Ȳ�","���Ĳ�","«��","����"]
     * data : [{"color":"#F98466","name":"������"},{"color":"#DF9798","name":"�������"},{"color":"#F98466","name":"�����"},{"color":"#BB7CD3","name":"����֤��"},{"color":"#DF9798","name":"̥��"},{"color":"#BB7CD3","name":"����"},{"color":"#F98466","name":"����"},{"color":"#BB7CD3","name":"ʪ��"},{"color":"#DF9798","name":"�̷�"},{"color":"#69A2CF","name":"�����"},{"color":"#DF9798","name":"ֽ���"},{"color":"#82C28E","name":"��ˮ����"}]
     * season2 : [{"color":"#F98466","name":"����"},{"color":"#F98466","name":"������"},{"color":"#F98466","name":"�Ȳ�"},{"color":"#F98466","name":"���Ĳ�"},{"color":"#F98466","name":"«��"},{"color":"#F98466","name":"����"}]
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
         * name : ������
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
         * name : ����
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
