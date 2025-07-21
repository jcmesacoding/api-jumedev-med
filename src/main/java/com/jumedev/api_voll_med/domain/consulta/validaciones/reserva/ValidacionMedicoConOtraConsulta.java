package com.jumedev.api_voll_med.domain.consulta.validaciones.reserva;

import com.jumedev.api_voll_med.domain.ValidacionException;
import com.jumedev.api_voll_med.domain.consulta.ConsultaRepository;
import com.jumedev.api_voll_med.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ValidacionMedicoConOtraConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos) {

        var medicoConOtraConsultaEnElMismoHorario = repository.existsByMedicoIdAndFechaAndMotivoCancelamientoIsNull(datos.idMedico(), datos.fecha());

        if(medicoConOtraConsultaEnElMismoHorario) {
            throw new ValidacionException("El médico ya tiene una consulta en la fecha y hora seleccionada.");
        }
    }
}
