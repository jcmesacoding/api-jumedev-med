package com.jumedev.api_voll_med.domain.consulta.validaciones.cancelamiento;

import com.jumedev.api_voll_med.domain.ValidacionException;
import com.jumedev.api_voll_med.domain.consulta.ConsultaRepository;
import com.jumedev.api_voll_med.domain.consulta.DatosCancelamientoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioConAnticipacion implements ValidadorCancelamientoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosCancelamientoConsulta datos) {
        var consulta = repository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if (diferenciaEnHoras < 24) {
            throw new ValidacionException("¡La consulta solo puede ser cancelada con anticipación mínima de 24 horas!");
        }
    }
}