package com.ml.web.controller;

import com.ml.po.SysUser;
import com.ml.service.SysUserService;
import com.ml.vo.SysUserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("login")
public class LoginController {

    private final Logger logger = LogManager.getLogger(LoginController.class);

    @Resource
    private SysUserService sysUserService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/doLogin")
    public SysUser login(@RequestBody @Valid SysUserVO userVO){
        logger.info("doLogin -> 登录{}",userVO.toString());
        logger.error("doLogin -> 用户名{}",userVO.getName());
        logger.warn("doLogin -> 登录名{}",userVO.getLoginAccount());
        SysUser user = sysUserService.findMasterSysUserByName(userVO);
//        user.getCreateDate();
        if(Objects.isNull(user)){
            throw new RuntimeException("异常信息!");
        }
        return user;
    }

    @GetMapping("/rabbit")
    public String sendRabbit() {
        sysUserService.sendRabbit();
        return "ok";
    }

    @GetMapping("/all1")
    public SysUser all1() {
        SysUserVO sysUserVO = new SysUserVO();
        sysUserVO.setLoginAccount("tiaojie");
        return sysUserService.findMasterSysUserByName(sysUserVO);
    }

    @GetMapping("/all2")
    public SysUser all2() {
        SysUserVO sysUserVO = new SysUserVO();
        sysUserVO.setLoginAccount("admin");
        return sysUserService.findSecondSysUserByName(sysUserVO);
    }

    @GetMapping("/add1")
    public int add1() {
        SysUser sysUser = new SysUser();
        sysUser.setLoginAccount("add1");
        return sysUserService.addMasterSysUser(sysUser);
    }

    @GetMapping("/add2")
    public int add2() {
        SysUser sysUser = new SysUser();
        sysUser.setLoginAccount("add2");
        return sysUserService.addSecondSysUser(sysUser);
    }

    /**
     * 退出系统
     * @param session
     *   Session
     */
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) {
        //清除Session
        session.invalidate();
        return "login";
    }
    @RequestMapping("404")
    public String page404() {
        return "404";
    }

    @RequestMapping("500")
    public String page500() {
        return "500";
    }
}
