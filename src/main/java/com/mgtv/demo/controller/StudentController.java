package com.mgtv.demo.controller;

import com.mgtv.demo.pojo.document.DemoDocument;
import com.mgtv.demo.pojo.model.master.StudentDO;
import com.mgtv.demo.pojo.response.Result;
import com.mgtv.demo.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhiguang@mgtv.com
 * @date 2019-08-05 18:45
 */
@RestController
@RequestMapping("/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student/list")
    public Result<List<StudentDO>> listStudent() {
        return Result.wrapSuccess(studentService.listStudent());
    }

    @GetMapping("/student/get")
    public Result<StudentDO> getStudent(@RequestParam("id") long id) {
        return Result.wrapSuccess(studentService.getStudent(id));
    }

    @GetMapping("/student/get2")
    public Result<StudentDO> getStudent2(@RequestParam("id") long id) {
        return Result.wrapSuccess(studentService.getStudentLocal(id));
    }

    @GetMapping("/redis/list")
    public Result<String> testRdisList() {
        return Result.wrapSuccess(studentService.testRdisList());
    }

    @PostMapping("/mq/sendMessage")
    public Result<Void> sendMessage(@RequestParam("message") String message) {
        studentService.sendMessage(StringUtils.trimToEmpty(message));
        return Result.wrapSuccess();
    }

    @GetMapping("/doc/get")
    public Result<DemoDocument> getDoc(@RequestParam("id") String id) {
        return Result.wrapSuccess(studentService.getDoc(id));
    }

    @PostMapping("/doc/save")
    public Result<Void> saveDoc(@RequestParam("title") String title) {
        DemoDocument doc = new DemoDocument();
        doc.setTitle(title);
        studentService.saveDoc(doc);
        return Result.wrapSuccess();
    }
}
