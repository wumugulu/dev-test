package com.wumugulu.devtest.service.mail.service.impl;

import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wumugulu.devtest.basedata.MailNotify;
import com.wumugulu.devtest.basedata.User;
import com.wumugulu.devtest.service.mail.service.MailNotifyService;
import com.wumugulu.devtest.service.mail.mapper.MailNotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class MailNotifyServiceImpl extends ServiceImpl<MailNotifyMapper, MailNotify> implements MailNotifyService{

    @Autowired
    private MailAccount mailAccount;

    @Override
    public Integer send(User user) {
        MailNotify mailNotify = new MailNotify();
        mailNotify.setUserId(user.getId());
        mailNotify.setEmail(user.getEmail());
        mailNotify.setSubject("欢迎新用户: " + user.getUsername());
        mailNotify.setContent("<h2>welcome to our website</h2><h4>enjoy~~~</h4>");
        mailNotify.setSendTimes((short) 0);
        mailNotify.setLastSend(new Date());
        mailNotify.setCreateTime(new Date());

        //发送邮件!!!
        Byte sendStatus = null;
        try {
            System.err.println("mailAccount: " + mailAccount.toString());
            String mailResult = MailUtil.send(mailAccount, mailNotify.getEmail(), mailNotify.getSubject(), mailNotify.getContent(), true);
            sendStatus = 8;
            System.err.println("mailResult: " + mailResult);
        } catch (Exception e) {
            e.printStackTrace();
            sendStatus = 9;
        }
        mailNotify.setStatus(sendStatus);
        Integer result = baseMapper.myInsert(mailNotify);

        return result;
    }

    @Override
    public List<MailNotify> myQuery(String keyword) {
        return baseMapper.myQuery(keyword);
    }
}




