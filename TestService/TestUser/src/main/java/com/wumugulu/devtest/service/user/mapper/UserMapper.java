package com.wumugulu.devtest.service.user.mapper;

import com.wumugulu.devtest.basedata.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.wumugulu.devtest.service.user.entity.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    public List<User> listByLimit(Integer limit);

    List<User> myList(Integer isDeleted);

    Integer myLock(User user);

    Integer myDelete(@Param("id") Integer id, @Param("isDeleted") Integer newStatus);

    Integer myGetCountByUsername(String username);

    Integer myGetCountByEmail(String email);

    Integer myInsert(User userSubmit);

    Integer myDeleteBatch(List<Integer> ids);

    Integer myUpdate(User user);
}




