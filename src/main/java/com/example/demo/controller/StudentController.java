package com.example.demo.controller;

import com.example.demo.entity.Department;
import com.example.demo.entity.Student;
import com.example.demo.service.DepartmentService;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @Autowired
    DepartmentService departmentService;

    @PostMapping
    public Student createStudent(@RequestBody Student student){
        return studentService.createStudent(student);
    }

    @GetMapping("{id}")
    public Student getStudent(@PathVariable("id") String id) {
        return studentService.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<Student> getStudentByName(@PathVariable("name") String name){
        return studentService.getStudentByName(name);
    }

    @GetMapping("/name/{name}/email/{email}")
    public Student getStudentByNameAndEmail(
            @PathVariable("name") String name,
            @PathVariable("email") String email
    ){
        return studentService.getStudentByNameAndEmail(name, email);
    }

    @GetMapping
    public List<Student> getAllStudents(){
        return studentService.getAll();
    }

    @GetMapping("/pageable")
    public List<Student> getAllStudentsPageable(
            @RequestParam("page") int pageNo,
            @RequestParam("size") int size
    ){
        return studentService.pageableStudents(pageNo, size);
    }

    @PutMapping("/{id}")
    public Student updateStudent(
            @PathVariable("id") String id,
            @RequestBody Student student
    ) {
        return studentService.upadateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(
            @PathVariable("id") String id
    ) {
        studentService.deleteStudent(id);
    }

    @GetMapping("/department/{id}")
    public Department getByDeptId(@PathVariable("id") String id){
        return departmentService.getById(id);
    }
}
