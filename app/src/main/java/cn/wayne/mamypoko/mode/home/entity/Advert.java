package cn.wayne.mamypoko.mode.home.entity;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;

import cn.wayne.mamypoko.base.entity.Entity;
import cn.wayne.mamypoko.base.entity.EntityList;

/**
 * Created by Lumia on 2015/4/6.
 */
public class Advert extends Entity implements EntityList{
        /**
         * add_dated : 2015-04-03 11:46:21
         * end_dated : 2015-04-07 00:00:00
         * md5_pic :
         * pic : http://www.qubaobei.com/ios/images/51/1428032781.png
         * source : 0
         * title :
         * type : 1
         * start_dated : 2015-04-06 00:00:00
         * url : 7039195
         * site : 61
         * post_id : 7039195
         * admin_id : 16430
         * adfrom : 0
         * recom_num : 0
         * id : 715
         * age_id : 0
         * state : 1
         * pic2 : http://www.qubaobei.com/ios/images/51/1428032781_2.png
         * pic200 : http://www.qubaobei.com/ios/images/51/1428032781_2.png
         * q_id : 0
         */
        private String add_dated;
        private String end_dated;
        private String md5_pic;
        private String pic;
        private String source;
        private String title;
        private String type;
        private String start_dated;
        private String url;
        private String site;
        private String post_id;
        private String admin_id;
        private String adfrom;
        private String recom_num;
        private String id;
        private String age_id;
        private String state;
        private String pic2;
        private String pic200;
        private String q_id;

        public void setAdd_dated(String add_dated) {
            this.add_dated = add_dated;
        }

        public void setEnd_dated(String end_dated) {
            this.end_dated = end_dated;
        }

        public void setMd5_pic(String md5_pic) {
            this.md5_pic = md5_pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setType(String type) {
            this.type = type;
        }

        public void setStart_dated(String start_dated) {
            this.start_dated = start_dated;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setSite(String site) {
            this.site = site;
        }

        public void setPost_id(String post_id) {
            this.post_id = post_id;
        }

        public void setAdmin_id(String admin_id) {
            this.admin_id = admin_id;
        }

        public void setAdfrom(String adfrom) {
            this.adfrom = adfrom;
        }

        public void setRecom_num(String recom_num) {
            this.recom_num = recom_num;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setAge_id(String age_id) {
            this.age_id = age_id;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setPic2(String pic2) {
            this.pic2 = pic2;
        }

        public void setPic200(String pic200) {
            this.pic200 = pic200;
        }

        public void setQ_id(String q_id) {
            this.q_id = q_id;
        }

        public String getAdd_dated() {
            return add_dated;
        }

        public String getEnd_dated() {
            return end_dated;
        }

        public String getMd5_pic() {
            return md5_pic;
        }

        public String getPic() {
            return pic;
        }

        public String getSource() {
            return source;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String getStart_dated() {
            return start_dated;
        }

        public String getUrl() {
            return url;
        }

        public String getSite() {
            return site;
        }

        public String getPost_id() {
            return post_id;
        }

        public String getAdmin_id() {
            return admin_id;
        }

        public String getAdfrom() {
            return adfrom;
        }

        public String getRecom_num() {
            return recom_num;
        }

        public String getId() {
            return id;
        }

        public String getAge_id() {
            return age_id;
        }

        public String getState() {
            return state;
        }

        public String getPic2() {
            return pic2;
        }

        public String getPic200() {
            return pic200;
        }

        public String getQ_id() {
            return q_id;
        }

    @Override
    public List<?> getList() {
        return null;
    }

    public static final List<Advert> parser(byte[] json){
        String str = new String(json);
        try {
            JSONObject obj = new JSONObject(str);
            String ret = obj.getString("ret");
            if(!"1".equals(ret)) {
                return null;
            }
            String gsonStr = obj.getString("data");

            Gson gson = new Gson();
            Type type = new TypeToken<List<Advert>>(){}.getType();
            return gson.fromJson(gsonStr,type);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }


}
