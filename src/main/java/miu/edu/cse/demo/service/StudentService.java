package miu.edu.cse.demo.service;

import miu.edu.cse.demo.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Optional<Student> createStudent(Student student);
    Optional<List<Student>> getStudentByFirstName(String firstName);
    Optional<List<Student>> getAllStudents();
}
