package com.wumugulu.devtest.service.user.config;

import cn.hutool.core.lang.Dict;
import com.wumugulu.devtest.common.utils.DictUtil;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Dict myException(Exception e) {
        return DictUtil.error(99, "myExceptin: " + e.getMessage());
    }
}
