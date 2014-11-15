package com.debjyotipaul.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
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
    List<CategoryCountForm> ad_histogram;
    @JsonProperty
    List<CategoryCountForm> tweet_histogram;
    @JsonProperty
    List<CategoryCountForm> user_histogram;

    public HistogramForm(List<CategoryCountForm> tweet_histogram) {
        this.ad_histogram = new ArrayList<CategoryCountForm>();
        this.tweet_histogram = tweet_histogram;
        this.user_histogram = new ArrayList<CategoryCountForm>();
    }

    public HistogramForm(List<CategoryCountForm> ad_histogram, List<CategoryCountForm> tweet_histogram, List<CategoryCountForm> user_histogram) {
        this.ad_histogram = ad_histogram;
        this.tweet_histogram = tweet_histogram;
        this.user_histogram = user_histogram;
    }

    public List<CategoryCountForm> getAd_histogram() {
        return ad_histogram;
    }

    public void setAd_histogram(List<CategoryCountForm> ad_histogram) {
        this.ad_histogram = ad_histogram;
    }

    public List<CategoryCountForm> getTweet_histogram() {
        return tweet_histogram;
    }

    public void setTweet_histogram(List<CategoryCountForm> tweet_histogram) {
        this.tweet_histogram = tweet_histogram;
    }

    public List<CategoryCountForm> getUser_histogram() {
        return user_histogram;
    }

    public void setUser_histogram(List<CategoryCountForm> user_histogram) {
        this.user_histogram = user_histogram;
    }
}
