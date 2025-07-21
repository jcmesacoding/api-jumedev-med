package com.jumedev.api_voll_med.domain.paciente;

import com.jumedev.api_voll_med.domain.direccion.DatosDireccion;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionPaciente(
        @NotNull Long id,
        String nombre,
        String telefono,
        DatosDireccion direccion
) {}
