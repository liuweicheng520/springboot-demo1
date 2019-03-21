package com.sy.springbootdemo1.controller;

import com.github.pagehelper.PageInfo;
import com.sy.springbootdemo1.pojo.Student;
import com.sy.springbootdemo1.service.StudentService;
import com.sy.springbootdemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description:
 * @Author: 刘炜程
 * @Date: 2019-02-23 14:00
 */
@SuppressWarnings("all")
@Controller
public class StudentContrller {


    @Autowired
    private StudentService service;


    /**
     * 访问list页面
     *
     * @return
     */
    @GetMapping("/list")
    public String listPage() {
        return "list";
    }

    /**
     * 访问list页面-学生列表
     *
     * @return
     */
    @GetMapping("/list/student")
    public String listStudentPage() {
        return "list-student";
    }


    /**
     * 分页查询学生列表
     *
     * @param currentNum
     * @return
     */
    @GetMapping("/list/{currentNum}")
    @ResponseBody
    public PageInfo<Student> querylistStudentPageHelper(@PathVariable int currentNum) {

        return service.queryStudnet(currentNum, 4);
    }
}
