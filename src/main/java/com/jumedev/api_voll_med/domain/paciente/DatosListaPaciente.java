package com.jumedev.api_voll_med.domain.paciente;

import com.jumedev.api_voll_med.domain.direccion.Direccion;

public record DatosListaPaciente(
        Long id,
        String nombre,
        String email,
        String telefono,
        String documento,
        Direccion direccion
) {
    public DatosListaPaciente(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNombre(),
                paciente.getEmail(),
                paciente.getTelefono(),
                paciente.getDocumento(),
                paciente.getDireccion()
        );
    }
}
