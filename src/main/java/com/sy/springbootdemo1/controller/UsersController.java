package com.sy.springbootdemo1.controller;

import com.github.pagehelper.PageInfo;
import com.sy.springbootdemo1.pojo.Record;
import com.sy.springbootdemo1.pojo.Student;
import com.sy.springbootdemo1.pojo.Users;
import com.sy.springbootdemo1.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: 刘炜程
 * @Date: 2019-02-23 13:57
 */
@SuppressWarnings("all")
@Controller
public class UsersController {

    @Autowired
    private UserService service;


    /**
     * admin登录
     *
     * @param users
     * @param session
     * @return
     */
    @PostMapping("/login/user")
    public String login(Users users,HttpSession session,Boolean rememberMe) {

        RedisTemplate redisTemplate = new RedisTemplate();

        String error = null;

        System.out.println("修改版本123");

        rememberMe = rememberMe == null ? false:true;

        //认证登陆
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(users.getAccount(), users.getPwd());
//        System.err.println(rememberMe);
        usernamePasswordToken.setRememberMe(rememberMe);
        Subject subject =  SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken);
        } catch (UnknownAccountException unknownAccountException) {
            //用户名为空
            error = unknownAccountException.getClass().getSimpleName();
        } catch (IncorrectCredentialsException incorrectCredentialsException) {
            error = incorrectCredentialsException.getClass().getSimpleName();

        } catch (Exception e) {
            error = e.getClass().getName();
        }

        if (subject.isAuthenticated()) {
            session.setAttribute("user",service.login(users));
            return "redirect:/list";
        }else{
            //用户或密码错误
            return "redirect:/login?error=" + error;
        }
    }

    @GetMapping("/exit")
    public String exit(HttpSession session) {
        SecurityUtils.getSubject().logout();
//        session.removeAttribute("user");
        return "redirect:/login";
    }

}
