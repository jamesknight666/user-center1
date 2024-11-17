package com.sennhei.usercenter1.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sennhei.usercenter1.common.BaseResponse;
import com.sennhei.usercenter1.common.ErrorCode;
import com.sennhei.usercenter1.common.ResultUtils;
import com.sennhei.usercenter1.exception.BusinessException;
import com.sennhei.usercenter1.model.User;
import com.sennhei.usercenter1.model.request.UserLoginRequest;
import com.sennhei.usercenter1.model.request.UserRegisterRequest;
import com.sennhei.usercenter1.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sennhei.usercenter1.constant.UserConstant.ADMIN_ROLE;
import static com.sennhei.usercenter1.constant.UserConstant.USER_LOGIN_STATE;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if(userRegisterRequest==null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        String planetCode=userRegisterRequest.getPlateCode();
        if(StringUtils.isAnyBlank(userAccount,userPassword,checkPassword,planetCode)){
            return null;
        }
        long result=userService.userRegister(userAccount,userPassword,checkPassword,planetCode);
        return ResultUtils.success(result);
    }

    @PostMapping("/logout")
    public BaseResponse<Integer>  userLogout (HttpServletRequest request) {
        if(request==null){
            return null;
        }
        int result=userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @PostMapping("/login")
    public BaseResponse<User>  userLogin (@RequestBody UserLoginRequest userLoginRequest, HttpServletRequest request) {
        if(userLoginRequest==null){
            return null;
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        if(StringUtils.isAnyBlank(userAccount,userPassword)){
            return null;
        }
        User user=userService.userLogin(userAccount,userPassword,request);
        return ResultUtils.success(user);
    }

    @GetMapping("/current")
    public BaseResponse<User> getCurrentUser(HttpServletRequest request) {
        Object userObj=request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser=(User) userObj;
        if(currentUser==null){
            return null;
        }
        long userId=currentUser.getId();
        //todo 校验用户是否合法
        User user=userService.getById(userId);
        user=userService.getSafetyUser(user);
        return ResultUtils.success(user);

    }

    @GetMapping("/search")
    public BaseResponse<List<User>> userSearch(String username,HttpServletRequest request) {
        if(!isAdmin(request)){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if(StringUtils.isNotBlank(username)){
            queryWrapper.like("username",username);
        }
        List<User> userList = userService.list(queryWrapper);
        List<User> list=userList.stream().map(user -> userService.getSafetyUser(user)).collect(Collectors.toList());
        return ResultUtils.success(list);

    }

    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteUser(@RequestBody long id,HttpServletRequest request) {
        if(!isAdmin(request)){
            return null;
        }
        if(id<=0){
            return null;
        }
        boolean result=userService.removeById(id);
        return ResultUtils.success(result);
    }

    /**
     * 是否是管理员
     * @param request
     * @return
     */
    private boolean isAdmin(HttpServletRequest request) {
        // 仅管理员可查询
        Object userObj=request.getSession().getAttribute(USER_LOGIN_STATE);
        User user=(User)userObj;
        return user!=null && user.getUserRole()==ADMIN_ROLE;
    }
}
