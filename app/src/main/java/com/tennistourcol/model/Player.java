package com.tennistourcol.model;

import org.jetbrains.annotations.NotNull;

import lombok.*;
import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Player {


    private String id;

    @NotNull
    private String name, mail, apodo, liga, ciudad, description;
    private String puntos;

    private List<String> schedule;

    private Integer imagen;
}
