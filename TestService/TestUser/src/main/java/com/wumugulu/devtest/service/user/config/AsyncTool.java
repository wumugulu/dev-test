package com.wumugulu.devtest.service.user.config;

import cn.hutool.core.lang.Dict;
import com.wumugulu.devtest.basedata.User;
import com.wumugulu.devtest.service.user.service.feign.MailFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class AsyncTool {

    @Autowired
    private MailFeignService mailFeignService;

    /**
     * 使用@Async注解实现异步调用邮件微服务的发送接口,需要使用@EnableAsync启用异步功能
     */
    @Async
    public void sendNotifyMail(User user) {
        System.err.println("feign req: " + user.toString());
        Dict sendResult = mailFeignService.send(user);
        System.err.println("feign result: " + sendResult.toString());
    }}
