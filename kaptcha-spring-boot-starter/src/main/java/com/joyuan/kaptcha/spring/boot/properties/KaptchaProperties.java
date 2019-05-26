package com.joyuan.kaptcha.spring.boot.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

import static com.joyuan.kaptcha.spring.boot.properties.KaptchaProperties.KAPTCHA_PREFIX;

/**
 * <p>@Title: </p>
 * <p>@Description: </p>
 * <p>@Copyright: 294786949@qq.com Inc. All rights reserved.</p>
 *
 * @author: 294786949@qq.com
 * @date: 2019/5/26 18:01
 * @version: v1.0.0
 */
@ConfigurationProperties(prefix = KAPTCHA_PREFIX)
public class KaptchaProperties {

    String DEFAULT_CACHE_DB_NAME = "kaptcha:cache:";
    String DEFAULT_PARAM_KEY_NAME = "key";
    String DEFAULT_PARAM_VALUE_NAME = "value";

    /**
     * spring.kaptcha.timeout = 60000
     * spring.kaptcha.image.width = 200
     * spring.kaptcha.image.height = 50
     * spring.kaptcha.textproducer.charstring = abcde2345678gfynmnpwx
     * spring.kaptcha.textproducer.charlength = 5
     * spring.kaptcha.textproducer.charspace = 2
     * spring.kaptcha.textproducer.fontnames = Courier
     * spring.kaptcha.textproducer.fontcolor = BLACK
     * spring.kaptcha.textproducer.fontsize = 40
     */

    static final String KAPTCHA_PREFIX = "spring.joyuan.kaptcha";

    /**
     *
     * 存储到缓存中系统中的缓存库名称
     */
    private String cacheName = DEFAULT_CACHE_DB_NAME;
    /**
     *
     * 请求时候带的参数key
     */
    private String paramKeyName = DEFAULT_PARAM_KEY_NAME;

    /**
     * 请求时候带的参数value名称
     */
    private String paramValueName = DEFAULT_PARAM_VALUE_NAME;
    private boolean enabled = false;


    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public String getParamKeyName() {
        return paramKeyName;
    }

    public void setParamKeyName(String paramKeyName) {
        this.paramKeyName = paramKeyName;
    }

    public String getParamValueName() {
        return paramValueName;
    }

    public void setParamValueName(String paramValueName) {
        this.paramValueName = paramValueName;
    }

    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
