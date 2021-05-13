package com.tennistourcol.model;

import org.jetbrains.annotations.NotNull;

import lombok.*;
import java.util.List;
import com.tennistourcol.model.Tournament;

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

    private List<Tournament> schedule;

    private Integer imagen;
}
