package com.debjyotipaul.resources;

import com.debjyotipaul.forms.*;
import com.debjyotipaul.util.ProcessData;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Path("/ads")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(SampleResource.class);

  private ProcessData data;

    private List<CategoryCountForm> user_health_chart;
    private List<CategoryCountForm> tweet_health_chart;
    private List<CategoryCountForm> tweet_nutrition_chart;
    private List<CategoryCountForm> user_nutrition_chart;
    private List<CategoryCountForm> user_health_pie;
    private List<CategoryCountForm> tweet_health_pie;
    private List<CategoryCountForm> tweet_nutrition_pie;
    private List<CategoryCountForm> user_nutrition_pie;

  public SampleResource(String template, String defaultName) {
    this.data = null;
    this.user_health_chart = new ArrayList<CategoryCountForm>();
    this.tweet_health_chart = new ArrayList<CategoryCountForm>();
    this.tweet_nutrition_chart = new ArrayList<CategoryCountForm>();
    this.user_nutrition_chart = new ArrayList<CategoryCountForm>();
    this.user_health_pie = new ArrayList<CategoryCountForm>();
    this.tweet_health_pie = new ArrayList<CategoryCountForm>();
    this.tweet_nutrition_pie = new ArrayList<CategoryCountForm>();
    this.user_nutrition_pie = new ArrayList<CategoryCountForm>();
  }

  @GET
  @Path("dummy")
  @Produces({MediaType.APPLICATION_JSON, "application/x-javascript; charset=UTF-8", "application/javascript; charset=UTF-8"})
  public Object sayHello(@QueryParam("minx") Optional<Double> minx,
      @QueryParam("miny") Optional<Double> miny,@QueryParam("maxx") Optional<Double> maxx,
      @QueryParam("maxy") Optional<Double> maxy, @QueryParam("callback") Optional<String> fname) {


    List<Tweet> tweetList = new ArrayList<Tweet>();
    for(int i = 0 ; i< 50 ; i++){
      Tweet tweet = new Tweet(minx,miny,maxx,maxy);
      tweetList.add(tweet);
      //System.out.println(tweet);
    }

      MapLocationForm mapLocationForm = new MapLocationForm((minx.get()+maxx.get())/2, (miny.get()+maxy.get())/2, 7 );

      List<CategoryCountForm> adCategoryHist= new ArrayList<CategoryCountForm>();
      adCategoryHist.add(new CategoryCountForm("category1", 10));
      adCategoryHist.add(new CategoryCountForm("category2",30));
      adCategoryHist.add(new CategoryCountForm("category3",60));
      adCategoryHist.add(new CategoryCountForm("category4",24));


      IntelliADForm intelliADForm = new IntelliADForm(50,true,mapLocationForm,minx.get(),miny.get(),maxx.get(),maxy.get(),adCategoryHist,tweetList);


      String result = fname.get()  + "(" + intelliADForm.toString()+ ")";
      return new JSONPObject(fname.get(), intelliADForm);
   // return new Message(id, String.format(template, minx));
  }

    @GET
    @Path("tweets")
    @Produces({MediaType.APPLICATION_JSON, "application/x-javascript; charset=UTF-8", "application/javascript; charset=UTF-8"})
    public Object getTweets(@QueryParam("minx") Optional<Double> minx,
                           @QueryParam("miny") Optional<Double> miny, @QueryParam("maxx") Optional<Double> maxx,
                           @QueryParam("maxy") Optional<Double> maxy, @QueryParam("callback") Optional<String> fname) throws IOException {


        ProcessData data = new ProcessData(minx.get(), miny.get(),maxx.get(),maxy.get());

        List<Tweet> nutritionTweets = data.getCurrentNutritionTweets();
        List<Tweet> healthTweets = data.getCurrentHealthTweets();

        data.makeHistograms();

        user_health_chart.clear();
        user_nutrition_chart.clear();
        tweet_nutrition_chart.clear();
        tweet_health_chart.clear();
        user_health_pie.clear();
        user_nutrition_pie.clear();
        tweet_nutrition_pie.clear();
        tweet_health_pie.clear();


        for (String key: data.tweetNutritionHistogram.keySet()){
            tweet_nutrition_chart.add(new CategoryCountForm(key, data.tweetNutritionHistogram.get(key)));
        }
        for (String key: data.tweetHealthHistogram.keySet()){
            tweet_health_chart.add(new CategoryCountForm(key, data.tweetHealthHistogram.get(key)));
        }
        for (String key: data.userHealthHistogram.keySet()){
            user_health_chart.add(new CategoryCountForm(key, data.userHealthHistogram.get(key)));
        }
        for (String key: data.userNutritionHistogram.keySet()){
            user_nutrition_chart.add(new CategoryCountForm(key, data.userNutritionHistogram.get(key)));
        }

        for (String key: data.tweetNutritionPieChart.keySet()){
            tweet_nutrition_pie.add(new CategoryCountForm(key, data.tweetNutritionPieChart.get(key)));
        }
        for (String key: data.tweetHealthPieChart.keySet()){
            tweet_health_pie.add(new CategoryCountForm(key, data.tweetHealthPieChart.get(key)));
        }
        for (String key: data.userHealthPieChart.keySet()){
            user_health_pie.add(new CategoryCountForm(key, data.userHealthPieChart.get(key)));
        }
        for (String key: data.userNutritionPieChart.keySet()){
            user_nutrition_pie.add(new CategoryCountForm(key, data.userNutritionPieChart.get(key)));
        }

        MapLocationForm mapLocationForm = new MapLocationForm((minx.get()+maxx.get())/2, (miny.get()+maxy.get())/2, 7 );

        List<CategoryCountForm> adCategoryHist= new ArrayList<CategoryCountForm>();


        int numTweet = 50;
        if (nutritionTweets.size() < 50 ){
          numTweet = nutritionTweets.size();
        }

        List<Tweet> outputTweets = new ArrayList<Tweet>();
        outputTweets.addAll(nutritionTweets.subList(0,numTweet));
        outputTweets.addAll(healthTweets.subList(0, healthTweets.size() < numTweet ? healthTweets.size() : numTweet));

        IntelliADForm intelliADForm = new IntelliADForm(50,true,mapLocationForm,minx.get(),miny.get(),maxx.get(),maxy.get(),adCategoryHist,outputTweets);
        
//        FileWriter file = new FileWriter("/var/www/histograms.json");
        FileWriter file = new FileWriter("../ui/histograms.json");
        try {
//            String jsonStr=new Gson().toJson(new HistogramForm(user_health_chart,tweet_health_chart,user_nutrition_chart,tweet_nutrition_chart));
            String jsonStr=new Gson().toJson(new HistogramForm(user_health_chart, user_health_pie, tweet_health_chart, tweet_health_pie, user_nutrition_chart,user_nutrition_pie,tweet_nutrition_chart, tweet_nutrition_pie));
            file.write(jsonStr);
            System.out.println("Successfully Copied JSON Object to File...");
            System.out.println("\nJSON Object: " + jsonStr);
 
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR");
        } finally {
            file.flush();
            file.close();
        }

        
        return new JSONPObject(fname.get(), intelliADForm);
    }

    @GET
    @Path("histograms")
    @Produces({MediaType.APPLICATION_JSON, "application/x-javascript; charset=UTF-8", "application/javascript; charset=UTF-8"})
    public Object getHistograms() {
        return new HistogramForm(user_health_chart, user_health_pie, tweet_health_chart, tweet_health_pie, user_nutrition_chart,user_nutrition_pie,tweet_nutrition_chart, tweet_nutrition_pie);
    }
}
