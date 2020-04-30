package com.ml.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.ml.entity.SysUser;
import com.ml.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private SysUserService sysUserService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/doLogin")
    @ResponseBody
    public JSONObject login(HttpServletRequest request, String loginAccount){
        JSONObject jsonObject = new JSONObject();
        logger.info("doLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogindoLogin -> 登录{}",loginAccount);
        if(null!=loginAccount){
            SysUser user = sysUserService.findSysUserByName(loginAccount);
            if(user!=null){
                logger.error("doLogin -> msg{}","登录成功");
                jsonObject.put("status","0");
                jsonObject.put("msg","登录成功！");
                jsonObject.put("SysUser",user.toString());
                return jsonObject;
            }else {
                jsonObject.put("status","1");
                jsonObject.put("msg","账号或密码不正确，请重新登录！");
                return jsonObject;
            }
        }else{
            logger.error("doLogin -> 参数为空");
            jsonObject.put("status","1");
            jsonObject.put("msg","请填写正确信息！");
            return jsonObject;
        }
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
