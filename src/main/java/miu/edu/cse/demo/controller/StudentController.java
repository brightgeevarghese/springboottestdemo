package miu.edu.cse.demo.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import miu.edu.cse.demo.model.Student;
import miu.edu.cse.demo.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Optional<List<Student>>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Optional<Student> newStudent = studentService.createStudent(student);
        return ResponseEntity.ok(newStudent.get());
    }

    @GetMapping("/{firstName}")
    public ResponseEntity<List<Student>> findStudentByFirstName(@PathVariable String firstName) {
        if (firstName.trim().isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(studentService.getStudentByFirstName(firstName).get());
    }
}
