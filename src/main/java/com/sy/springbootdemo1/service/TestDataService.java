package com.sy.springbootdemo1.service;

import com.sy.springbootdemo1.dao.StudentMapper;
import com.sy.springbootdemo1.dao.UsersMapper;
import com.sy.springbootdemo1.pojo.Student;
import com.sy.springbootdemo1.pojo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: 刘炜程
 * @Date: 2019-02-23 14:16
 */
@SuppressWarnings("all")
@Service
public class TestDataService {

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private UsersMapper usersMapper;
    /**
     * 新增学生测试数据
     */
    public int addTestDataStudnet() {
        Student student12 = new Student();
        student12.setJob(1);
        student12.setBirthday(new Date());
        student12.setCode("stu1");
        student12.setGrade("java1");
        student12.setMajor(1);
        student12.setTel("15616346818");
        student12.setName("阿里");
        student12.setSex(1);

        Student student11 = new Student();
        student11.setJob(1);
        student11.setBirthday(new Date());
        student11.setCode("stu1");
        student11.setGrade("java1");
        student11.setMajor(1);
        student11.setTel("15616346818");
        student11.setName("阿里");
        student11.setSex(1);

        Student student1 = new Student();
        student1.setJob(1);
        student1.setBirthday(new Date());
        student1.setCode("stu1");
        student1.setGrade("java1");
        student1.setMajor(1);
        student1.setTel("15616346818");
        student1.setName("阿里");
        student1.setSex(1);

        Student student2 = new Student();
        student2.setJob(1);
        student2.setBirthday(new Date());
        student2.setCode("stu1");
        student2.setGrade("java1");
        student2.setMajor(1);
        student2.setTel("15616346818");
        student2.setName("阿里");
        student2.setSex(1);


        Student student3 = new Student();
        student3.setJob(1);
        student3.setBirthday(new Date());
        student3.setCode("stu1");
        student3.setGrade("java1");
        student3.setMajor(1);
        student3.setTel("15616346818");
        student3.setName("阿里");
        student3.setSex(1);


        Student student4 = new Student();
        student4.setJob(1);
        student4.setBirthday(new Date());
        student4.setCode("stu1");
        student4.setGrade("java1");
        student4.setMajor(1);
        student4.setTel("15616346818");
        student4.setName("阿里");
        student4.setSex(1);

        List<Student> studentList = new ArrayList<>();
        studentList.add(student11);
        studentList.add(student12);
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        return studentMapper.insertList(studentList);
    }

    public void testAddUserData() {
        Users users = new Users();

        users.setAccount("李凤娟");
        users.setPwd("123123");
        users.setLastTime(new Date());
        users.setIp("192.168.1.1");

        usersMapper.insert(users);
    }


}
