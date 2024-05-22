package miu.edu.cse.demo.service.impl;

import lombok.RequiredArgsConstructor;
import miu.edu.cse.demo.model.Student;
import miu.edu.cse.demo.repository.StudentRepository;
import miu.edu.cse.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> createStudent(Student student) {
        return Optional.of(studentRepository.save(student));
    }

    @Override
    public Optional<List<Student>> getStudentByFirstName(String firstName) {
        return studentRepository.findByFirstName(firstName);
    }

    @Override
    public Optional<List<Student>> getAllStudents() {
        return Optional.of(studentRepository.findAll());
    }
}
