package com.jumedev.api_voll_med.controller;

import com.jumedev.api_voll_med.domain.consulta.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reserva;

    @Transactional
    @PostMapping
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos) {

        var detalleConsulta = reserva.reservar(datos);

        return ResponseEntity.ok(detalleConsulta);
    }

//    @Transactional
//    @DeleteMapping
//    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelamientoConsulta datos) {
//
//        reserva.cancelar(datos);
//
//        return ResponseEntity.noContent().build();
//    }

}
