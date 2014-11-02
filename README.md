# **Abstract** #

**IntelliAd**: A Social Media driven Intelligent Ad-Targeting framework using Geo-profiling. 


The idea is to tag all geo-location enabled tweets(available publicly) with semantic categories (say sports, politics etc.) and their sentiment (positive, neutral, negative) using text mining. To enable serving of Ads, they also need to be tagged using same categories based on their content. In the end, for every geolocation-enabled ad request, tags of data points in vicinity (the neighbourhood being controlled online) will be used to generate a runtime profile for the request, and appropriate ads will be picked for serving.


## API's ##
**GET Request: http://localhost/ads/tweets**

@QueryParam("minx") = Latitude South
@QueryParam("miny") = Longitude West 
@QueryParam("maxx") = Latitude North
@QueryParam("maxy") = Longitude East
@QueryParam("callback") = Javascript callback function name (since cross domain request)

**GET Request: http://localhost/ads/histograms**

Histogram of categories of Users, Tweets and Ads can be served over requested geo location

**GET Request: http://localhost/ads/adImages**

Get ads and its information along with URL appropriate to serve over that geo location.

**GET Request: http://localhost/ads/dummy**

To test

## UI ##
![IntelliAd](https://github.com/debjyoti385/intelliad/raw/master/intelliad.png "IntelliAd")


<Please list all API's here>


