package com.ml.po;

import lombok.Data;

import java.util.Date;

@Data
public class SysUser {

    private Integer id;

    private String name;

    private String password;

    private Date createDate;

    private String loginAccount;

    private String delFlag;

}
