package com.sy.springbootdemo1.controller;

import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Mod;
import com.sy.springbootdemo1.pojo.Record;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sy.springbootdemo1.pojo.Student;
import com.sy.springbootdemo1.pojo.Users;
import com.sy.springbootdemo1.service.RecordService;
import com.sy.springbootdemo1.service.StudentService;
import com.sy.springbootdemo1.vo.Vo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @Description:
 * @Author: 刘炜程
 * @Date: 2019-02-23 14:00
 */
@SuppressWarnings("all")
@Controller
public class RecordContrloller {
    @Autowired
    private RecordService service;

    @Autowired
    private StudentService studentService;

    @GetMapping("/list/mg")
    public String managementPage(Model model) {
        model.addAttribute("list", service.queryVoList(1, 4, null, null));
        return "list-management";
    }

    /**
     * 查看奖惩记录
     *
     * @param record 根据stucode
     * @return
     */
    @GetMapping("list/getRecord")
    @ResponseBody
    public List<Record> queryRecordByStuCode(Record record) {
        return service.queryRecordListByStuCode(record);
    }


    /**
     * 查看奖惩列表
     *
     * @param num
     * @param rname 模糊查询 奖惩名字
     * @param sname 模糊查询 学生姓名
     * @return
     */
    @GetMapping("list/getVo")
    @ResponseBody
    public PageInfo<Vo> queryVoList(Integer num, String rname, String sname) {
        return service.queryVoList(num, 4, rname, sname);
    }

    /**
     * 奖惩列表-查询表单提交
     *
     * @param rname
     * @param sname
     * @param model
     * @return
     */
    @PostMapping("list/getVo")
    public String queryVoList(String rname, String sname, Model model) {
        model.addAttribute("rname", rname);
        model.addAttribute("sname", sname);
        model.addAttribute("list", service.queryVoList(1, 4, rname, sname));
        return "list-management";
    }


    /**
     * 新增页面
     *
     * @param code
     * @param model
     * @return
     */
    @GetMapping("list/add")
    public String addPage(Integer code, Model model) {

        model.addAttribute("slist", studentService.queryStudnet(1, 999).getList());
        if (code != null && code != 0) {
            model.addAttribute("code", code);
        }
        return "list-add";
    }

    /**
     * 修改页面
     *
     * @param code
     * @param model
     * @return
     */
    @GetMapping("list/upd")
    public String updPage(Integer code, Record record, Model model) {
        record = service.queryOneById(record);
        model.addAttribute("obj", record);
        Student student = new Student();
        student.setCode(record.getStuCode());
        model.addAttribute("sobj", studentService.queryOnByStuCode(student));
        if (code != null && code != 0) {
            model.addAttribute("code", code);
        }
        return "list-upd";
    }

    /**
     * 新增
     *
     * @param record
     * @param model
     * @return
     */
    @PostMapping("/record/add")
    public String addRecord(Record record, HttpSession session) {
        record.setCreateBy(((Users)session.getAttribute("user")).getAccount());
        record.setCreateDate(new Date());
        int code;
        if (service.addRecord(record) != 0) {
            code = 200;
        } else {
            code = 500;
        }
        return "redirect:/list/add?code=" + code;
    }

    /**
     * 修改
     *
     * @param record
     * @param model
     * @return
     */
    @PostMapping("/record/upd")
    public String updRecord(Record record, Model model) {
        System.out.println(record.getId());
        int code;
        if (service.updRecord(record) != 0) {
            code = 200;
        } else {
            code = 500;
        }
        return "redirect:/list/upd?code=" + code+"&id="+record.getId();
    }

    /**
     * 删除
     *
     * @param record
     * @return
     */
    @DeleteMapping("/record/put")
    @ResponseBody
    public Map<String, Object> deleteRecord(Record record) {
        Map<String, Object> map = new HashMap<>();
        if (service.removeRecord(record) != 0) {
            map.put("code", 200);
        } else {
            map.put("code", 500);
        }
        return map;
    }
}
