package com.ecoSalud.springboot.app.models.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuario")
@NamedQueries({
        @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
        @NamedQuery(name = "Usuario.findByIdUsuario", query = "SELECT u FROM Usuario u WHERE u.id_usuario = :id_usuario"),
        @NamedQuery(name = "Usuario.findByNombres", query = "SELECT u FROM Usuario u WHERE u.nombres = :nombres"),
        @NamedQuery(name = "Usuario.findByApellidos", query = "SELECT u FROM Usuario u WHERE u.apellidoPaterno = :apellidoPaterno AND u.apellidoMaterno = :apellidoMaterno"),
        @NamedQuery(name = "Usuario.findByFechaNac", query = "SELECT u FROM Usuario u WHERE u.fechaNac = :fechaNac"),
        @NamedQuery(name = "Usuario.findByCorreo", query = "SELECT u FROM Usuario u WHERE u.correo = :correo"),
        @NamedQuery(name = "Usuario.findByPassword", query = "SELECT u FROM Usuario u WHERE u.password = :password"),
        @NamedQuery(name = "Usuario.findByDni", query = "SELECT u FROM Usuario u WHERE u.dni = :dni"),
        @NamedQuery(name = "Usuario.findByTelefono", query = "SELECT u FROM Usuario u WHERE u.telefono = :telefono"),
        @NamedQuery(name = "Usuario.findByDireccion", query = "SELECT u FROM Usuario u WHERE u.direccion = :direccion")
})
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario")
    private Integer id_usuario;

    @Basic(optional = false)
    @Column(name = "nombres")
    private String nombres;

    @Basic(optional = false)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;

    @Basic(optional = false)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;

    @Basic(optional = false)
    @Column(name = "fecha_nac")
    private String fechaNac;

    @Basic(optional = false)
    @Column(name = "correo")
    private String correo;

    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    @Basic(optional = false)
    @Column(name = "dni")
    private String dni;

    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;

    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    // @OneToMany(mappedBy = "usuario")
    // private List<Cita> cita;
}