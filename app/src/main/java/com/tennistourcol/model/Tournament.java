package com.tennistourcol.model;

import org.jetbrains.annotations.NotNull;

import lombok.*;

import java.math.BigInteger;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Tournament {

    private String id;

    @NotNull
    private String nombre;

    @NotNull
    private String responsable;

    @NotNull
    private String direccion;

    @NotNull
    private String ciudad;

    @NotNull
    private String club;

    @NotNull
    private String grado;

    @NotNull
    private String categoria;

    @NotNull
    private BigInteger precio;

    @NotNull
    private String hora;

    @NotNull
    private Date fechaInicio;

    @NotNull
    private Date fechaFin;

    private int foto;


}
