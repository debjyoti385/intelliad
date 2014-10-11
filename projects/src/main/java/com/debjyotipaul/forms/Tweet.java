package com.debjyotipaul.forms;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown =true )
public class Tweet {
@JsonProperty
  double lat;
@JsonProperty
  double lon;
@JsonProperty
  public String text;
@JsonProperty
  public int id;
@JsonProperty
  public String userDesc;
@JsonProperty
  public String date;
@JsonProperty
  public double height;
@JsonProperty
  public String path;
  
  
  Tweet(String rawTweet){
    
  }
  Tweet(){}
  
}