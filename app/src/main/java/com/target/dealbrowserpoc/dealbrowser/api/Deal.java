package com.target.dealbrowserpoc.dealbrowser.api;

/**
 * created by bryonnabaines on 2020-01-14
 */
import com.google.gson.annotations.SerializedName;

public class Deal {

  @SerializedName("title")
  public String title;
  @SerializedName("description")
  public String description;
  @SerializedName("image")
  public String image;
  @SerializedName("salePrice")
  public String salePrice;
  @SerializedName("price")
  public String price;
  @SerializedName("guid")
  public String guid;
  @SerializedName("aisle")
  public String aisle;
  @SerializedName("index")
  public Integer index;
  @SerializedName("_id")
  public String id;

  public Deal(String title, String description, String image, String salePrice, String price,
              String guid, String aisle, Integer index, String id) {
    this.title = title;
    this.description = description;
    this.image = image;
    this.salePrice = salePrice;
    this.price = price;
    this.guid = guid;
    this.aisle = aisle;
    this.index = index;
    this.id = id;
  }
}
