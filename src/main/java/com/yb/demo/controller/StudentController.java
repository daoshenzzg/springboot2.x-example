package com.yb.demo.controller;

import com.yb.demo.pojo.document.DemoDocument;
import com.yb.demo.pojo.model.db1.Student1DO;
import com.yb.demo.pojo.response.Result;
import com.yb.demo.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Result addStudent(@RequestParam("studName") String studName,
                             @RequestParam("studAge") Integer studAge,
                             @RequestParam("studSex") String studSex) {
        Student1DO student = new Student1DO();
        student.setStudName(studName);
        student.setStudAge(studAge);
        student.setStudSex(studSex);
        student = studentService.addStudent(student);
        return Result.renderOk(student);
    }

    @PostMapping("/student/txSave")
    public Result txSave(@RequestParam("studName") String studName,
                         @RequestParam("studAge") Integer studAge,
                         @RequestParam("studSex") String studSex) {
        Student1DO student = new Student1DO();
        student.setStudName(studName);
        student.setStudAge(studAge);
        student.setStudSex(studSex);
        studentService.txSave(student);
        return Result.renderOk();
    }

    @GetMapping("/student/list")
    public Result listStudent(@RequestParam("studName") String studName) {
        return Result.renderOk(studentService.listStudent(studName));
    }

    @GetMapping("/student/page")
    public Result selectPage(@RequestParam("pageNo") int pageNo) {
        return Result.renderOk(studentService.listStudentPage(pageNo, 5));
    }

    @GetMapping("/student/get")
    public Result<Student1DO> getStudent(@RequestParam("id") long id) {
        return Result.renderOk(studentService.getStudent(id));
    }

    @GetMapping("/student/get2")
    public Result getStudent2(@RequestParam("id") long id) {
        return Result.renderOk(studentService.getStudentLocal(id));
    }

    @GetMapping("/student/getDb1Master")
    public Result getDb1Master(@RequestParam("id") long id) {
        return Result.renderOk(studentService.getStudentDb1Master(id));
    }

    @GetMapping("/student/getDb1Slave")
    public Result getDb1Slave(@RequestParam("id") long id) {
        return Result.renderOk(studentService.getStudentDb1Slave(id));
    }

    @GetMapping("/student/getDb2Slave")
    public Result getDb2Slave(@RequestParam("id") long id) {
        return Result.renderOk(studentService.getStudentDB2Slave(id));
    }

    @GetMapping("/redis/list")
    public Result testRedisList() {
        return Result.renderOk(studentService.testRdisList());
    }

    @PostMapping("/mq/sendMessage")
    public Result sendMessage(@RequestParam("message") String message) {
        studentService.sendMessage(StringUtils.trimToEmpty(message));
        return Result.renderOk();
    }

    @GetMapping("/doc/get")
    public Result getDoc(@RequestParam("id") String id) {
        return Result.renderOk(studentService.getDoc(id));
    }

    @PostMapping("/doc/save")
    public Result saveDoc(@RequestParam("title") String title) {
        DemoDocument doc = new DemoDocument();
        doc.setTitle(title);
        studentService.saveDoc(doc);
        return Result.renderOk();
    }

    @GetMapping("/http/get")
    public Result httpGet() {
        return Result.renderOk(studentService.httpGet());
    }
}
