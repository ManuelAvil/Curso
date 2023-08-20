package com.example.demo.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private long id;

    @Column(name="descripcion")
    @Getter
    @Setter
    private String descripcion;

    @Column(name="estatus")
    @Getter
    @Setter
    private String estatus;
}
