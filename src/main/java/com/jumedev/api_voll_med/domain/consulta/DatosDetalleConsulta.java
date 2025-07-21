package com.jumedev.api_voll_med.domain.consulta;

import com.jumedev.api_voll_med.domain.medico.Medico;
import com.jumedev.api_voll_med.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(
        Long id,
        Long idPaciente,
        Long idMedico,
        LocalDateTime fecha
) {
    public DatosDetalleConsulta(Consulta consulta) {
        this(
                consulta.getId(),
                consulta.getPaciente().getId(),
                consulta.getMedico().getId(),
                consulta.getFecha()
        );
    }
}
