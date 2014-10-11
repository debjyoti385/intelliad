package com.debjyotipaul.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.debjyotipaul.SampleServiceConfiguration;
import com.debjyotipaul.api.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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
    SampleServiceConfiguration.getTweets(minx.get(),miny.get(),maxx.get(),maxy.get());
    Message.getFName(fname.get());
   /* LOGGER.info("Everything looks good..responding sayHello to {} with counter {}",
        name.or(defaultName), id);*/
    
  return Message.getFinalString();
   // return new Message(id, String.format(template, minx));


  }
}
