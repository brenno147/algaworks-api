package com.tce.estagio.domain.service;

import com.tce.estagio.domain.entity.Student;
import com.tce.estagio.domain.exception.NegocioException;
import com.tce.estagio.domain.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional
    public Student save(Student student){

        boolean emailEmUso = studentRepository.findByEmail(student.getEmail())
                .stream()
                .anyMatch(s -> s.getStudentId() != student.getStudentId());

        if(emailEmUso){
            throw new NegocioException("Email já em uso");
        }
        return studentRepository.save(student);
    }

    @Transactional
    public void excluir(Long id){
        studentRepository.deleteById(id);
    }

    public Student findById(Long id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Aluno não encontrado"));
    }
}
