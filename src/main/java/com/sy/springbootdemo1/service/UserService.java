package com.sy.springbootdemo1.service;

import ch.qos.logback.classic.spi.STEUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.springbootdemo1.dao.RecordMapper;
import com.sy.springbootdemo1.dao.StudentMapper;
import com.sy.springbootdemo1.dao.UsersMapper;
import com.sy.springbootdemo1.pojo.Record;
import com.sy.springbootdemo1.pojo.Student;
import com.sy.springbootdemo1.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @Description:
 * @Author: 刘炜程
 * @Date: 2019-02-22 20:27
 */
@SuppressWarnings("all")
@Service
public class UserService {


    @Autowired
    private UsersMapper usersMapper;
    /**
     *
     *
     * 登录
     *
     * @param users
     * @return 返回登录后的对象
     */
    public Users login(Users users) {
        return (Users) usersMapper.selectOne(users);
    }

    /**
     * 查询用户名是否存在
     * @param users
     * @return
     */
    public Users findOnByName(String account){
        Users users =new Users();
        users.setAccount(account);
        return usersMapper.selectOne(users);
    }
}
