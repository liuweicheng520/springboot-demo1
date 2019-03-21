package com.sy.springbootdemo1.listener;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * @Description: 监听sessionManger
 * @Author: 刘炜程
 * @Date: 2019-02-24 17:14
 */
@SuppressWarnings("all")
public class MySessionListener implements SessionListener {

    @Override
    public void onStart(Session session) {//会话创建时触发
        System.out.println("会话创建：" + session.getId());
    }
    @Override
    public void onExpiration(Session session) {//会话过期时触发
        System.out.println("会话过期：" + session.getId());
    }
    @Override
    public void onStop(Session session) {//退出/会话过期时触发
        System.out.println("会话停止：" + session.getId());
    }
}
