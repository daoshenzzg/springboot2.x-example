package com.mgtv.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mgtv.demo.pojo.document.DemoDocument;
import com.mgtv.demo.pojo.model.db1.Student1DO;
import com.mgtv.demo.pojo.model.db2.Student2DO;
import com.mgtv.demo.pojo.response.Result;
import com.mgtv.demo.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author daoshenzzg@163.com
 * @date 2019-08-05 18:45
 */
@RestController
@RequestMapping("/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/student/add")
    public Result<Student1DO> addStudent(@RequestParam("studName") String studName,
                                         @RequestParam("studAge") Integer studAge,
                                         @RequestParam("studSex") String studSex) {
        Student1DO student = new Student1DO();
        student.setStudName(studName);
        student.setStudAge(studAge);
        student.setStudSex(studSex);
        student = studentService.addStudent(student);
        return Result.wrapSuccess(student);
    }

    @PostMapping("/student/txSave")
    public Result<Void> txSave(@RequestParam("studName") String studName,
                               @RequestParam("studAge") Integer studAge,
                               @RequestParam("studSex") String studSex) {
        Student1DO student = new Student1DO();
        student.setStudName(studName);
        student.setStudAge(studAge);
        student.setStudSex(studSex);
        studentService.txSave(student);
        return Result.wrapSuccess();
    }

    @GetMapping("/student/list")
    public Result<List<Student1DO>> listStudent(@RequestParam("studName") String studName) {
        return Result.wrapSuccess(studentService.listStudent(studName));
    }

    @GetMapping("/student/page")
    public Result<IPage<Student1DO>> selectPage(@RequestParam("pageNo") int pageNo) {
        return Result.wrapSuccess(studentService.listStudentPage(pageNo, 5));
    }

    @GetMapping("/student/get")
    public Result<Student1DO> getStudent(@RequestParam("id") long id) {
        return Result.wrapSuccess(studentService.getStudent(id));
    }

    @GetMapping("/student/get2")
    public Result<Student1DO> getStudent2(@RequestParam("id") long id) {
        return Result.wrapSuccess(studentService.getStudentLocal(id));
    }

    @GetMapping("/student/getDb1Master")
    public Result<Student1DO> getDb1Master(@RequestParam("id") long id) {
        return Result.wrapSuccess(studentService.getStudentDb1Master(id));
    }

    @GetMapping("/student/getDb1Slave")
    public Result<Student1DO> getDb1Slave(@RequestParam("id") long id) {
        return Result.wrapSuccess(studentService.getStudentDb1Slave(id));
    }

    @GetMapping("/student/getDb2Slave")
    public Result<Student2DO> getDb2Slave(@RequestParam("id") long id) {
        return Result.wrapSuccess(studentService.getStudentDB2Slave(id));
    }

    @GetMapping("/redis/list")
    public Result<String> testRedisList() {
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

    @GetMapping("/http/get")
    public Result<String> httpGet() {
        return Result.wrapSuccess(studentService.httpGet());
    }
}
