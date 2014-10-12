package com.debjyotipaul.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: debjyoti.paul
 * Date: 10/11/14
 * Time: 12:20 PM
 * To change this template use File | Settings | File Templates.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class IntelliADForm extends Object {
    @JsonProperty
    int count;

    @JsonProperty
    boolean has_more;

    @JsonProperty
    MapLocationForm map_location;

    @JsonProperty
    double minx;

    @JsonProperty
    double miny;

    @JsonProperty
    double maxx;

    @JsonProperty
    double maxy;

    @JsonProperty
    List<CategoryCountForm> adCategoryHist;

    @JsonProperty
    List<Tweet> photos;

    @JsonProperty
    List<Tweet> ads;

    public IntelliADForm(int count, boolean has_more, MapLocationForm map_location, double minx, double miny, double maxx, double maxy, List<CategoryCountForm> adCategoryHist, List<Tweet> photos) {
        this.count = count;
        this.has_more = has_more;
        this.map_location = map_location;
        this.minx = minx;
        this.miny = miny;
        this.maxx = maxx;
        this.maxy = maxy;
        this.adCategoryHist = adCategoryHist;
        this.photos = photos;
    }

    public double getMinx() {
        return minx;
    }

    public void setMinx(double minx) {
        this.minx = minx;
    }

    public double getMiny() {
        return miny;
    }

    public void setMiny(double miny) {
        this.miny = miny;
    }

    public double getMaxx() {
        return maxx;
    }

    public void setMaxx(double maxx) {
        this.maxx = maxx;
    }

    public double getMaxy() {
        return maxy;
    }

    public void setMaxy(double maxy) {
        this.maxy = maxy;
    }

    public List<CategoryCountForm> getAdCategoryHist() {
        return adCategoryHist;
    }

    public void setAdCategoryHist(List<CategoryCountForm> adCategoryHist) {
        this.adCategoryHist = adCategoryHist;
    }

    public List<Tweet> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Tweet> photos) {
        this.photos = photos;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public MapLocationForm getMap_location() {
        return map_location;
    }

    public void setMap_location(MapLocationForm map_location) {
        this.map_location = map_location;
    }

    @Override
    public String toString() {
        return "{" +
                "\"count\":" + count +
                ", \"has_more\":" + has_more +
                ", \"map_location\":" + map_location.toString() +
                ", \"minx\":" + minx +
                ", \"miny\":" + miny +
                ", \"maxx\":" + maxx +
                ", \"maxy\":" + maxy +
                ", \"adCategoryHist\":" + adCategoryHist +
                ", \"photos\":" + photos +
                '}';
    }
}
