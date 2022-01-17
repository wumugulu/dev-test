package com.wumugulu.devtest.service.user.service.impl;

import cn.hutool.core.lang.Dict;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wumugulu.devtest.basedata.User;
import com.wumugulu.devtest.service.user.config.AsyncTool;
import com.wumugulu.devtest.service.user.mapper.UserMapper;
import com.wumugulu.devtest.service.user.service.UserService;
import com.wumugulu.devtest.service.user.service.feign.MailFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AsyncTool asyncTool;

    @Override
    public List<User> listByLimit(Integer limit) {
        return baseMapper.listByLimit(limit);
    }

    @Override
    public List<User> myList(Integer isDeleted) {
        return baseMapper.myList(isDeleted);
    }

    @Override
    public Integer myLock(User user) {
        user.setUpdateTime(new Date());
        return baseMapper.myLock(user);
    }

    @Override
    public Integer myDelete(Integer id, Integer newStatus) {
        return baseMapper.myDelete(id, newStatus);
    }

    @Override
    public Integer myReg(User user) {

        if (baseMapper.myGetCountByUsername(user.getUsername()) > 0)
            throw new RuntimeException("username is allready exist");

        if (baseMapper.myGetCountByEmail(user.getEmail()) > 0)
            throw new RuntimeException("email is allready exist");

        User userSubmit = new User();
        userSubmit.setEmail(user.getEmail());
        userSubmit.setUsername(user.getUsername());
        userSubmit.setPassword("123456");
        userSubmit.setCreateTime(new Date());
        userSubmit.setStatus((byte) 0);
        userSubmit.setIsDeleted((byte) 0);
        userSubmit.setLoginTimes(0);
        Integer result = baseMapper.myInsert(userSubmit);
        System.err.println("after insert: " + result + " --- " + userSubmit.toString());

        // 向新用户发送邮件,考虑到时效的问题,需要采用异步的方式
        asyncTool.sendNotifyMail(userSubmit);
        return userSubmit.getId();
    }

    @Override
    public Integer myDeleteBatch(List<Integer> ids) {
        return baseMapper.myDeleteBatch(ids);
    }


}




