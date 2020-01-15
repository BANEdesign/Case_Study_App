package com.target.dealbrowserpoc.dealbrowser.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * created by bryonnabaines on 2020-01-14
 */
//todo delete this
public class Network {

  public Network(){}

  public static final String BASE_URL = "http://target-deals.herokuapp.com/api/";

  Gson gson = new GsonBuilder()
      .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
      .create();

  Retrofit retrofit = new Retrofit.Builder()
      .baseUrl(BASE_URL)
      .addConverterFactory(GsonConverterFactory.create(gson))
      .build();
}
