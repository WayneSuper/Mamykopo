package cn.wayne.mamypoko.mode.circle.entity;

import java.util.List;

/**
 * Created by Pollux on 2015/4/2.
 *
 */
public class CircleModel  {

    private int logo;
    private String type;
    private String title;
    /**
     * No more than 5
     */
    private List<String> images;

    public CircleModel() {
    }

    public CircleModel(int logo, String type, String title, List<String> images) {
        this.logo = logo;
        this.type = type;
        this.title = title;
        this.images = images;
    }

    public int getLogo() {
        return logo;
    }

    public void setLogo(int logo) {
        this.logo = logo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
