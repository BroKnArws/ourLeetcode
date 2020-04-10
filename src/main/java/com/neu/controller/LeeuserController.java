package com.neu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neu.entity.Leeuser;
import com.neu.entity.vo.RegisterVo;
import com.neu.service.LeeuserService;
import com.neu.utils.JwtUtils;
import com.neu.utils.R;
import com.neu.utils.RandomUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 胡若琛
 * @since 2020-04-09
 */
@RestController
@RequestMapping("/leeuser")
@CrossOrigin
public class LeeuserController {

    @Autowired
    private LeeuserService userService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;



    @GetMapping("send/{phone}")
    public R sendMsm(@PathVariable("phone")String phone){
        String code = redisTemplate.opsForValue().get(phone);
        if(!StringUtils.isEmpty(code)){
            return R.ok();
        }
        code = RandomUtil.getFourBitRandom();
        Map<String,Object> param=new HashMap<>();
        param.put("code",code);
        Boolean isSend=userService.send(param,phone);

        if(isSend){
            redisTemplate.opsForValue().set(phone,code,5, TimeUnit.MINUTES);
            return R.ok();
        }else{
            return R.error().message("短信发送失败");
        }

    }


    @PostMapping("verifymsm")
    public R verifymsm(String phone,String code){

        if(StringUtils.isEmpty(phone) ||StringUtils.isEmpty(code)){
            return R.error().message("手机号和验证码都不能为空");
        }
        String redisCode= redisTemplate.opsForValue().get(phone);
        if(!code.equals(redisCode)){
            return R.error().message("验证码错误");
        }

        QueryWrapper<Leeuser> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",phone);
        Leeuser one = userService.getOne(wrapper);
        if(one!=null){
            String jwtToken = JwtUtils.getJwtToken(one.getId(), one.getNickname());
            return R.ok().data("token",jwtToken);
        }else{
            return R.ok().message("register");
        }

    }



    @PostMapping("register")
    public R register(@RequestBody RegisterVo registerVo){
        String token = userService.register(registerVo);
        return R.ok().data("token",token);
    }



    @PostMapping("uploadAvatar")
    public R uploadAvatar(MultipartFile file,HttpServletRequest request){
        if(file==null){
            return R.error().message("图片不能为空");
        }
        String url=userService.uploadFileAvatar(file);
        String id = JwtUtils.getMemberIdByJwtToken(request);
        System.out.println(id);
        if(StringUtils.isEmpty(id)){
            return R.error().message("未获取到id");
        }
        Leeuser byId = userService.getById("1248086129877356546");
        System.out.println(byId);
        byId.setAvatar(url);
        boolean save = userService.saveOrUpdate(byId);
        if(save){
            return R.ok().data("avatar",url);
        }else{
            return R.error().message("保存头像失败");
        }

    }



    @ApiOperation("根据token获取用户信息")
    @GetMapping("getMemberInfo")
    public R getMemberInfo(HttpServletRequest request) {
        String memberId = JwtUtils.getMemberIdByJwtToken(request);
        Leeuser member = userService.getById(memberId);
        return R.ok().data("userInfo",member);
    }
    
    
    
    @ApiOperation("根据手机号密码登录")
    @GetMapping("login")
    public R login(String phone,String password) {

        if(StringUtils.isEmpty(phone) ||StringUtils.isEmpty(password)){
            return R.error().message("手机号和密码都不能为空");
        }

        QueryWrapper<Leeuser> wrapper = new QueryWrapper<>();
        wrapper.eq("mobile",phone);
        Leeuser one = userService.getOne(wrapper);
        if(one==null){
            return R.error().message("无此用户");
        }
        if(!MD5.encrypt(password).equals(one.getPassword())){
            return R.error().message("密码错误");
        }

        String jwtToken = JwtUtils.getJwtToken(one.getId(), one.getNickname());
        return R.ok().data("token",jwtToken);

    }




}

