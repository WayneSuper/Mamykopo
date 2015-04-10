package cn.wayne.mamypoko.mode.home.entity;

import java.util.List;

import cn.wayne.mamypoko.base.entity.EntityList;

/**
 * Created by Pollux on 2015/4/9.
 * //
 */
public class FindContentEntity implements EntityList {
    @Override
    public List<?> getList() {
        return null;
    }

    /**
     *  {
     "content": "<br/><br/>原料：排骨500克，腐乳3块。<br/><br/>辅料：酱油1勺，糖适量，小葱5根，姜一小块，八角一小块，料酒2勺，芝麻油1勺。<br/><br/>做法：<br/><br/>1、排骨用水充分泡洗，凉水下锅，加姜片料酒焯至肉变白。<br/><br/>2、将焯好的肋排捞出冲洗干净，葱姜切片，准备八角。<br/><br/>3、红腐乳加少许汤汁用勺压碎备用。<br/><br/>4、锅中放适量油，入葱姜爆香后加入排骨翻炒。加入适量水，八角、少许糖。<br/><br/>5、加入调好的红腐乳汁和红烧酱油大火煮开后十分钟转中小火慢煮。<br/><br/>6、至汤汁快要收尽，加入少许芝麻油关火即可。<br/><br/>14、南瓜烧排骨<br/><br/>",
     "type": 1,
     "a": 0
     },
     {
     "big": "http://filebaby.qubaobei.com/uploads/qq/640p/20150407/25/090320688836.jpg",
     "type": 2,
     "width": 569,
     "height": 426,
     "content": "http://filebaby.qubaobei.com/uploads/qq/320p/20150407/25/090320688836.jpg"
     },
     */

    private String content;
    private String big;
    private int type;
    private int width;
    private int height;
    private int a;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getBig() {
        return big;
    }

    public void setBig(String big) {
        this.big = big;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }
}
