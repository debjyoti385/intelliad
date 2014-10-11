package com.debjyotipaul.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.debjyotipaul.forms.Tweet;

public class ProcessData {

  List<Tweet> currentTweets;
  double minx, miny, maxx, maxy;
  Map<String, Double> adHistogram;
  Map<String, Double> tweetHistogram;
  Map<String, Double> userHistogram;

  public ProcessData(double minx, double miny, double maxx, double maxy) {
    // TODO Auto-generated constructor stub
    currentTweets = new ArrayList<Tweet>();
    adHistogram = new HashMap<String, Double>();
    tweetHistogram = new HashMap<String, Double>();
    userHistogram = new HashMap<String, Double>();
    this.maxx = maxx;
    this.minx = minx;
    this.maxy = maxy;
    this.miny = miny;

  }

  public void makeHistograms() {
    for (Tweet t : currentTweets) {
      for (String str : DataLoader.allTweets.get(t)) {
        if (!t.photo_title.isEmpty()) {
          if (tweetHistogram.containsKey(str)) {
            tweetHistogram.put(str, tweetHistogram.get(str) + 1);
          } else {
            tweetHistogram.put(str, new Double(1));
          }

        } else {
          if (userHistogram.containsKey(str)) {
            userHistogram.put(str, userHistogram.get(str) + 1);
          } else {
            userHistogram.put(str, new Double(1));
          }
        }
        // System.out.println(str);
        for (Integer adCat : DataLoader.class2AdsClass.get(str)) {
          if (adHistogram.containsKey(DataLoader.adIndex2Name.get(adCat))) {
            adHistogram.put(DataLoader.adIndex2Name.get(adCat),
                adHistogram.get(DataLoader.adIndex2Name.get(adCat)) + 1);
          } else {
            adHistogram.put(DataLoader.adIndex2Name.get(adCat), new Double(1));
          }
        }
      }
    }
    normalize(adHistogram);
    normalize(tweetHistogram);
    normalize(userHistogram);    
    System.out.println("ADS::"+adHistogram);
    System.out.println("TWEET"+tweetHistogram);
    System.out.println("USER"+userHistogram);
  }

  public boolean isWithin(Tweet t) {
    return (t.getLatitude() >= miny && t.getLatitude() <= maxy && t.getLongitude() >= minx && t
        .getLongitude() <= maxx);
  }

  public void getCurrentTweets() {
    for (Entry<Tweet, List<String>> e : DataLoader.allTweets.entrySet()) {
      Tweet t = e.getKey();
      if (isWithin(t))
        currentTweets.add(t);
    }
    System.out.println("All Tweets in Process" + DataLoader.allTweets.size());
  }

  public void normalize(Map<String, Double> histogram){
   double sum = 0; 
   for(Double val : histogram.values()){
     sum+=val;
   }
   for(Entry<String, Double>E :histogram.entrySet()){ 
     E.setValue(E.getValue()*100/sum);
   } 
  }
  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    DataLoader.loadAllTweets("/home/mangat/tweetLabels.csv", true);
    DataLoader.loadAllTweets("/home/mangat/userLabels.csv", false);
    // loadAllAds("/home/mangat/userLabels.csv");
    DataLoader.makeMapping();
    // ProcessData Data = new ProcessData(-74.2557, 40.4957, -73.6895, 40.9176);
    ProcessData Data = new ProcessData(-74.2557, 40.4957, -74.2000, 40.9176);
    Data.getCurrentTweets();
    Data.makeHistograms();
    System.out.println(Data.currentTweets.size());
  }

}
