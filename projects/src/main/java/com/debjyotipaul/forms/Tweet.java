package com.debjyotipaul.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true )
public class Tweet {
  @JsonProperty
  int height;
@JsonProperty
  double latitude;
@JsonProperty
  double longitude;
@JsonProperty
  public String photo_title;
@JsonProperty
  public int photo_id;
@JsonProperty
  public String owner_name;
@JsonProperty
  public String owner_url;
@JsonProperty
public String photo_file_url;
@JsonProperty
  public String photo_url;
@JsonProperty
public String upload_date;
@JsonProperty
public int width;
@JsonProperty
public String owner_id;
public Tweet(int height, double latitude, double longitude, String photo_title, int photo_id,
    String owner_name, String owner_url, String photo_file_url, String photo_url,
    String upload_date, int width, String owner_id) {
  super();
  this.height = height;
  this.latitude = latitude;
  this.longitude = longitude;
  this.photo_title = photo_title;
  this.photo_id = photo_id;
  this.owner_name = owner_name;
  this.owner_url = owner_url;
  this.photo_file_url = photo_file_url;
  this.photo_url = photo_url;
  this.upload_date = upload_date;
  this.width = width;
  this.owner_id = owner_id;
}
public int getHeight() {
  return height;
}
public void setHeight(int height) {
  this.height = height;
}
public double getLatitude() {
  return latitude;
}
public void setLatitude(double latitude) {
  this.latitude = latitude;
}
public double getLongitude() {
  return longitude;
}
public void setLongitude(double longitude) {
  this.longitude = longitude;
}
public String getPhoto_title() {
  return photo_title;
}
public void setPhoto_title(String photo_title) {
  this.photo_title = photo_title;
}
public int getPhoto_id() {
  return photo_id;
}
public void setPhoto_id(int photo_id) {
  this.photo_id = photo_id;
}
public String getOwner_name() {
  return owner_name;
}
public void setOwner_name(String owner_name) {
  this.owner_name = owner_name;
}
public String getOwner_url() {
  return owner_url;
}
public void setOwner_url(String owner_url) {
  this.owner_url = owner_url;
}
public String getPhoto_file_url() {
  return photo_file_url;
}
public void setPhoto_file_url(String photo_file_url) {
  this.photo_file_url = photo_file_url;
}
public String getPhoto_url() {
  return photo_url;
}
public void setPhoto_url(String photo_url) {
  this.photo_url = photo_url;
}
public String getUpload_date() {
  return upload_date;
}
public void setUpload_date(String upload_date) {
  this.upload_date = upload_date;
}
public int getWidth() {
  return width;
}
public void setWidth(int width) {
  this.width = width;
}
public String getOwner_id() {
  return owner_id;
}
public void setOwner_id(String owner_id) {
  this.owner_id = owner_id;
}
  

  
}