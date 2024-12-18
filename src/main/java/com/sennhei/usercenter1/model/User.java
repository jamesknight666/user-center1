package com.sennhei.usercenter1.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

    /**
     * 用户
     * @TableName user
     */
    @TableName(value ="user")
    @Data
    public class User implements Serializable {
        @TableField(exist = false)
        private static final long serialVersionUID = 1L;
        /**
         * id
         */
        @TableId(type = IdType.AUTO)
        private Long id;
        /**
         * 用户昵称
         */
        private String username;
        /**
         * 账号
         */
        private String userAccount;
        /**
         * 用户头像
         */
        private String avatarUrl;
        /**
         * 性别
         */
        private Integer gender;
        /**
         * 密码
         */
        private String userPassword;
        /**
         * 电话
         */
        private String phone;
        /**
         * 邮箱
         */
        private String email;
        /**
         * 状态0-正常
         */
        private Integer userState;
        /**
         * 创建时间
         */
        private Date createTime;
        /**
         *
         */
        private Date updateTime;
        /**
         * 是否删除
         */
        @TableLogic
        private Integer isDelete;
        /**
         * 用户角色 0-普通用户 1-管理员
         */
        private Integer userRole;

        /**
         * 星球编号
         */
        private String planetCode;
    }