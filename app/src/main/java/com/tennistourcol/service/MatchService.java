package com.tennistourcol.service;

import com.tennistourcol.model.Match;
import com.tennistourcol.model.Response;
import com.tennistourcol.model.Tournament;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface MatchService {
    @POST("match")
    Call<Response> createTournament(@Body Match match);
}
