package com.in.githubapi.data.remote.rest;

import com.in.githubapi.data.model.RepoModel;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface RestApi {

    @GET("/search/repositories")
    Observable<RepoModel> getRepos(@QueryMap Map<String, String> filter);
}
