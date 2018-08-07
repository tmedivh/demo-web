package com.base.service;

import com.base.Bean.UserVO;
import com.base.mapper.UserInfoMapper;
import com.base.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/*
 * Copyright (C), 2011-2018 温州贷
 * Author: miaoyusong
 * Date:  2018/8/8 上午12:52
 * Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    /**
     * 根据用户名查询用户
     *
     * @param name
     * @return
     */
    public UserVO getUserByName(String name) {
        Example example = new Example(UserInfo.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("name");
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        if (null == userInfos) {
            throw new RuntimeException("用户不存在");
        }
        UserVO userVO = new UserVO();
        userVO.setName(userInfos.get(0).getName());
        userVO.setPass(userInfos.get(0).getPassword());
        return userVO;
    }
}
