package com.sy.springbootdemo1.filter;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.DefaultSessionKey;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: shiro 自定义filter 实现 login页面避免重复登录
 * @Author: 刘炜程
 * @Date: 2019-02-24 13:26
 */
@SuppressWarnings("all")
public class LoginFilter extends AccessControlFilter {

    /** 登录后首页的地址 */
    private String homePageUrl;

    public void setKickoutUrl(String homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    /**
     * 是否允许访问，返回true表示允许
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        return false;
    }
    /**
     * 表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）。
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        Subject subject = getSubject(request, response);
        if(subject.isAuthenticated() || subject.isRemembered()) {
            //如果登录了，跳转到首页
            WebUtils.issueRedirect(request,response, homePageUrl);
            return false;
        }
        return true;
    }
}
