package com.sy.springbootdemo1.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.*;

public class Record {
    @Id
    private Integer id;

    private String name;

    @Column(name = "stu_code")
    private String stuCode;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "create_by")
    private String createBy;

    /**
     * 描述
     */
    private String des;

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
     * @return stu_code
     */
    public String getStuCode() {
        return stuCode;
    }

    /**
     * @param stuCode
     */
    public void setStuCode(String stuCode) {
        this.stuCode = stuCode;
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * @return create_by
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * 获取描述
     *
     * @return des - 描述
     */
    public String getDes() {
        return des;
    }

    /**
     * 设置描述
     *
     * @param des 描述
     */
    public void setDes(String des) {
        this.des = des;
    }
}