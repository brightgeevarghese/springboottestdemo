package miu.edu.cse.demo.repository;

import miu.edu.cse.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<List<Student>> findByFirstName(String firstName);
}
