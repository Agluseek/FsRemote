package com.agluseek.farsoon.farsoononline.utils;

import android.content.Context;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.AppGlideModule;

import java.io.InputStream;

/**
 * Created by Farsoon on 2017/8/21.
 */
@GlideModule
public final class CustomAppGlideModule extends AppGlideModule {

    /**
     *  通过GlideBuilder设置默认的结构(Engine,BitmapPool ,ArrayPool,MemoryCache等等).
     * @param context
     * @param builder
     */
    public void applyOptions(Context context, GlideBuilder builder) {
        //重新设置内存限制
        builder.setMemoryCache(new LruResourceCache(10*1024*1024));
    }

    /**
     * 为App注册一个自定义的String类型的BaseGlideUrlLoader
     *
     * @param context
     * @param registry
     */

    public void registerComponents(Context context, Registry registry) {
        registry.append(String.class, InputStream.class,new CustomBaseGlideUrlLoader.Factory());
    }
    /**
     * 清单解析的开启
     *
     * 这里不开启，避免添加相同的modules两次
     * @return
     */
    public boolean isManifestParsingEnabled() {
        return false;
    }

}