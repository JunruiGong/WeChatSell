package com.WeChatSell.sell.handler;

import com.WeChatSell.sell.VO.ResultVO;
import com.WeChatSell.sell.config.ProjectUrlConfig;
import com.WeChatSell.sell.enums.ResultEnum;
import com.WeChatSell.sell.exception.SellException;
import com.WeChatSell.sell.exception.SellerAuthorizeException;
import com.WeChatSell.sell.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * 捕获异常
 */
@ControllerAdvice
public class SellExceptionHandler {

    @Autowired
    private ProjectUrlConfig projectUrlConfig;

    // 拦截登陆异常
    @ExceptionHandler(value = SellerAuthorizeException.class)
    public ModelAndView handlerAuthorizeException() {
        return new ModelAndView("redirect: "
                .concat(projectUrlConfig.getWechatOpenAuthorize())
                .concat("/sell/wechat/rqAuthorize")
                .concat("?returnUrl=")
                .concat(projectUrlConfig.getSell())
                .concat("/sell/seller/login"));

    }

    @ExceptionHandler(value = SellException.class)
    @ResponseBody
    public ResultVO handlerSellerException(SellException e){

        return ResultVOUtil.error(e.getCode(), e.getMessage());

    }
}
