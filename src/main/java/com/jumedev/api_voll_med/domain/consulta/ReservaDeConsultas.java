package com.jumedev.api_voll_med.domain.consulta;

import com.jumedev.api_voll_med.domain.ValidacionException;
import com.jumedev.api_voll_med.domain.consulta.validaciones.cancelamiento.ValidadorCancelamientoDeConsulta;
import com.jumedev.api_voll_med.domain.consulta.validaciones.reserva.ValidadorDeConsultas;
import com.jumedev.api_voll_med.domain.medico.Medico;
import com.jumedev.api_voll_med.domain.medico.MedicoRepository;
import com.jumedev.api_voll_med.domain.paciente.PacienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaDeConsultas {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidadorDeConsultas> validadores;

    @Autowired
    private List<ValidadorCancelamientoDeConsulta> validadoresCancelamiento;

    public DatosDetalleConsulta reservar(DatosReservaConsulta datos) {

        if(!pacienteRepository.existsById(datos.idPaciente())) {
            throw new ValidacionException("No existe un paciente con el id informado");
        }

        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
            throw new ValidacionException("No existe un médico con el id informado");
        }

        // Validaciones
        validadores.forEach(v -> v.validar(datos));

        var medico = elegirMedico(datos);
        if(medico == null) {
            throw new ValidacionException("No existe un médico disponible en ese horario");
        }
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();

        var consulta =  new Consulta(null, medico, paciente, datos.fecha(), null);

        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }


    private Medico elegirMedico(DatosReservaConsulta datos) {

        if(datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }

        if(datos.especialidad() == null) {
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se elige un médico");
        }

        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());

    }



    public void cancelar(DatosCancelamientoConsulta datos) {
        if (!consultaRepository.existsById(datos.idConsulta())) {
            throw new ValidacionException("¡El Id proporcionado de la consulta no existe!");
        }

        validadoresCancelamiento.forEach(v -> v.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }
}
