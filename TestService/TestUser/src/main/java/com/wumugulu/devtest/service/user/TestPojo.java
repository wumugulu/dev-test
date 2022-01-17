package com.wumugulu.devtest.service.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class TestPojo {
    @NotNull
    private Integer id;
    @Email
    private String name;
    private Date cdate;
}
