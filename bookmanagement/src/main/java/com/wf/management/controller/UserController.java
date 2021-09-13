package com.wf.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wf.management.common.Result;
import com.wf.management.entity.User;
import com.wf.management.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 功能描述
 *
 * @author: wf
 **/
@RestController
@RequestMapping("/usr")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/add")
    public Result<?> ceateUser(@RequestBody User user) {
        int insert = userMapper.insert(user);
        if (insert == 1) {
            return Result.success();
        }
        return Result.error("500", "新增失败");
    }

    @GetMapping("/query")
    public Result<?> queryByPage(@RequestParam(defaultValue = "1") int pageNum,
                                 @RequestParam(defaultValue = "10") int pageSize,
                                 @RequestParam(defaultValue = "") String search) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery();
        if (StringUtils.isNotBlank(search)) {
            queryWrapper.like(User::getNickName, search);
        }
        Page<User> userPage = userMapper.selectPage(new Page<>(pageNum, pageSize), queryWrapper);
        return Result.success(userPage);
    }

    @PutMapping("/edit")
    public Result<?> updateUserMsg(@RequestBody User user) {
        userMapper.updateById(user);
        return Result.success();
    }
}
