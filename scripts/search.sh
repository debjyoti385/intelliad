#!/usr/bin/python

from tweepy import OAuthHandler
from tweepy import API
from sys import argv, exit
from os.path import isfile

consumer_key='lJsKJonaYwDJmoh1DgBRTvCcj'
consumer_secret='MOM4hwlPNwxBNBtEvLNB0Tnm4mM0HWd3ineaiJUh1kBbAwG84w'
access_token='2242753148-8VTUjGmChswtDYgP0bfk9qI7wgmYpJPv62qzC4m'
access_token_secret='bEw9USLLdmwKIUOeJ9ZmmnfL58NX6xq2r2T891ba7CqdO'

def print_tweet(tweet):
	if True:
		if True:
			print
                        print 'TweetId=',tweet._json['id']
                        print 'TweetText=',tweet._json['text'].encode('ascii','ignore')
                        print 'CreatedTime=',tweet._json['created_at']
                        print 'UserId=',tweet._json['user']['id']
                        print 'Followers=',tweet._json['user']['followers_count']
                        print 'StatusCount=',tweet._json['user']['statuses_count']
                        print 'Description=',tweet._json['user']['description'].encode('ascii','ignore')
                        print 'FriendsCount=',tweet._json['user']['friends_count']
                        print 'FavouritesCount=',tweet._json['user']['favourites_count']
                        if tweet._json['coordinates']:
                                print 'LatLong=',tweet._json['coordinates']['coordinates']
                        else:
                                print 'LatLong= None'
                        print 'HashTags=',
                        for tag in tweet._json['entities']['hashtags']:
                                print tag['text'].encode('ascii','ignore'),'|',
                        print
                        if tweet._json['place']:
                                print 'Place: Fullname=',tweet._json['place']['full_name'],',CountryCode=',tweet._json['place']['country_code']
                        else:
                                print 'Place: None'

def write(tweet,f):
	s=(str(tweet._json['id'])+"\x01"+tweet._json['text'].encode('ascii','ignore')+"\x01"+tweet._json['created_at']+"\x01"+str(tweet._json['user']['id'])+"\x01"+ \
		str(tweet._json['user']['followers_count'])+"\x01"+str(tweet._json['user']['statuses_count'])+"\x01"+tweet._json['user']['description'].encode('ascii','ignore')+ \
		"\x01"+str(tweet._json['user']['friends_count'])+"\x01"+str(tweet._json['user']['favourites_count'])+"\x01")
	if tweet._json['coordinates']:
		s+=str(tweet._json['coordinates']['coordinates'])
	else:
		s+="None"
	s+="\x01"
	for tag in tweet._json['entities']['hashtags']:
		s+=(tag['text'].encode('ascii','ignore')+"|")
	s+="\x01"
	if tweet._json['place']:
		s+=(tweet._json['place']['full_name']+'|'+tweet._json['place']['country_code'])
	else:
		s+="None"

	s=s.replace("\n"," ")
	f.write(s+"\n")

def search(api,query,geocodeStr,resType,fname):
        tweets=api.search(q="",geocode=geocodeStr,count=100,result_type=resType,include_entities=True,lang="en")
        maxId=float("inf")
        count=0
        f=open(fname,'a')
	flg=True
	if(isfile('checkpoint')):
		sinceIdInit=int(open('checkpoint').read().strip())
		print 'SinceId from checkpoint=',sinceIdInit
	else:
		sinceIdInit=0
        while(count<180):
                for tweet in tweets:
                        write(tweet,f)
			if flg:
				ck=open('checkpoint','w')
				ck.write(str(tweet.id))
				print 'Wrote checkpoint tweetId=',tweet.id
				ck.close()
				flg=False
                        if tweet.id<maxId:
                                maxId=tweet.id
                tweets = api.search(q="",geocode=geocodeStr,since_id=sinceIdInit,max_id=maxId-1,count=100,result_type=resType,include_entities=True,lang="en")
                count += 1
        f.close()

if __name__=='__main__':
	auth = OAuthHandler(consumer_key, consumer_secret)
	auth.set_access_token(access_token, access_token_secret)
	
	api = API(auth)
	geocodeStr="21,83,1000km"
	query=""
	resType="recent"
	if len(argv) <2:
		print 'Usage: <fname>'
		exit(1)

	fname=argv[1]

	search(api,query,geocodeStr,resType,fname)


