package cn.wayne.mamypoko.cache;

/**
 * Created by Lumia on 2015/4/21.
 */
public interface CacheManager {

    String getString(String url);

    void putString(String url,String value);
}
