package com.calvin.mixmall.user.controller;


import cn.hutool.core.bean.BeanUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.calvin.mixmall.user.interceptor.UserSession;
import com.calvin.mixmall.support.ResultDTO;
import com.calvin.mixmall.support.ResultEnum;
import com.calvin.mixmall.user.entity.User;
import com.calvin.mixmall.user.service.IUserService;
import com.calvin.mixmall.user.vo.LoginReqVO;
import com.calvin.mixmall.user.vo.RegisterReqVO;
import com.calvin.mixmall.user.vo.UserVO;
import com.calvin.mixmall.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author guowei
 * @version 0.0.1
 * @date 2020-06-06
 */
@RestController
@Api(tags = "用户controller")
@RequestMapping("/api/user")
@Slf4j
@CrossOrigin
public class UserController {
    @Resource
    private IUserService userService;

    @Autowired
    private UserCache userCache;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultDTO login(@Valid @RequestBody LoginReqVO loginVo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getPhone, loginVo.getPhone())
        .eq(User::getPassword, DigestUtil.md5Hex(loginVo.getPassword()));
        List list = userService.list(queryWrapper);
        if (list.isEmpty()) {
            log.error("用户不存在,手机号:{}", loginVo.getPhone());
            return ResultDTO.failure(ResultEnum.USERNAME_ERROR);
        }
        User user = (User) list.get(0);

        String token = CommonUtil.getToken();
        userCache.updateLoginTokenInfo(user, loginVo.getPhone(), token);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("token", token);
        return ResultDTO.success(jsonObject);
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public ResultDTO register(@RequestBody RegisterReqVO reqVO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper();
        queryWrapper.lambda().eq(User::getPhone, reqVO.getPhone());
        List<User> list = userService.list(queryWrapper);
        if(!list.isEmpty()){
            return ResultDTO.failure(ResultEnum.PHONE_NOT_EXIST);
        }
        User user = User.builder().phone(reqVO.getPhone()).password(reqVO.getPassword())
                .nickname(reqVO.getNickname()).gmtCreate(LocalDateTime.now()).build();
        boolean flag = userService.save(user);
        if(!flag){
            return ResultDTO.failure(ResultEnum.DATABASE_ERROR);
        }
        return ResultDTO.success();
    }

    @ApiOperation("根据token查询")
    @GetMapping("/info")
    public ResultDTO<UserVO> getByToken() {
        User user = userService.getById(UserSession.getUserId());
        UserVO userVO = new UserVO();
        BeanUtil.copyProperties(user, userVO);
        return ResultDTO.success(userVO);
    }

    @ApiOperation("根据id查询")
    @GetMapping("/selectById/{id}")
    public ResultDTO<User> selectById(
            @ApiParam("ID") @PathVariable("id") Long id) {
        return ResultDTO.success(userService.selectById(id));
    }

}
