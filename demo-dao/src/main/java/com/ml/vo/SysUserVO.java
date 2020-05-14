package com.ml.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class SysUserVO {

    @NotNull(message = "用户id不能为空")
    private Integer id;

    @NotNull(message = "用户名不能为空")
    @Size(min = 2, max = 11, message = "账号长度必须是4-11个字符")
    private String name;

    @NotNull(message = "登录密码不能为空")
    @Size(min = 6, max = 16, message = "密码长度必须是6-16个字符")
    private String password;

    private Date createDate;

    @NotNull(message = "登录账号不能为空")
    @Size(min = 6, max = 16, message = "账号长度必须是6-16个字符")
    private String loginAccount;

    private String delFlag;

}
