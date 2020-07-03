package com.ml.service;

import com.ml.po.SysUser;
import com.ml.vo.SysUserVO;

public interface SysUserService {

    /**根据接口查询第一数据源的用户*/
    SysUser findMasterSysUserByName(SysUserVO userVO);
    /**根据接口查询第二数据源的用户*/
    SysUser findSecondSysUserByName(SysUserVO userVO);
    /**第一数据源添加用户*/
    int addMasterSysUser(SysUser user);
    /**第二数据源添加用户*/
    int addSecondSysUser(SysUser user);
    /**测试rabbit*/
    void sendRabbit();

}
