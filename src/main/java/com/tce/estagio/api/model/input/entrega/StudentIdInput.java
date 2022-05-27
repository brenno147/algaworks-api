package com.tce.estagio.api.model.input.entrega;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class StudentIdInput {

    @NotNull
    private Long id;
}
