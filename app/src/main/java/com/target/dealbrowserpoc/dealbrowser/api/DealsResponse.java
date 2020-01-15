package com.target.dealbrowserpoc.dealbrowser.api;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/**
 * created by bryonnabaines on 2020-01-14
 */
public class DealsResponse {
  @SerializedName("_id")
  public String id;
  @SerializedName("data")
  public List<Deal> data;
  @SerializedName("type")
  public String type;

  public DealsResponse(String id, List<Deal> data, String type) {
    this.id = id;
    this.data = data;
    this.type = type;
  }
}
