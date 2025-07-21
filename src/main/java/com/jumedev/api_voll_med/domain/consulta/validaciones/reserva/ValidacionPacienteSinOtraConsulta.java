package com.jumedev.api_voll_med.domain.consulta.validaciones.reserva;

import com.jumedev.api_voll_med.domain.ValidacionException;
import com.jumedev.api_voll_med.domain.consulta.ConsultaRepository;
import com.jumedev.api_voll_med.domain.consulta.DatosReservaConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionPacienteSinOtraConsulta implements ValidadorDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosReservaConsulta datos) {

        var primerHorario = datos.fecha().withHour(7);
        var ultimoHorario = datos.fecha().withHour(18);
        var pacienteTieneOtraConsultaEnElDia = repository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), primerHorario, ultimoHorario);

        if(pacienteTieneOtraConsultaEnElDia) {
            throw new ValidacionException("Paciente ya ha reservado una consulta en este dia.");
        }
    }
}
