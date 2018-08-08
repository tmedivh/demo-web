package com.api.controller;

import com.api.bean.ResponseVO;
import com.api.util.JWTUtil;
import com.base.Bean.UserVO;
import com.base.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/*
 * Copyright (C), 2011-2018 温州贷
 * Author: miaoyusong
 * Date:  2018/8/8 上午12:33
 * Description:
 */
@RestController
@RequestMapping(value = "/user")
public class LoginController {

    private UserService userService;

    @Autowired
    public void setService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseVO login(@RequestParam("username") String username,
                            @RequestParam("password") String password) {
        UserVO userVO = userService.getUserByName(username);
        if (userVO.getPass().equals(password)) {
            return ResponseVO.response().setMsg("login success").setData(JWTUtil.sign(username, password)).build();
        } else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/article")
    public ResponseVO article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return ResponseVO.response().setMsg("You are already logged in").build();
        } else {
            return ResponseVO.response().setMsg("You are guest").build();
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseVO requireAuth() {
        return ResponseVO.response().setMsg("You are authenticated").build();
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseVO requireRole() {
        return ResponseVO.response().setMsg("You are visiting require_role").build();
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseVO requirePermission() {
        return ResponseVO.response().setMsg("You are visiting permission require edit,view").build();
    }
}
