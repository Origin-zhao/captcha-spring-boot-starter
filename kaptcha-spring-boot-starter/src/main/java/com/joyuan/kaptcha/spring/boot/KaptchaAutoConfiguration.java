package com.joyuan.kaptcha.spring.boot;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import com.joyuan.kaptcha.spring.boot.properties.KaptchaProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>@Title: </p>
 * <p>@Description: </p>
 * <p>@Copyright: 294786949@qq.com Inc. All rights reserved.</p>
 *
 * @author: 294786949@qq.com
 * @date: 2019/5/26 18:01
 * @version: v1.0.0
 */

@Configuration
@EnableConfigurationProperties(value = KaptchaProperties.class)
@ConditionalOnProperty(prefix = "spring.joyuan.kaptcha", name = "enabled", matchIfMissing = false)
public class KaptchaAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnClass(CacheManager.class)
    public ICacheKaptcha sessionKaptcha(@Autowired CacheManager manager, KaptchaProperties properties) {
        return new DefaultCacheKaptcha(manager,properties);
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultKaptcha producer(KaptchaProperties kaptchaProperties) {
        Config config = new Config(kaptchaProperties.getProperties());
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(config);
        return defaultKaptcha;
    }

}
