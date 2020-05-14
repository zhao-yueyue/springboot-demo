package com.ml.service;

import com.ml.po.SysUser;
import com.ml.vo.SysUserVO;

public interface SysUserService {

    /**根据接口查询所用的用户*/
    SysUser findSysUserByName(SysUserVO userVO);
    /**添加用户*/
    int addUser(SysUser user);

}
