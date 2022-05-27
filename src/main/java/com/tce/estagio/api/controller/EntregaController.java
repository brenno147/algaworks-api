package com.tce.estagio.api.controller;

import com.tce.estagio.api.model.EntregaModel;
import com.tce.estagio.api.model.input.entrega.EntregaInput;
import com.tce.estagio.domain.entity.Entrega;
import com.tce.estagio.domain.repository.EntregaRepository;
import com.tce.estagio.domain.service.EntregaService;
import com.tce.estagio.api.mapper.EntregaMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private EntregaRepository entregaRepository;
    private EntregaService entregaService;
    private EntregaMapper entregaMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput){
        Entrega novaEntrega = entregaMapper.toEntity(entregaInput);

        return entregaMapper.toModel(entregaService.solicitar(novaEntrega));
    }

    @GetMapping
    public List<EntregaModel> listar(){

        return entregaMapper.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaModel> buscarPorId(@PathVariable Long id){
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entregaMapper.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizar(@PathVariable Long id){
        entregaService.finalizar(id);
    }

}
