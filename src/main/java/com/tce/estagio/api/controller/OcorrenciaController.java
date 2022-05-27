package com.tce.estagio.api.controller;

import com.tce.estagio.api.model.OcorrenciaModel;
import com.tce.estagio.api.model.input.ocorrencia.OcorrenciaInput;
import com.tce.estagio.domain.entity.Ocorrencia;
import com.tce.estagio.domain.service.OcorrenciaService;
import com.tce.estagio.api.mapper.OcorrenciaMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private OcorrenciaService ocorrenciaService;
    private OcorrenciaMapper ocorrenciaMapper;

    @PostMapping
    @ResponseStatus(value = org.springframework.http.HttpStatus.CREATED)
    public OcorrenciaModel registrarOcorrencia(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
        Ocorrencia ocorrencia = ocorrenciaService.registrarOcorrencia(entregaId, ocorrenciaInput.getDescricao());

        return ocorrenciaMapper.toModel(ocorrencia);
    }

    @GetMapping
    public List<OcorrenciaModel> listarOcorrencias(@PathVariable Long entregaId) {
        List<Ocorrencia> ocorrencias = ocorrenciaService.listarOcorrencias(entregaId);

        return ocorrenciaMapper.toCollectionModel(ocorrencias);
    }



}
