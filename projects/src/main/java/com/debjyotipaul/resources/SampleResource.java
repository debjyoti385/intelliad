package com.debjyotipaul.resources;

import com.debjyotipaul.forms.*;
import com.debjyotipaul.util.ProcessData;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/ads")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(SampleResource.class);

  private ProcessData data;

  private List<CategoryCountForm> ad_chart;
    private List<CategoryCountForm> user_chart;
    private List<CategoryCountForm> tweet_chart;

  public SampleResource(String template, String defaultName) {
    this.data = null;
    this.ad_chart = new ArrayList<CategoryCountForm>();
    this.user_chart = new ArrayList<CategoryCountForm>();
    this.tweet_chart = new ArrayList<CategoryCountForm>();
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
      System.out.println(tweet);
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
                           @QueryParam("maxy") Optional<Double> maxy, @QueryParam("callback") Optional<String> fname) {


        ProcessData data = new ProcessData(minx.get(), miny.get(),maxx.get(),maxy.get());

        List<Tweet> tweets = data.getSampleTweets();
        data.makeHistograms();

        ad_chart.clear();
        tweet_chart.clear();
        user_chart.clear();
        
        for (String key: data.adHistogram.keySet()){
            ad_chart.add(new CategoryCountForm(key,data.adHistogram.get(key)));
        }

        for (String key: data.tweetHistogram.keySet()){
            tweet_chart.add(new CategoryCountForm(key,data.tweetHistogram.get(key)));
        }
        for (String key: data.userHistogram.keySet()){
            user_chart.add(new CategoryCountForm(key,data.userHistogram.get(key)));
        }

        MapLocationForm mapLocationForm = new MapLocationForm((minx.get()+maxx.get())/2, (miny.get()+maxy.get())/2, 7 );

        List<CategoryCountForm> adCategoryHist= new ArrayList<CategoryCountForm>();
        adCategoryHist.add(new CategoryCountForm("category1", 10));
        adCategoryHist.add(new CategoryCountForm("category2",30));
        adCategoryHist.add(new CategoryCountForm("category3",60));
        adCategoryHist.add(new CategoryCountForm("category4",24));


        IntelliADForm intelliADForm = new IntelliADForm(50,true,mapLocationForm,minx.get(),miny.get(),maxx.get(),maxy.get(),adCategoryHist,tweets);

        return new JSONPObject(fname.get(), intelliADForm);
    }

    @GET
    @Path("histograms")
    @Produces({MediaType.APPLICATION_JSON, "application/x-javascript; charset=UTF-8", "application/javascript; charset=UTF-8"})
    public Object getHistograms() {
        return new HistogramForm(ad_chart,tweet_chart,user_chart);
    }
}
