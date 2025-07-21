package com.jumedev.api_voll_med.domain.medico;

import com.jumedev.api_voll_med.domain.consulta.Consulta;
import com.jumedev.api_voll_med.domain.direccion.DatosDireccion;
import com.jumedev.api_voll_med.domain.paciente.DatosRegistroPaciente;
import com.jumedev.api_voll_med.domain.paciente.Paciente;
import jakarta.persistence.EntityManager;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EntityManager em;


    @Test
    @DisplayName("Deberia devolver null cuando el medico buscado existe pero no esta disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario1() {
        // Given or arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = registrarMedico("Medico1", "medicorod@gmail.com", "1234567", Especialidad.CARDIOLOGIA);
        var paciente = registrarPaciente("Paciente1", "pacienterod@gmail.com","1204567");

        registrarConsulta(medico, paciente, lunesSiguienteALas10);
        // When or act
        var medicoLibre = medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);
        // Then or assert
        assertThat(medicoLibre).isNull();
    }

    @Test
    @DisplayName("Deberia devolver medico cuando el medico buscado esta disponible en esa fecha")
    void elegirMedicoAleatorioDisponibleEnLaFechaEscenario2() {
        // Given or arrange
        var lunesSiguienteALas10 = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
        var medico = registrarMedico("Medico1", "medicorod@gmail.com", "1234567", Especialidad.CARDIOLOGIA);

        // When or act
        var medicoLibre = medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad.CARDIOLOGIA, lunesSiguienteALas10);

        // Then or assert
        assertThat(medicoLibre).isEqualTo(medico);
    }





    private void registrarConsulta(Medico medico, Paciente paciente, LocalDateTime fecha) {

        em.persist(new Consulta(null, medico, paciente, fecha));
    }

    private Medico registrarMedico(String nombre, String email, String documento, Especialidad especialidad) {

        var medico = new Medico(datosMedico(nombre, email, documento, especialidad));
        em.persist(medico);
        return medico;
    }

    private Paciente registrarPaciente(String nombre, String email, String documento) {

        var paciente = new Paciente(datosPaciente(nombre, email, documento));
        em.persist(paciente);
        return paciente;
    }

    private DatosRegistroMedico datosMedico(String nombre, String email, String documento, Especialidad especialidad) {

        return new DatosRegistroMedico(
                nombre,
                email,
                "8098761820",
                documento,
                especialidad,
                datosDireccion()
        );
    }

    private DatosRegistroPaciente datosPaciente(String nombre, String email, String documento) {

        return new DatosRegistroPaciente(
                nombre,
                email,
                "809712860",
                documento,
                datosDireccion()
        );
    }

    private DatosDireccion datosDireccion() {

        return new DatosDireccion(
                "calle A",
                "28",
                "1",
                "282",
                "Distrito Nacional",
                "2302",
                "Santo Domingo"

        );

    }
}