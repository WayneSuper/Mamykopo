package cn.wayne.mamypoko.net;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import java.io.File;

import cn.wayne.mamypoko.utils.Logger;

/**
 * Created by Lumia on 2015/4/5.
 */
public class MamyClient {
    private static final String HOST = "http://www.qubaobei.com";
    private static final String BASE_URL=HOST+"/%s";
    private static AsyncHttpClient client = null;
    static {
       if(client == null) {
           synchronized (MamyClient.class) {
               if(client == null) {
                   client = new AsyncHttpClient();
               }
           }
       }
    }
    public static void get(String url ,RequestParams params, ResponseHandlerInterface handler) {
       client.get(packageUrl(url),params,handler);
        Logger.debug(new StringBuilder(packageUrl(url)).append(params.toString()).toString());
    }

    public static void getWithFull(String url ,RequestParams params, ResponseHandlerInterface handler) {
        client.get(url,params,handler);
        Logger.debug(new StringBuilder(url).append(params.toString()).toString());
    }

    public static void post(String url ,RequestParams params, ResponseHandlerInterface handler) {
        client.post(packageUrl(url), params, handler);
        Logger.debug(new StringBuilder(packageUrl(url)).append(params.toString()).toString());

    }

    private static String packageUrl(String url) {
        if(url.indexOf("/") == 0) {
            url = url.substring(1);
        }
        return String.format(BASE_URL,url);
    }
}
