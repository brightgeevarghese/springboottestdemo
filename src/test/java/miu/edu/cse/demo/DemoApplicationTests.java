package miu.edu.cse.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    private TestRestTemplate template;

    @Test
    void contextLoads() {
        String response = template.getForObject("/hello?name=Tom", String.class);
        assertEquals("Hello, Tom!", response);
    }
}
