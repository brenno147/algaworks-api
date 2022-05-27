package com.tce.estagio.domain.exception;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

public class NaoEncontradoException extends NegocioException{
    public NaoEncontradoException(String msg) {
        super(msg);
    }

}
