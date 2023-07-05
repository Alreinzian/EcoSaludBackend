package com.ecoSalud.springboot.app.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "historial")
@NamedQueries({
        @NamedQuery(name = "Historial.findAll", query = "SELECT h FROM Historial h"),
        @NamedQuery(name = "Historial.findById_historial", query = "SELECT h FROM Historial h WHERE h.id_historial = :id_historial"),
        @NamedQuery(name = "Historial.findById_usuario", query = "SELECT h FROM Historial h WHERE h.id_usuario = :id_usuario"),
        @NamedQuery(name = "Historial.findByid_doctor", query = "SELECT h FROM Historial h WHERE h.id_doctor = :id_doctor"),
        @NamedQuery(name = "Historial.findByDiagnostico", query = "SELECT h FROM Historial h WHERE h.diagnostico = :diagnostico"),
        @NamedQuery(name = "Historial.findByTratamiento", query = "SELECT h FROM Historial h WHERE h.tratamiento = :tratamiento"),
        @NamedQuery(name = "Historial.findByFecha_registro", query = "SELECT h FROM Historial h WHERE h.fecha_registro = :fecha_registro")
})
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer id_historial;

    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    @ManyToOne(optional = false)
    private Usuario id_usuario;

    @JoinColumn(name = "id_doctor", referencedColumnName = "id_doctor")
    @ManyToOne(optional = false)
    private Doctor id_doctor;

    @Column(name = "fecha_registro")
    private String fecha_registro;

    @Column(name = "diagnostico")
    private String diagnostico;

    @Column(name = "tratamiento")
    private String tratamiento;

    // Getters y setters generados automáticamente por Lombok

}
