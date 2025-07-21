package com.jumedev.api_voll_med.domain.consulta.validaciones.reserva;

import com.jumedev.api_voll_med.domain.ValidacionException;
import com.jumedev.api_voll_med.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacionConsultaConAnticipacion implements ValidadorDeConsultas {

    public void validar(DatosReservaConsulta datos) {

        var fechaConsulta = datos.fecha();
        var ahora = LocalDateTime.now();
        var diferenciaEnMinutos = Duration.between(ahora, fechaConsulta).toMinutes();

        if(diferenciaEnMinutos < 30) {
            throw new ValidacionException("No es posible reservar con tiempo menor a 30 minutos.");
        }

    }
}
