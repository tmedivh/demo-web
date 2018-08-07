package com.base.service;

import com.base.Bean.UserVO;

/*
 * Copyright (C), 2011-2018 温州贷
 * Author: miaoyusong
 * Date:  2018/8/8 上午12:52
 * Description:
 */
public interface UserService {

    /**
     * 根据用户名查询用户
     *
     * @param name
     * @return
     */
    public UserVO getUserByName(String name);
}
