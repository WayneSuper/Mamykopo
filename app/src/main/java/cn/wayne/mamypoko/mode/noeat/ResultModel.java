package cn.wayne.mamypoko.mode.noeat;

import java.util.List;

import cn.wayne.mamypoko.base.entity.Entity;
import cn.wayne.mamypoko.base.entity.EntityList;

/**
 * Created by Pollux on 2015/4/14.
 * //
 */
public class ResultModel extends Entity implements EntityList {


    /**
     * ret : 1
     * data : [{"stage2":"1","id":"109","stage3":"1","stage4":"1","nick":"·¬ÇÑ¡¢ÑóÊÁ×Ó","stage1":"1","name":"Î÷ºìÊÁ","img":"http://filebaby.qubaobei.com/uploads/nbnc/120/109.jpg"},{"stage2":"1","id":"260","stage3":"1","stage4":"1","nick":"°²Ê¯Áñ¡¢½ğó¿¡¢½ğÅÓ¡¢ÖÓÊ¯Áñ¡¢Ìì½¬¡¢¸ÊÊ¯Áñ¡¢µ¤Èô","stage1":"1","name":"Ê¯Áñ","img":"http://filebaby.qubaobei.com/uploads/nbnc/120/260.jpg"},{"stage2":"1","id":"238","stage3":"1","stage4":"1","nick":"¼¦Êº¹û¡¢°Î×Ó¡¢À®°È·¬Ê¯Áñ¡¢°ÅÀÖ","stage1":"1","name":"·¬Ê¯Áñ","img":"http://filebaby.qubaobei.com/uploads/nbnc/120/238.jpg"}]
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

    @Override
    public List<?> getList() {
        return getData();
    }

    public class DataEntity {
        /**
         * stage2 : 1
         * id : 109
         * stage3 : 1
         * stage4 : 1
         * nick : ·¬ÇÑ¡¢ÑóÊÁ×Ó
         * stage1 : 1
         * name : Î÷ºìÊÁ
         * img : http://filebaby.qubaobei.com/uploads/nbnc/120/109.jpg
         */
        private String stage2;
        private String id;
        private String stage3;
        private String stage4;
        private String nick;
        private String stage1;
        private String name;
        private String img;

        public void setStage2(String stage2) {
            this.stage2 = stage2;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setStage3(String stage3) {
            this.stage3 = stage3;
        }

        public void setStage4(String stage4) {
            this.stage4 = stage4;
        }

        public void setNick(String nick) {
            this.nick = nick;
        }

        public void setStage1(String stage1) {
            this.stage1 = stage1;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getStage2() {
            return stage2;
        }

        public String getId() {
            return id;
        }

        public String getStage3() {
            return stage3;
        }

        public String getStage4() {
            return stage4;
        }

        public String getNick() {
            return nick;
        }

        public String getStage1() {
            return stage1;
        }

        public String getName() {
            return name;
        }

        public String getImg() {
            return img;
        }
    }
}
