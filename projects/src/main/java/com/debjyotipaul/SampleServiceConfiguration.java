package com.debjyotipaul;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;

import org.hibernate.validator.constraints.NotEmpty;

class Photo {
  double hightPx;
  double widthPx;
  String path;
  String Category;
}


public class SampleServiceConfiguration extends Configuration {
  @NotEmpty
  private String template = "hi";
  private String requestData = "";
  private String adCatJson = "";
  private String tweetJson = "";
  private String photosJson = "";
  public static List<Tweet> tweets = new ArrayList<Tweet>();
  public static List<Photo> photos = new ArrayList<Photo>();
  public static double x1, y1, x2, y2;

  @NotEmpty
  private String defaultName = "Stranger";
  
  @JsonProperty
  public String getTemplate() {
    return template;
  }

  @JsonProperty
  public void setTemplate(String template) {
    this.template = template;
  }

  @JsonProperty
  public String getDefaultName() {
    return defaultName;
  }

   public static void getTweets(double x1, double y1, double x2, double y2) {
       SampleServiceConfiguration.y2 = y2;
       SampleServiceConfiguration.y1 = y1;
       SampleServiceConfiguration.x2 = x2;
       SampleServiceConfiguration.x1 = x1;
   }
  //public static void getTweets() {

  //}

  public static void getDefaultAds() {
    for (int i = 0; i < 10; i++) {
      Photo t = new Photo();
      t.hightPx = Math.random() * 200;
      t.widthPx = Math.random() * 100;
      t.path =
          "http://img3.wikia.nocookie.net/__cb20100520131746/logopedia/images/5/5c/Google_logo.png";
      t.Category = "Entertainment";

      photos.add(t);
    }

  }

  public static void getDefaultTweets() {
    for (int i = 0; i < 10; i++) {
      Tweet t = new Tweet();
      t.lat = Math.random() * (SampleServiceConfiguration.y2 - SampleServiceConfiguration.y1) + SampleServiceConfiguration.y1;
      t.lon = Math.random() * (SampleServiceConfiguration.x2 - SampleServiceConfiguration.x1) + SampleServiceConfiguration.x1;
      t.text = "SAMPLE TWEET NO: " + i;
      t.userDesc = "USER DESC NO: " + i;

      SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMMMMMMMMM yyyy");
      Calendar cal = new GregorianCalendar(2014, 8, (int) (Math.random() * 29 + 1));
      t.date = sdf.format(cal.getTime());
      tweets.add(t);
    }
  }

  @JsonProperty
  public String fillsCatAds() {

    return new String("\"adCategoryHist\":{\"Entertainment\":10,\"Games\":60,\"Shopping\":30},");
  }

  public String fillsAdData() {
    String ret = "";
    for (Photo t : photos) {
      ret +=
          "{\"height\":" + t.hightPx + ",\"width\":" + t.widthPx + ",\"photo_file_url\":\""
              + t.path + "\"},";
    }
    return ret.substring(0, ret.length() - 1);
  }

  public String fillTweets() {
    String ret = "";
    for (Tweet t : tweets) {
      ret +=
          "{\"latitude\":"
              + t.lat
              + ",\"longitude\":"
              + t.lon
              + ",\"tweet_text\":\""
              + t.text
              + "\",\"user_desc\":\""
              + t.userDesc
              + "\","
              + "\"created_at\":\""
              + t.date.toString()
              + "\",\"photo_file_url\":\"http://img3.wikia.nocookie.net/__cb20100520131746/logopedia/images/5/5c/Google_logo.png\"},";
    }
    return ret.substring(0, ret.length() - 1);
  }

  @JsonProperty
  public void setDefaultName(String name) {
    this.defaultName = name;
  }

  public static void main(String args[]) {
    SampleServiceConfiguration serv = new SampleServiceConfiguration();
    SampleServiceConfiguration.getDefaultTweets();
    SampleServiceConfiguration.getDefaultAds();
    System.out.println("({\"count\":1528,\"has_more\":true," + "\"map_location\":{\"lat\":"
        + (serv.x1 + serv.x2) / 2 + ",\"lon\":" + (serv.y1 + serv.y2) / 2
        + ",\"panoramio_zoom\":7},"
        + "\"minx\": 88.971,\"miny\": 42.091,\"maxx\": 90.937,\"maxy\": 93.17,"
        + serv.fillsCatAds() + "\"tweets\":[" + serv.fillTweets() + "],\"adsPhotos\":["
        + serv.fillsAdData() + "]})");
  }
}
