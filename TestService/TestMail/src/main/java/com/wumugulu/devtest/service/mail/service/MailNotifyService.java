package com.wumugulu.devtest.service.mail.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wumugulu.devtest.basedata.MailNotify;
import com.wumugulu.devtest.basedata.User;

import java.util.List;

/**
 *
 */
public interface MailNotifyService extends IService<MailNotify> {

    Integer send(User user);

    List<MailNotify> myQuery(String keyword);
}
