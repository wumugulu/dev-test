package com.wumugulu.devtest.service.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wumugulu.devtest.basedata.User;

import java.util.List;

/**
 *
 */
public interface UserService extends IService<User> {

    public List<User> listByLimit(Integer limit);

    /**
     * 查询用户列表,
     * @param isDeleted 0:有效用户, 1:已删除用户, null:所有用户
     * @return
     */
    List<User> myList(Integer isDeleted);

    /**
     * 锁定或解锁用户(由user.status决定)
     * @param user
     * @return
     */
    Integer myLock(User user);

    /**
     * 删除或恢复用户(由newStatus决定)
     * @param id
     * @param newStatus 0:恢复, 1:删除
     * @return
     */
    Integer myDelete(Integer id, Integer newStatus);

    /**
     * 注册新用户
     * @param user
     * @return
     */
    Integer myReg(User user);

    Integer myDeleteBatch(List<Integer> ids);

    Integer myUpdate(User user);
}
