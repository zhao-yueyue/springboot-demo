package com.ml.service;

import com.ml.entity.SysUser;

import java.util.List;

public interface SysUserService {

    /**根据接口查询所用的用户*/
    SysUser findSysUserByName(String loginAccount);
    /**添加用户*/
    int addUser(SysUser user);

}
