package com.tce.estagio.repository;

import com.tce.estagio.domain.entity.Guardian;
import com.tce.estagio.domain.entity.Student;
import com.tce.estagio.domain.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentRepositoryTest {


    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void testSaveStudent(){
        Guardian guardian = Guardian.builder()
                .name("Teste pai")
                .email("testepai.com")
                .phone("123456789")
                .build();

        Student student = Student.builder()
                .firsName("Teste")
                .lastName("Testinson")
                .email("teste.com")
                .guardian(guardian)
                .build();

        studentRepository.save(student);
    }
}