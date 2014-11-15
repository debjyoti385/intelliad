package com.debjyotipaul.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Optional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;

@JsonIgnoreProperties(ignoreUnknown =true )
public class Ads {
  @JsonProperty
  int height;
@JsonProperty
  double latitude;
@JsonProperty
  double longitude;
@JsonProperty
  public String photo_title; //text
@JsonProperty
  public int photo_id;
@JsonProperty
  public String owner_name; //user descirption
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
public int owner_id;
public Ads(int height, double latitude, double longitude, String photo_title, int photo_id,
    String owner_name, String owner_url, String photo_file_url, String photo_url,
    String upload_date, int width, int owner_id) {
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

public  Ads(Optional<Double> minx, Optional<Double> miny,Optional<Double> maxx, Optional<Double> maxy) {
        this.height = 375;

        Random randomGenerator = new Random();
        System.out.println("@@@"+(int)(Math.abs(maxx.get() - minx.get())));
        int randomX = randomGenerator.nextInt((int)(Math.abs(maxx.get() - minx.get())));
       // System.out.println("---"+(int)(Math.abs(maxx.get() - minx.get())));
        int randomY = randomGenerator.nextInt((int)(  Math.abs(maxy.get() - miny.get()   )));
        int count = randomGenerator.nextInt(50);
        this.latitude = minx.get()  + (double) randomX;
        this.longitude = miny.get()  + (double) randomY;
        this.photo_title = "Sample Ads alias of photo_title" + count ;
        this.photo_id = 11630238;
        this.owner_name = "Rnsun";
        this.owner_url = "http://www.panoramio.com/user/475995";
        this.photo_file_url = "http://mw2.google.com/mw-panoramio/photos/medium/11630238.jpg";
        this.photo_url = "http://www.panoramio.com/photo/11630238";
        SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMMMMMMMMM yyyy");
        Calendar cal = new GregorianCalendar(2014, 8, (int) (Math.random() * 29 + 1));
        this.upload_date = sdf.format(cal.getTime());

        this.width = 500;
        this.owner_id = 475995;

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
public int getOwner_id() {
  return owner_id;
}
public void setOwner_id(int owner_id) {
  this.owner_id = owner_id;
}

    @Override
    public String toString() {
        return "{" +
                "\"height\":" + height +
                ", \"latitude\":" + latitude +
                ", \"longitude\":" + longitude +
                ", \"photo_title\":'" + photo_title + '\'' +
                ", \"photo_id\":" + photo_id +
                ", \"owner_name\":'" + owner_name + '\'' +
                ", \"owner_url\":'" + owner_url + '\'' +
                ", \"photo_file_url\":'" + photo_file_url + '\'' +
                ", \"photo_url\":'" + photo_url + '\'' +
                ", \"upload_date\":'" + upload_date + '\'' +
                ", \"width\":" + width +
                ", \"owner_id\":" + owner_id +
                '}';
    }
}