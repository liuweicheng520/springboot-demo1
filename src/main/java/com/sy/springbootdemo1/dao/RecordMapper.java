package com.sy.springbootdemo1.dao;

import com.sy.springbootdemo1.pojo.Record;
import com.sy.springbootdemo1.utils.MyMapper;
import com.sy.springbootdemo1.vo.Vo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecordMapper extends MyMapper<Record> {

    /**
     * 查询奖惩管理
     * @param rname 奖惩
     * @param sname 学生姓名
     */
    public List<Vo> queryReocrdAndStudent(@Param("rname")String rname, @Param("sname")String sname);
}