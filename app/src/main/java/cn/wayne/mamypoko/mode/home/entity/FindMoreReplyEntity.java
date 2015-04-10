package cn.wayne.mamypoko.mode.home.entity;

import java.util.List;

import cn.wayne.mamypoko.base.entity.EntityList;

/**
 * Created by Pollux on 2015/4/10.
 * //
 */
public class FindMoreReplyEntity implements EntityList {
    @Override
    public List<?> getList() {
        return null;
    }

    private int ret ;

    private List<FindReplyEntity> data;

    private int has_more;

    private int re_num;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public List<FindReplyEntity> getData() {
        return data;
    }

    public void setData(List<FindReplyEntity> data) {
        this.data = data;
    }

    public int getHas_more() {
        return has_more;
    }

    public void setHas_more(int has_more) {
        this.has_more = has_more;
    }

    public int getRe_num() {
        return re_num;
    }

    public void setRe_num(int re_num) {
        this.re_num = re_num;
    }
}
