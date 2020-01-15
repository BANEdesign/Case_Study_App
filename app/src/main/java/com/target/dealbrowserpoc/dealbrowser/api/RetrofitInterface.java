package com.target.dealbrowserpoc.dealbrowser.api;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * created by bryonnabaines on 2020-01-14
 */
public interface RetrofitInterface {
  @GET("deals")
  Observable<DealsResponse> getDeals();
}
