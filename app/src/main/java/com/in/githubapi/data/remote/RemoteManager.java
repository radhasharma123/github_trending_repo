package com.in.githubapi.data.remote;

import com.in.githubapi.data.model.RepoModel;
import com.in.githubapi.data.remote.rest.RestApi;
import com.in.githubapi.data.remote.rest.RestClient;
import java.util.Map;
import io.reactivex.Observable;

public class RemoteManager {
    private static RemoteManager mInstance = null;

    private RemoteManager(){}

    public static RemoteManager getInstance(){
        return mInstance == null ? mInstance= new RemoteManager() : mInstance;
    }

    public Observable<RepoModel> getRepos(Map<String, String> map){
        RestApi api = RestClient.getApiService();
        return api.getRepos(map);
    }
}
