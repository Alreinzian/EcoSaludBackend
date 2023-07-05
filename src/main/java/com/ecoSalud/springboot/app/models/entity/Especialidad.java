package com.ecoSalud.springboot.app.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "especialidad")
@NamedQueries({
        @NamedQuery(name = "Especialidad.findAll", query = "SELECT e FROM Especialidad e"),
        @NamedQuery(name = "Especialidad.findById_especialidad", query = "SELECT e FROM Especialidad e WHERE e.id_especialidad = :id_especialidad"),
        @NamedQuery(name = "Especialidad.findByEspecialidad", query = "SELECT e FROM Especialidad e WHERE e.especialidad = :especialidad"),
        @NamedQuery(name = "Especialidad.findById_doctor", query = "SELECT e FROM Especialidad e WHERE e.id_doctor = :id_doctor")
})
public class Especialidad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especialidad")
    private Integer id_especialidad;

    @Column(name = "especialidad")
    private String especialidad;

    @JoinColumn(name = "id_doctor", referencedColumnName = "id_doctor")
    @ManyToOne(optional = false)
    private Doctor id_doctor;


}
