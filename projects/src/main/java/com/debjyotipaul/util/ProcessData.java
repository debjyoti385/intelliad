package com.debjyotipaul.util;

import com.debjyotipaul.forms.Tweet;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Map.Entry;

public class ProcessData {

  List<Tweet> currentTweets;

  double minx, miny, maxx, maxy;

  public Map<String, Double> adHistogram;
  public Map<String, Double> tweetHistogram;
  public Map<String, Double> userHistogram;

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
      PriorityQueue<HistObject> histQueue = new PriorityQueue<HistObject>(k, new Comparator<HistObject>() {
          public int compare(HistObject obj1, HistObject obj2) {
              return (obj1.count > obj2.count) ? -1 : 1;
          }
      });
    for (Tweet t : currentTweets) {
      for (String str : DataLoader.allTweets.get(t)) {
          if (tweetHistogram.containsKey(str)) {
            tweetHistogram.put(str, tweetHistogram.get(str) + 1);
          } else {
            tweetHistogram.put(str, new Double(1));
          }
        // System.out.println(str);
      }
    }
    for (String category : tweetHistogram.keySet()){
        histQueue.add(new HistObject(category,tweetHistogram.get(category)));
    }


    tweetHistogram.clear();
      int i =0;
      while(true){
          HistObject obj = histQueue.poll();
          if(obj== null || i >=k)
              break;
          tweetHistogram.put(obj.category,obj.count);
          i++;
      }
    normalize(tweetHistogram);
    System.out.println("TWEET" + tweetHistogram);
  }

  public boolean isWithin(Tweet t) {
    return (t.getLatitude() >= miny && t.getLatitude() <= maxy && t.getLongitude() >= minx && t
        .getLongitude() <= maxx);
  }

  public List<Tweet> getCurrentTweets() {
    for (Entry<Tweet, List<String>> e : DataLoader.allTweets.entrySet()) {
      Tweet t = e.getKey();
      if (isWithin(t))
        currentTweets.add(t);
    }
    System.out.println("All Tweets in Process" + DataLoader.allTweets.size());
    return currentTweets;

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
    if (currentTweets.size() <= 2000)
      return currentTweets;
    for (int i = 0; i < 100; i++) {
      tweetList.add(currentTweets.get((int) (Math.random() * currentTweets.size())));
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
          "", "", 500, 475995));
      // create object here
    }
    return adList;
  }

  /**
   * @param args
   */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    DataLoader.loadAllTweets("/var/www/intelliad/tweetLabels.csv", true);
    DataLoader.loadAllTweets("/var/www/intelliad/userLabels.csv", false);
    // loadAllAds("/home/mangat/userLabels.csv");
    DataLoader.makeMapping();
    // ProcessData Data = new ProcessData(-74.2557, 40.4957, -73.6895, 40.9176);
    ProcessData Data = new ProcessData(-74.2557, 40.4957, -74.2000, 40.9176);
    Data.getCurrentTweets();
    Data.makeHistograms();
      DataLoader.loadAllAds("/var/www/intelliad/imContentsample.csv");
//    for(int i = 0;i<7;i++){
//    DataLoader.loadAllAds("/var/www/intelliad/imContent.csv-"+i);
//    }
    System.out.println(Data.getSampleAds());
  }

}
