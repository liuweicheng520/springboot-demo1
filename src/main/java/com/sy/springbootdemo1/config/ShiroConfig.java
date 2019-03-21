package com.sy.springbootdemo1.config;


import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.sy.springbootdemo1.filter.KickoutSessionControlFilter;
import com.sy.springbootdemo1.filter.LoginFilter;
import com.sy.springbootdemo1.filter.UserFilter;
import com.sy.springbootdemo1.listener.MySessionListener;
import com.sy.springbootdemo1.realm.MyShiroRealm;
import com.sy.springbootdemo1.shiro.RetryLimitCredentialsMatcher;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.RememberMeManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.SessionListener;
import org.apache.shiro.session.mgt.ExecutorServiceSessionValidationScheduler;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.*;

@Configuration
public class ShiroConfig {

    /**
     * 负责shiroBean的生命周期
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * 这是个自定义的认证类，继承子AuthorizingRealm，负责用户的认证和权限处理
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public MyShiroRealm shiroRealm() {
        MyShiroRealm realm = new MyShiroRealm();
        realm.setCredentialsMatcher(retryLimitCredentialsMatcher());
        //realm.setCredentialsMatcher(hashedCredentialsMatcher());
        return realm;
    }

    @Bean
    public RetryLimitCredentialsMatcher retryLimitCredentialsMatcher(){
        return  new RetryLimitCredentialsMatcher(ehCacheManager());
    }

    /**
     * 安全管理器
     * 将realm加入securityManager
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        //注意是DefaultWebSecurityManager！！！
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(shiroRealm());

        securityManager.setSessionManager(configWebSessionManager());
        securityManager.setRememberMeManager(rememberMeManager());
        System.out.println("登陆！！！！！！！！！");
        return securityManager;
    }

    /**
     * shiro标签方言 整合thymeleaf
     *
     * @return
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }

    /**
     * shiro filter 工厂类
     * 1.定义ShiroFilterFactoryBean
     * 2.设置SecurityManager
     * 3.配置拦截器
     * 4.返回定义ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {

        System.err.println("进入读取！");
        //1
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();


        LinkedHashMap<String, Filter> filtersMap = new LinkedHashMap<>();
        //限制同一帐号同时在线的个数
        filtersMap.put("kickout", kickoutSessionControlFilter());
        filtersMap.put("isLogin", loginFilter());
        filtersMap.put("users", userFilter());
        shiroFilterFactoryBean.setFilters(filtersMap);

        //2
        //注册securityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        System.out.println("11");
        //3
        // 拦截器+配置登录和登录成功之后的url
        //LinkHashMap是有序的，shiro会根据添加的顺序进行拦截
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
        //配置不会被拦截的连接  这里顺序判断
        //anon，所有的url都可以匿名访问
        //authc：所有url都必须认证通过才可以访问
        //user，配置记住我或者认证通过才能访问
        //logout，退出登录
//        filterChainDefinitionMap.put("/JQuery/**","anon");
//        filterChainDefinitionMap.put("/js/**","anon");
        //配置退出过滤器
        filterChainDefinitionMap.put("/login", "isLogin,anon");
        filterChainDefinitionMap.put("/login/**", "kickout,anon");
        filterChainDefinitionMap.put("/center/**", "kickout,user");
        filterChainDefinitionMap.put("/**", "kickout,user,users");
//        filterChainDefinitionMap.put("/lxt","anon");
//        filterChainDefinitionMap.put("/login","authc");
//        filterChainDefinitionMap.put("/success","anon");
//        filterChainDefinitionMap.put("/index","anon");
//        filterChainDefinitionMap.put("/Register","anon");
//        filterChainDefinitionMap.put("/logout","logout");
        //过滤连接自定义，从上往下顺序执行，所以用LinkHashMap /**放在最下边
//        filterChainDefinitionMap.put("/**","authc");
        //设置登录界面，如果不设置为寻找web根目录下的文件
        shiroFilterFactoryBean.setLoginUrl("/login");
        //设置登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/list");
        //设置登录未成功，也可以说无权限界面
//        shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        System.out.println("shiro拦截工厂注入类成功");

        //返回
        return shiroFilterFactoryBean;
    }

    /**
     * 缓存管理器
     *
     * @return cacheManager
     */
    @Bean
    public EhCacheManager ehCacheManager() {
        EhCacheManager cacheManager = new EhCacheManager();
        return cacheManager;
    }

