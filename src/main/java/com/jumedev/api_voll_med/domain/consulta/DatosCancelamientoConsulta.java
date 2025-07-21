package com.jumedev.api_voll_med.domain.consulta;

import jakarta.validation.constraints.NotNull;

public record DatosCancelamientoConsulta(

        @NotNull
        Long idConsulta,
        @NotNull
        MotivoCancelamiento motivo
) {}
