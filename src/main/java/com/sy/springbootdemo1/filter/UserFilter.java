package com.sy.springbootdemo1.filter;

import com.alibaba.fastjson.JSON;
import com.sy.springbootdemo1.service.UserService;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @Description: 重写shiro的 user 已认证或记住我
 * @Author: 刘炜程
 * @Date: 2019-02-25 11:15
 */
@SuppressWarnings("all")
public class UserFilter extends AccessControlFilter {

    @Autowired
    private UserService service;

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {

        System.out.println("进入拦截器User");
        Subject subject = this.getSubject(request, response);
        if (subject.getPrincipal() != null) {
            subject.getSession().setAttribute("user", service.findOnByName(String.valueOf(subject.getPrincipal())));
        }
        System.out.println(subject.getPrincipal());
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        this.saveRequestAndRedirectToLogin(request, response);
        return false;
    }
}
