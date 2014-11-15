package com.debjyotipaul.util;

import com.debjyotipaul.forms.Tweet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class DataLoader {
  static BufferedReader fileReader = null;
  public static Map<Tweet, List<String>> allNutritionTweets = new HashMap<Tweet, List<String>>();
  public static Map<Tweet, List<String>> allHealthTweets = new HashMap<Tweet, List<String>>();
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

  public static void loadAllNutritionTweets(String filePath) {
    String line = "";
    // Create the file reader
    try {
      fileReader = new BufferedReader(new FileReader(filePath));
      while ((line = fileReader.readLine()) != null) {
        // Get all tokens available in line
        String[] tokens = line.split("\t");
        int len = tokens.length;

        if (!(tokens[4].isEmpty() || tokens[5].isEmpty())) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Calendar calendar = Calendar.getInstance();
            Date date = formatter.parse(tokens[7]);

          SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMMMMMMMMM yyyy HH:mm:ss");
          calendar.setTime(date);
          String upload_date = sdf.format(calendar.getTime());

          Tweet t =
                  new Tweet(375,Double.parseDouble(tokens[4]),Double.parseDouble(tokens[5]),tokens[2],Long.parseLong(tokens[0]),
                          "",
                          "http://www.panoramio.com/user/475995","http://mw2.google.com/mw-panoramio/photos/medium/11630238.jpg",
                          "http://www.panoramio.com/photo/11630238",upload_date,500,Long.parseLong(tokens[1]));

          List<String> categories = new ArrayList<String>();
          categories.add(tokens[11]);
          allNutritionTweets.put(t, categories);
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
      System.out.println("All Tweets" + allNutritionTweets.size());
  }

    public static void loadAllHealthTweets(String filePath) {
        String line = "";
        // Create the file reader
        try {
            fileReader = new BufferedReader(new FileReader(filePath));
            while ((line = fileReader.readLine()) != null) {
                // Get all tokens available in line
                String[] tokens = line.split("\t");
                int len = tokens.length;

                if (!(tokens[4].isEmpty() || tokens[5].isEmpty())) {

                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Calendar calendar = Calendar.getInstance();
                    Date date = formatter.parse(tokens[7]);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd MMMMMMMMMMMM yyyy HH:mm:ss");
                    calendar.setTime(date);
                    String upload_date = sdf.format(calendar.getTime());

                    Tweet t =
                            new Tweet(375,Double.parseDouble(tokens[4]),Double.parseDouble(tokens[5]),tokens[2],Long.parseLong(tokens[0]),
                                    "",
                                    "http://www.panoramio.com/user/475995","http://mw2.google.com/mw-panoramio/photos/medium/11630238.jpg",
                                    "http://www.panoramio.com/photo/11630238",upload_date,500,Integer.parseInt(tokens[1]));

                    List<String> categories = new ArrayList<String>();
                    categories.add(tokens[11]);
                    allHealthTweets.put(t, categories);
                }
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("All Tweets" + allHealthTweets.size());
    }

  public static void loadAllAds(String filePath) {
    String line = "";
    String adCatName = adIndex2Name.get(Integer.parseInt(filePath.split("-")[1]));
    // Create the file reader
    try {
      fileReader = new BufferedReader(new FileReader(filePath));
      while ((line = fileReader.readLine()) != null) {
        // Get all tokens available in line
        String[] tokens = line.split("\t");
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
    //DataLoader.loadAllNutritionTweets("/var/www/intelliad/userLabels.csv", false);
    DataLoader.loadAllNutritionTweets("/var/www/intelliad/tweetLabel.csv");
    DataLoader.loadAllHealthTweets("/var/www/intelliad/tweetLabel.csv");
    //DataLoader.makeMapping();
    //loadAllAds("/var/www/intelliad/imContentsample.csv");
//    for(int i = 0;i<7;i++){
//    loadAllAds("/var/www/intelliad/imContent.csv-"+i);
//    }
//    System.out.println(allAds);
      System.out.println(allNutritionTweets);
  }
}
