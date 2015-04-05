package cn.wayne.mamypoko.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.wayne.mamypoko.utils.ImagePipelineFractory;

/**
 * Created by Pollux on 2015/4/3.
 * //
 */
public class AppCore extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(getApplicationContext(), ImagePipelineFractory.getImagePipelineConfig(getApplicationContext()));
    }


}
