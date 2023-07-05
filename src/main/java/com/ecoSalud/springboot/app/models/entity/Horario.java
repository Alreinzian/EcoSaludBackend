package com.ecoSalud.springboot.app.models.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@NoArgsConstructor
@Entity
@Table(name = "horario")
@NamedQueries({
        @NamedQuery(name = "Horario.findAll", query = "SELECT h FROM Horario h"),
        @NamedQuery(name = "Horario.findById_horario", query = "SELECT h FROM Horario h WHERE h.id_horario = :id_horario"),
        @NamedQuery(name = "Horario.findById_doctor", query = "SELECT h FROM Horario h WHERE h.id_doctor = :id_doctor"),
        @NamedQuery(name = "Horario.findByDia", query = "SELECT h FROM Horario h WHERE h.dia = :dia"),
        @NamedQuery(name = "Horario.findByHorario", query = "SELECT h FROM Horario h WHERE h.horario = :horario"),
        @NamedQuery(name = "Horario.findByHorarioinicio", query = "SELECT h FROM Horario h WHERE h.horarioinicio = :horarioinicio"),
        @NamedQuery(name = "Horario.findByHorariofin", query = "SELECT h FROM Horario h WHERE h.horariofin = :horariofin")
})
public class Horario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_horario")
    private Integer id_horario;

    @JoinColumn(name = "id_doctor", referencedColumnName = "id_doctor")
    @ManyToOne(optional = false)
    private Doctor id_doctor;

    @Column(name = "dia")
    private String dia;

    @Column(name = "horario")
    private String horario;

    @Column(name = "horarioinicio")
    private String horarioinicio;

    @Column(name = "horariofin")
    private String horariofin;

    // Getters y setters generados automáticamente por Lombok

}
