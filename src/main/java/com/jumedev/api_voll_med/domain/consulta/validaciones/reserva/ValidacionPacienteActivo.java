package com.jumedev.api_voll_med.domain.consulta.validaciones.reserva;

import com.jumedev.api_voll_med.domain.ValidacionException;
import com.jumedev.api_voll_med.domain.consulta.DatosReservaConsulta;
import com.jumedev.api_voll_med.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionPacienteActivo implements ValidadorDeConsultas {

    @Autowired
    private PacienteRepository repository;

    public void validar(DatosReservaConsulta datos) {

        var pacienteEstaActivo = repository.findActivoById(datos.idPaciente());

        if(!pacienteEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con paciente inactivo.");
        }
    }
}
