package com.debjyotipaul.api;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.debjyotipaul.SampleServiceConfiguration;

public class Message {

  private final long id;
  private static String fName;
  @Length(max = 3)
  private final String content;

  public Message(long id, String content) {
    this.id = id;
    this.content = content;
  }

  public static void getFName(String name) {
    fName = name;
  }

  public long getId() {
    return id;
  }

  public String getContent() {
    return content;
  }

/*
  public static String getFinalString() {
    SampleServiceConfiguration.tweets.clear();
    SampleServiceConfiguration.photos.clear();
    SampleServiceConfiguration serv = new SampleServiceConfiguration();
    SampleServiceConfiguration.getDefaultTweets();
    SampleServiceConfiguration.getDefaultAds();
   /* return (fName + "({\"count\":1528,\"has_more\":true," + "\"map_location\":{\"lat\":"
        + (serv.x1 + serv.x2) / 2 + ",\"lon\":" + (serv.y1 + serv.y2) / 2 + ",\"panoramio_zoom\":7},"
        + "\"minx\": 88.971,\"miny\": 42.091,\"maxx\": 90.937,\"maxy\": 93.17,"
        + serv.fillsCatAds() + "\"tweets\":[" + serv.fillTweets() + "],\"adsPhotos\":["
        + serv.fillsAdData() + "]})");*/
    //return "hello";

  }


