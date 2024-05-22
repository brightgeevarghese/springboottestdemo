package miu.edu.cse.demo.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import miu.edu.cse.demo.model.Student;
import miu.edu.cse.demo.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getAllStudents() throws Exception {
        Optional<List<Student>> students = Optional.of(
                List.of(
                        new Student("Jim", "John", 23),
                        new Student("Joe", "Tom", 24)
                )
        );
        // Mocks the studentService.getAllStudents() method
        // to return the predefined list of students.
        Mockito.when(studentService.getAllStudents()).thenReturn(students);
        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/api/v1/students"))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        String response = mvcResult.getResponse().getContentAsString();
        System.out.println(response);
        List<Student> responseStudents = new Gson().fromJson(
                response, new TypeToken<List<Student>>() {}.getType()
        );
        assertEquals(students.get().size(), responseStudents.size());
        assertEquals(students.get().get(0), responseStudents.get(0));
    }

    @Test
    void createStudent() throws Exception {
        Student student = new Student("Jane", "Doe", 22);
        Optional<Student> createdStudent = Optional.of(student);
        Mockito.when(studentService.createStudent(student)).thenReturn(createdStudent);
        String response = mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/v1/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(student))
                )
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        System.out.println("The response is "+response);
        Student studentResponse = new Gson().fromJson(response, Student.class);
        assertEquals(student, studentResponse);
    }

    @Test
    void findStudentByFirstName() throws Exception {
        Student student = new Student("Jane", "Doe", 22);
        Student student2 = new Student("Jane", "Joe", 32);
        Optional<List<Student>> students = Optional.of(List.of(student, student2));
        Mockito.when(studentService.getStudentByFirstName("Jane")).thenReturn(students);
        String response = mockMvc
                .perform(
                        MockMvcRequestBuilders.get("/api/v1/students/{firstName}", "Jane")
                )
                .andDo(MockMvcResultHandlers.print())
                .andReturn()
                .getResponse()
                .getContentAsString();
        List<Student> foundStudents = new Gson().fromJson(response, new TypeToken<List<Student>>(){}.getType());
        assertEquals(students.get(), foundStudents);
    }
}