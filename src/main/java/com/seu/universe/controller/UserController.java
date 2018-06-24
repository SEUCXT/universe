package com.seu.universe.controller;

import com.seu.universe.config.ViewObject;
import com.seu.universe.service.RedisService;
import com.seu.universe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/getVertificationCode")
    @ResponseBody
    public ViewObject VertificationCode(@RequestParam("email") String email) {
        ViewObject vo = userService.getVertificationCode(email);
        return vo;
    }

    @RequestMapping("/register")
    @ResponseBody
    public ViewObject register(@RequestParam("email") String email,
                               @RequestParam("nickname") String nickname,
                               @RequestParam("password") String password,
                               @RequestParam("vertificationCode") String vertificationCode) {
        ViewObject vo = new ViewObject();
        String code = redisService.get(email);
        if (StringUtils.isEmpty(code) || !code.equals(vertificationCode)) {
            vo.set(ViewObject.ERROR, 1).set(ViewObject.MESSAGE, "验证码错误！");
            return vo;
        }
        vo = userService.userRegister(email, nickname, password);
        return vo;
    }
}
