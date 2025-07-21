package com.jumedev.api_voll_med.domain.consulta.validaciones.reserva;

import com.jumedev.api_voll_med.domain.ValidacionException;
import com.jumedev.api_voll_med.domain.consulta.DatosReservaConsulta;
import com.jumedev.api_voll_med.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionMedicoActivo implements ValidadorDeConsultas {

    @Autowired
    private MedicoRepository repository;

    public void validar(DatosReservaConsulta datos) {

        // elección de médico (opcional)
        if(datos.idMedico() == null) {
            return;
        }

        var medicoEstaActivo = repository.findActivoById(datos.idMedico());

        if(!medicoEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con médico inactivo.");
        }
    }
}
