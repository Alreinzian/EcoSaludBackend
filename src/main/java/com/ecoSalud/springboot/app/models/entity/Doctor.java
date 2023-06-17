package com.ecoSalud.springboot.app.models.entity;



import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctor")
@NamedQueries({
    @NamedQuery(name = "Doctor.findAll", query = "SELECT d FROM Doctor d"),
    @NamedQuery(name = "Doctor.findById_doctor", query = "SELECT d FROM Doctor d WHERE d.id_doctor = :id_doctor"),
    @NamedQuery(name = "Doctor.findByNombres", query = "SELECT d FROM Doctor d WHERE d.nombres = :nombres"),
    @NamedQuery(name = "Doctor.findByApellidos", query = "SELECT d FROM Doctor d WHERE d.apellidos = :apellidos"),
    @NamedQuery(name = "Doctor.findByDisponibilidad", query = "SELECT d FROM Doctor d WHERE d.disponibilidad = :disponibilidad"),
    @NamedQuery(name = "Doctor.findByEspecialidad", query = "SELECT d FROM Doctor d WHERE d.especialidad = :especialidad")
})
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_doctor")
    private Integer id_doctor;

    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;

    @Basic(optional = false)
    @Column(name = "apellidos")
    private String apellidos;

    @Basic(optional = false)
    @Column(name = "especialidad")
    private String especialidad;

    @Basic(optional = false)
    @Column(name = "disponibilidad")
    private String disponibilidad;
}
