package com.ml.service.impl;

import com.ml.datasource.MyDataSource;
import com.ml.mapper.SysUserMapper;
import com.ml.po.SysUser;
import com.ml.service.SysUserService;
import com.ml.vo.SysUserVO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.management.RuntimeErrorException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;
    @Resource
    private RabbitTemplate rabbitTemplate;

    @MyDataSource
    @Transactional
    @Override
    public SysUser findMasterSysUserByName(SysUserVO userVO) {
        return sysUserMapper.findSysUserByName(userVO.getLoginAccount());
    }

    @MyDataSource("second")
    @Transactional
    @Override
    public SysUser findSecondSysUserByName(SysUserVO userVO) {
        return sysUserMapper.findSysUserByName(userVO.getLoginAccount());
    }

    /**
     * 新增数据后报错，没有加@Transactional注解，数据库记录成功，导致事物不能回滚
     */
    @MyDataSource
    @Override
    public int addMasterSysUser(SysUser sysUser) {
        sysUserMapper.addSysUser(sysUser);
        throw new RuntimeErrorException(new Error("master error!!!!!"));
    }

    /**
     * 新增数据后报错，加@Transactional注解，数据库记录失败，事物回滚
     */
    @MyDataSource("second")
    @Transactional
    @Override
    public int addSecondSysUser(SysUser sysUser) {
        sysUserMapper.addSysUser(sysUser);
        throw new RuntimeErrorException(new Error("second error!!!!!"));
    }

    @Override
    public void sendRabbit() {
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
    }

}
