package com.debjyotipaul.forms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created with IntelliJ IDEA.
 * User: debjyoti.paul
 * Date: 10/11/14
 * Time: 12:25 PM
 * To change this template use File | Settings | File Templates.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MapLocationForm {

    @JsonProperty
    double lat;

    @JsonProperty
    double lon;

    @JsonProperty
    double panoramio_zoom;

    public MapLocationForm(double lat, double lon, double panoramio_zoom) {
        this.lat = lat;
        this.lon = lon;
        this.panoramio_zoom = panoramio_zoom;
    }

    public MapLocationForm() {
        this.lat = 22.6233625;
        this.lon = 88.38991240701594;
        this.panoramio_zoom = 7;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getPanoramio_zoom() {
        return panoramio_zoom;
    }

    public void setPanoramio_zoom(double panoramio_zoom) {
        this.panoramio_zoom = panoramio_zoom;
    }

    @Override
    public String toString() {
        return "{" +
                "\"lat\":" + lat +
                ", \"lon\":" + lon +
                ", \"panoramio_zoom\":" + panoramio_zoom +
                '}';
    }
}
