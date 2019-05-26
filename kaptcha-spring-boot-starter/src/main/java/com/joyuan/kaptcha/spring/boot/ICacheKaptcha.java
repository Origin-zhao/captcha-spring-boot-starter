package com.joyuan.kaptcha.spring.boot;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;

/**
 * <p>@Title: </p>
 * <p>@Description: </p>
 * <p>@Copyright: 294786949@qq.com Inc. All rights reserved.</p>
 *
 * @author: 294786949@qq.com
 * @date: 2019/5/26 18:01
 * @version: v1.0.0
 */
public interface ICacheKaptcha {

    /**
     * 相应一个图片流
     * @return
     */
    Map<String,String> responseImage(HttpServletResponse response)throws IOException;

    /**
     * 创建文本
     * @return
     */
    String createCaptchaText();

    /**
     * 创建图片
     * @return
     */
    BufferedImage createImage(String txt);

    /**
     * 校验验证码是否正确
     * @param key 要校验的键
     * @param value 要校验的值
     * @return
     */
    boolean verification(String key,String value);

    /**
     * 校验验证码是否正确
     * @return
     */
    boolean verification(HttpServletRequest request);

    /**
     * 校验验证码是否正确,校验完成之后做删除操作
     * @param key 要校验的键
     * @param value 要校验的值
     * @return
     */
    boolean verificationAndDel(String key,String value);


}
