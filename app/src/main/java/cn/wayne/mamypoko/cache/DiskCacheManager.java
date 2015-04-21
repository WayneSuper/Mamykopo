package cn.wayne.mamypoko.cache;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Lumia on 2015/4/21.
 */
public class DiskCacheManager implements CacheManager{
    private static final String UNIQUE_NAME = "cache";
    private static final long MAX_SIZE = 10 * 1024;
    private String mUniqueName = UNIQUE_NAME;
    private long mMaxSize = MAX_SIZE;
    private DiskLruCache mDiskLruCache;
    private long CACHE_MIN = 60 * 1000;
    private int CACHE_TIME = 1 ;
    private int mCacheTime = CACHE_TIME;

    public DiskCacheManager(Context context,String uniqueName , long maxSize) {
        try {
            mMaxSize = maxSize;
            mUniqueName = uniqueName;
            mCacheTime = CACHE_TIME;
            mDiskLruCache = DiskLruCache.open(getCacheDir(context,uniqueName),getAppVersion(context),1,maxSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public DiskCacheManager(Context context) {
        this(context,UNIQUE_NAME,MAX_SIZE);
    }

    private int getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }

    private File getCacheDir(Context context, String uniqueName) {
        String path = Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) ?
                context.getExternalCacheDir().getPath() : context.getCacheDir().getPath();
        return new File(path,uniqueName);
    }


    @Override
    public String getString(String url) {
        if(isOverTime(0,url)){   //判断是否过期
            return null;
        }
        BufferedInputStream bis = null;
        StringBuffer sb = new StringBuffer();
        try {
            DiskLruCache.Snapshot mSnapshot = mDiskLruCache.get(toHexString(url));
            if(mSnapshot == null) {
                return null;
            }
            bis = new BufferedInputStream(mSnapshot.getInputStream(0));
            byte[] buff = new byte[1024];
            int len;
            while ((len = bis.read(buff)) != -1) {
                sb.append(new String(buff,0,len));
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bis!=null)bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private boolean isOverTime(int index , String url) {
        File file = new File(mDiskLruCache.getDirectory()
                +File.separator+toHexString(url)+"." + index);
        if(file.exists()) {
            long desire = System.currentTimeMillis() - file.lastModified();
            Log.d("TAG","Current Time :" + new SimpleDateFormat("mm:ss").format(new Date(System.currentTimeMillis())));
            Log.d("TAG","File Modify Time :" + new SimpleDateFormat("mm:ss").format(new Date(file.lastModified())));
            Log.d("TAG","Desire Time :" + new SimpleDateFormat("mm:ss").format(new Date(desire)));
            if(desire > mCacheTime * CACHE_MIN) {
                Log.d("TAG","File is Over time,will be request data from Server");
                return true;
            }else {
                Log.d("TAG","Using The Cache data");
                return false;
            }
        }
        Log.d("TAG","File is Over time,will be request data from Server");
        return true;
    }

    @Override
    public void putString(String url, String value) {
        BufferedOutputStream bos = null;
        try {
            DiskLruCache.Editor editor = mDiskLruCache.edit(toHexString(url));
            if(editor != null) {
                bos = new BufferedOutputStream(editor.newOutputStream(0));
                bos.write(value.getBytes(CacheUtil.UTF_8));
                editor.commit();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(bos!=null)bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void clear() {
        try {
            mDiskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void flush() {
        try {
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String toHexString(String url) {
        byte[] bytes = getMD5String(url);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    private byte[] getMD5String(String url) {
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(url.getBytes());
           return mDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            return String.valueOf(url.hashCode()).getBytes();
        }
    }

    public int getCacheTime() {
        return mCacheTime;
    }

    /**
     * 单位分钟
     * @param mCacheTime
     */
    public void setCacheTime(int mCacheTime) {
        this.mCacheTime = mCacheTime;
    }
}
