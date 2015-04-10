package cn.wayne.mamypoko.base.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.wayne.mamypoko.mode.home.entity.FindContent;
import cn.wayne.mamypoko.mode.home.entity.FindContentEntity;
import cn.wayne.mamypoko.mode.home.entity.FindMoreReplyEntity;
import cn.wayne.mamypoko.mode.home.entity.FindReplyEntity;

/**
 * Created by Pollux on 2015/4/9.
 * //
 */
public class FindParser {


    public static FindContent parser(byte[] responseBody) {
        String str = new String(responseBody);
        try {
            JSONObject obj = new JSONObject(str);
            int status = obj.getInt("ret");
            if(status!=1) {
                return  null;
            }
            FindContent mFindContent = new FindContent();
            mFindContent.setReplys(parserReplys(obj.getString("reply")));
            mFindContent.setHas_more(obj.getInt("has_more"));
            mFindContent.setMsg_reply(obj.getString("msg_reply"));
            JSONObject oo = obj.getJSONObject("data");
            mFindContent.setAge_str(oo.getString("age_str"));
            mFindContent.setAvatar(oo.getString("avatar"));
            mFindContent.setB_id(oo.getString("b_id"));
            mFindContent.setIs_lock(oo.getInt("is_lock"));
            mFindContent.setLevel(oo.getInt("level"));
            mFindContent.setNickname(oo.getString("nickname"));
            mFindContent.setO_user_id(oo.getString("o_user_id"));
            mFindContent.setRe_num(oo.getString("re_num"));
            mFindContent.setTitle(oo.getString("title"));
            mFindContent.setCity(oo.getString("city"));
            mFindContent.setUser_id(oo.getString("user_id"));
            mFindContent.setContents(parserContents(oo.getString("content")));
            return mFindContent;
        } catch (JSONException e) {
            e.printStackTrace();
            return  null;
        }
    }

    private static List<FindReplyEntity> parserReplys(String reply) {
        Gson gson = new Gson();
        return gson.fromJson(reply,new TypeToken<List<FindReplyEntity>>(){}.getType());
    }

    private static List<FindContentEntity> parserContents(String content) {
        Gson gson = new Gson();
        return gson.fromJson(content,new TypeToken<List<FindContentEntity>>(){}.getType());
    }

    public static FindMoreReplyEntity parserMoreReply(byte[] responseBody) {
        Gson gson = new Gson();
        String str = new String(responseBody);
        return gson.fromJson(str,new TypeToken<FindMoreReplyEntity>(){}.getType());
    }
}
