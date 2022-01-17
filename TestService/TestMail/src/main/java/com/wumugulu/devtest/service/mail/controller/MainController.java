package com.wumugulu.devtest.service.mail.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.RandomUtil;
import com.wumugulu.devtest.basedata.MailNotify;
import com.wumugulu.devtest.basedata.User;
import com.wumugulu.devtest.common.utils.DictUtil;
import com.wumugulu.devtest.service.mail.service.MailNotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/mail")
public class MainController {

    @Autowired
    private MailNotifyService mailNotifyService;

    @PostMapping("/send")
    public Dict send(@RequestBody User user) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String randomString = RandomUtil.randomString(10);
        System.err.println("/mail/send/" + randomString + " ... " + user.toString());
//        return DictUtil.success(randomString);
        return DictUtil.success(mailNotifyService.send(user));
    }

    @RequestMapping("/list")
    public Dict list() {
        List<MailNotify> list = mailNotifyService.list();
        return DictUtil.success(list);
    }

    @RequestMapping("/query")
    public Dict query(String keyword) {
        List<MailNotify> list = mailNotifyService.myQuery(keyword);
        return DictUtil.success(list);
    }

}
