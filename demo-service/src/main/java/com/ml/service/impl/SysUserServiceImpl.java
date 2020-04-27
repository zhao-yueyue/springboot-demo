package com.ml.service.impl;

import com.ml.entity.SysUser;
import com.ml.mapper.SysUserMapper;
import com.ml.service.SysUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    @Transactional
    public int addUser(SysUser user){
        boolean flag = true;
        int result = sysUserMapper.addSysUser(user);
        return result;
    }


    @Override
    public SysUser findSysUserByName(String loginAccount) {
        SysUser sysUser = sysUserMapper.findSysUserByName(loginAccount);
        sysUser.setName("小莉丫");
        return sysUser;
    }

}
