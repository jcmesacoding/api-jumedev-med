package com.jumedev.api_voll_med.domain.paciente;

import com.jumedev.api_voll_med.domain.direccion.DatosDireccion;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DatosRegistroPaciente(
        @NotBlank String nombre,
        @NotBlank @Email String email,
        String telefono,
        @NotBlank @Pattern(regexp = "\\d{7,9}") String documento,
        @NotNull @Valid DatosDireccion direccion
) {}
