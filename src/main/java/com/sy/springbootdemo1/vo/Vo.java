package com.sy.springbootdemo1.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @Description:
 * @Author: 刘炜程
 * @Date: 2019-02-23 15:05
 */
@SuppressWarnings("all")
public class Vo {
    private int id;
    private String recordName;
    private String studentName;
    private String createBy;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date createDate;

    public String getRecordName() {
        return recordName;
    }

    public void setRecordName(String recordName) {
        this.recordName = recordName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
