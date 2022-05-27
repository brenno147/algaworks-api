package com.tce.estagio.domain.service;

import com.tce.estagio.domain.entity.Entrega;
import com.tce.estagio.domain.entity.Ocorrencia;
import com.tce.estagio.domain.exception.NaoEncontradoException;
import com.tce.estagio.domain.exception.NegocioException;
import com.tce.estagio.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OcorrenciaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Transactional
    public Ocorrencia registrarOcorrencia(Long entregaId, String descricao) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new NaoEncontradoException("Entrega não encontrada"));

        return entrega.adcionarOcorrencia(descricao);
    }

    public List<Ocorrencia> listarOcorrencias(Long entregaId) {
        Entrega entrega = entregaRepository.findById(entregaId)
                .orElseThrow(() -> new NaoEncontradoException("Entrega não encontrada"));

        return entrega.getOcorrencias();
    }
}
