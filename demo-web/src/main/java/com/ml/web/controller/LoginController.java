package com.ml.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ml.po.SysUser;
import com.ml.service.SysUserService;
import com.ml.vo.SysUserVO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("login")
public class LoginController {

    private final Logger logger = LogManager.getLogger(LoginController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/doLogin")
    public SysUser login(HttpServletRequest request, @RequestBody @Valid SysUserVO userVO){
        JSONObject jsonObject = new JSONObject();
        logger.info("doLogin -> 登录{}",userVO.toString());
        logger.error("doLogin -> 用户名{}",userVO.getName());
        logger.warn("doLogin -> 登录名{}",userVO.getLoginAccount());
        SysUser user = sysUserService.findSysUserByName(userVO);
//        user.getCreateDate();
        if(Objects.isNull(user)){
            throw new RuntimeException("异常信息!");
        }
        return user;
    }
    /**
     * 退出系统
     * @param session
     *   Session
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/logout")
    public String logout(HttpSession session) throws Exception{
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
