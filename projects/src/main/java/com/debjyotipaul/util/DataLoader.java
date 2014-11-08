package com.debjyotipaul.util;

import com.debjyotipaul.forms.Tweet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataLoader {
  static BufferedReader fileReader = null;
  public static Map<Tweet, List<String>> allTweets = new HashMap<Tweet, List<String>>();
  public static Map<String, List<String>> allAds = new HashMap<String, List<String>>();
  public static Map<String, ArrayList<Integer>> class2AdsClass =
      new HashMap<String, ArrayList<Integer>>();
  public static Map<Integer, String> adIndex2Name = new HashMap<Integer, String>();

  public static void makeMapping() {
    // TODO Auto-generated constructor stub
    /*
     * 0: Entertainment 3: Social 6: Utility 1: Games 4: Products 2: News 5: Travel
     */

    adIndex2Name.put(0, "Entertainment");
    adIndex2Name.put(1, "Games");
    adIndex2Name.put(2, "News");
    adIndex2Name.put(3, "Social");
    adIndex2Name.put(4, "Products");
    adIndex2Name.put(5, "Travel");
    adIndex2Name.put(6, "Utility");


    // Tweet classes
    class2AdsClass.put("Business", new ArrayList<Integer>(Arrays.asList(2, 5)));
    class2AdsClass.put("Games", new ArrayList<Integer>(Arrays.asList(1)));
    class2AdsClass.put("Travel & Tourism", new ArrayList<Integer>(Arrays.asList(5)));
    class2AdsClass.put("Sports", new ArrayList<Integer>(Arrays.asList(1)));
    class2AdsClass.put("Fashion & Lifestyle", new ArrayList<Integer>(Arrays.asList(3, 4)));
    class2AdsClass.put("Commodity", new ArrayList<Integer>(Arrays.asList(4)));
    class2AdsClass.put("Arts", new ArrayList<Integer>(Arrays.asList(0, 3)));
    class2AdsClass.put("Entertainment", new ArrayList<Integer>(Arrays.asList(0)));
    class2AdsClass.put("Social Activism", new ArrayList<Integer>(Arrays.asList(2, 3)));
    class2AdsClass.put("Religion", new ArrayList<Integer>(Arrays.asList(0)));
    class2AdsClass.put("Health & Fitness", new ArrayList<Integer>(Arrays.asList(4, 6)));
    class2AdsClass.put("Food & Dining", new ArrayList<Integer>(Arrays.asList(3, 5)));
    class2AdsClass.put("Jobs & Education", new ArrayList<Integer>(Arrays.asList(0, 3)));
    // User categories
    class2AdsClass.put("Organisation", new ArrayList<Integer>(Arrays.asList(2, 3)));
    class2AdsClass.put("Social", new ArrayList<Integer>(Arrays.asList(0, 1, 3)));
    class2AdsClass.put("Working", new ArrayList<Integer>(Arrays.asList(2)));
    class2AdsClass.put("Casual", new ArrayList<Integer>(Arrays.asList(0, 1, 3)));
    class2AdsClass.put("Retired", new ArrayList<Integer>(Arrays.asList(2)));
    class2AdsClass.put("Fitness", new ArrayList<Integer>(Arrays.asList(4, 6)));
    class2AdsClass.put("Student", new ArrayList<Integer>(Arrays.asList(0, 1, 3)));
    class2AdsClass.put("Religious", new ArrayList<Integer>(Arrays.asList(0, 3)));
  }

  public static void loadAllTweets(String filePath, boolean isTweet) {
    String line = "";
    // Create the file reader
    try {
      fileReader = new BufferedReader(new FileReader(filePath));
      String uText = "";
      String tText = "";
      // NOt needed
      // Read the file line by line
      while ((line = fileReader.readLine()) != null) {
        // Get all tokens available in line
        String[] tokens = line.split("\u0001");
        // System.out.println(tokens.length);
        int len = tokens.length;
        if (len < 4)
          continue;
        if (!(tokens[len - 1].isEmpty() || tokens[len - 2].isEmpty())) {
          if (isTweet) {
            tText = tokens[0];
          } else {
            uText = tokens[0];
          }

          SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMMMMMMMMM yyyy");
          Calendar cal = new GregorianCalendar(2014, 8, (int) (Math.random() * 29 + 1));
          String upload_date = sdf.format(cal.getTime());


          Tweet t =
              new Tweet(375, Double.parseDouble(tokens[len - 2]),
                  Double.parseDouble(tokens[len - 1]), tText, 11630238, uText,
                  "http://www.panoramio.com/user/475995",
                  "http://mw2.google.com/mw-panoramio/photos/medium/11630238.jpg",
                  "http://www.panoramio.com/photo/11630238", upload_date, 500, 475995);
          allTweets.put(t, Arrays.asList((tokens[1].split(","))));
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    System.out.println("All Tweets" + allTweets.size());
  }

  public static void loadAllAds(String filePath) {
    String line = "";
    String adCatName = adIndex2Name.get(Integer.parseInt(filePath.split("-")[1]));
    // Create the file reader
    try {
      fileReader = new BufferedReader(new FileReader(filePath));
      while ((line = fileReader.readLine()) != null) {
        // Get all tokens available in line
        String[] tokens = line.split("\u0001");
        if (allAds.containsKey(adCatName)) {
          allAds.get(adCatName).add(tokens[0]);
        } else {
          allAds.put((adCatName), (new ArrayList<String>(Arrays.asList(new String[] {tokens[0]}))));

        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  public static void main(String args[]) {
    DataLoader.loadAllTweets("/var/www/intelliad/userLabels.csv", false);
    // DataLoader.loadAllTweets("/home/mangat/tweetLabels.csv", true);
    DataLoader.makeMapping();
    loadAllAds("/var/www/intelliad/imContentsample.csv");
//    for(int i = 0;i<7;i++){
//    loadAllAds("/var/www/intelliad/imContent.csv-"+i);
//    }
     System.out.println(allAds);
  }
}
