package com.sy.springbootdemo1.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sy.springbootdemo1.dao.RecordMapper;
import com.sy.springbootdemo1.pojo.Record;
import com.sy.springbootdemo1.pojo.Users;
import com.sy.springbootdemo1.vo.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: 刘炜程
 * @Date: 2019-02-23 14:16
 */
@SuppressWarnings("all")
@Service
public class RecordService {

    @Autowired
    private RecordMapper recordMapper;

    /**
     * 查询奖惩记录
     *
     * @param record
     * @return 返回查询结果
     */
    public List<Record> queryRecordListByStuCode(Record record) {
        return recordMapper.select(record);
    }

    /**
     * 查询Vo分页
     *
     * @param num
     * @param size
     * @param rname
     * @param sname
     * @return
     */
    public PageInfo<Vo> queryVoList(int num, int size, String rname, String sname) {
        PageHelper.startPage(num, size);
        PageInfo<Vo> list = new PageInfo<>(recordMapper.queryReocrdAndStudent(rname, sname));
        return list;
    }

    /**
     * 新增奖惩记录
     *
     * @param record
     * @return
     */
    public int addRecord(Record record) {
        return recordMapper.insert(record);
    }

    /**
     * 修改
     *
     * @param record
     * @return
     */
    public int updRecord(Record record) {
        return recordMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 删除
     *
     * @param record
     * @return
     */
    public int removeRecord(Record record) {
        return recordMapper.delete(record);
    }


    /**
     * 根据id查询一条记录
     *
     * @param record
     * @return
     */
    public Record queryOneById(Record record) {
        return recordMapper.selectOne(record);
    }
}
