#!/usr/bin/python

from tweepy import OAuthHandler
from tweepy import API
from sys import argv, exit
from os.path import isfile
from tweepy.streaming import StreamListener
from tweepy import Stream
from tweepy import API
from time import strftime, sleep
import thread

consumer_key='lJsKJonaYwDJmoh1DgBRTvCcj'
consumer_secret='MOM4hwlPNwxBNBtEvLNB0Tnm4mM0HWd3ineaiJUh1kBbAwG84w'
access_token='2242753148-8VTUjGmChswtDYgP0bfk9qI7wgmYpJPv62qzC4m'
access_token_secret='bEw9USLLdmwKIUOeJ9ZmmnfL58NX6xq2r2T891ba7CqdO'

class CustomStreamListener(StreamListener):
        def __init__(self,baseFilename,api):
                self.baseFilename=baseFilename
                self.currentTime=strftime("%Y-%m-%d-%H")
                self.f=open(self.baseFilename+"."+self.currentTime,"a")
                self.api=api

        def on_status(self,status):
                if strftime("%Y-%m-%d-%H") != self.currentTime:
                        self.f.close()
                        self.currentTime=strftime("%Y-%m-%d-%H")
                        self.f=open(self.baseFilename+"."+self.currentTime,"a")
                thread.start_new_thread(self.write,(status,self.f))
        def on_error(self,status):
                print status
                self.f.close()

	def write(self,tweet,f):
		j=tweet._json
		if j['lang'] != 'en':
			return
		s=(str(j['id'])+"\x01"+j['text'].encode('ascii','ignore')+"\x01"+j['created_at']+"\x01"+str(j['user']['id'])+"\x01"+ \
			str(j['user']['followers_count'])+"\x01"+str(j['user']['statuses_count'])+"\x01")
		if j['user']['description']:
			s+=j['user']['description'].encode('ascii','ignore')
		else:
			s+="None"
		s+=("\x01"+str(j['user']['friends_count'])+"\x01"+str(j['user']['favourites_count'])+"\x01")
		if j['coordinates']:
			s+=str(j['coordinates']['coordinates'])
		else:
			s+="None"
		s+="\x01"
		for tag in j['entities']['hashtags']:
			s+=(tag['text'].encode('ascii','ignore')+"|")
		s+="\x01"
		if j['place']:
			s+=(j['place']['full_name']+'|'+j['place']['country_code'])
		else:
			s+="None"

		s=s.replace("\n"," ").encode('ascii','ignore')
		f.write(s+"\n")

def stream(auth,api,filepath):
        exceptionCount=0
        while(True):
                try:
                        stream = Stream(auth, CustomStreamListener(filepath,api))
                        stream.filter(locations=[-124.848974, 24.396308, -66.885444, 49.384358])
                except:
                        exceptionCount+=1
                        if(exceptionCount%100==0):
                                print 'Exceptions:',exceptionCount
			sleep(90)

if __name__=='__main__':
	auth = OAuthHandler(consumer_key, consumer_secret)
	auth.set_access_token(access_token, access_token_secret)
	
	api = API(auth)
	if len(argv) < 2:
		print 'Usage: <filePrefix>'
		exit(1)

	stream(auth,api,argv[1])

