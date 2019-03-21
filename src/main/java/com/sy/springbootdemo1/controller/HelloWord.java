package com.sy.springbootdemo1.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sy.springbootdemo1.pojo.Record;
import com.sy.springbootdemo1.pojo.Student;
import com.sy.springbootdemo1.pojo.Users;
import com.sy.springbootdemo1.service.TestDataService;
import com.sy.springbootdemo1.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description:
 * @Author: 刘炜程
 * @Date: 2019-02-22 17:00
 */
@SuppressWarnings("all")
@Controller
public class HelloWord {

    @Autowired
    private TestDataService service;

    RedisTemplate redisTemplate = new RedisTemplate();

    @GetMapping("/login")
    public String helloWord(Model model, String error) {

        System.err.println(JSON.toJSONString(SecurityUtils.getSubject().getPrincipal()));

        String msg = null;
        if(error !=null){
            if(error.equals("UnknownAccountException")){
                msg = "账号或密码错误";
            }else if(error.equals("IncorrectCredentialsException")){
                msg = "账号或密码错误";
            }else if(error.equals("SomeoneElseLoggedIn")){
                msg = "您的账号已在其他地方登陆";
            }
            model.addAttribute("msg", msg);
        }
        return "login";
    }


    @GetMapping("/testData")
    @ResponseBody
    public String addData() {
        service.testAddUserData();
        return "新增一条数据";
    }


    @GetMapping("/testData/student")
    @ResponseBody
    public String addDataStudent() {
        return "新增"+ service.addTestDataStudnet()+"条数据";
    }


}
