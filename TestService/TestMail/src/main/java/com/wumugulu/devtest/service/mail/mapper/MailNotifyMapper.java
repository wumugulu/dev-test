package com.wumugulu.devtest.service.mail.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wumugulu.devtest.basedata.MailNotify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Entity com.wumugulu.devtest.service.mail.beans.MailNotify
 */
@Mapper
public interface MailNotifyMapper extends BaseMapper<MailNotify> {

    Integer myInsert(MailNotify mailNotify);

    List<MailNotify> myQuery(String keyword);
}




