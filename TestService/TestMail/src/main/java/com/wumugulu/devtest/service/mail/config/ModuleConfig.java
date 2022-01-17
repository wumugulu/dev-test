package com.wumugulu.devtest.service.mail.config;

import cn.hutool.extra.mail.MailAccount;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ModuleConfig {

    @Value("${myMail.host}")
    private String host;

    @Value("${myMail.port}")
    private Integer port;

    @Value("${myMail.auth}")
    private Boolean auth;

    @Value("${myMail.from}")
    private String from;

    @Value("${myMail.user}")
    private String user;

    @Value("${myMail.pass}")
    private String pass;

    @Bean
    public MailAccount getMailAccount() {
        MailAccount mailAccount = new MailAccount();
        mailAccount.setHost(host);
        mailAccount.setPort(port);
        mailAccount.setAuth(auth);
        mailAccount.setFrom(from);
        mailAccount.setUser(user);
        mailAccount.setPass(pass);
        return mailAccount;
    }
}
