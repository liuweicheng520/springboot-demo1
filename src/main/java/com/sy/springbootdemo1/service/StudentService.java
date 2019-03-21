package com.sy.springbootdemo1.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.springbootdemo1.dao.StudentMapper;
import com.sy.springbootdemo1.pojo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: 刘炜程
 * @Date: 2019-02-23 14:16
 */
@SuppressWarnings("all")
@Service
public class StudentService {


    @Autowired
    private StudentMapper studentMapper;

    /**
     * 查询学生list分页
     *
     * @param num
     * @param size
     * @return
     */
    public PageInfo<Student> queryStudnet(int num, int size) {
        PageHelper.startPage(num, size);
        PageInfo<Student> studentPageInfo = new PageInfo<>(studentMapper.selectAll());
        return studentPageInfo;
    }

    /**
     * 查询一个学生根据学号
     * @param student
     * @return
     */
    public Student queryOnByStuCode(Student student){
        return studentMapper.selectOne(student);
    }
}
