package com.debjyotipaul.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;
import com.debjyotipaul.api.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.atomic.AtomicLong;

@Path("/debjyotipaul")
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
    public Message sayHello(
            @QueryParam("name")
            Optional<String> name) {
        long id = counter.incrementAndGet();
        LOGGER.info("Everything looks good..responding sayHello to {} with counter {}", name.or(defaultName), id);
        return new Message(id, String.format(template, name.or(defaultName)));
    }
}
