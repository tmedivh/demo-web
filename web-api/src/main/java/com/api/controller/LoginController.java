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
import org.springframework.http.HttpStatus;
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
            return new ResponseVO(200, "Login success", JWTUtil.sign(username, password));
        } else {
            throw new UnauthorizedException();
        }
    }

    @GetMapping("/article")
    public ResponseVO article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseVO(200, "You are already logged in", null);
        } else {
            return new ResponseVO(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public ResponseVO requireAuth() {
        return new ResponseVO(200, "You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseVO requireRole() {
        return new ResponseVO(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseVO requirePermission() {
        return new ResponseVO(200, "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseVO unauthorized() {
        return new ResponseVO(401, "Unauthorized", null);
    }
}
