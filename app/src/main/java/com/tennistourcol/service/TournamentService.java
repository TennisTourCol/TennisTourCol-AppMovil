package com.tennistourcol.service;

import com.tennistourcol.model.Response;
import com.tennistourcol.model.Tournament;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.POST;

public interface TournamentService {
    @POST("tournament")
    Call<Response> createTournament(@Body Tournament tournament);

    @DELETE("tournament")
    Call<Response> deleteTournament(@Body Tournament tournament);
}
