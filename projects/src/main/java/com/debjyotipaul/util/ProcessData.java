package com.debjyotipaul.util;

import com.debjyotipaul.forms.Tweet;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class ProcessData {

  List<Tweet> currentNutritionTweets;
    List<Tweet> currentHealthTweets;

  double minx, miny, maxx, maxy;

  public Map<String, Double> tweetHealthHistogram;
  public Map<String, Double> tweetNutritionHistogram;
  public Map<String, Double> userHealthHistogram;
  public Map<String, Double> userNutritionHistogram;
  public Map<String, Set<Long>> userHealthHistogramSet;
  public Map<String, Set<Long>> userNutritionHistogramSet;

  public ProcessData(double minx, double miny, double maxx, double maxy) {
    // TODO Auto-generated constructor stub
    currentNutritionTweets = new ArrayList<Tweet>();
    currentHealthTweets = new ArrayList<Tweet>();
    tweetHealthHistogram = new HashMap<String, Double>();
    tweetNutritionHistogram = new HashMap<String, Double>();
    userHealthHistogram = new HashMap<String, Double>();
    userNutritionHistogram = new HashMap<String, Double>();
    userHealthHistogramSet = new HashMap<String, Set<Long>>();
    userNutritionHistogramSet = new HashMap<String, Set<Long>>();
    this.maxx = maxx;
    this.minx = minx;
    this.maxy = maxy;
    this.miny = miny;

  }
    private class HistObject{
        String category;
        Double count;

        private HistObject(String category, Double count) {
            this.category = category;
            this.count = count;
        }
    }

  public void makeHistograms() {
      final int k = 5;
      PriorityQueue<HistObject> histTweetNutritionQueue = new PriorityQueue<HistObject>(k, new Comparator<HistObject>() {
          public int compare(HistObject obj1, HistObject obj2) {
              return (obj1.count > obj2.count) ? -1 : 1;
          }
      });
      PriorityQueue<HistObject> histTweetHealthQueue = new PriorityQueue<HistObject>(k, new Comparator<HistObject>() {
          public int compare(HistObject obj1, HistObject obj2) {
              return (obj1.count > obj2.count) ? -1 : 1;
          }
      });
      PriorityQueue<HistObject> histUserHealthQueue = new PriorityQueue<HistObject>(k, new Comparator<HistObject>() {
          public int compare(HistObject obj1, HistObject obj2) {
              return (obj1.count > obj2.count) ? -1 : 1;
          }
      });
      PriorityQueue<HistObject> histUserNutritionQueue = new PriorityQueue<HistObject>(k, new Comparator<HistObject>() {
          public int compare(HistObject obj1, HistObject obj2) {
              return (obj1.count > obj2.count) ? -1 : 1;
          }
      });
    for (Tweet t : currentNutritionTweets) {
      for (String category : DataLoader.allNutritionTweets.get(t)) {
          if (tweetNutritionHistogram.containsKey(category)) {
            tweetNutritionHistogram.put(category, tweetNutritionHistogram.get(category) + 1);
          } else {
            tweetNutritionHistogram.put(category, new Double(1));
          }
          if (userNutritionHistogramSet.containsKey(category)){
            userNutritionHistogramSet.get(category).add(t.getOwner_id());
            userNutritionHistogramSet.put(category, userNutritionHistogramSet.get(category));
          }
          else{
            Set<Long> tempSet = new HashSet<Long>();
            tempSet.add(t.getOwner_id());
            userNutritionHistogramSet.put(category,tempSet);
          }
      }
    }

      for (Tweet t : currentHealthTweets) {
          for (String category : DataLoader.allHealthTweets.get(t)) {
              if (tweetHealthHistogram.containsKey(category)) {
                  tweetHealthHistogram.put(category, tweetHealthHistogram.get(category) + 1);
              } else {
                  tweetHealthHistogram.put(category, new Double(1));
              }
              if (userHealthHistogramSet.containsKey(category)){
                  userHealthHistogramSet.get(category).add(t.getOwner_id());
                  userHealthHistogramSet.put(category, userHealthHistogramSet.get(category));
              }
              else{
                  Set<Long> tempSet = new HashSet<Long>();
                  tempSet.add(t.getOwner_id());
                  userHealthHistogramSet.put(category,tempSet);
              }
              // System.out.println(str);
          }
      }

    for (String category : tweetNutritionHistogram.keySet()){
        histTweetNutritionQueue.add(new HistObject(category, tweetNutritionHistogram.get(category)));
        histUserNutritionQueue.add(new HistObject(category,(double) userNutritionHistogramSet.get(category).size()));
    }

      for (String category : tweetHealthHistogram.keySet()){
          histTweetHealthQueue.add(new HistObject(category, tweetHealthHistogram.get(category)));
          histUserHealthQueue.add(new HistObject(category,(double) userHealthHistogramSet.get(category).size()));
      }


    tweetNutritionHistogram.clear();
    tweetHealthHistogram.clear();
    userHealthHistogram.clear();
    userNutritionHistogram.clear();
      int i =0;
      while(true){
          HistObject nutriObj = histTweetNutritionQueue.poll();
          HistObject nutriUserObj = histUserNutritionQueue.poll();
          if(nutriObj== null || i >=k)
              break;
          tweetNutritionHistogram.put(nutriObj.category, nutriObj.count);
          userNutritionHistogram.put(nutriUserObj.category,nutriUserObj.count);
          i++;
      }
      i=0;
      while(true){
          HistObject healthObj = histTweetHealthQueue.poll();
          HistObject healthUserObj = histUserHealthQueue.poll();
          if (healthObj == null || i >=k)
              break;
          tweetHealthHistogram.put(healthObj.category,healthObj.count);
          userHealthHistogram.put(healthUserObj.category,healthUserObj.count);
          i++;
      }
    normalize(tweetNutritionHistogram);
    normalize(tweetHealthHistogram);
    normalize(userHealthHistogram);
    normalize(userNutritionHistogram);
    System.out.println("TWEET NUTRITION " + tweetNutritionHistogram);
    System.out.println("TWEET HEALTH    " + tweetHealthHistogram);
    System.out.println("USER NUTRITION  " + userNutritionHistogram);
    System.out.println("USER HEALTH     " + userHealthHistogram);
  }

  public boolean isWithin(Tweet t) {
    return (t.getLatitude() >= miny && t.getLatitude() <= maxy && t.getLongitude() >= minx && t
        .getLongitude() <= maxx);
  }

  public List<Tweet> getCurrentNutritionTweets() {
    for (Entry<Tweet, List<String>> e : DataLoader.allNutritionTweets.entrySet()) {
      Tweet t = e.getKey();
      if (isWithin(t))
        currentNutritionTweets.add(t);
    }
    System.out.println("All Tweets in Process" + DataLoader.allNutritionTweets.size());
    return currentNutritionTweets;

  }
  public List<Tweet> getCurrentHealthTweets(){
      for (Entry<Tweet, List<String>> e : DataLoader.allHealthTweets.entrySet()) {
          Tweet t = e.getKey();
          if (isWithin(t))
              currentHealthTweets.add(t);
      }
      System.out.println("All Tweets in Process" + DataLoader.allNutritionTweets.size());
      return currentHealthTweets;
  }

  public void normalize(Map<String, Double> histogram) {
    double sum = 0;
    for (Double val : histogram.values()) {
      sum += val;
    }
    for (Entry<String, Double> E : histogram.entrySet()) {
      E.setValue(E.getValue() * 100 / sum);
    }
  }

  public List<Tweet> getSampleTweets() {
    List<Tweet> tweetList = new ArrayList<Tweet>();
    if (currentNutritionTweets.size() <= 2000)
      return currentNutritionTweets;
    for (int i = 0; i < 100; i++) {
      tweetList.add(currentNutritionTweets.get((int) (Math.random() * currentNutritionTweets.size())));
    }
    return tweetList;
  }

  public List<Tweet> getSampleAds() {
    List<Tweet> adList = new ArrayList<Tweet>();
    for (Entry<String, List<String>> ads : DataLoader.allAds.entrySet()) {
      String category = ads.getKey();
      String path = ads.getValue().get((int) (Math.random() * ads.getValue().size()));
      SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMMMMMMMMM yyyy");
      adList.add(new Tweet(375, 76.0, 76.0, "", 0, category, "",
          "http://localhost:/"+path,
          "", "", 500, 475995,0));
      // create object here
    }
    return adList;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    DataLoader.loadAllNutritionTweets("/var/www/intelliad/tweetLabels.csv");
    DataLoader.loadAllNutritionTweets("/var/www/intelliad/userLabels.csv");
    // loadAllAds("/home/mangat/userLabels.csv");
    DataLoader.makeMapping();
    // ProcessData Data = new ProcessData(-74.2557, 40.4957, -73.6895, 40.9176);
    ProcessData Data = new ProcessData(-74.2557, 40.4957, -74.2000, 40.9176);
    Data.getCurrentNutritionTweets();
    Data.makeHistograms();
      DataLoader.loadAllAds("/var/www/intelliad/imContentsample.csv");
//    for(int i = 0;i<7;i++){
//    DataLoader.loadAllAds("/var/www/intelliad/imContent.csv-"+i);
//    }
    System.out.println(Data.getSampleAds());
  }

}
