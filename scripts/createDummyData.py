import sys,random

# bounding box params
minLat = 40.495996
maxLat = 40.915256
minLong = -74.259088
maxLong = -73.700272

def isInBBox(lat,lon):
	if lat>=40.495996 and lat<=40.915256 and lon>=-74.259088 and lon<=-73.700272:
		return True
	return False

f=open(sys.argv[2],"w")

for line in open(sys.argv[1],"r"):
	sp=line.strip().split("\x01")
	if len(sp)<10:
		continue
	tweetid=sp[0]
	text=sp[1]
	ts=sp[2]
	userId=sp[3]
	followerCount=sp[4]
	favouriteCount=sp[5]
	userDesc=sp[6]
	count1=sp[7]
	count2=sp[8]
	latlon=sp[9].strip("][").split(",")
	flg=0
	
	if len(latlon)==2:
		try:
			lat=float(latlon[1].strip())
			lon=float(latlon[0].strip())
			if isInBBox(lat,lon):
				flg=1
		except ValueError:
			continue		
	if flg==0:
		lat=random.uniform(minLat,maxLat)
		lon=random.uniform(minLong,maxLong)
	f.write("\x01".join([tweetid,text,ts,userId,followerCount,favouriteCount,userDesc,count1,count2,str(lat),str(lon)])+"\n")
	
f.close()	
