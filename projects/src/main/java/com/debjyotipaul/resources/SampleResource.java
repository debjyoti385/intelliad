package com.debjyotipaul.resources;

import com.codahale.metrics.annotation.Timed;
import com.debjyotipaul.forms.IntelliADForm;
import com.debjyotipaul.forms.MapLocationForm;
import com.debjyotipaul.forms.Tweet;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Path("/ads")
@Produces(MediaType.APPLICATION_JSON)
public class SampleResource {

  private static final Logger LOGGER = LoggerFactory.getLogger(SampleResource.class);

  private final String template;
  private final String defaultName;
  private final AtomicLong counter;

  public SampleResource(String template, String defaultName) {
    this.template = template;
    this.defaultName = defaultName;
    this.counter = new AtomicLong();
  }

  @GET
  @Timed
  public String sayHello(@QueryParam("minx") Optional<Double> minx,
      @QueryParam("miny") Optional<Double> miny,@QueryParam("maxx") Optional<Double> maxx,
      @QueryParam("maxy") Optional<Double> maxy, @QueryParam("callback") Optional<String> fname) {
    long id = counter.incrementAndGet();

    List<Tweet> tweetList = new ArrayList<Tweet>();
    for(int i = 0 ; i< 50 ; i++){
      Tweet tweet = new Tweet(minx,miny);
      tweetList.add(tweet);
      System.out.println(tweet);
    }

      MapLocationForm mapLocationForm = new MapLocationForm((minx.get()+maxx.get())/2, (miny.get()+maxy.get())/2, 7 );

      Map<String,Integer> adCategoryHist= new HashMap<String, Integer>();
      adCategoryHist.put("category1",10);
      adCategoryHist.put("category2",30);
      adCategoryHist.put("category3",60);

      IntelliADForm intelliADForm = new IntelliADForm(50,true,mapLocationForm,minx.get(),miny.get(),maxx.get(),maxy.get(),adCategoryHist,tweetList);

      String result = fname.get()  + intelliADForm+ ")";
  return result;
   // return new Message(id, String.format(template, minx));


  }
}
