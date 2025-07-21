package com.jumedev.api_voll_med.domain.consulta.validaciones.reserva;

import com.jumedev.api_voll_med.domain.ValidacionException;
import com.jumedev.api_voll_med.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class ValidacionConsultaFueraHorario implements ValidadorDeConsultas {

    public void validar(DatosReservaConsulta datos) {

        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeAperturaClinica = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierreClinica = fechaConsulta.getHour() > 18;

        if(domingo || horarioAntesDeAperturaClinica || horarioDespuesDeCierreClinica) {
            throw new ValidacionException("Los servicios de la clinica no están disponibles en este horario.");
        }
    }
}
