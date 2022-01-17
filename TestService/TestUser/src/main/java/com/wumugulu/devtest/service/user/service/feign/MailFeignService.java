package com.wumugulu.devtest.service.user.service.feign;

import cn.hutool.core.lang.Dict;
import com.wumugulu.devtest.basedata.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
@FeignClient(value = "module-service-mail", path = "/mail")
public interface MailFeignService {

    @RequestMapping("/list")
    public Dict list();

    @PostMapping("/send")
    public Dict send(@RequestBody User user);

}
