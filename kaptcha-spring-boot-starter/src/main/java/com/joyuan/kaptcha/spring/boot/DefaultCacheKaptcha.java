package com.joyuan.kaptcha.spring.boot;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.joyuan.kaptcha.spring.boot.properties.KaptchaProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>@Title: </p>
 * <p>@Package: com.ryanbing.kaptcha.spring.boot</p>
 * <p>@Description: </p>
 * <p>@Copyright: 294786949@qq.com Inc. All rights reserved.</p>
 *
 * @author: 294786949@qq.com
 * @date: 2019/5/26 16:23
 * @version: v1.0.0
 */
public class DefaultCacheKaptcha implements ICacheKaptcha {

    final Cache cache;
    final CacheManager cacheManager;
    final KaptchaProperties properties;
    private Producer producer = null;

    public DefaultCacheKaptcha(CacheManager manager, KaptchaProperties properties){
        this.cacheManager = manager;
        this.cache = manager.getCache(properties.getCacheName());
        this.properties = properties;

        if(this.properties.getProperties() == null){
            this.producer = new DefaultKaptcha();
        }else{
            this.producer = new Config(this.properties.getProperties()).getProducerImpl();
        }
    }

    @Override
    public Map<String, String> responseImage(HttpServletResponse response) throws IOException {
        String text = this.createCaptchaText();
        String key  = UUID.randomUUID().toString().replaceAll("-","");

        //发送图片
        BufferedImage bi = this.createImage(text);
        response.addHeader(this.properties.getParamKeyName(),key);
        KaptchaUtils.sendImage(response,bi);

        //保存到缓存中
        this.cache.put(key,text);
        LinkedHashMap<String, String> data = new LinkedHashMap<>(2);
        data.put("key",key);
        data.put("value",text);
        return data;
    }

    @Override
    public String createCaptchaText() {
        return this.producer.createText();
    }

    @Override
    public BufferedImage createImage(String txt) {
        return this.producer.createImage(txt);
    }

    /**
     * 只做验证是否匹配和过期
     * @param key 要校验的键
     * @param value 要校验的值
     * @return
     */
    @Override
    public boolean verification(String key, String value) {
        if(StringUtils.isEmpty(value)){
            return false;
        }
        String o = this.cache.get(key,String.class);
        return value.equalsIgnoreCase(o);
    }

    @Override
    public boolean verification(HttpServletRequest request) {
        String key   = request.getParameter(this.properties.getParamKeyName());
        String value = request.getParameter(this.properties.getParamValueName());
        return this.verification(key,value);
    }

    /**
     * 验证并删除
     * @param key 要校验的键
     * @param value 要校验的值
     * @return
     */
    @Override
    public boolean verificationAndDel(String key, String value) {
        boolean b = verification(key,value);
        this.cache.evict(key);
        return b;
    }
}
