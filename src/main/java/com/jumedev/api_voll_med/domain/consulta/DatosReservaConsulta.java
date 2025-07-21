package com.jumedev.api_voll_med.domain.consulta;

import com.jumedev.api_voll_med.domain.medico.Especialidad;
import com.jumedev.api_voll_med.domain.paciente.Paciente;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosReservaConsulta(
        Long idMedico,
        @NotNull
        Long idPaciente,
        @NotNull
        @Future
        LocalDateTime fecha,
        Especialidad especialidad
) {}
