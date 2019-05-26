package com.joyuan.kaptcha.spring.boot;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * <p>@Title: </p>
 * <p>@Description: </p>
 * <p>@Copyright: 294786949@qq.com Inc. All rights reserved.</p>
 *
 * @author: 294786949@qq.com
 * @date: 2019/5/26 18:01
 * @version: v1.0.0
 */
public class KaptchaUtils {


    public static void sendImage(HttpServletResponse resp,BufferedImage bi) throws IOException {
        // Set to expire far in the past.
        resp.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        resp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        resp.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header.
        resp.setHeader("Pragma", "no-cache");
        // return a jpeg
        resp.setContentType("image/jpeg");
        ServletOutputStream out = resp.getOutputStream();
        ImageIO.write(bi, "jpg", out);
    }

}

