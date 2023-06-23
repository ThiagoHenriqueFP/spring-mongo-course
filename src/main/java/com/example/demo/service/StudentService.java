package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.entity.Student;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class StudentService{
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    SubjectRepository subjectRepository;
    public Student createStudent(Student student) {
        if (student.getDepartment() != null)
            departmentRepository.save(student.getDepartment());

        if (student.getSubjects() != null && student.getSubjects().size() > 0)
            subjectRepository.saveAll(student.getSubjects());


        return studentRepository.save(student);
    }

    public Student findById(String id){
        return studentRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Could not found this student"
        ));
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public Student upadateStudent(String id, Student student){
        Student s = findById(id);
        if (!s.getId().equals(id))
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Student not found"
            );

        student.setId(id);

        return studentRepository.save(student);
    }

    public void deleteStudent(String id){
        Student s = findById(id);
        if (!s.getId().equals(id))
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Student not found"
            );

        studentRepository.deleteById(id);
    }

    public List<Student> getStudentByName(String name){
        return studentRepository.findByName(name);
    }

    public Student getStudentByNameAndEmail(String name, String email){
        return studentRepository.findByNameAndEmail(name, email);
    }

    public List<Student> pageableStudents(int pageNo, int size){
        Pageable pageable  = PageRequest.of(pageNo, size);

        return studentRepository.findAll(pageable).getContent();
    }
}
