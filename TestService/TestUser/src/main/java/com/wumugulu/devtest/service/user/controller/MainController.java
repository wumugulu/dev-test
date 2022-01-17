package com.wumugulu.devtest.service.user.controller;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.StrUtil;
import com.wumugulu.devtest.basedata.User;
import com.wumugulu.devtest.common.utils.DictUtil;
import com.wumugulu.devtest.service.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/user")
public class MainController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public Dict test(HttpServletRequest request) {
        String ipAddress = request.getHeader("x-forwarded-for");
        return DictUtil.success(ipAddress);
    }

    @GetMapping("/list")
    public Dict list(Integer isDeleted) {
        List<User> list = userService.myList(isDeleted);
        return DictUtil.success(list);
    }

    @PostMapping("/lock")
    public Dict lock(@RequestBody User user) {
        if (user.getId() == null)
            return DictUtil.error(10009, "id can not be null");
        if (user.getStatus() == null || (user.getStatus() != 0 && user.getStatus() != 1))
            return DictUtil.error(10010, "status is not valid");

        Integer result = userService.myLock(user);
        return DictUtil.success(result);
    }

    @PostMapping("/delete")
    public Dict delete(Integer id) {
        if (id == null)
            return DictUtil.error(10009, "id can not be null");

        Integer result = userService.myDelete(id, 1);
        return DictUtil.success(result);
    }

    @PostMapping("/recovery")
    public Dict recovery(Integer id) {
        if (id == null)
            return DictUtil.error(10009, "id can not be null");

        Integer result = userService.myDelete(id, 0);
        return DictUtil.success(result);
    }

    @PostMapping("/deleteBatch")
    public Dict deleteBatch(@RequestParam(value = "ids") Integer[] ids) {
        if (ids == null || ids.length == 0)
            return DictUtil.error(10009, "ids can not be null or empty");

        Integer result = userService.myDeleteBatch(Arrays.asList(ids));
        return DictUtil.success(result);
    }

    @PostMapping("/update")
    public Dict update(@RequestBody User user) {
        if (user == null || user.getId() == null)
            return DictUtil.error(10009, "user can not be null or empty");

        Integer result = userService.myUpdate(user);
        return DictUtil.success(result);
    }

    @PostMapping("/reg")
    public Dict reg(@RequestBody User user) {
        if (StrUtil.isBlank(user.getEmail()))
            return DictUtil.error(10005, "email can not be null");
        if (!Validator.isEmail(user.getEmail()))
            return DictUtil.error(10006, "email address is invalid");
        if (StrUtil.isBlank(user.getUsername()))
            return DictUtil.error(10007, "username can not be null");
        if (user.getUsername().length() < 6)
            return DictUtil.error(10008, "username is too short, at least 6 character");

        try {
            Integer result = userService.myReg(user);
            return DictUtil.success(result);
        }catch (Exception e) {
            return DictUtil.error(20011, e.getMessage());
        }
    }


    @RequestMapping("/listByLimit")
    public Dict listByLimit(Integer limit) {
        List<User> list = userService.listByLimit(limit);
        return DictUtil.success(list);
    }

}
