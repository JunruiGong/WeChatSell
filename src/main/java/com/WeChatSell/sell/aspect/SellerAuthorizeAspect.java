package com.WeChatSell.sell.aspect;

import com.WeChatSell.sell.constant.CookieConstant;
import com.WeChatSell.sell.constant.RedisConstant;
import com.WeChatSell.sell.exception.SellerAuthorizeException;
import com.WeChatSell.sell.util.CookieUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
@Slf4j
public class SellerAuthorizeAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(public * com.WeChatSell.sell.controller.Sell*.*(..))" +
            "&& !execution(public * com.WeChatSell.sell.controller.SellerUserController.*(..))")
    public void verify() {
    }

    @Before("verify()")
    public void doVerify() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 查询cookie
        Cookie cookie = CookieUtil.get(request, CookieConstant.TOKEN);

        if (cookie == null) {
            log.warn("【登陆校验】Cookie中查询不到token");
            throw new SellerAuthorizeException();
        }

        // 去redis中查找
        String tokenValue = redisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_PREFIX, cookie.getValue()));
        if (StringUtils.isEmpty(tokenValue)) {
            log.warn("【登陆校验】Redis中查询不到该token");
            throw new SellerAuthorizeException();
        }

    }
}
