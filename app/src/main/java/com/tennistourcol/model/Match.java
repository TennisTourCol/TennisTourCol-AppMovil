package com.tennistourcol.model;

import java.util.Date;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Match {

    private String id;
    private String player1;
    private String player2;
    private String court;
    private Date date;
    private String hour;
    private String round;
}