# Kaptcha Spring boot starter

Very simple way to use [Kaptcha](http://code.google.com/p/kaptcha/).and 
you can use session and redis to store captcha information.

## 1. 导入依赖

```xml
 <dependency>
            <groupId>com.joyuan.core</groupId>
            <artifactId>kaptcha-spring-boot-starter</artifactId>
            <version>1.0.0.RELEASE</version>
 </dependency>
```


### 2. 基本配置

*application.properties*

## application.yml
spring.joyuan.kaptcha.enabled=true
spring.joyuan.kaptcha.cache-name=kaptcha
spring.joyuan.kaptcha.param-key-name=kaptcha_id
spring.joyuan.kaptcha.param-value-name=kaptcha_value


```yml
 spring:  
    joyuan:
        kaptcha:
            enabled: true # 是否启用
            cache-name: kaptcha # 缓存db名称
            param-key-name: #http 缓存用到的key
            param-value-name: #http 获取验证码的key
            properties:
              kaptcha.border: yes
              kaptcha.border.color: black
              kaptcha.border.thickness: 1
              kaptcha.image.width: 200
              kaptcha.image.height: 50
              kaptcha.producer.impl: com.google.code.kaptcha.impl.DefaultKaptcha
              kaptcha.textproducer.impl: com.google.code.kaptcha.text.impl.DefaultTextCreator
              kaptcha.textproducer.char.string: abcde2345678gfynmnpwx
              kaptcha.textproducer.char.length: 5
              kaptcha.textproducer.font.names: 	Arial, Courier
              kaptcha.textproducer.font.size: 40px
              kaptcha.textproducer.font.color: black
              kaptcha.textproducer.char.space: 2
              kaptcha.noise.impl: 	com.google.code.kaptcha.impl.DefaultNoise
              kaptcha.noise.color: black
              kaptcha.obscurificator.impl: com.google.code.kaptcha.impl.WaterRipple
              kaptcha.background.impl: com.google.code.kaptcha.impl.DefaultBackground
              kaptcha.background.clear.from: light grey
              kaptcha.background.clear.to: 	white
              kaptcha.word.impl: com.google.code.kaptcha.text.impl.DefaultWordRenderer
```


## 3. 在Controller中使用

```java
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
```