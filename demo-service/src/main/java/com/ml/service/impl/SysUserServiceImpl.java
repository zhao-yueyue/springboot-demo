package com.ml.service.impl;

import com.ml.po.SysUser;
import com.ml.mapper.SysUserMapper;
import com.ml.service.SysUserService;
import com.ml.vo.SysUserVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.Objects;

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
    public SysUser findSysUserByName(SysUserVO userVO) {
        SysUser sysUser = sysUserMapper.findSysUserByName(userVO.getLoginAccount());
        if(Objects.isNull(sysUser)){
            throw new NullPointerException("用户不存在");
        }
        if(Objects.equals(sysUser.getPassword(),userVO.getPassword())){
            throw new NullPointerException("账号或密码错误");
        }
        return sysUser;
    }

}
