package com.sennhei.usercenter1.service;

import com.sennhei.usercenter1.model.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author JamesKing
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-11-13 09:54:56
*/
public interface UserService extends IService<User> {

    /**
     * 用户注册
     * @param userAccount 用户账号
     * @param userPassword 用户密码
     * @param checkPassword 校验码
     * @param planetCode 星球编码
     * @return 用户id
     */
    long userRegister(String userAccount,String userPassword,String checkPassword,String planetCode);

    /**
     * 用户登录
     *
     * @param userAccount  用户账号
     * @param userPassword 用户密码
     * @param request
     * @return 返回脱敏后的用户信息
     */
    User userLogin(String userAccount,String userPassword,HttpServletRequest request);

    /**
     * 用户托名
     * @param originalUser
     * @return
     */
    User getSafetyUser(User originalUser);

    /**
     * 用户账户
     * @param request
     * @return
     */
    int userLogout(HttpServletRequest request);
}
