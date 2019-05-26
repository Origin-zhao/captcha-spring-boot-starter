package com.joyuan.web;

import com.joyuan.kaptcha.spring.boot.ICacheKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping(value = "/captcha")
public class KaptchaDemoController {

    @Autowired
    private ICacheKaptcha redisKaptcha;

    @RequestMapping(value = "/get",method = {RequestMethod.POST, RequestMethod.GET})
    public void getCap(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Map<String, String> map = redisKaptcha.responseImage(response);
        System.out.println(map.toString());
    }

    @RequestMapping(value = "/valid",method = {RequestMethod.POST, RequestMethod.GET})
    public boolean isValid(HttpServletRequest request, String captcha) {
        return redisKaptcha.verification(request);
    }

}
