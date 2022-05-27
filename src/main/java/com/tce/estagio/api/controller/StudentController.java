package com.tce.estagio.api.controller;

import com.tce.estagio.domain.entity.Student;
import com.tce.estagio.domain.repository.StudentRepository;
import com.tce.estagio.domain.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentService studentService;

    @GetMapping("/estudantes")
    public List<Student> listar() {
        return studentRepository.findAll();
    }

    @GetMapping("/estudantes/{id}")
    public ResponseEntity<Student> listarPorId(@PathVariable Long id) {
        Optional<Student> student= studentRepository.findById(id);

        if (student.isPresent()) {
            return ResponseEntity.ok(student.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/estudantes")
    @ResponseStatus(HttpStatus.CREATED)
    public Student salvar(@Valid @RequestBody Student student) {
        return studentService.save(student);
    }

    @PutMapping("/estudantes/{id}")
    public ResponseEntity<Student> atualizar(@PathVariable Long id, @Valid @RequestBody Student student) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        student.setStudentId(id);
        student = studentService.save(student);

        return ResponseEntity.ok(student);
    }

    @DeleteMapping("/estudantes/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        if (!studentRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        studentService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
