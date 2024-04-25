package com.yja.controller;

import com.yja.pojo.Result;
import com.yja.pojo.User;
import com.yja.service.UserService;
import com.yja.utils.JwtUtil;
import com.yja.utils.Md5Util;
import com.yja.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 注册接口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$")  String username, @Pattern(regexp = "^\\S{5,16}$")  String password){
        // 查询用户
        User user = userService.findByUserName(username);
        if (user==null){
            //注册
            userService.register(username,password);
            return Result.success();
        }else{
            //占用
            return Result.error("用户名已占用");
        }
    }

    /**
     * 登录接口
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$")  String username, @Pattern(regexp = "^\\S{5,16}$") String password){
        // 查询用户
        User user = userService.findByUserName(username);
        if (user==null){
            return Result.error("用户名不存在");
        }
        // 判断密码是否正确
        if (Md5Util.getMD5String(password).equals(user.getPassword())){
            // 登录成功
            Map<String, Object> claims =new HashMap<>();
            claims.put("id",user.getId());
            claims.put("username",user.getUsername());
            String token = JwtUtil.genToken(claims);
            ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
            operations.set(token,token,1, TimeUnit.HOURS);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("/userInfo")
    public Result<User> userInfo(){
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    /**
     * 更新头像
     * @param avatarUrl
     * @return
     */
    @PatchMapping("/updateAvatar")
        public Result updateAvatar(@RequestParam @URL String avatarUrl){
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    /**
     * 更新密码
     * @param params
     * @return
     */
    @PatchMapping("/updatPwd")
    public Result updatPwd(@RequestBody Map<String, String> params,@RequestHeader("Authorization") String token){
        // 校验参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)){
            return Result.error("缺少必要的参数");
        }

        // 校验原密码
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);
        if (!Md5Util.getMD5String(oldPwd).equals(loginUser.getPassword())){
            return Result.error("原密码错误");
        }

        if (!newPwd.equals(rePwd)){
            return Result.error("两次密码不一致");
        }
        // 更新
        userService.updatPwd(newPwd);
        // 删除redis中token
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.getOperations().delete(token);
        return Result.success();
    }
}
