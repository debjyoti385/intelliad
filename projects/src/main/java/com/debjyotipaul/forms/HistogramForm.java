package com.debjyotipaul.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: debjyoti.paul
 * Date: 10/12/14
 * Time: 3:10 AM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistogramForm {

    @JsonProperty
    List<CategoryCountForm> user_health_chart;
    @JsonProperty
    List<CategoryCountForm> tweet_health_chart;
    @JsonProperty
    List<CategoryCountForm> user_nutrition_chart;
    @JsonProperty
    List<CategoryCountForm> tweet_nutrition_chart;

//    public HistogramForm(List<CategoryCountForm> tweet_health_chart) {
//        this.user_health_chart = new ArrayList<CategoryCountForm>();
//        this.tweet_health_chart = tweet_health_chart;
//        this.user_nutrition_chart = new ArrayList<CategoryCountForm>();
//    }

    public HistogramForm(List<CategoryCountForm> user_health_chart, List<CategoryCountForm> tweet_health_chart, List<CategoryCountForm> user_nutrition_chart, List<CategoryCountForm> tweet_nutrition_chart) {
        this.user_health_chart = user_health_chart;
        this.tweet_health_chart = tweet_health_chart;
        this.user_nutrition_chart = user_nutrition_chart;
        this.tweet_nutrition_chart = tweet_nutrition_chart;
    }

    public List<CategoryCountForm> getUser_health_chart() {
        return user_health_chart;
    }

    public void setUser_health_chart(List<CategoryCountForm> user_health_chart) {
        this.user_health_chart = user_health_chart;
    }

    public List<CategoryCountForm> getTweet_health_chart() {
        return tweet_health_chart;
    }

    public void setTweet_health_chart(List<CategoryCountForm> tweet_health_chart) {
        this.tweet_health_chart = tweet_health_chart;
    }

    public List<CategoryCountForm> getUser_nutrition_chart() {
        return user_nutrition_chart;
    }

    public void setUser_nutrition_chart(List<CategoryCountForm> user_nutrition_chart) {
        this.user_nutrition_chart = user_nutrition_chart;
    }
}
