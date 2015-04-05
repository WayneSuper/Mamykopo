package cn.wayne.mamypoko.utils;

import android.content.Context;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Supplier;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by Pollux on 2015/4/3.
 * //
 */
public class ImagePipelineFractory {
    private static ImagePipelineConfig sImagePipelineConfig;
    /**
     * Creates config using android http stack as network backend.
     */
    public static ImagePipelineConfig getImagePipelineConfig(Context context) {
        if (sImagePipelineConfig == null) {
            ImagePipelineConfig.Builder configBuilder = ImagePipelineConfig.newBuilder(context);
            configureDiskCache(configBuilder, context);
            configureMomeryCache(configBuilder, context);
            sImagePipelineConfig = configBuilder.build();
        }
        return sImagePipelineConfig;
    }

    private static void configureMomeryCache(ImagePipelineConfig.Builder configBuilder, Context context) {
        final MemoryCacheParams bitmapCacheParams = new MemoryCacheParams(
                ConfigConstants.MAX_MEMORY_CACHE_SIZE, // Max total size of elements in the cache
                Integer.MAX_VALUE,                     // Max entries in the cache
                ConfigConstants.MAX_MEMORY_CACHE_SIZE, // Max total size of elements in eviction queue
                Integer.MAX_VALUE,                     // Max length of eviction queue
                Integer.MAX_VALUE);                    // Max cache entry size
        configBuilder
                .setBitmapMemoryCacheParamsSupplier(
                        new Supplier<MemoryCacheParams>() {
                            public MemoryCacheParams get() {
                                return bitmapCacheParams;
                            }
                        });

    }

    private static void configureDiskCache(ImagePipelineConfig.Builder configBuilder, Context context) {
        configBuilder.setMainDiskCacheConfig(
                DiskCacheConfig.newBuilder()
                        .setBaseDirectoryPath(context.getApplicationContext().getCacheDir())
                        .setBaseDirectoryName(ConfigConstants.DISK_CACHE_FILE_DIR)
                        .setMaxCacheSize(ConfigConstants.MAX_DISK_CACHE_SIZE)
                        .build());
    }
}
