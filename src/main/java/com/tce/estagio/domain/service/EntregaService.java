package com.tce.estagio.domain.service;

import com.tce.estagio.domain.entity.Entrega;
import com.tce.estagio.domain.entity.StatusEntrega;
import com.tce.estagio.domain.entity.Student;
import com.tce.estagio.domain.exception.NaoEncontradoException;
import com.tce.estagio.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class EntregaService {

    private StudentService studentService;
    private EntregaRepository entregaRepository;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Student student = studentService.findById(entrega.getStudent().getStudentId());

        entrega.setStudent(student);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaRepository.save(entrega);
    }

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new NaoEncontradoException("Entrega não encontrada"));

        entrega.finalizar();

        entregaRepository.save(entrega);
    }

}