    /**
     * 自定义user拦截器
     * @return
     */
    @Bean
    public UserFilter userFilter(){
        return  new UserFilter();
    }

    @Bean
    public List<SessionListener> mySessionListener() {
        List<SessionListener> listeners = new ArrayList<>();
        listeners.add(new MySessionListener());
        return listeners;
    }

    /**
     * 会话管理器
     *
     * @return sessionManager
     */
    @Bean
    public DefaultWebSessionManager configWebSessionManager() {
        DefaultWebSessionManager manager = new DefaultWebSessionManager();
        // 加入缓存管理器
        manager.setCacheManager(ehCacheManager());
        // 删除过期的session
        manager.setDeleteInvalidSessions(true);

        manager.setSessionListeners(mySessionListener());
        // 设置全局session超时时间
        manager.setGlobalSessionTimeout(30 * 60 * 1000);

//        SimpleCookie cookie = new SimpleCookie();
//        cookie.setValue("sid");
////        cookie.setPath("/");
//
//        manager.setSessionIdCookie(cookie);
        // 是否定时检查session
        manager.setSessionValidationSchedulerEnabled(true);
        manager.setSessionValidationScheduler(configSessionValidationScheduler());
        manager.setSessionIdUrlRewritingEnabled(false);
        manager.setSessionIdCookieEnabled(true);
        return manager;
    }


    /**
     * session会话验证调度器
     *
     * @return session会话验证调度器
     */
    @Bean
    public ExecutorServiceSessionValidationScheduler configSessionValidationScheduler() {
        ExecutorServiceSessionValidationScheduler sessionValidationScheduler = new ExecutorServiceSessionValidationScheduler();
        //设置session的失效扫描间隔，单位为毫秒
        sessionValidationScheduler.setInterval(300 * 1000);
        return sessionValidationScheduler;
    }

    /**
     * 记住我管理器 配置
     * @return
     */
    @Bean
    public RememberMeManager rememberMeManager() {
        CookieRememberMeManager rememberMeManager = new CookieRememberMeManager();
        rememberMeManager.setCipherKey(Base64.decode("自定义秘钥"));
//        System.err.println(rememberMeManager.getCipherKey());

        //自定义Cookie配置
        SimpleCookie rememberMeCookie = new SimpleCookie();
        rememberMeCookie.setName("rememberMeCookie");
        rememberMeCookie.setValue("rememberMe");
        rememberMeCookie.setHttpOnly(true);
        rememberMeCookie.setMaxAge(2592000); //30天

        rememberMeManager.setCookie(rememberMeCookie);

        return  rememberMeManager;
    }

    @Bean
    public FormAuthenticationFilter authenticationFilter(){
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }

    /**
     *
     * @return
     */
    @Bean
    public KickoutSessionControlFilter kickoutSessionControlFilter() {
        KickoutSessionControlFilter kickoutSessionControlFilter = new KickoutSessionControlFilter();
        //用于根据会话ID，获取会话进行踢出操作的；
        kickoutSessionControlFilter.setSessionManager(configWebSessionManager());
        //使用cacheManager获取相应的cache来缓存用户登录的会话；用于保存用户—会话之间的关系的；
        kickoutSessionControlFilter.setCacheManager(ehCacheManager());
        //是否踢出后来登录的，默认是false；即后者登录的用户踢出前者登录的用户；11
        kickoutSessionControlFilter.setKickoutAfter(false);
        //同一个用户最大的会话数，默认1；比如2的意思是同一个用户允许最多同时两个人登录；
        kickoutSessionControlFilter.setMaxSession(1);
        //被踢出后重定向到的地址；
        kickoutSessionControlFilter.setKickoutUrl("/login?error=SomeoneElseLoggedIn");
        return kickoutSessionControlFilter;
    }

    /**
     * 避免重复登录
     *
     * @return
     */
    @Bean
    public LoginFilter loginFilter() {
        LoginFilter loginFilter = new LoginFilter();
        //被踢出后重定向到的地址；
        loginFilter.setKickoutUrl("/list");
        return loginFilter;
    }
}
