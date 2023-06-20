package com.ecoSalud.springboot.app.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cita")
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
    @NamedQuery(name = "Cita.findByIdCita", query = "SELECT c FROM Cita c WHERE c.idCita = :idCita"),
    @NamedQuery(name = "Cita.findByUsuario", query = "SELECT c FROM Cita c WHERE c.usuario.id_usuario = :id_usuario"),
    @NamedQuery(name = "Cita.findByDoctor", query = "SELECT c FROM Cita c WHERE c.doctor.id_doctor = :id_doctor"),
    @NamedQuery(name = "Cita.findByFecha", query = "SELECT c FROM Cita c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cita.findByHora", query = "SELECT c FROM Cita c WHERE c.hora = :hora"),
    @NamedQuery(name = "Cita.findByUbicacion", query = "SELECT c FROM Cita c WHERE c.ubicacion = :ubicacion"),
    @NamedQuery(name = "Cita.findByEstadoCita", query = "SELECT c FROM Cita c WHERE c.estadoCita = :estadoCita"),
    @NamedQuery(name = "Cita.findByDiagnostico", query = "SELECT c FROM Cita c WHERE c.diagnostico = :diagnostico")

})
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cita")
    private Integer idCita;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    @JoinColumn(name = "id_doctor", referencedColumnName = "id_doctor")
    @ManyToOne(optional = false)
    private Doctor doctor;

    @Column(name = "fecha")
    private LocalDate fecha;

    @Column(name = "hora")
    private LocalTime hora;

    @Column(name = "ubicacion")
    private String ubicacion;

    @Column(name = "estado_cita")
    private String estadoCita;

    @Column(name = "diagnostico")
    private String diagnostico;

    // Getters y setters generados automáticamente por Lombok

}

