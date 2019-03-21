package com.sy.springbootdemo1.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.*;

public class Student {
    @Id
    private Integer id;

    private String code;

    private String name;

    /**
     * 专业 1.计算机 2.电器 3.金融
     */
    private Integer major;

    private String grade;

    /**
     * 1.男 2.女
     */
    private Integer sex;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;


    /**
     * 电话
     */
    private String tel;

    /**
     * 职务 1.普通学生 2.组长 3.班长
     */
    private Integer job;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取专业 1.计算机 2.电器 3.金融
     *
     * @return major - 专业 1.计算机 2.电器 3.金融
     */
    public Integer getMajor() {
        return major;
    }

    /**
     * 设置专业 1.计算机 2.电器 3.金融
     *
     * @param major 专业 1.计算机 2.电器 3.金融
     */
    public void setMajor(Integer major) {
        this.major = major;
    }

    /**
     * @return grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * 获取1.男 2.女
     *
     * @return sex - 1.男 2.女
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置1.男 2.女
     *
     * @param sex 1.男 2.女
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * @return birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 获取电话
     *
     * @return tel - 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置电话
     *
     * @param tel 电话
     */
    public void setTel(String  tel) {
        this.tel = tel;
    }

    /**
     * 获取职务 1.普通学生 2.组长 3.班长
     *
     * @return job - 职务 1.普通学生 2.组长 3.班长
     */
    public Integer getJob() {
        return job;
    }

    /**
     * 设置职务 1.普通学生 2.组长 3.班长
     *
     * @param job 职务 1.普通学生 2.组长 3.班长
     */
    public void setJob(Integer job) {
        this.job = job;
    }
}