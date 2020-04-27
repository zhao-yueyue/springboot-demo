package com.ml.mapper;

import com.ml.entity.SysUser;
import org.springframework.data.repository.query.Param;

public interface SysUserMapper {

    SysUser findSysUserByName(@Param("loginAccount") String loginAccount);
    int addSysUser(SysUser SysUser);
    
}
